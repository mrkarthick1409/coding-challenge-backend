package com.am.appreview.service.impl;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.enums.ApplicantStatus;
import com.am.appreview.exception.AppReviewException;
import com.am.appreview.model.Applicant;
import com.am.appreview.model.ApplicantProjects;
import com.am.appreview.repository.ApplicantProjectRepository;
import com.am.appreview.repository.ApplicantRepository;
import com.am.appreview.service.ApplicantService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicantProjectRepository applicantProjectRepository;

    @Override
    public List<ApplicantDTO> getAllApplicants() {
        List<Applicant> applicants = applicantRepository.findAll();
        applicants.forEach(applicant -> applicant.getApplicantProjects());
        Type applicationType = new TypeToken<List<ApplicantDTO>>(){}.getType();
        return modelMapper.map(applicants, applicationType);
    }

    @Override
    public Applicant createApplicant(ApplicantDTO applicantDTO) throws Exception {
        Applicant applicant = modelMapper.map(applicantDTO,Applicant.class);
        applicant.setStatus(ApplicantStatus.New);
        Optional<Applicant> existingApplicant = applicantRepository.findByEmailAddress(applicantDTO.getEmailAddress());
        if(existingApplicant.isPresent()) {
            throw new AppReviewException("Applicant already exists");
        }
        applicant =  applicantRepository.save(applicant);
        for (ApplicantProjects applicantProject : applicant.getApplicantProjects()) {
            applicantProject.setApplicantId(applicant.getId());
        }
        applicantProjectRepository.saveAll(applicant.getApplicantProjects());
        return applicant;
    }

    @Override
    @Transactional
    public Applicant updateApplicant(Long applicantId, ApplicantDTO applicantDTO) {
        Applicant applicant;
        try {
            applicant = applicantRepository.findById(applicantId)
                    .orElseThrow(() -> new AppReviewException("Invalid applicantId"));
            applicantProjectRepository.deleteAllByApplicantId(applicantId);
            Applicant newApplicant = modelMapper.map(applicantDTO,Applicant.class);
            newApplicant.setId(applicantId);
            newApplicant.setStatus(applicant.getStatus());
            for (ApplicantProjects applicantProject : newApplicant.getApplicantProjects()) {
                applicantProject.setApplicantId(applicantId);
            }
            applicantProjectRepository.saveAll(newApplicant.getApplicantProjects());
            applicant = applicantRepository.save(newApplicant);
        } catch (Exception e) {
            throw new AppReviewException("Error updating applicant: "+e.getMessage());
        }
        return applicant;
    }

    @Override
    public void downloadApplicantDetailsAsPdf(Long applicantId, HttpServletResponse response) {
        convertContentForPdfDownload(getApplicantPdfContent(applicantId),response, applicantId);
    }

    private String getApplicantPdfContent(Long applicantId) {
        VelocityEngine velocityEngine = initVelocityEngine();
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid applicantId"));
        Set<ApplicantProjects> applicantProjectsSet = applicant.getApplicantProjects();
        Template template = velocityEngine.getTemplate("templates/applicant-details.vm");
        VelocityContext context = new VelocityContext();
        context.put("applicant", applicant);
        StringWriter writer = new StringWriter();
        template.merge( context, writer );
        return writer.toString();
    }

    private VelocityEngine initVelocityEngine() {
        VelocityEngine velocityEngine =new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        return velocityEngine;
    }

    private void convertContentForPdfDownload(String content, HttpServletResponse response, Long applicantId) {
        PdfWriter pdfWriter = null;
        // create a new document
        Document document = new Document();
        try {
            document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            String resultantFileName  = applicantId+"_details.pdf";
            OutputStream file = new FileOutputStream(resultantFileName);
            pdfWriter = PdfWriter.getInstance(document, out);

            // open document
            document.open();
            XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
            xmlWorkerHelper.getDefaultCssResolver(true);
            xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(content));
            // close the document
            document.close();
            // close the writer
            pdfWriter.close();

            // set in response for download document
            byte[] dataInByteArrayFormat = out.toByteArray();
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + System.nanoTime() + ".pdf");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(dataInByteArrayFormat);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

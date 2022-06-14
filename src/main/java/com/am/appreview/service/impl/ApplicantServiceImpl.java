package com.am.appreview.service.impl;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.enums.ApplicantStatus;
import com.am.appreview.model.Applicant;
import com.am.appreview.model.ApplicantProjects;
import com.am.appreview.repository.ApplicantProjectRepository;
import com.am.appreview.repository.ApplicantRepository;
import com.am.appreview.service.ApplicantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
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
    public Applicant createApplicant(ApplicantDTO applicantDTO) {
        Applicant applicant = modelMapper.map(applicantDTO,Applicant.class);
        applicant.setStatus(ApplicantStatus.New);
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
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid applicantId"));
        applicantProjectRepository.deleteAllByApplicantId(applicantId);
        Applicant newApplicant = modelMapper.map(applicantDTO,Applicant.class);
        newApplicant.setId(applicantId);
        newApplicant.setStatus(applicant.getStatus());
        for (ApplicantProjects applicantProject : newApplicant.getApplicantProjects()) {
            applicantProject.setApplicantId(applicantId);
        }
        applicantProjectRepository.saveAll(newApplicant.getApplicantProjects());
        return applicantRepository.save(newApplicant);
    }

}

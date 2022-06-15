package com.am.appreview.controller;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.model.Applicant;
import com.am.appreview.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/applicants")
    public List<ApplicantDTO> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @PostMapping("/applicant")
    public Applicant createApplicant(@Valid @RequestBody ApplicantDTO applicantDTO) throws Exception {
        return applicantService.createApplicant(applicantDTO);
    }

    @PutMapping("/applicant/{applicantId}")
    public Applicant updateApplicant(@PathVariable Long applicantId, @Valid @RequestBody ApplicantDTO applicantDTO) {
        return applicantService.updateApplicant(applicantId,applicantDTO);
    }

    @GetMapping("/applicant/{applicantId}/download")
    public void downloadApplicantDetailsAsPdf(@PathVariable Long applicantId, final HttpServletResponse response) {
        applicantService.downloadApplicantDetailsAsPdf(applicantId,response);
    }

}
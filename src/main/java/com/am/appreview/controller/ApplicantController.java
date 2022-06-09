package com.am.appreview.controller;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.model.Applicant;
import com.am.appreview.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/applicants")
    public Page<Applicant> getAllApplicants(Pageable pageable) {
        return applicantService.getAllApplicants(pageable);
    }

    @PostMapping("/applicant")
    public Applicant createApplicant(@Valid @RequestBody ApplicantDTO applicantDTO) {
        return applicantService.createApplicant(applicantDTO);
    }

    @PutMapping("/applicant/{applicantId}")
    public Applicant updateApplicant(@PathVariable Long applicantId, @Valid @RequestBody ApplicantDTO applicantDTO) {
        return applicantService.updateApplicant(applicantId,applicantDTO);
    }


}
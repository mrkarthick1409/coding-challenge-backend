package com.am.appreview.service;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.model.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicantService {

    Page<Applicant> getAllApplicants(Pageable pageable);

    Applicant createApplicant(ApplicantDTO applicantDTO);

    Applicant updateApplicant(Long applicantId, ApplicantDTO applicantDTO);

}

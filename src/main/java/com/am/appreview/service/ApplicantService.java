package com.am.appreview.service;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.model.Applicant;

import java.util.List;

public interface ApplicantService {

    List<ApplicantDTO> getAllApplicants();

    Applicant createApplicant(ApplicantDTO applicantDTO);

    Applicant updateApplicant(Long applicantId, ApplicantDTO applicantDTO);

}

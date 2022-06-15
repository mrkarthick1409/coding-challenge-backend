package com.am.appreview.service;

import com.am.appreview.dto.ApplicantDTO;
import com.am.appreview.model.Applicant;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ApplicantService {

    List<ApplicantDTO> getAllApplicants();

    Applicant createApplicant(ApplicantDTO applicantDTO) throws Exception;

    Applicant updateApplicant(Long applicantId, ApplicantDTO applicantDTO);

    void downloadApplicantDetailsAsPdf(Long applicantId, HttpServletResponse response);
}

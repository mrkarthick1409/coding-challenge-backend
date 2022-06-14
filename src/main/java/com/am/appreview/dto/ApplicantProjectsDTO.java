package com.am.appreview.dto;

import com.am.appreview.enums.Capacity;
import com.am.appreview.enums.EmploymentMode;
import com.am.appreview.model.Applicant;
import lombok.Data;


@Data
public class ApplicantProjectsDTO {

    private Long id;
    private Long applicantId;
    private String projectName;
    private Capacity capacity;
    private EmploymentMode employmentMode;
    private int durationInWeeks;
    private String role;
    private int year;
    private int duration;
    private int teamSize;
    private String repositoryUrl; // optional
    private String liveUrl; // optional

}

package com.am.appreview.dto;

import lombok.Data;

@Data
public class ApplicantDTO {

    private Long id;
    private String emailAddress;
    private String name;
    private String githubUserName;
    private String status;

}

package com.am.appreview.model;

import com.am.appreview.enums.Capacity;
import com.am.appreview.enums.EmploymentMode;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applicant_projects")
@Data
public class ApplicantProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private Long applicantId;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Capacity capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentMode employmentMode;

    @Column
    private int durationInWeeks;

    @Column
    private String role;

    @Column
    private int year;

    @Column
    private int duration;

    @Column
    private int teamSize;

    @Column
    private String repositoryUrl;

    @Column
    private String liveUrl; // optional

}

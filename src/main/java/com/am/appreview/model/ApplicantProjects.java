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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applicant_projects")
@Data
public class ApplicantProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="applicant_id", nullable=false)
    private Applicant applicantProjects;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Capacity capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentMode employmentMode;

    private int durationInWeeks;

    private String role;

    private int year;

    private int duration;

    private int teamSize;

    private String repositoryUrl; // optional

    private String liveUrl; // optional


}

package com.am.appreview.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "applicant")
@Data
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String githubUserName;

    @OneToMany(mappedBy="applicantProjects")
    private List<ApplicantProjects> applicantProjects;

    @Column(nullable = false)
    private String status; // Status of applicant review - InProgress, Selected , Rejected

    @ManyToOne
    @JoinColumn(name="id")
    private ApplicantReviewer applicantReviewer;


}

package com.am.appreview.model;

import com.am.appreview.enums.ApplicantStatus;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "applicant")
@Data
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String githubUserName;

    @OneToMany(mappedBy="applicantId")
    private Set<ApplicantProjects> applicantProjects;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicantStatus status;

    @ManyToOne
    private ApplicantReviewer applicantReviewer;

}

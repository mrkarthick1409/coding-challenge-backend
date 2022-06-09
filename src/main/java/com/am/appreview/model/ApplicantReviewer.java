package com.am.appreview.model;

import javax.persistence.*;
import java.util.List;

public class ApplicantReviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String emailAddress;

    @OneToMany(mappedBy = "applicantReviewer")
    private List<Applicant> applicants;
}

package com.am.appreview.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "applicant_reviewer")
@Data
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

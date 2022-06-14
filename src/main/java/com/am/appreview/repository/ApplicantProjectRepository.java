package com.am.appreview.repository;

import com.am.appreview.model.ApplicantProjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantProjectRepository extends JpaRepository<ApplicantProjects, Long> {

    void deleteAllByApplicantId(Long applicantId);

}

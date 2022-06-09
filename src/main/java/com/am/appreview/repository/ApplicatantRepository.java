package com.am.appreview.repository;

import com.am.appreview.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicatantRepository extends JpaRepository<Applicant, Long> {
}
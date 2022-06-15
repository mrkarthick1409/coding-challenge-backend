package com.am.appreview.repository;

import com.am.appreview.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Optional<Applicant> findByEmailAddress(String emailAddress);

}

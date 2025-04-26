package com.jarvis.HospitalManagementSystem.ApiRepository;

import com.jarvis.HospitalManagementSystem.ApiEntity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestPatientRepository extends JpaRepository<Patient, Long> {
}

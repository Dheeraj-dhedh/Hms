package com.jarvis.HospitalManagementSystem.ApiServices;

import com.jarvis.HospitalManagementSystem.ApiEntity.Patient;

import java.util.List;

public interface RestPatientServiceInterface {
    List<Patient> getAllPatients();
    Patient savePatient(Patient patient);
    Patient getPatientById(Long id);
    void deletePatientById(Long id);
}

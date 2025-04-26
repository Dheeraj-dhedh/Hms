package com.jarvis.HospitalManagementSystem.ApiServices;

import com.jarvis.HospitalManagementSystem.ApiEntity.Patient;
import com.jarvis.HospitalManagementSystem.ApiRepository.RestPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RestPatientService implements RestPatientServiceInterface {

    private final RestPatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }

    public Page<Patient> getPatientsWithPagination(int page, int size) {
        //Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Sort sort = Sort.by("age").descending().and(Sort.by("name").ascending());
        Pageable pageable = PageRequest.of(page, size, sort);
        return patientRepository.findAll(pageable);
    }

}

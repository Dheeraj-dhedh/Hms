package com.jarvis.HospitalManagementSystem.ApiServices;

import com.jarvis.HospitalManagementSystem.ApiEntity.Patient;
import com.jarvis.HospitalManagementSystem.ApiRepository.RestPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "allPatientsCache", key = "'all'")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    @CachePut(value = "patientCache", key = "#result.id")
    @CacheEvict(value = "allPatientsCache", key = "'all'")
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @Cacheable(value = "patientCache", key = "#id")
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(value = "patientCache", key = "#id")
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

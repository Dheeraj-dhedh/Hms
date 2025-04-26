package com.jarvis.HospitalManagementSystem.ApiController;

import com.jarvis.HospitalManagementSystem.ApiEntity.Patient;
import com.jarvis.HospitalManagementSystem.ApiServices.RestPatientService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@Log4j2
@Data
public class RestPatientController {

    @Autowired
    private RestPatientService patientService;

    // Get all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        log.info("Fetching all patients...");
        return patientService.getAllPatients();
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        log.info("Fetching patient with ID: {}", id);
        return patientService.getPatientById(id);
    }

    // Add a new patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        log.info("Creating new patient: {}", patient);
        return patientService.savePatient(patient);
    }

    // Update an existing patient
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        log.info("Updating patient with ID: {}", id);
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            existingPatient.setName(updatedPatient.getName());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setGender(updatedPatient.getGender());
            existingPatient.setAddress(updatedPatient.getAddress());
            return patientService.savePatient(existingPatient);
        } else {
            return null;
        }
    }

    // Delete patient by ID
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        log.warn("Deleting patient with ID: {}", id);
        patientService.deletePatientById(id);
        return "Patient deleted successfully with ID: " + id;
    }

    //-------------------------Pagination------------------------------------------

    @GetMapping("/paginated")
    public Page<Patient> getPaginatedPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        log.info("Fetching paginated patients - page: {}, size: {}", page, size);
        return patientService.getPatientsWithPagination(page, size);
    }


}

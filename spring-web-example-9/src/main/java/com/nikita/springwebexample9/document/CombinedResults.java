package com.nikita.springwebexample9.document;

import com.nikita.springwebexample9.repository.DoctorRepository;
import com.nikita.springwebexample9.repository.PatientRepository;
import reactor.core.publisher.Mono;

public class CombinedResults {

    private DoctorRepository doctorRepository;

    private PatientRepository patientRepository;

    public CombinedResults(final DoctorRepository doctorRepository,final PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
}
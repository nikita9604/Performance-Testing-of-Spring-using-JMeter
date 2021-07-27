package com.nikita.springwebexample9.repository;

import com.nikita.springwebexample9.document.Appointment;
import com.nikita.springwebexample9.document.CreateAppointment;
import com.nikita.springwebexample9.document.Doctor;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface DoctorRepositoryExtended{
    public Flux<Appointment> findAllAppointments(int did);
    public Mono<Doctor> getDoctorWithMaximumAppointments();
}

package com.nikita.springwebexample9.repository;

import com.nikita.springwebexample9.document.Doctor;
import com.nikita.springwebexample9.document.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface PatientRepository extends ReactiveCrudRepository<Patient,Integer> {

    Flux<Patient> findTop3ByOrderByAgeDesc();

    Flux<Patient> findByNameContaining(String name);

    @Query("SELECT * FROM patient WHERE age > $1")
    Flux<Patient> CategoryByAge(Integer age);

    @Query("SELECT * FROM patient WHERE id = :id")
    Mono<Patient> patientbyId(int id);
}

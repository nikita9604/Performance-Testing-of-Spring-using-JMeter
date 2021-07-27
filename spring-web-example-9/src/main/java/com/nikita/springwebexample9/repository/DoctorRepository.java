package com.nikita.springwebexample9.repository;

import com.nikita.springwebexample9.document.Doctor;
import com.nikita.springwebexample9.document.Patient;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

@Repository
public interface DoctorRepository extends ReactiveCrudRepository<Doctor,Integer>,DoctorRepositoryExtended{

    Flux<Doctor> findBySpecs(String specs);

    Flux<Doctor> findBySpecsOrderByDnameDesc(String specs);

    //Flux<Doctor> findTop2BySpecs(String specs, Pageable pageable);
    @Query("SELECT * FROM doctor WHERE dname = :#{[0]}")
    Mono<Doctor> findByDName(String dname);
    // Update Specs of a doctor given Name
    @Query("UPDATE doctor SET specs = :specs where dname = :dname")
    Mono<Doctor> updateSpecs(String specs, String dname);

    @Query("SELECT * FROM doctor WHERE did = :did")
    Flux<Doctor> doctorbyId(int did);
}

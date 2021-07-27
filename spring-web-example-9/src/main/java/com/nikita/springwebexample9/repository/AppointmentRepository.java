package com.nikita.springwebexample9.repository;

import com.nikita.springwebexample9.document.CreateAppointment;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AppointmentRepository extends ReactiveCrudRepository<CreateAppointment,Integer> {
    @Query("SELECT Count(*) FROM Appointment WHERE did = :did")
    Mono<Integer> getCountByDid(int did);

}

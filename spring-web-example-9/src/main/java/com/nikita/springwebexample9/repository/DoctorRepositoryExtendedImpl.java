package com.nikita.springwebexample9.repository;

import com.nikita.springwebexample9.document.Appointment;
import com.nikita.springwebexample9.document.CreateAppointment;
import com.nikita.springwebexample9.document.Doctor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DoctorRepositoryExtendedImpl implements DoctorRepositoryExtended{
    private DatabaseClient client;
    public DoctorRepositoryExtendedImpl(DatabaseClient client) {

        this.client = client;

    }

    public Flux<Appointment> findAllAppointments(int did) {

        String query = "SELECT *"

                + " FROM Doctor Natural JOIN Appointment Natural JOIN Patient WHERE did = :did";

        DoctorMapper mapper = new DoctorMapper();
        Flux<Appointment> result = client.sql(query)

                .bind("did", did).map(mapper::apply).all();



        return result;

    }
    public Mono<Doctor> getDoctorWithMaximumAppointments()
    {
//        String query = "select * from doctor natural join (select did,count(id) from appointment group by did) as subresult where count=(\n" +
//                "select max(count) from  (select did,count(id) from appointment group by did) as subresult)";
        String query="select * from doctor natural join (select did,count(id) as occurances from appointment group by did order by occurances desc limit 1) as subresult";
        Mono<Doctor> result = client.sql(query).map(row -> new Doctor(row.get("did", Integer.class),
            row.get("dname", String.class),row.get("specs",String.class))).one();
        return result;
    }
}

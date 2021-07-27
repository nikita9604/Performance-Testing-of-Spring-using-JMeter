package com.nikita.springwebexample9.repository;

import com.nikita.springwebexample9.document.Appointment;
import com.nikita.springwebexample9.document.Doctor;
import com.nikita.springwebexample9.document.Patient;
import io.r2dbc.spi.Row;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.function.BiFunction;

public class DoctorMapper implements BiFunction<Row, Object, Appointment>{

    @Override
    public Appointment apply(Row row, Object o) {

        int doctorid = row.get("did", Integer.class);

        String doctorname = row.get("dname", String.class);
        String specs = row.get("specs", String.class);

        String patientname = row.get("name", String.class);
        int patientid= row.get("id", Integer.class);
        int age=row.get("age", Integer.class);
        Patient patient = new Patient(patientid, patientname,age);

        Appointment doc = new Appointment(doctorid, doctorname, specs,patient);

        return doc;

    }
}

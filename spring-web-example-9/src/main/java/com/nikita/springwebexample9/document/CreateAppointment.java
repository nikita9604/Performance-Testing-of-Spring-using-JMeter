package com.nikita.springwebexample9.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value="Appointment")
public class CreateAppointment {

    @Id
    int aid;
    int did;
    int id;
}

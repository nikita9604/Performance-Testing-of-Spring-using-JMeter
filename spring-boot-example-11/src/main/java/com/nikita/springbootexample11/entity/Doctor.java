package com.nikita.springbootexample11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private int did;
    private String dname;
    private String specs;

//    @OneToMany(mappedBy = "doctor")
//    Set<Appointment> appointments;
    //@OneToMany(targetEntity = Appointment.class,cascade = CascadeType.ALL)
    //@JoinColumn(name = "did", referencedColumnName = "did")
    //private List<Appointment> appointment;
}

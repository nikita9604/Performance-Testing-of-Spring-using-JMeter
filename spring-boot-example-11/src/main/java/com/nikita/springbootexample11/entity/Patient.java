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
public class Patient {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;

//    @OneToMany(mappedBy = "patient")
//    Set<Appointment> appointments;
    //@OneToMany(targetEntity = Appointment.class,cascade = CascadeType.ALL)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    //private List<Appointment> appointment;
}

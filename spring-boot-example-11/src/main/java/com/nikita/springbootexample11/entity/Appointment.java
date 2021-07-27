package com.nikita.springbootexample11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private int aid;


    @ManyToOne(targetEntity = Patient.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Patient patient;

    @ManyToOne(targetEntity = Doctor.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "did")
    private Doctor doctor;

//    @OneToOne(targetEntity = Patient.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private Patient patient;
//
//    @OneToOne(targetEntity = Doctor.class)
//    @JoinColumn(name = "did")
//    private Doctor doctor;
}

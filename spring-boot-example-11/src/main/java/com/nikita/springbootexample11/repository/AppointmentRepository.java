package com.nikita.springbootexample11.repository;

import com.nikita.springbootexample11.entity.Appointment;
import com.nikita.springbootexample11.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query("SELECT a FROM Appointment a JOIN a.doctor d JOIN a.patient p WHERE p.age>?1")
    public List<Appointment> getCountByPAge(int age);

    @Query("SELECT a FROM Appointment a JOIN a.doctor d JOIN a.patient p WHERE d.did=:did")
    public List<Appointment> findAllAppointments(int did);

    //@Query(value = "SELECT a.* from Appointment a JOIN (select a.did,count(a.did) as occurances from appointment a " +
    //        "GROUP BY a.did ORDER BY occurances DESC LIMIT 1) as subresult", nativeQuery = true)
//    @Query(value = "SELECT a.* from Appointment a WHERE a.did = (select d.did from Doctor d " +
//            "GROUP BY d.dname ORDER BY count(d.dname) DESC LIMIT 1)", nativeQuery = true)
    @Query(value = "SELECT a.* from Appointment a WHERE a.did = (select d.did from Appointment d " +
            "GROUP BY d.did ORDER BY count(d.did) DESC LIMIT 1)", nativeQuery = true)
       public List<Appointment> getAllWithMaximumAppointments();

//    @Query(value = "SELECT d.* FROM Appointment a JOIN doctor d JOIN patient p" +
//            "GROUP BY d.did ORDER BY count(d.did) DESC LIMIT 1", nativeQuery = true)
//    public List<Doctor> getDoctorWithMaximumAppointments();

}

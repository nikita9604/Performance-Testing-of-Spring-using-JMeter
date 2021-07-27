package com.nikita.springbootexample11.repository;

import com.nikita.springbootexample11.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    public List<Doctor> findBySpecs(String specs);

    public List<Doctor> findBySpecsOrderByDnameDesc(String specs);

    @Query("SELECT d FROM Doctor d WHERE d.dname=?1")
    public List<Doctor> findByDName(String dname);

    @Query("SELECT d FROM Doctor d WHERE d.did =:did")
    public List<Doctor> doctorbyId(@Param("did")int did);

    @Query(value = "SELECT *, COUNT(*) AS Total FROM Doctor GROUP BY dname ORDER BY Total DESC LIMIT 1",nativeQuery = true)
    public List<Doctor> getDoctorWithMaximumAppointments();
}

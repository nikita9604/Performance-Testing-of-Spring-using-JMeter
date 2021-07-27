package com.nikita.springbootexample11.repository;

import com.nikita.springbootexample11.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

    public List<Patient> findTop3ByOrderByAgeDesc();

    public List<Patient> findByNameContaining(String name);

    @Query("SELECT p from Patient p where p.age>?1")
    public List<Patient> CategoryByAge(int age);

    @Query("SELECT p from Patient p where p.id=:id")
    public List<Patient> patientbyId(@Param("id") int id);
}

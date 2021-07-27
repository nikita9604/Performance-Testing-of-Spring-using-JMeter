package com.nikita.springbootexample11.service;

import com.nikita.springbootexample11.entity.Appointment;
import com.nikita.springbootexample11.entity.Doctor;
import com.nikita.springbootexample11.entity.Patient;
import com.nikita.springbootexample11.repository.AppointmentRepository;
import com.nikita.springbootexample11.repository.DoctorRepository;
import com.nikita.springbootexample11.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {
    @Autowired
    private DoctorRepository repository;
    @Autowired
    private PatientRepository repository2;
    @Autowired
    private AppointmentRepository repository3;

    // Doctor Repository CRUD Operations
    public Doctor addDoctors(Doctor doctors){ return repository.save(doctors); }
    public List<Doctor> getDoctors(){ return repository.findAll(); }
    public Doctor getDoctorById(int id){ return repository.findById(id).orElse(null); }
    public String deleteDoctor(int id){
        repository.deleteById(id);
        return "Removed Doctor with ID - " + id;
    }
    public Doctor updateDoctor(Doctor doctor){
        Doctor existingProduct=repository.findById(doctor.getDid()).orElse(null);
        existingProduct.setDname(doctor.getDname());
        existingProduct.setSpecs(doctor.getSpecs());
        return repository.save(existingProduct);
    }

    // Patient Repository CRUD Operations
    public Patient addPatients(Patient patients){ return repository2.save(patients); }
    public List<Patient> getPatients(){ return repository2.findAll(); }
    public Patient getPatientById(int id){ return repository2.findById(id).orElse(null); }
    public String deletePatient(int id){
        repository2.deleteById(id);
        return "Removed Report with ID - " + id;
    }
    public Patient updatePatient(Patient patient){
        Patient existingProduct=repository2.findById(patient.getId()).orElse(null);
        existingProduct.setName(patient.getName());
        existingProduct.setAge(patient.getAge());
        return repository2.save(existingProduct);
    }

    // Appointment Repository CRUD Operations
    //public List<Appointment> addAppointments(List<Appointment> appointments){ return repository3.saveAll(appointments); }
    public List<Appointment> getAppointments(){ return repository3.findAll(); }
    public Appointment geAppointmentById(int id){ return repository3.findById(id).orElse(null); }
    public String deleteAppointment(int id){
        repository3.deleteById(id);
        return "Removed Report with ID - " + id;
    }
    public Appointment updateAppointment(Appointment appointment){
        Appointment existingProduct=repository3.findById(appointment.getAid()).orElse(null);
        //existingProduct.setId(appointment.getId());
        //existingProduct.setDid(appointment.getDid());
        return repository3.save(existingProduct);
    }

    // Patient Details
    public List<Patient> findTop3ByOrderByAgeDesc() { return repository2.findTop3ByOrderByAgeDesc(); }
    public List<Patient> findByNameContaining(String name) { return repository2.findByNameContaining(name); }
    public List<Patient> CategoryByAge(int age) { return repository2.CategoryByAge(age); }
    public List<Patient> patientbyId(int id) { return repository2.patientbyId(id); }

    // Doctor Details
    public List<Doctor> findBySpecs(String specs) { return repository.findBySpecs(specs); }
    public List<Doctor> findBySpecsOrderByDnameDesc(String specs) { return repository.findBySpecsOrderByDnameDesc(specs); }
    public List<Doctor> findByDName(String dname) { return repository.findByDName(dname); }
    public List<Doctor> doctorbyId(int did) { return repository.doctorbyId(did); }
    public List<Doctor> getDoctorWithMaximumAppointments() { return repository3.getAllWithMaximumAppointments().stream().map(Appointment::getDoctor).limit(1).collect(Collectors.toList()); }
    //public List<Doctor> findAllAppointments(int did) { return repository.findAllAppointments(did); }

    // Appointment Details
    public List<Appointment> getCountByPAge(int age) { return repository3.getCountByPAge(age); }
    public List<Appointment> findAllAppointments(int did) { return repository3.findAllAppointments(did); }
    public List<Appointment> getAllWithMaximumAppointments() { return repository3.getAllWithMaximumAppointments(); }
}

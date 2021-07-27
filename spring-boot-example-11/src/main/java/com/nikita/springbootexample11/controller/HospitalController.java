package com.nikita.springbootexample11.controller;

import com.nikita.springbootexample11.dto.AppRequest;
import com.nikita.springbootexample11.dto.DoctorRequest;
import com.nikita.springbootexample11.dto.PatientRequest;
import com.nikita.springbootexample11.entity.Appointment;
import com.nikita.springbootexample11.entity.Doctor;
import com.nikita.springbootexample11.entity.Patient;
import com.nikita.springbootexample11.repository.AppointmentRepository;
import com.nikita.springbootexample11.repository.DoctorRepository;
import com.nikita.springbootexample11.repository.PatientRepository;
import com.nikita.springbootexample11.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private HospitalService service;

    //  PostMapping - CRUD Operations of all 3 entities
    //@PostMapping("/addP")
    //public Patient addEntry(@RequestBody PatientRequest request){ return patientRepository.save(request.getPatient());}
    //@PostMapping("/addD")
    //public Doctor addEntry(@RequestBody DoctorRequest request){ return doctorRepository.save(request.getDoctor());}
    @PostMapping("/d")
    public Doctor addDoctors(@RequestBody Doctor doctor){
        return service.addDoctors(doctor);
    }
    @PostMapping("/p")
    public Patient addPatients(@RequestBody Patient patient){
        return service.addPatients(patient);
    }
    @PostMapping("/a")
    public Appointment addAppointments(@RequestBody AppRequest request){
        Appointment app=new Appointment();
        app.setPatient(service.getPatientById(request.getId()));
        app.setDoctor(service.getDoctorById(request.getDid()));
        return appointmentRepository.save(app);
    }

    //  GetMapping - CRUD Operations of all 3 entities
    @GetMapping("/p")
    public List<Patient> getPatients(){ return service.getPatients(); }
    @GetMapping("/d")
    public List<Doctor> getDoctors(){ return service.getDoctors(); }
    @GetMapping("/a")
    public List<Appointment> getAppointments(){ return service.getAppointments(); }
    @GetMapping("/p/{id}")
    public Patient getPatientById(@PathVariable int id){ return service.getPatientById(id); }
    @GetMapping("/d/{id}")
    public Doctor getDoctorById(@PathVariable int id){ return service.getDoctorById(id); }
//    @GetMapping("/a/{id}")
//    public Appointment getAppointmentById(@PathVariable int id){ return service.geAppointmentById(id); }

    //  PutMapping - CRUD Operations of all 3 entities
    @PutMapping("/updateP")
    public Patient updatePatient(@RequestBody Patient patient){
        return service.updatePatient(patient);
    }
    @PutMapping("/updateD")
    public Doctor updateDoctor(@RequestBody Doctor doctor){ return service.updateDoctor(doctor); }
    @PutMapping("/updateA")
    public Appointment updateAppointment(@RequestBody Appointment appointment){ return service.updateAppointment(appointment); }

    //  DeleteMapping - CRUD Operations of all 3 entities
    @DeleteMapping("/d/{id}")
    public String deleteDoctor(@PathVariable int id){
        return service.deleteDoctor(id);
    }
    @DeleteMapping("/p/{id}")
    public String deletePatient(@PathVariable int id){ return service.deletePatient(id); }
    @DeleteMapping("/a/{id}")
    public String deleteAppointment(@PathVariable int id){ return service.deleteAppointment(id); }

    // Patient Repository
    @GetMapping("/page")
    public List<Patient> findTop3ByOrderByAgeDesc() { return patientRepository.findTop3ByOrderByAgeDesc(); }
    @GetMapping("/pname/{name}")
    public List<Patient> findByNameContaining(@PathVariable String name) { return patientRepository.findByNameContaining(name); }
    @GetMapping("/page/{age}")
    public List<Patient> CategoryByAge(@PathVariable int age) { return patientRepository.CategoryByAge(age); }


    // Doctor Repository
    @GetMapping("/D/{specs}")
    public List<Doctor> findBySpecs(@PathVariable String specs) { return doctorRepository.findBySpecs(specs); }
    @GetMapping("/DOrder/{specs}")
    public List<Doctor> findBySpecsOrderByDnameDesc(@PathVariable String specs) { return doctorRepository.findBySpecsOrderByDnameDesc(specs); }
    @GetMapping("/Ddname/{dname}")
    public List<Doctor> findByDName(@PathVariable String dname) { return doctorRepository.findByDName(dname); }
    @GetMapping("/Did/{did}")
    public List<Doctor> doctorbyId(@PathVariable int did) { return doctorRepository.doctorbyId(did); }
    @GetMapping("/d/max")
    public List<Doctor> getDoctorWithMaximumAppointments() { return service.getDoctorWithMaximumAppointments(); }
    //@GetMapping("/DAll/{did}")
    //public List<Doctor> findAllAppointments(@PathVariable int did) { return doctorRepository.findAllAppointments(did); }

    // Appointment Repository
    @GetMapping("/Acount/{age}")
    public List<Appointment> getCountByPAge(@PathVariable int age) { return service.getCountByPAge(age); }
    @GetMapping("/a/{did}")
    public List<Appointment> findAllAppointments(@PathVariable int did) { return service.findAllAppointments(did); }
    @GetMapping("/AallM")
    public List<Appointment> getAllWithMaximumAppointments() { return service.getAllWithMaximumAppointments(); }
}
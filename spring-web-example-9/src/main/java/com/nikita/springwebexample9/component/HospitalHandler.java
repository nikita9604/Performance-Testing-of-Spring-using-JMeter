package com.nikita.springwebexample9.component;

import com.nikita.springwebexample9.document.*;
import com.nikita.springwebexample9.repository.AppointmentRepository;
import com.nikita.springwebexample9.repository.DoctorRepository;
import com.nikita.springwebexample9.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HospitalHandler {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    // Patient Functions (CRUD)
    public Mono<ServerResponse> getAllPatients(ServerRequest request) {
        Flux<Patient> patients = patientRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(patients, Patient.class);
    }
    public Mono<ServerResponse> getPatient(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return patientRepository.findById(id).flatMap(patient -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(patient), Patient.class)).switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> addNewPatient(ServerRequest request) {
        Mono<Patient> patientMono = request.bodyToMono(Patient.class);
        Mono<Patient> newPatient = patientMono.flatMap(patientRepository::save);
        return ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(newPatient, Patient.class);
    }
    public Mono<ServerResponse> updatePatient(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Patient> patientMono = request.bodyToMono(Patient.class);
        return patientRepository.findById(id)
                .flatMap(patient -> patientMono.flatMap(patient1 -> {
                    patient.setName(patient1.getName());
                    patient.setAge(patient1.getAge());
                    Mono<Patient> updatedPatient = patientRepository.save(patient);
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(updatedPatient, Patient.class);
                }));
    }
    public Mono<ServerResponse> deletePatient(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return patientRepository.findById(id).flatMap(patient -> patientRepository.delete(patient)
                        .then(ServerResponse.ok().build())).switchIfEmpty(ServerResponse.notFound().build());
    }

    // Doctor Function (CRUD)
    public Mono<ServerResponse> getAllDoctors(ServerRequest request) {
        Flux<Doctor> doctors = doctorRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(doctors, Patient.class);
    }
    public Mono<ServerResponse> getDoctor(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Doctor> doc=doctorRepository.findById(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(doc, Doctor.class).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addNewDoctor(ServerRequest request) {
        Mono<Doctor> doctorMono = request.bodyToMono(Doctor.class);

        Mono<Doctor> newDoctor = doctorMono.flatMap(doctorRepository::save);
        return ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(newDoctor, Doctor.class);
    }
    public Mono<ServerResponse> updateDoctor(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Doctor> doctorMono = request.bodyToMono(Doctor.class);
        return doctorRepository.findById(id)
                .flatMap(doctor -> doctorMono.flatMap(doctor1 -> {
                    doctor.setDname(doctor1.getDname());
                    doctor.setSpecs(doctor1.getSpecs());
                    Mono<Doctor> updatedDoctor = doctorRepository.save(doctor);
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(updatedDoctor, Doctor.class);
                }));
    }
    public Mono<ServerResponse> deleteDoctor(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return doctorRepository.findById(id).flatMap(doctor -> doctorRepository.delete(doctor)
                .then(ServerResponse.ok().build())).switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> getDoctorWithMaxAppointment(ServerRequest request) {

        Mono<Doctor> doc=doctorRepository.getDoctorWithMaximumAppointments();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(doc, Doctor.class).switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> getAppointmentForDoctorWithId(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Flux<Appointment> appointments=doctorRepository.findAllAppointments(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(appointments, Appointment.class).switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> addNewAppointment(ServerRequest request) {
        Mono<CreateAppointment> AppointmentMono = request.bodyToMono(CreateAppointment.class);

        Mono<CreateAppointment> newAppointment = AppointmentMono.flatMap(appointmentRepository::save);
        return ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(newAppointment, CreateAppointment.class);
    }
    public Mono<ServerResponse> getCountOfAppointmentWithDid(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Integer> count=appointmentRepository.getCountByDid(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(count, Integer.class).switchIfEmpty(ServerResponse.notFound().build());
    }

    // Patient Repository
    public Mono<ServerResponse> findTop3ByOrderByAgeDesc(ServerRequest request) {
        Flux<Patient> result = patientRepository.findTop3ByOrderByAgeDesc();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Patient.class);
    }

    public Mono<ServerResponse> findByNameContaining(ServerRequest request) {
        String name = request.pathVariable("name");
        Flux<Patient> result = patientRepository.findByNameContaining(name);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Patient.class);
    }

    public Mono<ServerResponse> CategoryByAge(ServerRequest request) {
        int age = Integer.parseInt(request.pathVariable("age"));
        Flux<Patient> result = patientRepository.CategoryByAge(age);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Patient.class);
    }

    public Mono<ServerResponse> AlldetailsbyId(ServerRequest request) {
        int pid = Integer.parseInt(request.pathVariable("pid"));
        Flux<Doctor> result = doctorRepository.doctorbyId(pid);
        Mono<Patient> result1 = patientRepository.patientbyId(pid);
        Flux<Object> combinedResults = Flux.merge(result1,result);
        //Mono<CombinedResults> combinedResults = result.flatMap(res -> result1.map(res1 -> new CombinedResults(res,res1)));
        //return combinedResults;
        //return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(combinedResults, CombinedResults.class);
        return ServerResponse.ok().body(combinedResults, CombinedResults.class);
    }

    // Doctor Repository
    public Mono<ServerResponse> findByDName(ServerRequest request) {
        String dName = request.pathVariable("dname");
        return doctorRepository.findByDName(dName)
                .flatMap(doctor -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(doctor), Doctor.class)).switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> findBySpecs(ServerRequest request) {
        String specs = request.pathVariable("specs");
        Flux<Doctor> result = doctorRepository.findBySpecs(specs);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Doctor.class);
    }
    public Mono<ServerResponse> updateSpecs(ServerRequest request) {
        String specs = request.pathVariable("specs");
        String dname = request.pathVariable("dname");
        Mono<Doctor> result = doctorRepository.updateSpecs(specs,dname);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Doctor.class);
    }
    public Mono<ServerResponse> findBySpecsOrderByDnameDesc(ServerRequest request) {
        String specs = request.pathVariable("specs");
        Flux<Doctor> result = doctorRepository.findBySpecsOrderByDnameDesc(specs);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Doctor.class);
    }
}

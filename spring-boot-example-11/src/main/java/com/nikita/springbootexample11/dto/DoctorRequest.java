package com.nikita.springbootexample11.dto;

import com.nikita.springbootexample11.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorRequest {
    private Doctor doctor;
}

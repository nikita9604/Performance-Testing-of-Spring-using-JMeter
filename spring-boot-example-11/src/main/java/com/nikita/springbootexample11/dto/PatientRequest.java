package com.nikita.springbootexample11.dto;

import com.nikita.springbootexample11.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientRequest {
    private Patient patient;
}

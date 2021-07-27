package com.nikita.springwebexample9.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {
    @Id
    private int did;
    private String dname;
    private String specs;



}

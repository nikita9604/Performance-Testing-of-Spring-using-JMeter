package com.nikita.springwebexample9.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private int did;
    private String dname;
    private String specs;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Patient p;
}

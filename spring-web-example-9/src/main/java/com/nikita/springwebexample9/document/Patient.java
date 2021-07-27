package com.nikita.springwebexample9.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private int id;
    private String name;
    private int age;
}

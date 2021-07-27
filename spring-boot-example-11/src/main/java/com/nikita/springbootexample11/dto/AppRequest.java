package com.nikita.springbootexample11.dto;

import com.nikita.springbootexample11.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppRequest {
   private int id;
   private int did;
}

package com.docsehr.patient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 60, message = "Name must be atleast 2 characters")
    private String name;

    @Min(value = 0, message = "Age must be at least 0")
    @Max(value = 120, message = "Age must be at most 120")
    private int age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobile;

    @NotBlank(message = "Address is required")
    private String address;
}
package com.example.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Name is requerd..!")
    private String name;

    @NotBlank(message="Email id is requerd...!")
    @Email(message="Valid email id  format is requerd...!")
    private String email;

    @NotBlank(message = "Account number is required")
    
    private String accountNumber;

    @Min(value = 0, message = "Balance cannot be negative")
    private double balance;
}

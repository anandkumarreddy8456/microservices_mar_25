package com.anand.microservice.userService.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "userservice",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotBlank(message = "fullname is mandatory")
    private String fullName;
    @NotBlank(message = "username is Mandatory")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    @NotBlank(message = "Role is Manadatory")
    private String role;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @NotBlank(message = "password is mandatory")
    private String password;
}

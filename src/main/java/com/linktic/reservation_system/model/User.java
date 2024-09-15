package com.linktic.reservation_system.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
}

package com.hei.gestionabsenceexamen.entity;

import lombok.Data;

@Data
public class Supervisor {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department; // Département de supervision
}
package com.hei.gestionabsenceexamen.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Absence {
    private Long id;
    private LocalDate absenceDate;
    private Boolean justified;
    private String reason; // Justification de l'absence si applicable

    // Référence vers l'étudiant qui a cette absence
    private Student student;

    // Référence vers le superviseur qui valide l'absence
    private Supervisor supervisor;

    // Statut de la validation de l'absence par le superviseur
    private boolean validatedBySupervisor;
}
package com.hei.gestionabsenceexamen.entity;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String group; // J1, J2, H1, H2
    private String className; // Ex: L1, L2
    private int unjustifiedAbsencesCount;
    private String CORStatus; // Convocation, Observation, Renvoi
    private LocalDate CORStartDate;

    private List<Absence> absences; // Liste des absences associées à l'étudiant

    public void setCorStatus(String corStatus) {
        this.CORStatus = corStatus;
    }

    public void setCorStartDate(LocalDate corStartDate) {
        this.CORStartDate = corStartDate;
    }

    public String getCorStatus() {
        return CORStatus;
    }

    public LocalDate getCorStartDate() {
        return CORStartDate;
    }
}
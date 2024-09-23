package com.hei.gestionabsenceexamen.entity;

import lombok.Data;
import java.util.List;

@Data
public class Course {
    private Long id;
    private String name;
    private String professor;

    // Liste des étudiants inscrits au cours
    private List<Student> students;

    // Liste des absences enregistrées pour ce cours
    private List<Absence> absences;
}
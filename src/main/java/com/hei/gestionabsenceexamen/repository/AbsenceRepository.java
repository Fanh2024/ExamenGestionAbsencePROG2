package com.hei.gestionabsenceexamen.repository;

import com.hei.gestionabsenceexamen.entity.Absence;
import com.hei.gestionabsenceexamen.entity.Student;
import com.hei.gestionabsenceexamen.entity.Supervisor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbsenceRepository {

    private Connection connection;

    public AbsenceRepository() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Méthode pour récupérer toutes les absences, avec les informations d'étudiant et de superviseur
    public List<Absence> getAllAbsences() throws SQLException {
        String sql = """
        SELECT a.id, a.absence_date, a.justified, a.reason, a.validated_by_supervisor, 
               s.id AS student_id, s.first_name AS student_first_name, s.last_name AS student_last_name, s.email AS student_email,
               s.birth_date, s."group", s.class_name, s.unjustified_absences_count, s.cor_status, s.cor_start_date,
               su.id AS supervisor_id, su.first_name AS supervisor_first_name, su.last_name AS supervisor_last_name, su.email AS supervisor_email,
               su.department AS supervisor_department
        FROM absences a
        LEFT JOIN students s ON a.student_id = s.id
        LEFT JOIN supervisors su ON a.supervisor_id = su.id
    """;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Absence> absences = new ArrayList<>();

        while (resultSet.next()) {
            Absence absence = new Absence();
            absence.setId((long) resultSet.getInt("id"));
            absence.setAbsenceDate(resultSet.getDate("absence_date").toLocalDate());
            absence.setJustified(resultSet.getBoolean("justified"));
            absence.setReason(resultSet.getString("reason"));
            absence.setValidatedBySupervisor(resultSet.getBoolean("validated_by_supervisor"));

            // Récupérer les informations de l'étudiant
            if (resultSet.getObject("student_id") != null) {
                Student student = new Student();
                student.setId((long) resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("student_first_name"));
                student.setLastName(resultSet.getString("student_last_name"));
                student.setEmail(resultSet.getString("student_email"));
                student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                student.setGroup(resultSet.getString("group"));
                student.setClassName(resultSet.getString("class_name"));
                student.setUnjustifiedAbsencesCount(resultSet.getInt("unjustified_absences_count"));
                student.setCorStatus(resultSet.getString("cor_status"));
                student.setCorStartDate(resultSet.getDate("cor_start_date") != null ? resultSet.getDate("cor_start_date").toLocalDate() : null);
                absence.setStudent(student);
            }

            // Récupérer les informations du superviseur
            if (resultSet.getObject("supervisor_id") != null) {
                Supervisor supervisor = new Supervisor();
                supervisor.setId((long) resultSet.getInt("supervisor_id"));
                supervisor.setFirstName(resultSet.getString("supervisor_first_name"));
                supervisor.setLastName(resultSet.getString("supervisor_last_name"));
                supervisor.setEmail(resultSet.getString("supervisor_email"));
                supervisor.setDepartment(resultSet.getString("supervisor_department")); // Ajout du département
                absence.setSupervisor(supervisor);
            }

            absences.add(absence);
        }

        return absences;
    }


    // Méthode pour récupérer une absence par ID avec les informations d'étudiant et de superviseur
    public Absence getAbsenceById(Long id) throws SQLException {
        String sql = """
        SELECT a.id, a.absence_date, a.justified, a.reason, a.validated_by_supervisor, 
               s.id AS student_id, s.first_name AS student_first_name, s.last_name AS student_last_name, s.email AS student_email,
               s.birth_date, s."group", s.class_name, s.unjustified_absences_count, s.cor_status, s.cor_start_date,
               su.id AS supervisor_id, su.first_name AS supervisor_first_name, su.last_name AS supervisor_last_name, su.email AS supervisor_email,
               su.department AS supervisor_department
        FROM absences a
        LEFT JOIN students s ON a.student_id = s.id
        LEFT JOIN supervisors su ON a.supervisor_id = su.id
        WHERE a.id = ?
    """;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Absence absence = new Absence();
            absence.setId((long) resultSet.getInt("id"));
            absence.setAbsenceDate(resultSet.getDate("absence_date").toLocalDate());
            absence.setJustified(resultSet.getBoolean("justified"));
            absence.setReason(resultSet.getString("reason"));
            absence.setValidatedBySupervisor(resultSet.getBoolean("validated_by_supervisor"));

            // Récupérer les informations de l'étudiant
            if (resultSet.getObject("student_id") != null) {
                Student student = new Student();
                student.setId((long) resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("student_first_name"));
                student.setLastName(resultSet.getString("student_last_name"));
                student.setEmail(resultSet.getString("student_email"));
                student.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                student.setGroup(resultSet.getString("group"));
                student.setClassName(resultSet.getString("class_name"));
                student.setUnjustifiedAbsencesCount(resultSet.getInt("unjustified_absences_count"));
                student.setCorStatus(resultSet.getString("cor_status"));
                student.setCorStartDate(resultSet.getDate("cor_start_date") != null ? resultSet.getDate("cor_start_date").toLocalDate() : null);
                absence.setStudent(student);
            }

            // Récupérer les informations du superviseur
            if (resultSet.getObject("supervisor_id") != null) {
                Supervisor supervisor = new Supervisor();
                supervisor.setId((long) resultSet.getInt("supervisor_id"));
                supervisor.setFirstName(resultSet.getString("supervisor_first_name"));
                supervisor.setLastName(resultSet.getString("supervisor_last_name"));
                supervisor.setEmail(resultSet.getString("supervisor_email"));
                supervisor.setDepartment(resultSet.getString("supervisor_department")); // Ajout du département
                absence.setSupervisor(supervisor);
            }

            return absence;
        } else {
            return null; // ou lever une exception si nécessaire
        }
    }


    // Méthode pour sauvegarder une absence
    public void saveAbsence(Absence absence) throws SQLException {
        String sql = "INSERT INTO absences (absence_date, justified, reason, student_id, supervisor_id, validated_by_supervisor) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(absence.getAbsenceDate()));
        preparedStatement.setBoolean(2, absence.getJustified());
        preparedStatement.setString(3, absence.getReason());
        preparedStatement.setLong(4, absence.getStudent().getId());
        preparedStatement.setLong(5, absence.getSupervisor().getId());
        preparedStatement.setBoolean(6, absence.isValidatedBySupervisor());
        preparedStatement.executeUpdate();
    }

    // Méthode pour mettre à jour une absence
    public void updateAbsence(Long id, Absence absence) throws SQLException {
        String sql = "UPDATE absences SET absence_date = ?, justified = ?, reason = ?, student_id = ?, supervisor_id = ?, validated_by_supervisor = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(absence.getAbsenceDate()));
        preparedStatement.setBoolean(2, absence.getJustified());
        preparedStatement.setString(3, absence.getReason());
        preparedStatement.setLong(4, absence.getStudent().getId());
        preparedStatement.setLong(5, absence.getSupervisor().getId());
        preparedStatement.setBoolean(6, absence.isValidatedBySupervisor());
        preparedStatement.setLong(7, id);
        preparedStatement.executeUpdate();
    }

    // Méthode pour supprimer une absence
    public void deleteAbsence(Long id) throws SQLException {
        String sql = "DELETE FROM absences WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }
}

package com.hei.gestionabsenceexamen.repository;

import com.hei.gestionabsenceexamen.entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private Connection connection;

    public StudentRepository() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Student> students = new ArrayList<>();

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setEmail(resultSet.getString("email"));
            student.setGroup(resultSet.getString("group"));
            student.setClassName(resultSet.getString("class_name"));
            student.setUnjustifiedAbsencesCount(resultSet.getInt("unjustified_absences_count"));
            student.setCorStatus(resultSet.getString("cor_status"));
            student.setCorStartDate(resultSet.getDate("cor_start_date") != null ? resultSet.getDate("cor_start_date").toLocalDate() : null);

            students.add(student);
        }

        return students;
    }

    public Student getStudentById(Long id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setEmail(resultSet.getString("email"));
            student.setGroup(resultSet.getString("group"));
            student.setClassName(resultSet.getString("class_name"));
            student.setUnjustifiedAbsencesCount(resultSet.getInt("unjustified_absences_count"));
            student.setCorStatus(resultSet.getString("cor_status"));
            student.setCorStartDate(resultSet.getDate("cor_start_date") != null ? resultSet.getDate("cor_start_date").toLocalDate() : null);

            return student;
        } else {
            return null;
        }
    }

    public void saveStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, email, group, class_name, unjustified_absences_count, cor_status, cor_start_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setString(4, student.getGroup());
        preparedStatement.setString(5, student.getClassName());
        preparedStatement.setInt(6, student.getUnjustifiedAbsencesCount());
        preparedStatement.setString(7, student.getCorStatus());
        preparedStatement.setDate(8, Date.valueOf(student.getCorStartDate()));
        preparedStatement.executeUpdate();
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, group = ?, class_name = ?, unjustified_absences_count = ?, cor_status = ?, cor_start_date = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setString(3, student.getEmail());
        preparedStatement.setString(4, student.getGroup());
        preparedStatement.setString(5, student.getClassName());
        preparedStatement.setInt(6, student.getUnjustifiedAbsencesCount());
        preparedStatement.setString(7, student.getCorStatus());
        preparedStatement.setDate(8, Date.valueOf(student.getCorStartDate()));
        preparedStatement.setLong(9, student.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteStudent(Long id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }
}

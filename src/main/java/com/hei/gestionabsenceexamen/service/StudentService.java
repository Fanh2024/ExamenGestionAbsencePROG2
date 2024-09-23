package com.hei.gestionabsenceexamen.service;

import com.hei.gestionabsenceexamen.entity.Student;
import com.hei.gestionabsenceexamen.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService() throws SQLException {
        studentRepository = new StudentRepository();
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(Long id) throws SQLException {
        return studentRepository.getStudentById(id);
    }

    public void saveStudent(Student student) throws SQLException {
        studentRepository.saveStudent(student);
    }

    public void updateStudent(Student student) throws SQLException {
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(Long id) throws SQLException {
        studentRepository.deleteStudent(id);
    }
}

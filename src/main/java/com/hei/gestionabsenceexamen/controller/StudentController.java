package com.hei.gestionabsenceexamen.controller;

import com.hei.gestionabsenceexamen.entity.Student;
import com.hei.gestionabsenceexamen.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController() throws SQLException {
        studentService = new StudentService();
    }

    @GetMapping
    public List<Student> getAllStudents() throws SQLException {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) throws SQLException {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) throws SQLException {
        studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) throws SQLException {
        student.setId(id);
        studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) throws SQLException {
        studentService.deleteStudent(id);
    }
}

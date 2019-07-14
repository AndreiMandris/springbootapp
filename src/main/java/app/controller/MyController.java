package app.controller;

import app.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.service.StudentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/{id}")
    public Optional<Student> getStudent(@PathVariable BigDecimal id) {
        return studentService.findById(id);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        studentService.registerStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudentById(@PathVariable BigDecimal id, @RequestBody Student student) {
        studentService.putUsingEm(student, id);
    }
}

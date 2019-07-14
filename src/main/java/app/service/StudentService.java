package app.service;

import app.dao.StudentDao;
import app.model.Student;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Optional<Student> findById(BigDecimal id) {
        return studentDao.findById(id);
    }

    public void createStudent(Student student){
        studentDao.save(student);
    }

    public void updateStudent(Student newStudent, BigDecimal id){
        studentDao.updateStudentById(newStudent.getName(), newStudent.getAge(), id);
    }

    public void deleteStudentById(BigDecimal id){
        studentDao.deleteById(id);
    }

    @Transactional
    public void putUsingEm(Student newStudent, BigDecimal id){
        Student studentFromDB = studentDao.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no username with the specified id!"));
        studentFromDB.setName(newStudent.getName());
        studentFromDB.setAge(newStudent.getAge());
    }

    @Transactional
    public void registerStudent(Student student){
        if (studentDao.findAll().stream()
                .anyMatch(s -> s.getName().equals(student.getName()) && s.getAge() == student.getAge())){
            throw new RuntimeException("Student with same name and age already exists!");
        }
        studentDao.save(student);
    }

    public List<Student> getAllStudents(){
        return studentDao.findAll();
    }
}

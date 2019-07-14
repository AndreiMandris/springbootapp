package app.dao;

import app.model.Student;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional(propagation = Propagation.MANDATORY) //works only on methods defined here
public interface StudentDao extends JpaRepository<Student, BigDecimal> {

    @Modifying //lets spring know this is not a select query, it's done to modify the data
    @Query("UPDATE Student s SET name = :name, age= :age WHERE id = :id")
    void updateStudentById(String name, int age, BigDecimal id);

    @Override
    <S extends Student> S save(S s);
}

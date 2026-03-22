package com.brahmacoaching.studentmanagement.repository;

import com.brahmacoaching.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByStudentNameContainingIgnoreCase(String name);

    List<Student> findByClassName(String className);

    List<Student> findBySchoolNameIgnoreCase(String schoolName);

    List<Student> findByGender(String gender);

    // Flexible multi-param search including className
    @Query("SELECT s FROM Student s WHERE " +
            "(:name IS NULL OR LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:school IS NULL OR LOWER(s.schoolName) = LOWER(:school)) AND " +
            "(:gender IS NULL OR s.gender = :gender) AND " +
            "(:className IS NULL OR s.className = :className)")
    List<Student> searchStudents(
            @Param("name") String name,
            @Param("school") String school,
            @Param("gender") String gender,
            String className);
}
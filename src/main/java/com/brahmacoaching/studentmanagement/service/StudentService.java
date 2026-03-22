package com.brahmacoaching.studentmanagement.service;

import com.brahmacoaching.studentmanagement.dto.StudentRequestDTO;
import com.brahmacoaching.studentmanagement.dto.StudentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO dto);
    StudentResponseDTO getStudentById(UUID id);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO updateStudent(UUID id, StudentRequestDTO dto);
    void deleteStudent(UUID id);
    // className param added
    List<StudentResponseDTO> searchStudents(String name, String school,
                                            String gender, String className);
}
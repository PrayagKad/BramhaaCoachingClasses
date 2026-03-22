package com.brahmacoaching.studentmanagement.service;

import com.brahmacoaching.studentmanagement.dto.StudentRequestDTO;
import com.brahmacoaching.studentmanagement.dto.StudentResponseDTO;
import com.brahmacoaching.studentmanagement.exception.StudentNotFoundException;
import com.brahmacoaching.studentmanagement.mapper.StudentMapper;
import com.brahmacoaching.studentmanagement.model.Student;
import com.brahmacoaching.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        return studentMapper.toDTO(studentRepository.save(studentMapper.toEntity(dto)));
    }

    @Override
    public StudentResponseDTO getStudentById(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return studentMapper.toDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(studentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO updateStudent(UUID id, StudentRequestDTO dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        existing.setStudentName(dto.getStudentName());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setGender(dto.getGender());
        existing.setParentName(dto.getParentName());
        existing.setParentEducation(dto.getParentEducation());
        existing.setMobileNo(dto.getMobileNo());
        existing.setAddress(dto.getAddress());
        existing.setSchoolName(dto.getSchoolName());
        existing.setAadharCard(dto.getAadharCard());
        existing.setClassName(dto.getClassName());
        return studentMapper.toDTO(studentRepository.save(existing));
    }

    @Override
    public void deleteStudent(UUID id) {
        if (!studentRepository.existsById(id))
            throw new StudentNotFoundException("Student not found with id: " + id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> searchStudents(String name, String school,
                                                   String gender, String className) {
        return studentRepository.searchStudents(name, school, gender, className)
                .stream().map(studentMapper::toDTO).collect(Collectors.toList());
    }
}
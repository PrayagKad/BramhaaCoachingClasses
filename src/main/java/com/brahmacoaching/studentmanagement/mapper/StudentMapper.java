package com.brahmacoaching.studentmanagement.mapper;

import com.brahmacoaching.studentmanagement.dto.StudentRequestDTO;
import com.brahmacoaching.studentmanagement.dto.StudentResponseDTO;
import com.brahmacoaching.studentmanagement.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto) {
        Student s = new Student();
        s.setStudentName(dto.getStudentName());
        s.setDateOfBirth(dto.getDateOfBirth());
        s.setGender(dto.getGender());
        s.setParentName(dto.getParentName());
        s.setParentEducation(dto.getParentEducation());
        s.setMobileNo(dto.getMobileNo());
        s.setAddress(dto.getAddress());
        s.setSchoolName(dto.getSchoolName());
        s.setAadharCard(dto.getAadharCard());
        s.setClassName(dto.getClassName());
        return s;
    }

    public StudentResponseDTO toDTO(Student s) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(s.getId());
        dto.setStudentName(s.getStudentName());
        dto.setDateOfBirth(s.getDateOfBirth());
        dto.setGender(s.getGender());
        dto.setParentName(s.getParentName());
        dto.setParentEducation(s.getParentEducation());
        dto.setMobileNo(s.getMobileNo());
        dto.setAddress(s.getAddress());
        dto.setSchoolName(s.getSchoolName());
        dto.setAadharCard(s.getAadharCard());
        dto.setClassName(s.getClassName());
        return dto;
    }
}
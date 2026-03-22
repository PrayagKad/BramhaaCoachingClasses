package com.brahmacoaching.studentmanagement.dto;

import java.time.LocalDate;
import java.util.UUID;

public class StudentResponseDTO {

    private UUID id;
    private String studentName;
    private LocalDate dateOfBirth;
    private String gender;
    private String parentName;
    private String parentEducation;
    private String mobileNo;
    private String address;
    private String schoolName;
    private String aadharCard;
    private String className;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public String getParentEducation() { return parentEducation; }
    public void setParentEducation(String parentEducation) { this.parentEducation = parentEducation; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getAadharCard() { return aadharCard; }
    public void setAadharCard(String aadharCard) { this.aadharCard = aadharCard; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
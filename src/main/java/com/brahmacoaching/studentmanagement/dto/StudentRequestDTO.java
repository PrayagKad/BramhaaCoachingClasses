package com.brahmacoaching.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class StudentRequestDTO {

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private String gender;
    private String parentName;
    private String parentEducation;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number — must be 10 digits starting with 6-9")
    private String mobileNo;

    private String address;
    private String schoolName;

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar card must be exactly 12 digits")
    private String aadharCard;

    // "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
    private String className;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentEducation() {
        return parentEducation;
    }

    public void setParentEducation(String parentEducation) {
        this.parentEducation = parentEducation;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
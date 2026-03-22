package com.brahmacoaching.studentmanagement.dto;

import java.time.LocalDate;
import java.util.UUID;

public class FeeReceiptResponseDTO {

    private UUID id;
    private String receiptNumber;
    private UUID studentId;
    private String studentName;
    private String className;
    private String schoolName;
    private String parentName;
    private String mobileNo;
    private String address;

    // Fee fields — all nullable
    private Double admissionFees;
    private Double totalFees;
    private Double remainingFees;
    private Double feeAmountPaid;

    private LocalDate paymentDate;
    private LocalDate totalFeesDatedOn;
    private String remarks;
    private String generatedBy;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getAdmissionFees() { return admissionFees; }
    public void setAdmissionFees(Double admissionFees) { this.admissionFees = admissionFees; }

    public Double getTotalFees() { return totalFees; }
    public void setTotalFees(Double totalFees) { this.totalFees = totalFees; }

    public Double getRemainingFees() { return remainingFees; }
    public void setRemainingFees(Double remainingFees) { this.remainingFees = remainingFees; }

    public Double getFeeAmountPaid() { return feeAmountPaid; }
    public void setFeeAmountPaid(Double feeAmountPaid) { this.feeAmountPaid = feeAmountPaid; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public LocalDate getTotalFeesDatedOn() { return totalFeesDatedOn; }
    public void setTotalFeesDatedOn(LocalDate totalFeesDatedOn) { this.totalFeesDatedOn = totalFeesDatedOn; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
}
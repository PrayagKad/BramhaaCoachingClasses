package com.brahmacoaching.studentmanagement.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class FeeReceiptRequestDTO {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    // All fee fields nullable — admin fills manually
    private Double admissionFees;
    private Double totalFees;
    private Double remainingFees;
    private Double feeAmountPaid;

    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    private LocalDate totalFeesDatedOn;
    private String remarks;

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public Double getAdmissionFees() {
        return admissionFees;
    }

    public void setAdmissionFees(Double admissionFees) {
        this.admissionFees = admissionFees;
    }

    public Double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(Double totalFees) {
        this.totalFees = totalFees;
    }

    public Double getRemainingFees() {
        return remainingFees;
    }

    public void setRemainingFees(Double remainingFees) {
        this.remainingFees = remainingFees;
    }

    public Double getFeeAmountPaid() {
        return feeAmountPaid;
    }

    public void setFeeAmountPaid(Double feeAmountPaid) {
        this.feeAmountPaid = feeAmountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getTotalFeesDatedOn() {
        return totalFeesDatedOn;
    }

    public void setTotalFeesDatedOn(LocalDate totalFeesDatedOn) {
        this.totalFeesDatedOn = totalFeesDatedOn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
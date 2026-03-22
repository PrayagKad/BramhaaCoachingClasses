package com.brahmacoaching.studentmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fee_receipts")
public class FeeReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Auto-generated: BCC-2026-0001
    @Column(name = "receipt_number", unique = true, nullable = false, length = 20)
    private String receiptNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // All fee fields are nullable — entered manually when generating receipt
    private Double admissionFees;   // Admission Fees
    private Double totalFees;       // Total Fees
    private Double remainingFees;   // Remaining Fees
    private Double feeAmountPaid;   // Amount paid in this receipt

    private LocalDate paymentDate;
    private LocalDate totalFeesDatedOn;  // Total Fees Dated On field

    private String remarks;

    @Column(name = "generated_by")
    private String generatedBy;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

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
package com.brahmacoaching.studentmanagement.service;

import com.brahmacoaching.studentmanagement.dto.FeeReceiptRequestDTO;
import com.brahmacoaching.studentmanagement.dto.FeeReceiptResponseDTO;
import com.brahmacoaching.studentmanagement.exception.StudentNotFoundException;
import com.brahmacoaching.studentmanagement.model.FeeReceipt;
import com.brahmacoaching.studentmanagement.model.Student;
import com.brahmacoaching.studentmanagement.repository.FeeReceiptRepository;
import com.brahmacoaching.studentmanagement.repository.StudentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeeReceiptService {

    private final FeeReceiptRepository feeReceiptRepository;
    private final StudentRepository studentRepository;

    public FeeReceiptService(FeeReceiptRepository feeReceiptRepository,
                             StudentRepository studentRepository) {
        this.feeReceiptRepository = feeReceiptRepository;
        this.studentRepository = studentRepository;
    }

    private String generateReceiptNumber() {
        int year = LocalDate.now().getYear();
        String prefix = "BCC-" + year + "-";
        return feeReceiptRepository.findLatestReceiptNumberByPrefix(prefix)
                .map(last -> {
                    int seq = Integer.parseInt(last.substring(last.lastIndexOf('-') + 1));
                    return prefix + String.format("%04d", seq + 1);
                })
                .orElse(prefix + "0001");
    }

    public FeeReceiptResponseDTO createReceipt(FeeReceiptRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + dto.getStudentId()));

        String adminUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        FeeReceipt receipt = new FeeReceipt();
        receipt.setReceiptNumber(generateReceiptNumber());
        receipt.setStudent(student);
        receipt.setAdmissionFees(dto.getAdmissionFees());
        receipt.setTotalFees(dto.getTotalFees());
        receipt.setRemainingFees(dto.getRemainingFees());
        receipt.setFeeAmountPaid(dto.getFeeAmountPaid());
        receipt.setPaymentDate(dto.getPaymentDate());
        receipt.setTotalFeesDatedOn(dto.getTotalFeesDatedOn());
        receipt.setRemarks(dto.getRemarks());
        receipt.setGeneratedBy(adminUsername);

        return toDTO(feeReceiptRepository.save(receipt));
    }

    public List<FeeReceiptResponseDTO> getReceiptsByStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId))
            throw new StudentNotFoundException("Student not found: " + studentId);
        return feeReceiptRepository.findByStudentIdOrderByPaymentDateDesc(studentId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FeeReceiptResponseDTO getReceiptById(UUID id) {
        FeeReceipt r = feeReceiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found: " + id));
        return toDTO(r);
    }

    private FeeReceiptResponseDTO toDTO(FeeReceipt r) {
        FeeReceiptResponseDTO dto = new FeeReceiptResponseDTO();
        dto.setId(r.getId());
        dto.setReceiptNumber(r.getReceiptNumber());
        dto.setStudentId(r.getStudent().getId());
        dto.setStudentName(r.getStudent().getStudentName());
        dto.setClassName(r.getStudent().getClassName());
        dto.setSchoolName(r.getStudent().getSchoolName());
        dto.setParentName(r.getStudent().getParentName());
        dto.setMobileNo(r.getStudent().getMobileNo());
        dto.setAddress(r.getStudent().getAddress());
        dto.setAdmissionFees(r.getAdmissionFees());
        dto.setTotalFees(r.getTotalFees());
        dto.setRemainingFees(r.getRemainingFees());
        dto.setFeeAmountPaid(r.getFeeAmountPaid());
        dto.setPaymentDate(r.getPaymentDate());
        dto.setTotalFeesDatedOn(r.getTotalFeesDatedOn());
        dto.setRemarks(r.getRemarks());
        dto.setGeneratedBy(r.getGeneratedBy());
        return dto;
    }
}
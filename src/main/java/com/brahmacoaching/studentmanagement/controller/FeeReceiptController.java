package com.brahmacoaching.studentmanagement.controller;

import com.brahmacoaching.studentmanagement.dto.FeeReceiptRequestDTO;
import com.brahmacoaching.studentmanagement.dto.FeeReceiptResponseDTO;
import com.brahmacoaching.studentmanagement.service.FeeReceiptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/receipts")
public class FeeReceiptController {

    private final FeeReceiptService feeReceiptService;

    public FeeReceiptController(FeeReceiptService feeReceiptService) {
        this.feeReceiptService = feeReceiptService;
    }

    // Only ADMIN can create receipts
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeeReceiptResponseDTO> create(
            @Valid @RequestBody FeeReceiptRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(feeReceiptService.createReceipt(dto));
    }

    // Get all receipts for a student (payment history)
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FeeReceiptResponseDTO>> getByStudent(
            @PathVariable UUID studentId) {
        return ResponseEntity.ok(feeReceiptService.getReceiptsByStudent(studentId));
    }

    // Get single receipt by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeeReceiptResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(feeReceiptService.getReceiptById(id));
    }
}
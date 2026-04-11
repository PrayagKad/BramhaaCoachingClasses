package com.brahmacoaching.studentmanagement.repository;

import com.brahmacoaching.studentmanagement.model.FeeReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeeReceiptRepository extends JpaRepository<FeeReceipt, UUID> {

    // All receipts for a specific student (payment history)
    List<FeeReceipt> findByStudentIdOrderByPaymentDateDesc(UUID studentId);

    // Check if receipt number already exists
    boolean existsByReceiptNumber(String receiptNumber);

    // Get the latest receipt number to compute next sequence
    @Query("SELECT f.receiptNumber FROM FeeReceipt f WHERE f.receiptNumber LIKE :prefix% ORDER BY f.receiptNumber DESC LIMIT 1")
    Optional<String> findLatestReceiptNumberByPrefix(String prefix);

    @Modifying
    @Query("DELETE FROM FeeReceipt f WHERE f.student.id = :studentId")
    void deleteByStudentId(@Param("studentId") UUID studentId);

}
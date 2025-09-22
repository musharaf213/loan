package com.example.loan.repository;

import com.example.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    //schaut ob customerId existiert anhand von customerName
    @Query("SELECT l.customerId FROM Loan l WHERE l.customerName = :customerName")
    String findCustomerIdByCustomerName(@Param("customerName") String customerName);

    // Total per customer
    @Query("SELECT SUM(l.amount) FROM Loan l WHERE l.customerId = :customerId")
    Double getTotalLoanAmount(@Param("customerId") String customerId);
}

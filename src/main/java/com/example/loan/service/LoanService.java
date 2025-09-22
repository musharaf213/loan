package com.example.loan.service;

import com.example.loan.entity.Loan;
import com.example.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Loan createLoan(Loan loanRequest) {
        // Validierung des Kreditbetrags
        if (loanRequest.getAmount() < 500 || loanRequest.getAmount() > 12000.50) {
            throw new IllegalArgumentException("Loan amount must be between 500 and 12000.50");
        }

        // Prüfen, ob dieser Kunde bereits existiert, damit dieselbe customerId vergeben wird
        String existingCustomerId = loanRepository.findCustomerIdByCustomerName(loanRequest.getCustomerName());

        if (existingCustomerId != null) {
            loanRequest.setCustomerId(existingCustomerId);
        }

        return loanRepository.save(loanRequest);
    }

    public Double getTotalLoanAmountByCustomer(String customerId) {
        return loanRepository.getTotalLoanAmount(customerId);
    }

    //Zusatz

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan updateLoan(Long id, Loan updatedLoan) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (existingLoan.isPresent()){
            Loan loan = existingLoan.get();
            loan.setAmount(updatedLoan.getAmount());
            loan.setCustomerName(updatedLoan.getCustomerName());
            loan.setCustomerId(updatedLoan.getCustomerId());
            return loanRepository.save(loan);
        } else{
            throw new RuntimeException("Loan not found");
        }
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }


}
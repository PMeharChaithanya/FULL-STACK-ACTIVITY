package com.example.library.repository;

import com.example.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Active loans = loans not yet returned
    long countByMemberIdAndReturnDateIsNull(Long memberId);

    List<Loan> findByMemberId(Long memberId);
}

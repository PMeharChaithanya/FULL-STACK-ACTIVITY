package com.example.library.service;

import com.example.library.exception.LibraryException;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.Member;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LibraryService {

    // Business rule constants
    public static final int MAX_BOOKS_PER_MEMBER = 3;
    public static final int LOAN_PERIOD_DAYS = 14;
    public static final double FINE_PER_DAY = 10.0;

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    // Constructor injection - Spring automatically injects the repository beans
    public LibraryService(BookRepository bookRepository,
                          MemberRepository memberRepository,
                          LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    // ---------- Books ----------

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new LibraryException("Book not found with id " + id));
    }

    public Book addBook(Book book) {
        if (book.getTotalCopies() <= 0) {
            throw new LibraryException("Total copies must be greater than 0");
        }
        book.setAvailableCopies(book.getTotalCopies());
        return bookRepository.save(book);
    }

    // ---------- Members ----------

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new LibraryException("Member not found with id " + id));
    }

    // ---------- Loans ----------

    public List<Loan> getLoansForMember(Long memberId) {
        getMemberById(memberId);
        return loanRepository.findByMemberId(memberId);
    }

    /**
     * Business Rule 1: A book can be issued only if at least one copy is available.
     * Business Rule 2: A member cannot hold more than MAX_BOOKS_PER_MEMBER books at a time.
     */
    @Transactional
    public Loan borrowBook(Long memberId, Long bookId) {
        Member member = getMemberById(memberId);
        Book book = getBookById(bookId);

        if (book.getAvailableCopies() <= 0) {
            throw new LibraryException("Book '" + book.getTitle() + "' is not available");
        }

        long activeLoans = loanRepository.countByMemberIdAndReturnDateIsNull(memberId);
        if (activeLoans >= MAX_BOOKS_PER_MEMBER) {
            throw new LibraryException("Member '" + member.getName()
                    + "' has already borrowed the maximum of " + MAX_BOOKS_PER_MEMBER + " books");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        LocalDate today = LocalDate.now();
        Loan loan = new Loan(book, member, today, today.plusDays(LOAN_PERIOD_DAYS));
        return loanRepository.save(loan);
    }

    /**
     * Business Rule 3: A fine of FINE_PER_DAY is charged for each day a book is returned after its due date.
     */
    @Transactional
    public Loan returnBook(Long loanId, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LibraryException("Loan not found with id " + loanId));

        if (loan.isReturned()) {
            throw new LibraryException("Loan " + loanId + " has already been returned");
        }

        loan.setReturnDate(returnDate);
        loan.setFine(calculateFine(loan.getDueDate(), returnDate));

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate > 0 ? daysLate * FINE_PER_DAY : 0.0;
    }
}

package com.example.library.service;

import com.example.library.exception.LibraryException;
import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // each test is rolled back so tests do not affect each other
class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Test
    void borrowBook_reducesAvailableCopies() {
        Book book = libraryService.addBook(new Book("Test Book", "Author", "111", 2));
        Member member = libraryService.addMember(new Member("Test", "t@example.com", "1"));

        Loan loan = libraryService.borrowBook(member.getId(), book.getId());

        assertNotNull(loan.getId());
        assertEquals(LocalDate.now().plusDays(14), loan.getDueDate());
        assertEquals(1, libraryService.getBookById(book.getId()).getAvailableCopies());
    }

    @Test
    void borrowBook_failsWhenNoCopiesAvailable() {
        Book book = libraryService.addBook(new Book("Single Copy", "Author", "222", 1));
        Member m1 = libraryService.addMember(new Member("A", "a@example.com", "1"));
        Member m2 = libraryService.addMember(new Member("B", "b@example.com", "2"));

        libraryService.borrowBook(m1.getId(), book.getId());

        LibraryException ex = assertThrows(LibraryException.class,
                () -> libraryService.borrowBook(m2.getId(), book.getId()));
        assertEquals("Book 'Single Copy' is not available", ex.getMessage());
    }

    @Test
    void borrowBook_failsWhenMemberExceedsLimit() {
        Member member = libraryService.addMember(new Member("C", "c@example.com", "3"));
        for (int i = 0; i < 3; i++) {
            Book b = libraryService.addBook(new Book("Book " + i, "Author", "33" + i, 1));
            libraryService.borrowBook(member.getId(), b.getId());
        }
        Book fourth = libraryService.addBook(new Book("Book 4", "Author", "334", 1));

        assertThrows(LibraryException.class,
                () -> libraryService.borrowBook(member.getId(), fourth.getId()));
    }

    @Test
    void calculateFine_chargesTenPerLateDay() {
        LocalDate due = LocalDate.of(2026, 10, 1);

        assertEquals(0.0, libraryService.calculateFine(due, LocalDate.of(2026, 9, 28)));
        assertEquals(0.0, libraryService.calculateFine(due, due));
        assertEquals(50.0, libraryService.calculateFine(due, LocalDate.of(2026, 10, 6)));
    }
}

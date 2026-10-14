package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.Member;
import com.example.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to the Library Management System! Reg No: RA2311003011556";
    }

    // ---------- Books ----------

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/available")
    public List<Book> getAvailableBooks() {
        return libraryService.getAvailableBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return libraryService.getBookById(id);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return libraryService.addBook(book);
    }

    // ---------- Members ----------

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return libraryService.getAllMembers();
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public Member addMember(@RequestBody Member member) {
        return libraryService.addMember(member);
    }

    @GetMapping("/members/{id}/loans")
    public List<Loan> getLoansForMember(@PathVariable Long id) {
        return libraryService.getLoansForMember(id);
    }

    // ---------- Loans ----------

    @PostMapping("/loans/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public Loan borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return libraryService.borrowBook(memberId, bookId);
    }

    @PutMapping("/loans/{loanId}/return")
    public Loan returnBook(@PathVariable Long loanId,
                           @RequestParam(required = false) LocalDate returnDate) {
        return libraryService.returnBook(loanId, returnDate != null ? returnDate : LocalDate.now());
    }
}

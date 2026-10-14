package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.Member;
import com.example.library.repository.BookRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Loads sample data into the auto-configured H2 database at startup
@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public DataLoader(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) {
        bookRepository.save(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2));
        bookRepository.save(new Book("Effective Java", "Joshua Bloch", "9780134685991", 3));
        bookRepository.save(new Book("Spring in Action", "Craig Walls", "9781617294945", 1));
        bookRepository.save(new Book("Head First Java", "Kathy Sierra", "9780596009205", 4));

        memberRepository.save(new Member("Asha", "asha@example.com", "9876543210"));
        memberRepository.save(new Member("Ravi", "ravi@example.com", "9876501234"));
    }
}

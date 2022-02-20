package com.amberlight.test.web.apps.kafka.books.core.service.app.service;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Genre;
import com.amberlight.test.web.apps.kafka.books.core.domain.repository.BookAuthorRepository;
import com.amberlight.test.web.apps.kafka.books.core.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("bookService")
public class BookServiceImpl implements BookService {

    // todo clean redundant imports

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Book createBook(BookDto bookDto) {
        // todo add validation, and maybe add dto-to-entity conversion
        Book newBook = new Book();
        newBook.setName(bookDto.getName());
        newBook.setDescription(bookDto.getDescription());
        newBook.setPublished(bookDto.getPublished());
        Genre genreForNewBook = new Genre();
        genreForNewBook.setId(bookDto.getGenre().getId());
        newBook.setGenre(genreForNewBook);
        Book savedBook = bookRepository.saveAndFlush(newBook);
        Set<Long> existingAuthorsIds = bookDto.getAuthors().stream()
                .map(AuthorDto::getId).collect(Collectors.toSet());
        List<Author> existingAuthors = bookAuthorRepository.findAllById(existingAuthorsIds);
        if (existingAuthors.isEmpty()) {
            throw new IllegalStateException("No authors were found by ids provided");
        }
        if (existingAuthors.size() != existingAuthorsIds.size()) {
            throw new IllegalStateException("Not all requested authors found by ids provided");
        }
        existingAuthors.forEach(author -> author.getBooks().add(savedBook));
        bookAuthorRepository.saveAllAndFlush(existingAuthors);
        return savedBook;
    }

    /**
     * {@inheritDoc}
     */
//    @Transactional(readOnly = true)
    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


}

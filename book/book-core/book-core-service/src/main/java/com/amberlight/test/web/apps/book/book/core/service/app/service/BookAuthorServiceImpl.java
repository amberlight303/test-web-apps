package com.amberlight.test.web.apps.book.book.core.service.app.service;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.book.book.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.book.book.core.domain.repository.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookAuthorService")
public class BookAuthorServiceImpl implements BookAuthorService {

    private BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Author createAuthor(AuthorDto authorDto) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(authorDto.getFirstName());
        newAuthor.setLastName(authorDto.getLastName());
        return bookAuthorRepository.saveAndFlush(newAuthor);
    }

    /**
     * {@inheritDoc}
     */
//    @Transactional(readOnly = true)
    @Override
    public Author findAuthor(Long id) {
        // todo maybe to throw an exception for "book author not found"
        return bookAuthorRepository.findById(id).orElse(null);
    }

}

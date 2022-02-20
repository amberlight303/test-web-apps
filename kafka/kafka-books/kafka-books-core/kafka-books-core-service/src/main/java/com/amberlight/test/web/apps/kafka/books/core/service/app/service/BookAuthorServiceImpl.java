package com.amberlight.test.web.apps.kafka.books.core.service.app.service;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.kafka.books.core.domain.repository.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookAuthorService")
public class BookAuthorServiceImpl implements BookAuthorService {

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

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

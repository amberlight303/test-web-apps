package com.amberlight.test.web.apps.kafka.books.core.service.app.service.command;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookAuthorCommandService")
public class BookAuthorCommandServiceImpl implements BookAuthorCommandService {

    @Autowired
    private BookAuthorService bookAuthorService;

    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateBookAuthorDocument process(CreateBookAuthorCommand command) {
        Author createdAuthor = bookAuthorService.createAuthor(command.getAuthor());

        System.out.println("createdAuthor is: " + createdAuthor);

        createdAuthor = bookAuthorService.findAuthor(createdAuthor.getId());

        System.out.println("createdAuthor after CREATED (retrieved one it is): " + createdAuthor);

        AuthorDto createdAuthorDto = conversionService.convert(createdAuthor, AuthorDto.class);
        return CreateBookAuthorDocument.builder().author(createdAuthorDto).build();
    }

}

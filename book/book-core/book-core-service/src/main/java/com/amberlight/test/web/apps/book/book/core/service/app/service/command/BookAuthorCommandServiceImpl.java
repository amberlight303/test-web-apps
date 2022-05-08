package com.amberlight.test.web.apps.book.book.core.service.app.service.command;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.book.book.core.service.app.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookAuthorCommandService")
public class BookAuthorCommandServiceImpl implements BookAuthorCommandService {

    private BookAuthorService bookAuthorService;

    private ConversionService conversionService;

    public BookAuthorCommandServiceImpl(BookAuthorService bookAuthorService, ConversionService conversionService) {
        this.bookAuthorService = bookAuthorService;
        this.conversionService = conversionService;
    }

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

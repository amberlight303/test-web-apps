package com.amberlight.test.web.apps.book.book.core.service.app.handler;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.dto.book.BookDto;
import com.amberlight.test.web.apps.book.book.core.service.util.StreamUtil;
import com.amberlight.test.web.apps.domain.validation.ArgumentValidations;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import org.springframework.stereotype.Component;

@Component("commandValidator")
public class CommandValidatorImpl implements CommandValidator {

    @Override
    public void validateCommand(CreateBookCommand command) {
        ArgumentValidations.notNull("createBookCommand", command);
        ArgumentValidations.notNull("createBookCommand.book", command.getBook());

        BookDto book = command.getBook();
        ArgumentValidations.validatorFor("createBookCommand.book.name",
                book.getName()).notEmpty().length(1, 255).validate();
        ArgumentValidations.validatorFor("createBookCommand.book.description",
                book.getDescription()).notEmpty().length(1, 255).validate();
        ArgumentValidations.notNull("createBookCommand.book.published", book.getPublished());

        ArgumentValidations.notNull("createBookCommand.book.genre", book.getGenre());
        ArgumentValidations.notNull("createBookCommand.book.genre.id", book.getGenre().getId());

        ArgumentValidations.positiveAmount("createBookCommand.book.price", book.getPrice());

        ArgumentValidations.notEmptyCollection("createBookCommand.book.authors", book.getAuthors());

        // todo add validation method for the max valid size of a collection and map
        // todo think about fields that should not be sent (memory consumption breach, etc)

        book.getAuthors().forEach(StreamUtil.withCounter((i, author) -> {
            ArgumentValidations.notNull(String.format("createBookCommand.book.authors[%d]", i), author);
            ArgumentValidations.notNull(String.format("createBookCommand.book.authors[%d].id", i), author.getId());
        }));

    }

    @Override
    public void validateCommand(CreateBookAuthorCommand command) {
        ArgumentValidations.notNull("createBookAuthorCommand", command);
        ArgumentValidations.notNull("createBookAuthorCommand.author", command.getAuthor());

        AuthorDto author = command.getAuthor();
        ArgumentValidations.validatorFor("createBookAuthorCommand.author.firstName",
                author.getFirstName()).notEmpty().length(1, 255).validate();
        ArgumentValidations.validatorFor("createBookAuthorCommand.author.lastName",
                author.getLastName()).notEmpty().length(1, 255).validate();
    }



}

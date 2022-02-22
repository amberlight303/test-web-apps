package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.domain.validation.ArgumentValidations;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.amberlight.test.web.apps.kafka.books.core.service.util.StreamUtil.withCounter;

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

        ArgumentValidations.notNull("createBookCommand.genre", book.getGenre());
        ArgumentValidations.notNull("createBookCommand.genre.id", book.getGenre().getId());

        ArgumentValidations.notEmptyCollection("createBookCommand.book.authors", book.getAuthors());

        // todo add validation method for the max valid size of a collection and map
        // todo think about fields that should not be sent (memory consumption breach, etc)

        book.getAuthors().forEach(withCounter((i, author) -> {
            ArgumentValidations.notNull(String.format("createBookCommand.authors[%d]", i), author);
            ArgumentValidations.notNull(String.format("createBookCommand.authors[%d].id", i), author.getId());
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

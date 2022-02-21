package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.command.BookAuthorCommandService;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.command.BookCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commandHandler")
public class CommandHandlerImpl implements CommandHandler {

    @Autowired
    private BookCommandService bookCommandService;

    @Autowired
    private BookAuthorCommandService bookAuthorCommandService;

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateBookDocument process(CreateBookCommand command) {

        // todo validation happens here through CommandValidator

        return bookCommandService.process(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateBookAuthorDocument process(CreateBookAuthorCommand command) {

        // todo validation happens here through CommandValidator

        return bookAuthorCommandService.process(command);
    }
}
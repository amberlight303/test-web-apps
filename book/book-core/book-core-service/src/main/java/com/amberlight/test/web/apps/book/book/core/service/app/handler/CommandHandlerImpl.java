package com.amberlight.test.web.apps.book.book.core.service.app.handler;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.book.book.core.service.app.service.command.BookAuthorCommandService;
import com.amberlight.test.web.apps.book.book.core.service.app.service.command.BookCommandService;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commandHandler")
public class CommandHandlerImpl implements CommandHandler {

    @Autowired
    private BookCommandService bookCommandService;

    @Autowired
    private BookAuthorCommandService bookAuthorCommandService;

    @Autowired
    private CommandValidator commandValidator;

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateBookDocument process(CreateBookCommand command) {
        commandValidator.validateCommand(command);
        return bookCommandService.process(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateBookAuthorDocument process(CreateBookAuthorCommand command) {
        commandValidator.validateCommand(command);
        return bookAuthorCommandService.process(command);
    }
}

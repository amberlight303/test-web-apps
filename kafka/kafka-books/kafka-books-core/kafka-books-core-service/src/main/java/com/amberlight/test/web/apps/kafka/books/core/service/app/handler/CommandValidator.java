package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookCommand;

public interface CommandValidator {

    void validateCommand(CreateBookCommand command);

    void validateCommand(CreateBookAuthorCommand command);

}

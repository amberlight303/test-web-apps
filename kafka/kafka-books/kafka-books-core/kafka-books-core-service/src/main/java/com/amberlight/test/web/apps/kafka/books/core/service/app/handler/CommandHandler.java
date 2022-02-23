package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookDocument;

import javax.validation.constraints.NotNull;

public interface CommandHandler {

    CreateBookDocument process(@NotNull CreateBookCommand command);

    CreateBookAuthorDocument process(@NotNull CreateBookAuthorCommand command);

}

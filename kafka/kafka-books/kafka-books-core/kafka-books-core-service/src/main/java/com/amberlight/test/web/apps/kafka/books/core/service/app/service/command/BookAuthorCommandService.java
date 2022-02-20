package com.amberlight.test.web.apps.kafka.books.core.service.app.service.command;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookDocument;

import javax.validation.constraints.NotNull;

public interface BookAuthorCommandService {

    CreateBookAuthorDocument process(@NotNull CreateBookAuthorCommand command);

}

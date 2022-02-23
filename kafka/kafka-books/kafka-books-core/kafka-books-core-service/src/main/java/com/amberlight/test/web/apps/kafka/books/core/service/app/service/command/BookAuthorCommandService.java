package com.amberlight.test.web.apps.kafka.books.core.service.app.service.command;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookAuthorDocument;

import javax.validation.constraints.NotNull;

public interface BookAuthorCommandService {

    CreateBookAuthorDocument process(@NotNull CreateBookAuthorCommand command);

}

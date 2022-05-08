package com.amberlight.test.web.apps.book.book.core.service.app.handler;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookAuthorDocument;

import javax.validation.constraints.NotNull;

public interface CommandHandler {

    CreateBookDocument process(CreateBookCommand command);

    CreateBookAuthorDocument process(CreateBookAuthorCommand command);

}

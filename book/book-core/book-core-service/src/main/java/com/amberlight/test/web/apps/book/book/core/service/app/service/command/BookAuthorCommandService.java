package com.amberlight.test.web.apps.book.book.core.service.app.service.command;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookAuthorDocument;

import javax.validation.constraints.NotNull;

public interface BookAuthorCommandService {

    CreateBookAuthorDocument process(CreateBookAuthorCommand command);

}

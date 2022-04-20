package com.amberlight.test.web.apps.book.book.core.service.app.service.command;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookDocument;

import javax.validation.constraints.NotNull;

public interface BookCommandService {

    CreateBookDocument process(@NotNull CreateBookCommand command);

}

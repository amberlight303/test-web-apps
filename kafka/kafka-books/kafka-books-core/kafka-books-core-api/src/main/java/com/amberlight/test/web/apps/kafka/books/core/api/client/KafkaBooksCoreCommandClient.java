package com.amberlight.test.web.apps.kafka.books.core.api.client;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.CreateBookDocument;
import feign.RequestLine;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface KafkaBooksCoreCommandClient {

    @RequestLine("POST /book/create")
    CreateBookDocument process(@NotNull CreateBookCommand command);

    @RequestLine("POST /book/author/create")
    CreateBookAuthorDocument process(@NotNull CreateBookAuthorCommand command);

}

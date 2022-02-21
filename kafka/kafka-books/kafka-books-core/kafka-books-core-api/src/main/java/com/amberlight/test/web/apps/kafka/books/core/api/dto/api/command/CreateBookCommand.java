package com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookCommand {

    private BookDto book;

}
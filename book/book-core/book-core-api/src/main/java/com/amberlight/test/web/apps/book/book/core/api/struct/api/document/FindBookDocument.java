package com.amberlight.test.web.apps.book.book.core.api.struct.api.document;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.book.BookDto;
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
public class FindBookDocument {

    private BookDto book;

}

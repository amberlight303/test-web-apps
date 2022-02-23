package com.amberlight.test.web.apps.kafka.books.core.api.struct.dto.author;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.dto.book.BookDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<BookDto> books;
}

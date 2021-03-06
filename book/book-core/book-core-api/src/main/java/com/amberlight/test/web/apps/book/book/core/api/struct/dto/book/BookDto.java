package com.amberlight.test.web.apps.book.book.core.api.struct.dto.book;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private GenreDto genre;
    private BigDecimal price;
    private Set<AuthorDto> authors;
    private LocalDateTime published;
}

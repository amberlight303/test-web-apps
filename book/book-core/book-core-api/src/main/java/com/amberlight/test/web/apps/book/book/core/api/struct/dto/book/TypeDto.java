package com.amberlight.test.web.apps.book.book.core.api.struct.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeDto implements Serializable {
    private Long id;
    private String name;
    private Set<GenreDto> genres;
    private List<BookDto> books;
}

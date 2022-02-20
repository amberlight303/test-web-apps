package com.amberlight.test.web.apps.kafka.books.core.domain.converter.book;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.GenreDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Genre;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreToGenreDto implements Converter<Genre, GenreDto> {

    @Autowired
    @Lazy
    private TypeToTypeDto typeToTypeDto;

    @Autowired
    @Lazy
    private BookToBookDto bookToBookDto;

    @Override
    public GenreDto convert(Genre source) {
        GenreDto.GenreDtoBuilder builder = GenreDto.builder();
        builder.id(source.getId()).name(source.getName());
        if (Hibernate.isInitialized(source.getType()) && source.getType() != null) {
            builder.type(typeToTypeDto.convert(source.getType()));
        }
        if (Hibernate.isInitialized(source.getBooks()) && source.getBooks() != null) {
            builder.books(source.getBooks().stream()
                    .map(book -> bookToBookDto.convert(book)).collect(Collectors.toList()));
        }
        return builder.build();
    }

}

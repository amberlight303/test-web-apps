package com.amberlight.test.web.apps.book.book.core.domain.converter.book;

import com.amberlight.test.web.apps.book.book.core.domain.entity.book.Type;
import com.amberlight.test.web.apps.book.book.core.api.struct.dto.book.TypeDto;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TypeToTypeDto implements Converter<Type, TypeDto> {

    @Autowired
    @Lazy
    private GenreToGenreDto genreToGenreDto;

    @Override
    public TypeDto convert(Type source) {
        TypeDto.TypeDtoBuilder builder = TypeDto.builder();
        builder.id(source.getId()).name(source.getName());
        if (Hibernate.isInitialized(source.getGenres()) && source.getGenres() != null) {
            builder.genres(source.getGenres().stream()
                    .map(genre -> genreToGenreDto.convert(genre)).collect(Collectors.toSet()));
        }
        return builder.build();
    }

}

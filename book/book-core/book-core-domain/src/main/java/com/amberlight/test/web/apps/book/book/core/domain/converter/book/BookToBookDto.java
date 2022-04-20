package com.amberlight.test.web.apps.book.book.core.domain.converter.book;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.book.BookDto;
import com.amberlight.test.web.apps.book.book.core.domain.converter.author.AuthorToAuthorDto;
import com.amberlight.test.web.apps.book.book.core.domain.entity.book.Book;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookToBookDto implements Converter<Book, BookDto> {

    @Autowired
    @Lazy
    private AuthorToAuthorDto authorToAuthorDto;

    @Autowired
    @Lazy
    private GenreToGenreDto genreToGenreDto;

    @Override
    public BookDto convert(Book source) {
        BookDto.BookDtoBuilder builder = BookDto.builder();
        builder.id(source.getId()).name(source.getName()).description(source.getDescription())
                .published(source.getPublished()).price(source.getPrice());
        if (Hibernate.isInitialized(source.getGenre()) && source.getGenre() != null) {
            builder.genre(genreToGenreDto.convert(source.getGenre()));
        }
        if (Hibernate.isInitialized(source.getAuthors()) && source.getAuthors() != null) {
            builder.authors(source.getAuthors().stream()
                    .map(author -> authorToAuthorDto.convert(author)).collect(Collectors.toSet()));
        }
        return builder.build();
    }

}

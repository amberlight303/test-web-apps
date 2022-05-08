package com.amberlight.test.web.apps.book.book.core.domain.converter.author;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.book.book.core.domain.converter.book.BookToBookDto;
import com.amberlight.test.web.apps.book.book.core.domain.entity.author.Author;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorToAuthorDto implements Converter<Author, AuthorDto> {

    private BookToBookDto bookToBookDto;

    public AuthorToAuthorDto(@Lazy BookToBookDto bookToBookDto) {
        this.bookToBookDto = bookToBookDto;
    }

    @Override
    public AuthorDto convert(Author source) {
        AuthorDto.AuthorDtoBuilder builder = AuthorDto.builder();
        builder.id(source.getId()).firstName(source.getFirstName()).lastName(source.getLastName());
        if (Hibernate.isInitialized(source.getBooks()) && source.getBooks() != null) {
            builder.books(source.getBooks().stream().map(book -> bookToBookDto.convert(book)).collect(Collectors.toSet()));
        }
        return builder.build();
    }

}

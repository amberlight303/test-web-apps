package com.amberlight.test.web.apps.book.book.core.service.app.service;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.book.book.core.domain.entity.author.Author;

public interface BookAuthorService {

    Author createAuthor(AuthorDto authorDto);

    Author findAuthor(Long id);

}

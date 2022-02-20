package com.amberlight.test.web.apps.kafka.books.core.service.app.service;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;

public interface BookAuthorService {

    Author createAuthor(AuthorDto authorDto);

    Author findAuthor(Long id);

}

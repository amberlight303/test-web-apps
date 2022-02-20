package com.amberlight.test.web.apps.kafka.books.core.service.app.service;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;

public interface BookService {

    Book createBook(BookDto bookDto);

    Book findBook(Long id);

}

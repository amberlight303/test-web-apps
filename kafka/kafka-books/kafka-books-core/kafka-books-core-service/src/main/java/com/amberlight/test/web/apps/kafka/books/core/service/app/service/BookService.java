package com.amberlight.test.web.apps.kafka.books.core.service.app.service;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.dto.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;

public interface BookService {

    Book createBook(BookDto bookDto);

    Book findBook(Long id);

}

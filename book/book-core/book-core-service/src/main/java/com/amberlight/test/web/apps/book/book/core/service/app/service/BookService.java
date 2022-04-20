package com.amberlight.test.web.apps.book.book.core.service.app.service;

import com.amberlight.test.web.apps.book.book.core.api.struct.dto.book.BookDto;
import com.amberlight.test.web.apps.book.book.core.domain.entity.book.Book;

public interface BookService {

    Book createBook(BookDto bookDto);

    Book findBook(Long id);

}

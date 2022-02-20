package com.amberlight.test.web.apps.kafka.books.core.service.app.service.query;


import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookQueryService")
public class BookQueryServiceImpl implements BookQueryService {

    @Autowired
    private BookService bookService;

    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookDocument process(FindBookQuery query) {
        Book book = bookService.findBook(query.getId());
        BookDto bookDto = conversionService.convert(book, BookDto.class);
        return FindBookDocument.builder().book(bookDto).build();
    }

}

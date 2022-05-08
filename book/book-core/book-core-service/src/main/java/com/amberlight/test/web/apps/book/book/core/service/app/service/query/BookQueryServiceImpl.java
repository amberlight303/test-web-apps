package com.amberlight.test.web.apps.book.book.core.service.app.service.query;


import com.amberlight.test.web.apps.book.book.core.api.struct.dto.book.BookDto;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;
import com.amberlight.test.web.apps.book.book.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.book.book.core.service.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookQueryService")
public class BookQueryServiceImpl implements BookQueryService {

    private BookService bookService;

    private ConversionService conversionService;

    public BookQueryServiceImpl(BookService bookService, ConversionService conversionService) {
        this.bookService = bookService;
        this.conversionService = conversionService;
    }

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

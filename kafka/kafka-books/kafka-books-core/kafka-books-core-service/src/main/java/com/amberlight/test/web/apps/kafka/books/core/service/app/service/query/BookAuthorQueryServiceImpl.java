package com.amberlight.test.web.apps.kafka.books.core.service.app.service.query;


import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.model.book.BookDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookAuthorService;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookAuthorQueryService")
public class BookAuthorQueryServiceImpl implements BookAuthorQueryService {

    @Autowired
    private BookAuthorService bookAuthorService;

    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookAuthorDocument process(FindBookAuthorQuery query) {
        Author author = bookAuthorService.findAuthor(query.getId());
        return FindBookAuthorDocument.builder().author(conversionService.convert(author, AuthorDto.class)).build();
    }

}

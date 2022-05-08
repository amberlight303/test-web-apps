package com.amberlight.test.web.apps.book.book.core.service.app.service.query;


import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.book.book.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.book.book.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.book.book.core.service.app.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service("bookAuthorQueryService")
public class BookAuthorQueryServiceImpl implements BookAuthorQueryService {

    private BookAuthorService bookAuthorService;

    private ConversionService conversionService;

    public BookAuthorQueryServiceImpl(BookAuthorService bookAuthorService, ConversionService conversionService) {
        this.bookAuthorService = bookAuthorService;
        this.conversionService = conversionService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookAuthorDocument process(FindBookAuthorQuery query) {
        Author author = bookAuthorService.findAuthor(query.getId());
        return FindBookAuthorDocument.builder().author(conversionService.convert(author, AuthorDto.class)).build();
    }

}

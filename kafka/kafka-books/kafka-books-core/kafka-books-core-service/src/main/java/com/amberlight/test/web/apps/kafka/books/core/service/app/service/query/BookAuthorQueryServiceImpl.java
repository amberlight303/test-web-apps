package com.amberlight.test.web.apps.kafka.books.core.service.app.service.query;


import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.dto.author.AuthorDto;
import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.BookAuthorService;
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

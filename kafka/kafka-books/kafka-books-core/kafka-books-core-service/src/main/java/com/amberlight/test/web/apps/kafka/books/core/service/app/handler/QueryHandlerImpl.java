package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.query.BookAuthorQueryService;
import com.amberlight.test.web.apps.kafka.books.core.service.app.service.query.BookQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("queryHandler")
public class QueryHandlerImpl implements QueryHandler {

    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private BookAuthorQueryService bookAuthorQueryService;

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookDocument process(FindBookQuery query) {

        // todo validation of the query should happen here via StructValidator

        return bookQueryService.process(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookAuthorDocument process(FindBookAuthorQuery query) {

        // todo validation of the query should happen here via StructValidator

        return bookAuthorQueryService.process(query);
    }
}

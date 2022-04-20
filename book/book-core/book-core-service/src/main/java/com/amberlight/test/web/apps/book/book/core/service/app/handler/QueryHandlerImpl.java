package com.amberlight.test.web.apps.book.book.core.service.app.handler;

import com.amberlight.test.web.apps.book.book.core.service.app.service.query.BookAuthorQueryService;
import com.amberlight.test.web.apps.book.book.core.service.app.service.query.BookQueryService;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("queryHandler")
public class QueryHandlerImpl implements QueryHandler {

    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private BookAuthorQueryService bookAuthorQueryService;

    @Autowired
    private QueryValidator queryValidator;

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookDocument process(FindBookQuery query) {
        queryValidator.validateQuery(query);
        return bookQueryService.process(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FindBookAuthorDocument process(FindBookAuthorQuery query) {
        queryValidator.validateQuery(query);
        return bookAuthorQueryService.process(query);
    }
}

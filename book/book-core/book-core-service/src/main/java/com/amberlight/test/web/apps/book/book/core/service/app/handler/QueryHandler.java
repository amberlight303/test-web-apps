package com.amberlight.test.web.apps.book.book.core.service.app.handler;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;

import javax.validation.constraints.NotNull;

public interface QueryHandler {

    FindBookDocument process(FindBookQuery query);

    FindBookAuthorDocument process(FindBookAuthorQuery query);

}

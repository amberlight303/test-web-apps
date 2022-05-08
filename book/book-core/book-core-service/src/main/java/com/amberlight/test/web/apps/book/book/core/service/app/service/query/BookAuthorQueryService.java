package com.amberlight.test.web.apps.book.book.core.service.app.service.query;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookAuthorQuery;

import javax.validation.constraints.NotNull;

public interface BookAuthorQueryService {

    FindBookAuthorDocument process(FindBookAuthorQuery query);

}

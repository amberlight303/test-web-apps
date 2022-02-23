package com.amberlight.test.web.apps.kafka.books.core.service.app.service.query;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.query.FindBookAuthorQuery;

import javax.validation.constraints.NotNull;

public interface BookAuthorQueryService {

    FindBookAuthorDocument process(@NotNull FindBookAuthorQuery query);

}

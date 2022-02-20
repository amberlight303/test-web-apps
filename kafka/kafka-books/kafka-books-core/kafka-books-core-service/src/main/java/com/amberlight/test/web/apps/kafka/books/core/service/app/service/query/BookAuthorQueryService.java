package com.amberlight.test.web.apps.kafka.books.core.service.app.service.query;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;

import javax.validation.constraints.NotNull;

public interface BookAuthorQueryService {

    FindBookAuthorDocument process(@NotNull FindBookAuthorQuery query);

}

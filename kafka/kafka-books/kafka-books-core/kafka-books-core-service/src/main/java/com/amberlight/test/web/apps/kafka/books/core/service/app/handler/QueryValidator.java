package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;

public interface QueryValidator {

    void validateQuery(FindBookQuery command);

    void validateQuery(FindBookAuthorQuery command);

}

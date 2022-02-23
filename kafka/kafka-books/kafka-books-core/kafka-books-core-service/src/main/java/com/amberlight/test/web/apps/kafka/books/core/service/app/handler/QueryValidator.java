package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.struct.api.query.FindBookQuery;

public interface QueryValidator {

    void validateQuery(FindBookQuery query);

    void validateQuery(FindBookAuthorQuery query);

}

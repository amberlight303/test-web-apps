package com.amberlight.test.web.apps.kafka.books.core.service.app.handler;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;
import org.springframework.stereotype.Component;

@Component("queryValidator")
public class QueryValidatorImpl implements QueryValidator {

    @Override
    public void validateQuery(FindBookQuery command) {

    }

    @Override
    public void validateQuery(FindBookAuthorQuery command) {

    }

}

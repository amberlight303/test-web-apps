package com.amberlight.test.web.apps.book.book.core.service.app.handler;

import com.amberlight.test.web.apps.domain.validation.ArgumentValidations;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;
import org.springframework.stereotype.Component;

@Component("queryValidator")
public class QueryValidatorImpl implements QueryValidator {

    @Override
    public void validateQuery(FindBookQuery query) {
        ArgumentValidations.notNull("findBookQuery", query);
        ArgumentValidations.notNull("findBookQuery.id", query.getId());
    }

    @Override
    public void validateQuery(FindBookAuthorQuery query) {
        ArgumentValidations.notNull("findBookAuthorQuery", query);
        ArgumentValidations.notNull("findBookAuthorQuery.id", query.getId());
    }

}

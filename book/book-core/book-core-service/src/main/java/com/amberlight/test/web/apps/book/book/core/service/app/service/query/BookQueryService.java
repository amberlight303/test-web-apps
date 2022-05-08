package com.amberlight.test.web.apps.book.book.core.service.app.service.query;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;

import javax.validation.constraints.NotNull;

public interface BookQueryService {

    FindBookDocument process(FindBookQuery query);

}

package com.amberlight.test.web.apps.book.book.core.api.client;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;
import feign.RequestLine;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface BookCoreQueryClient {

    @RequestLine("POST /book/find-one")
    FindBookDocument process(@NotNull FindBookQuery query);

    @RequestLine("POST /book/author/find-one")
    FindBookAuthorDocument process(@NotNull FindBookAuthorQuery query);

}

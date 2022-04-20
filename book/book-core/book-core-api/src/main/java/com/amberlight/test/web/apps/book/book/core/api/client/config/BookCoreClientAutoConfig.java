package com.amberlight.test.web.apps.book.book.core.api.client.config;

import com.amberlight.test.web.apps.book.book.core.api.client.BookCoreCommandClient;
import com.amberlight.test.web.apps.book.book.core.api.client.BookCoreQueryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        BookCoreClientAutoConfig.BookCoreCommandClientBean.class,
        BookCoreClientAutoConfig.BookCoreQueryClientBean.class
})
public class BookCoreClientAutoConfig {

    @FeignClient(name = "book-core", contextId = "book-core-command", path = "book-core/api/")
    interface BookCoreCommandClientBean extends BookCoreCommandClient {}

    @FeignClient(name = "book-core", contextId = "book-core-query", path = "book-core/api/")
    interface BookCoreQueryClientBean extends BookCoreQueryClient {}

}

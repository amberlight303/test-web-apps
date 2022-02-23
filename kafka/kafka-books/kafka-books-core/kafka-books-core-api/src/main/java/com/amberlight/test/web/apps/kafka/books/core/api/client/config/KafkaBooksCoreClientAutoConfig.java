package com.amberlight.test.web.apps.kafka.books.core.api.client.config;

import com.amberlight.test.web.apps.kafka.books.core.api.client.KafkaBooksCoreCommandClient;
import com.amberlight.test.web.apps.kafka.books.core.api.client.KafkaBooksCoreQueryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        KafkaBooksCoreClientAutoConfig.KafkaBooksCoreCommandClientBean.class,
        KafkaBooksCoreClientAutoConfig.KafkaBooksCoreQueryClientBean.class
})
public class KafkaBooksCoreClientAutoConfig {

    @FeignClient(name = "kafka-books-core", contextId = "kafka-books-core-command", path = "kafka-books-core/api/")
    interface KafkaBooksCoreCommandClientBean extends KafkaBooksCoreCommandClient {}

    @FeignClient(name = "kafka-books-core", contextId = "kafka-books-core-query", path = "kafka-books-core/api/")
    interface KafkaBooksCoreQueryClientBean extends KafkaBooksCoreQueryClient {}

}

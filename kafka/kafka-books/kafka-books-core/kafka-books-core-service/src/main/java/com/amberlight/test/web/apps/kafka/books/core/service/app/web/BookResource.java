package com.amberlight.test.web.apps.kafka.books.core.service.app.web;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookQuery;
import com.amberlight.test.web.apps.kafka.books.core.service.app.handler.CommandHandler;
import com.amberlight.test.web.apps.kafka.books.core.service.app.handler.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookResource {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @PostMapping("/create")
    public ResponseEntity<CreateBookDocument> createBook(@RequestBody CreateBookCommand command) {
        return ResponseEntity.ok(commandHandler.process(command));
    }

    @PostMapping("/find-one")
    public ResponseEntity<FindBookDocument> findBook(@RequestBody FindBookQuery query) {
        return ResponseEntity.ok(queryHandler.process(query));
    }

}

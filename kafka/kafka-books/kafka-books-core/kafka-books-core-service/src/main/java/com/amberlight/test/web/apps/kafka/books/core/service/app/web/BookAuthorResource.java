package com.amberlight.test.web.apps.kafka.books.core.service.app.web;

import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.command.CreateBookAuthorCommand;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.CreateBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.document.FindBookAuthorDocument;
import com.amberlight.test.web.apps.kafka.books.core.api.dto.api.query.FindBookAuthorQuery;
import com.amberlight.test.web.apps.kafka.books.core.service.app.handler.CommandHandler;
import com.amberlight.test.web.apps.kafka.books.core.service.app.handler.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book/author")
public class BookAuthorResource {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @PostMapping("/create")
    public ResponseEntity<CreateBookAuthorDocument> createBookAuthor(@RequestBody CreateBookAuthorCommand command) {
        return ResponseEntity.ok(commandHandler.process(command));
    }

    @PostMapping("/find-one")
    public ResponseEntity<FindBookAuthorDocument> findBookAuthor(@RequestBody FindBookAuthorQuery query) {
        return ResponseEntity.ok(queryHandler.process(query));
    }

}

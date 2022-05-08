package com.amberlight.test.web.apps.book.book.core.service.app.web;

import com.amberlight.test.web.apps.book.book.core.api.struct.api.command.CreateBookCommand;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.CreateBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.document.FindBookDocument;
import com.amberlight.test.web.apps.book.book.core.api.struct.api.query.FindBookQuery;
import com.amberlight.test.web.apps.book.book.core.service.app.handler.CommandHandler;
import com.amberlight.test.web.apps.book.book.core.service.app.handler.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookResource {

    private CommandHandler commandHandler;

    private QueryHandler queryHandler;

    public BookResource(CommandHandler commandHandler, QueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateBookDocument> createBook(@RequestBody CreateBookCommand command) {
        return ResponseEntity.ok(commandHandler.process(command));
    }

    @PostMapping("/find-one")
    public ResponseEntity<FindBookDocument> findBook(@RequestBody FindBookQuery query) {
        return ResponseEntity.ok(queryHandler.process(query));
    }

}

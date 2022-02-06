package com.amberlight.test.web.apps.kafka.books.core.service.app.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book/")
public class BookPublishResource {

    @PostMapping("/publish")
    public ResponseEntity<String> publishBook() {
        return ResponseEntity.ok("VSE HOROSHO, RODNENKIY!");
    }

}

/*
TODO

Stage 1:

Goal:
Make a "create & query" app with postgres, JPA, liquibase (+ maybe with entity graph, DTOs, protobuf or avros).
Need to make sure it runs, it's configured, and it works. I need to think about the architecture for this sort of apps.

1) Make a domain module for entities, repos, converters, etc
2) Add liquibase integration
3) Create a user, db, tables (author, book, genre, author_book) in liquibase xml
4) Create entities (maybe with the usage of entity graphs)
5) Populate tables with records
6) Make endpoints for creating & querying entities
7) Make sure that I can create and query entities (just basic stuff without validation & conversion etc).

 */
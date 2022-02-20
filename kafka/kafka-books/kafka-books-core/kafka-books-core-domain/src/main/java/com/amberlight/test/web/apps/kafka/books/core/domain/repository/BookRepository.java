package com.amberlight.test.web.apps.kafka.books.core.domain.repository;

import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "BookBasicWithAuthors")
    @Override
    Optional<Book> findById(Long aLong);

}

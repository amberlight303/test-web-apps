package com.amberlight.test.web.apps.kafka.books.core.domain.repository;

import com.amberlight.test.web.apps.kafka.books.core.domain.entity.book.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
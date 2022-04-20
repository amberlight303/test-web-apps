package com.amberlight.test.web.apps.book.book.core.domain.repository;

import com.amberlight.test.web.apps.book.book.core.domain.entity.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookAuthorRepository extends JpaRepository<Author, Long> {

    @Override
    @Query("SELECT author FROM Author author " +
            "LEFT JOIN FETCH author.books books " +
            "LEFT JOIN FETCH books.genre genre " +
            "LEFT JOIN FETCH genre.type " +
            "WHERE author.id = :id")
    Optional<Author> findById(@Param("id") Long id);

    @Override
    @Query("SELECT DISTINCT author FROM Author author " +
            "LEFT JOIN FETCH author.books books " +
            "LEFT JOIN FETCH books.genre genre " +
            "LEFT JOIN FETCH genre.type " +
            "WHERE author.id in :ids")
    List<Author> findAllById(@Param("ids") Iterable<Long> ids);

}

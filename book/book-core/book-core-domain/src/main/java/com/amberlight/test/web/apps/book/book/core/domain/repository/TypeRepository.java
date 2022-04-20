package com.amberlight.test.web.apps.book.book.core.domain.repository;

import com.amberlight.test.web.apps.book.book.core.domain.entity.book.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
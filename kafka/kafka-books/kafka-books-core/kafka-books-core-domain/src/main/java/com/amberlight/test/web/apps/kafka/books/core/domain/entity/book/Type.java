package com.amberlight.test.web.apps.kafka.books.core.domain.entity.book;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", orphanRemoval = true)
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", orphanRemoval = true)
    @ToString.Exclude
    private Set<Genre> genres = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Type type = (Type) o;
        return id != null && Objects.equals(id, type.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

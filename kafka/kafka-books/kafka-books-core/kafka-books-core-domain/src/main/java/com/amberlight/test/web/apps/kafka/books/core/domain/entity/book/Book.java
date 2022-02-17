package com.amberlight.test.web.apps.kafka.books.core.domain.entity.book;

import com.amberlight.test.web.apps.kafka.books.core.domain.entity.author.Author;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Table(name = "book")
@NamedEntityGraph(name = "BookWithAuthors",
        attributeNodes = @NamedAttributeNode("authors")
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

//    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    @ToString.Exclude
    private Type type;

//    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    @ToString.Exclude
    private Genre genre;

//    @NonNull
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    @ToString.Exclude
    private Set<Author> authors = new LinkedHashSet<>();

    @NonNull
    @Column(name = "published", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime published;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

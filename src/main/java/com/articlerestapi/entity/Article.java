package com.articlerestapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 30)
    private String title;
    @Size(min = 5)
    private String description;

    @Column(name = "publication_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate datePublication;

    @Size(min = 2, max = 30)
    private String magazine;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @CreationTimestamp
    private LocalDate dateSavedArticle;
    @CreationTimestamp
    private LocalTime timeSavedArticle;



}

package com.articlerestapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 20)
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    List<Article> articleList = new ArrayList<>();



}

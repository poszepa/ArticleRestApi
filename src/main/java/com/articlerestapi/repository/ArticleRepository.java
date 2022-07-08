package com.articlerestapi.repository;

import com.articlerestapi.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT article FROM Article article ORDER BY article.datePublication DESC")
    List<Article> ListOfArtcileSortedByDatePublication();


}

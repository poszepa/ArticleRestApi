package com.articlerestapi.repository;

import com.articlerestapi.entity.Article;
import com.articlerestapi.entity.Author;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

@DisplayName("Article Repository Specificity")
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired TestEntityManager entityManager;
    @Autowired ArticleRepository articleRepository;
    @Autowired AuthorRepository authorRepository;

    @Test
    @DisplayName("Should return list of article sorted by date of publication Desc")
    public void listOfArticleSortedByDateOfPublication_success() {
        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        entityManager.persist(author);

        Article article = Article.builder().datePublication(LocalDate.of(2022, 10, 12)).title("TitleTest1").description("descrpitonTest1").author(author).magazine("testMagazine1").build();
        entityManager.persist(article);

        Article article2 = Article.builder().datePublication(LocalDate.of(2020, 10, 12)).title("TitleTest2").description("descrpitonTest2").author(author).magazine("testMagazine2").build();
        entityManager.persist(article2);

        List<Article> articleList = articleRepository.ListOfArtcileSortedByDatePublication();

        Truth.assertThat(articleList).hasSize(2);
        Truth.assertThat(articleList.get(0)).isEqualTo(article);
    }

}
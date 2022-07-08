package com.articlerestapi.service;

import com.articlerestapi.entity.Article;
import com.articlerestapi.entity.Author;
import com.articlerestapi.repository.ArticleRepository;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;


@DisplayName("Article Service Specificity")
@ExtendWith(SpringExtension.class)
@Import(ArticleService.class)
class ArticleServiceTest {

    @MockBean ArticleRepository articleRepository;
    @Autowired private ArticleService articleService;



    @Test
    @DisplayName("Should return list of article with key search in description")
    public void ShouldReturnListOfArticleWithKeyOnDescription() {
        Author author = Author.builder().firstName("test").lastName("test").build();
        Article article = Article.builder().description("description").author(author).datePublication(LocalDate.of(2022, 10, 22)).magazine("magazine").title("test1").build();
        Article article2 = Article.builder().description("description").author(author).datePublication(LocalDate.of(2022, 10, 22)).magazine("magazine").title("test2").build();
        Mockito.when(articleRepository.findAll()).thenReturn(
                List.of(article, article2));
        List<Article> articleList = articleService.findArticleWithKeyOnTitleOrDescription("description");

        Truth.assertThat(articleList).hasSize(2);
    }
    @Test
    @DisplayName("Should return list of article with key search in title")
    public void ShouldReturnListOfArticleWithKeyInTitle() {
        Author author = Author.builder().firstName("test").lastName("test").build();
        Article article = Article.builder().description("description").author(author).datePublication(LocalDate.of(2022, 10, 22)).magazine("magazine").title("test1").build();
        Article article2 = Article.builder().description("description").author(author).datePublication(LocalDate.of(2022, 10, 22)).magazine("magazine").title("test2").build();
        Mockito.when(articleRepository.findAll()).thenReturn(
                List.of(article, article2));
        List<Article> articleList = articleService.findArticleWithKeyOnTitleOrDescription("test1");

        Truth.assertThat(articleList).hasSize(1);
    }

    @Test
    @DisplayName("Should NOT return list of article with key search in title and description")
    public void ShouldNotFindAnyRecords() {
        Author author = Author.builder().firstName("test").lastName("test").build();
        Article article = Article.builder().description("description").author(author).datePublication(LocalDate.of(2022, 10, 22)).magazine("magazine").title("test1").build();
        Article article2 = Article.builder().description("description").author(author).datePublication(LocalDate.of(2022, 10, 22)).magazine("magazine").title("test2").build();
        Mockito.when(articleRepository.findAll()).thenReturn(
                List.of(article, article2));
        List<Article> articleList = articleService.findArticleWithKeyOnTitleOrDescription("test3");

        Truth.assertThat(articleList).hasSize(0);
    }



}
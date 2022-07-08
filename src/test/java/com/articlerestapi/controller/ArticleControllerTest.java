package com.articlerestapi.controller;



import com.articlerestapi.entity.Article;
import com.articlerestapi.entity.Author;
import com.articlerestapi.repository.ArticleRepository;
import com.articlerestapi.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;


@DisplayName("Controler Article specificity")
@WebMvcTest(controllers = ArticleController.class)
class ArticleControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ArticleRepository articleRepository;
    @MockBean private ArticleService articleService;


    @Test
    @DisplayName("Should return article by id")
    public void getArticleById_success() throws Exception {
        Author build = Author.builder().firstName("kamil").lastName("poszepczynski").build();
        Article article = Article.builder().author(build).magazine("test").description("test").title("test").datePublication(LocalDate.of(2022, 10, 12)).build();
        given(articleRepository.findById(1l)).willReturn(java.util.Optional.ofNullable(article));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/article/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..author.firstName").value("kamil"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..magazine").value("test"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @DisplayName("Should return article list sorted by publication DESC")
    public void getArticleListByDatePublication_success() throws Exception {
        Author build = Author.builder().firstName("kamil").lastName("poszepczynski").build();
        Article article = Article.builder().author(build).magazine("test").description("test").title("test").datePublication(LocalDate.of(2022, 10, 12)).build();
        Article article2 = Article.builder().author(build).magazine("test2").description("test2").title("test2").datePublication(LocalDate.of(2021, 10, 12)).build();
        Mockito.when(articleRepository.ListOfArtcileSortedByDatePublication()).thenReturn(List.of(article2, article));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/article/desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..[0].magazine").value("test2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..[1].magazine").value("test"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Should return article list by key ")
    public void getArticleListByKey_success() throws Exception {
        Author build = Author.builder().firstName("kamil").lastName("poszepczynski").build();
        Article article = Article.builder().author(build).magazine("test").description("test").title("test").datePublication(LocalDate.of(2022, 10, 12)).build();
        Article article2 = Article.builder().author(build).magazine("test2").description("test super").title("test2").datePublication(LocalDate.of(2021, 10, 12)).build();
        Mockito.when(articleService.findArticleWithKeyOnTitleOrDescription("test")).thenReturn(List.of(article2, article));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/article/findByKey/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..[0].magazine").value("test2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..[1].magazine").value("test"))
                .andDo(MockMvcResultHandlers.print());
    }



}
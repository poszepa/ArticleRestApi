package com.articlerestapi.controller;



import com.articlerestapi.entity.Article;
import com.articlerestapi.entity.Author;
import com.articlerestapi.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import static org.mockito.BDDMockito.given;


@DisplayName("Controler Article specificity")
@WebMvcTest(controllers = ArticleController.class)
class ArticleControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ArticleRepository articleRepository;


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

}
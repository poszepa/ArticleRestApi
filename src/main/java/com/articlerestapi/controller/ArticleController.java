package com.articlerestapi.controller;

import com.articlerestapi.entity.Article;
import com.articlerestapi.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;


    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id")Long articleID) {
        Article article = articleRepository.findById(articleID).orElseThrow(() -> new EntityNotFoundException("Not found article with those id"));
        return ResponseEntity.ok(article);
    }




}

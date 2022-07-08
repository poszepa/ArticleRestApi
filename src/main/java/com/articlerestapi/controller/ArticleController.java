package com.articlerestapi.controller;

import com.articlerestapi.entity.Article;
import com.articlerestapi.repository.ArticleRepository;
import com.articlerestapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;


    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id")Long articleID) {
       return articleRepository.findById(articleID)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/article/desc")
    public List<Article> getArticleListSortedByPublicationDesc() {
        return articleRepository.ListOfArtcileSortedByDatePublication();
    }

    @GetMapping("/article/findByKey/{key}")
    public List<Article> getArticleListWithFoundedKeyInTitleOrDescription(@PathVariable("key")String key) {
        return articleService.findArticleWithKeyOnTitleOrDescription(key);
    }

    @PostMapping("/article")
    ResponseEntity<Article> saveArticle(@RequestBody @Valid Article article, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Article savedArticleDB = articleRepository.save(article);
        URI savedArticle = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticleDB.getId())
                .toUri();
        return ResponseEntity.created(savedArticle).body(savedArticleDB);
    }

    @PutMapping("article/{id}")
    ResponseEntity<?> modifyArticle(@PathVariable Long id, @RequestBody @Valid Article article, BindingResult result) {
        if(result.hasErrors()) {
            ResponseEntity.badRequest().build();
        }
        return articleService.modifyArticle(id, article)
                .map(article1 -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("article/{id}")
    ResponseEntity<?> removeArticle(@PathVariable("id")Long id) {
        articleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }






}

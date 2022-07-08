package com.articlerestapi.service;

import com.articlerestapi.entity.Article;
import com.articlerestapi.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findArticleWithKeyOnTitleOrDescription(String searchKey) {
        List<Article> articleList = articleRepository.findAll().stream()
                .filter(article -> article.getTitle().toLowerCase().contains(searchKey.toLowerCase())
                        || article.getDescription().toLowerCase().contains(searchKey.toLowerCase()))
                .collect(Collectors.toList());
        if(articleList.size() == 0) {
            return new ArrayList<>();
        }
        return articleList;
    }

    public Optional<Article> modifyArticle(Long articleID, Article article) {
        if(!articleRepository.existsById(articleID)) {
            return Optional.empty();
        }
        Article articleFromDM = articleRepository.getById(articleID);
        article.setId(articleID);
        article.setDateSavedArticle(articleFromDM.getDateSavedArticle());
        article.setTimeSavedArticle(articleFromDM.getTimeSavedArticle());
        articleRepository.save(article);
        return Optional.of(article);
    }
}

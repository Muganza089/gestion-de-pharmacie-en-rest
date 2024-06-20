package org.example.gestionpharmacie.services;

import org.example.gestionpharmacie.models.Article;
import org.example.gestionpharmacie.ripositories.ArticleRipository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRipository articleRipository;

    public ArticleService(ArticleRipository articleRipository) {
        this.articleRipository = articleRipository;
    }

    public List<Article> getAllArticles(){
        return articleRipository.findAll();
    }
    public Optional<Article> getArticleById(Long id){
        return articleRipository.findById(id);
    }
    public Article saveArticle(Article article){
        return articleRipository.save(article);
    }
    public void deleteArticle(Long id){
        articleRipository.deleteById(id);
    }
    public List<Article> saveAllArticles(List<Article> listArticles){
        return articleRipository.saveAll(listArticles);
    }

}

package org.example.gestionpharmacie.Restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.gestionpharmacie.models.Article;
import org.example.gestionpharmacie.models.Client;
import org.example.gestionpharmacie.services.ArticleService;
import org.example.gestionpharmacie.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {


    private final ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleService.getArticleById(id);
        return article.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        Optional<Article> clientOptional = articleService.getArticleById(id);
        if (clientOptional.isPresent()) {
            Article article = clientOptional.get();
            article.setNom(articleDetails.getNom());
            article.setQuantite(articleDetails.getQuantite());

            return ResponseEntity.ok(articleService.saveArticle(article));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
       articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}

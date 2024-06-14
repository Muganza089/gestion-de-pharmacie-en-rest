package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRipository extends JpaRepository<Article,Long> {

}
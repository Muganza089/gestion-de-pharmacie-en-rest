package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Commande;
import org.example.gestionpharmacie.models.Produit;
import org.example.gestionpharmacie.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s JOIN FETCH s.produits ")
    List<Stock> findAllWithDetails();
}

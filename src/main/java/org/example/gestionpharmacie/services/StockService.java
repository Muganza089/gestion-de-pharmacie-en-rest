package org.example.gestionpharmacie.services;

import org.example.gestionpharmacie.models.Produit;
import org.example.gestionpharmacie.models.Stock;
import org.example.gestionpharmacie.ripositories.ProduitRepository;
import org.example.gestionpharmacie.ripositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final ProduitRepository produitRepository;

    @Autowired
    public StockService(StockRepository stockRepository, ProduitRepository produitRepository) {
        this.stockRepository = stockRepository;
        this.produitRepository = produitRepository;
    }
    public List<Stock> getAllStocks() {
        return stockRepository.findAllWithDetails();
    }

    public Stock saveStock(Stock stock) {
        for (Produit produit : stock.getProduits()) {
            produit.setStock(stock);
        }
        return stockRepository.save(stock);
    }
    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }




    public List<Produit> retirerProduits(String nomProduit, int quantite) {
        List<Produit> produits = produitRepository.findByNomOrderByDateAjoutAsc(nomProduit);
        List<Produit> produitsRetires = new ArrayList<>();

        int quantiteRestante = quantite;
        for (Produit produit : produits) {
            if (quantiteRestante <= 0) break;

            int quantiteARetirer = Math.min(produit.getQuantite(), quantiteRestante);
            produit.setQuantite(produit.getQuantite() - quantiteARetirer);
            quantiteRestante -= quantiteARetirer;

            produitsRetires.add(new Produit(produit.getNom(), produit.getNumeroLot(), produit.getDateExpiration(),
                    quantiteARetirer, produit.getPrix(), produit.getStock()));

            if (produit.getQuantite() == 0) {
                produitRepository.delete(produit);
            } else {
                produitRepository.save(produit);
            }
        }

        if (quantiteRestante > 0) {
            throw new IllegalArgumentException("Quantité demandée non disponible en stock.");
        }

        return produitsRetires;
    }


}



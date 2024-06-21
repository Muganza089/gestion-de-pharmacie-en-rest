package org.example.gestionpharmacie.Restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.gestionpharmacie.models.Produit;
import org.example.gestionpharmacie.services.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produits")
public class ProduitController {


    private final ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        Optional<Produit> produitOptional = produitService.getProduitById(id);
        if (produitOptional.isPresent()) {
            Produit produit = produitOptional.get();
            produit.setNom(produitDetails.getNom());
            produit.setNumeroLot(produitDetails.getNumeroLot());
            produit.setQuantite(produitDetails.getQuantite());
            produit.setPrix(produitDetails.getPrix());
            produit.setDateExpiration(produitDetails.getDateExpiration());
            return ResponseEntity.ok(produitService.saveProduit(produit));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}

package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomOrderByDateAjoutAsc(String nom);
}

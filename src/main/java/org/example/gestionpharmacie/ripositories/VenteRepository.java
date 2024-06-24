package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Commande;
import org.example.gestionpharmacie.models.Stock;
import org.example.gestionpharmacie.models.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {

    @Query("SELECT v FROM Vente v JOIN FETCH v.client c LEFT JOIN FETCH v.produits  ")
    List<Vente> findAllWithDetails();
}

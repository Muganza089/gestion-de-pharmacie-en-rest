package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT c FROM commande c JOIN FETCH c.fournisseur f LEFT JOIN FETCH c.articles a")
    List<Commande> findAllWithDetails();
}

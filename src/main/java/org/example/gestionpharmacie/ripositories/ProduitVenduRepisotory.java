package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.ProduitVendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitVenduRepisotory extends JpaRepository<ProduitVendu,Long> {
}

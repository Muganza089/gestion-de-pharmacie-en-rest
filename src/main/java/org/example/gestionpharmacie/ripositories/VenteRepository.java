package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}

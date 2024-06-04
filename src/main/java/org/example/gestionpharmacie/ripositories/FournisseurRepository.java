package org.example.gestionpharmacie.ripositories;

import org.example.gestionpharmacie.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}

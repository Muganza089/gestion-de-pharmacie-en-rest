package org.example.gestionpharmacie.services;

import lombok.AllArgsConstructor;
import org.example.gestionpharmacie.models.ProduitVendu;
import org.example.gestionpharmacie.ripositories.ProduitVenduRepisotory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProduitVenduService {
    private final ProduitVenduRepisotory produitVenduRepisotory;
    public List<ProduitVendu> getAllProduitsVendus(){
        return produitVenduRepisotory.findAll();
    }
    public Optional<ProduitVendu> getProduitsVenduById(Long id){
        return produitVenduRepisotory.findById(id);
    }
    public ProduitVendu saveProduit(ProduitVendu produitVendu){
        return produitVenduRepisotory.save(produitVendu);

    }
    public void deleteProduit(Long id){
        produitVenduRepisotory.deleteById(id);
    }


}

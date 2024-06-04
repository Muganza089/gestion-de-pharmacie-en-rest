package org.example.gestionpharmacie.controllers;

import lombok.RequiredArgsConstructor;
import org.example.gestionpharmacie.models.Fournisseur;
import org.example.gestionpharmacie.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fournisseurs")
public class FournisseurController {


    private final FournisseurService fournisseurService;

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long id) {
        Optional<Fournisseur> fournisseur = fournisseurService.getFournisseurById(id);
        return fournisseur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.saveFournisseur(fournisseur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseurDetails) {
        Optional<Fournisseur> fournisseurOptional = fournisseurService.getFournisseurById(id);
        if (fournisseurOptional.isPresent()) {
            Fournisseur fournisseur = fournisseurOptional.get();
            fournisseur.setNom(fournisseurDetails.getNom());
            fournisseur.setAdresse(fournisseurDetails.getAdresse());
            fournisseur.setContact(fournisseurDetails.getContact());
            return ResponseEntity.ok(fournisseurService.saveFournisseur(fournisseur));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.noContent().build();
    }
}

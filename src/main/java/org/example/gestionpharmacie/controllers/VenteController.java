package org.example.gestionpharmacie.controllers;

import lombok.RequiredArgsConstructor;
import org.example.gestionpharmacie.models.Vente;
import org.example.gestionpharmacie.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ventes")
public class VenteController {


    private final VenteService venteService;

    @GetMapping
    public List<Vente> getAllVentes() {
        return venteService.getAllVentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vente> getVenteById(@PathVariable Long id) {
        Optional<Vente> vente = venteService.getVenteById(id);
        return vente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vente createVente(@RequestBody Vente vente) {
        return venteService.saveVente(vente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vente> updateVente(@PathVariable Long id, @RequestBody Vente venteDetails) {
        Optional<Vente> venteOptional = venteService.getVenteById(id);
        if (venteOptional.isPresent()) {
            Vente vente = venteOptional.get();
            vente.setClient(venteDetails.getClient());
            vente.setProduit(venteDetails.getProduit());
            vente.setQuantite(venteDetails.getQuantite());
            vente.setPrixTotal(venteDetails.getPrixTotal());
            vente.setDateVente(venteDetails.getDateVente());
            return ResponseEntity.ok(venteService.saveVente(vente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }
}


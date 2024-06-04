package org.example.gestionpharmacie.controllers;

import lombok.RequiredArgsConstructor;
import org.example.gestionpharmacie.models.Commande;
import org.example.gestionpharmacie.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.getCommandeById(id);
        return commande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Optional<Commande> commandeOptional = commandeService.getCommandeById(id);
        if (commandeOptional.isPresent()) {
            Commande commande = commandeOptional.get();
            commande.setClient(commandeDetails.getClient());
            commande.setProduit(commandeDetails.getProduit());
            commande.setQuantite(commandeDetails.getQuantite());
            commande.setDateCommande(commandeDetails.getDateCommande());
            return ResponseEntity.ok(commandeService.saveCommande(commande));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}


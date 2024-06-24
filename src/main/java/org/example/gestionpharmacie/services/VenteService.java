package org.example.gestionpharmacie.services;
import org.example.gestionpharmacie.models.Client;
import org.example.gestionpharmacie.models.Produit;
import org.example.gestionpharmacie.models.ProduitVendu;
import org.example.gestionpharmacie.models.Vente;
import org.example.gestionpharmacie.ripositories.ProduitRepository;
import org.example.gestionpharmacie.ripositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VenteService {
    private final VenteRepository venteRepository;
    private final ProduitRepository produitRepository;
    private final StockService stockService;
    private final ProduitService produitService;
    private final ClientService clientService;


    @Autowired
    public VenteService(VenteRepository venteRepository, ProduitRepository produitRepository, StockService stockService, ProduitService produitService, ClientService clientService) {
        this.venteRepository = venteRepository;
        this.produitRepository = produitRepository;
        this.stockService = stockService;
        this.produitService = produitService;
        this.clientService = clientService;
    }


    @Transactional
    public void saveVente(Long clientId, List<ProduitVendu> produits) {
        Client client = clientService.getClientById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        double prixTotal = produits.stream().mapToDouble(p -> p.getPrix() * p.getQuantite()).sum();

        Vente vente = new Vente(client, produits, prixTotal);
        for (ProduitVendu produit : produits) {

            produit.setVente(vente);
        }
        venteRepository.save(vente);
    }

    public List<Vente> getAllVentes() {
        return venteRepository.findAllWithDetails();
    }



    public Optional<Vente> getVenteById(Long id) {
        return venteRepository.findById(id);
    }

    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}



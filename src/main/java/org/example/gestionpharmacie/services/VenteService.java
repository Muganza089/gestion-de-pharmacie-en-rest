package org.example.gestionpharmacie.services;
import org.example.gestionpharmacie.models.Client;
import org.example.gestionpharmacie.models.Produit;
import org.example.gestionpharmacie.models.Vente;
import org.example.gestionpharmacie.ripositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenteService {
    private final VenteRepository venteRepository;
    private final StockService stockService;
    private final ProduitService produitService;
    private final ClientService clientService;

    @Autowired
    public VenteService(VenteRepository venteRepository, StockService stockService, ProduitService produitService, ClientService clientService) {
        this.venteRepository = venteRepository;
        this.stockService = stockService;
        this.produitService = produitService;
        this.clientService = clientService;
    }

    public Vente saveVente(Long clientId, Long produitId, int quantite) {
        Produit produit = produitService.getProduitById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));
        Client client = clientService.getClientById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client non trouvé"));

        double prixTotal = produit.getPrix() * quantite;

        stockService.retirerProduits(produit.getNom(), quantite);

        Vente vente = new Vente(client, produit, quantite, prixTotal);
        return venteRepository.save(vente);
    }

    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    public Optional<Vente> getVenteById(Long id) {
        return venteRepository.findById(id);
    }

    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}



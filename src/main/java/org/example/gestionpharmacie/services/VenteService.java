package org.example.gestionpharmacie.services;
import org.example.gestionpharmacie.models.Vente;
import org.example.gestionpharmacie.ripositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenteService {

    private final VenteRepository venteRepository;

    public VenteService(VenteRepository venteRepository) {
        this.venteRepository = venteRepository;
    }

    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    public Optional<Vente> getVenteById(Long id) {
        return venteRepository.findById(id);
    }

    public Vente saveVente(Vente vente) {
        return venteRepository.save(vente);
    }

    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}


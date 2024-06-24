package org.example.gestionpharmacie.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AfterDomainEventPublication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private double prixTotal;
    private LocalDate dateVente;

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits = new ArrayList<>();

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitVendu> produitVendus = new ArrayList<>();

    public Vente(Client client, List<ProduitVendu> produits, double prixTotal) {
        this.client = client;
        this.produitVendus = produits;
        this.prixTotal = prixTotal;
        this.dateVente = LocalDate.now();
    }
}



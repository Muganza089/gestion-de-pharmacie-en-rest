package org.example.gestionpharmacie.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private int quantite;
    private double prixTotal;
    private LocalDate dateVente;

    public Vente(Client client, Produit produit, int quantite, double prixTotal) {
        this.client = client;
        this.produit = produit;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
        this.dateVente = LocalDate.now();
    }
}


package org.example.gestionpharmacie.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor @Getter @Setter
public class ProduitVendu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private String nom;
    private double prix;
    private int quantite;
    @ManyToOne
    @JoinColumn(name = "vente_id")
    private Vente vente;

    public ProduitVendu(Client client, String nom, double prix,int quantite) {
        this.client = client;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }
}

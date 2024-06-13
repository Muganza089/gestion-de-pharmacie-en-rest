package org.example.gestionpharmacie.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Fournisseur_id", referencedColumnName = "id")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "produit_id", referencedColumnName = "id")
    private Produit produit;
    private int quantite;
    private Date dateCommande;

    // Getters and setters
}

package org.example.gestionpharmacie.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String numeroLot;
    private String DateExpiration;
    private int quantite;
    private double prix;
    private LocalDate dateAjout;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    public Produit(String nom, String numeroLot, String dateExpiration, int quantite, double prix, Stock stock) {
        this.nom = nom;
        this.numeroLot = numeroLot;
        this.DateExpiration = dateExpiration;
        this.quantite = quantite;
        this.prix = prix;
        this.stock = stock;
        this.dateAjout = LocalDate.now();
    }


}

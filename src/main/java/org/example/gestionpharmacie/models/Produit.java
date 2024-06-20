package org.example.gestionpharmacie.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String numeroLot;
    private int quantite;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;


}

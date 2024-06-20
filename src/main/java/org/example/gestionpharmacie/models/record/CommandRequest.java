package org.example.gestionpharmacie.models.record;

import org.example.gestionpharmacie.models.Fournisseur;

import java.util.Date;

public record CommandRequest(Fournisseur fournisseur, Date dateCommande) {

}

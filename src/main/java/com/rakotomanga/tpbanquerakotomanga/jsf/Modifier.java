/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.jsf;

import com.rakotomanga.tpbanquerakotomanga.entities.CompteBancaire;
import com.rakotomanga.tpbanquerakotomanga.jsf.util.Util;
import com.rakotomanga.tpbanquerakotomanga.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 * Backing bean pour la page JSF modifierComptes
 *
 * @author Hasina
 */
@Named(value = "modifier")
@ViewScoped
public class Modifier implements Serializable{

    private Long id;
    private CompteBancaire compteBancaire;
    private String nom;

    @PositiveOrZero
    private int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of Modifier
     */
    public Modifier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void loadCompte() {
        this.nom = this.compteBancaire.getNom();
        this.compteBancaire = this.gestionnaireCompte.findById(id);
        this.solde = this.compteBancaire.getSolde();
    }
    
    public String modifier() {
        boolean erreur = false;
        String message = "";
        if (this.nom.equalsIgnoreCase("")) {
            Util.messageErreur("Le champ nom ne doit pas être vide", "Le champ nom ne doit pas être vide", "form:nom");
            erreur = true;
        }
        if (!erreur) {
            message += "Modification reussie avec succés: ";
            if (!this.compteBancaire.getNom().equals(this.nom)) {
                message += " le nom " + this.compteBancaire.getNom() + " a été changé en " + this.nom;
            }
            if (this.compteBancaire.getSolde() != this.solde) {
                message += " le solde " + this.compteBancaire.getSolde() + " a été changé en " + this.solde;
            }
            this.compteBancaire.setNom(this.nom);
            this.compteBancaire.setSolde(this.solde);
            this.gestionnaireCompte.update(this.compteBancaire);
            Util.addFlashInfoMessage(message);
            return "listeComptes?faces-redirect=true";
        }
        return null;
    }

}

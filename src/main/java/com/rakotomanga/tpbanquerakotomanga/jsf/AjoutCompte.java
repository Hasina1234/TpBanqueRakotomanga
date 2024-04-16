/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.jsf;

import com.rakotomanga.tpbanquerakotomanga.entities.CompteBancaire;
import com.rakotomanga.tpbanquerakotomanga.jsf.util.Util;
import com.rakotomanga.tpbanquerakotomanga.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Backing bean pour la page JSF ajoutCompte
 * @author Hasina
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    
    private String nom;
    
    @PositiveOrZero
    private int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public AjoutCompte() {
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
    
    @Transactional
    public String action() {
        boolean erreur = false;
        if (this.nom.equalsIgnoreCase("")) {
            Util.messageErreur("Le champ nom doit être remplit", "Le champ nom doit être remplit", "form:nom");
            erreur = true;
        }
        if (!erreur) {
            this.gestionnaireCompte.creerCompte(new CompteBancaire(this.nom, this.solde));
            Util.addFlashInfoMessage("Le nouveau compte " + this.nom + " a été créé avec succès");
            return "listeComptes?faces-redirect=true";
        } else {
            return null;
        }

    }

}

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
import java.io.Serializable;

/**
 * Backing bean pour la page JSF transfert
 * @author Hasina
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private Long idSource;
    private Long idDestination;
    private int somme;  
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
        
    public Transfert() {
    }
    
    /**
     * Transfert de solde entre les deux comptes choisi
     */
    public String transferer() {
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findById(this.getIdSource());
        CompteBancaire destination = gestionnaireCompte.findById(this.getIdDestination());
        if (source == null) {
            Util.messageErreur("Aucun compte source avec cet id !", "Aucun compte source avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < this.getSomme()) {
                Util.messageErreur("Le solde du compte source est insuffisant", "Le solde du compte source est insuffisant", "form:montant");
                erreur = true;
            }
        }
        if (destination == null) {
            Util.messageErreur("Aucun compte destination avec cet id !", "Aucun compte destination avec cet id !", "form:destination");
            erreur = true;
        }
        
        if (this.getSomme() < 0) {
            Util.messageErreur("Le montant saisi est invalide", "Le montant saisi est invalide", "form:montant");
            erreur = true;
        }

        if (erreur) {
            return null;
        }
        this.gestionnaireCompte.transferer(this.getIdSource(), this.getIdDestination(), this.getSomme());
        String nomSource = source.getNom();
        String nomDestination = destination.getNom();
        Util.addFlashInfoMessage("Montant: " + somme + " a été transferé depuis " + nomSource + " vers " + nomDestination);
        return "listeComptes?faces-redirect=true";
    }
    
    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.jsf;

import com.rakotomanga.tpbanquerakotomanga.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
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
    
    public String transferer() {
        this.gestionnaireCompte.transferer(this.getIdSource(), this.getIdDestination(), this.getSomme());
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.jsf;

import com.rakotomanga.tpbanquerakotomanga.entities.CompteBancaire;
import com.rakotomanga.tpbanquerakotomanga.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean pour la page JSF listeComptes
 * @author Hasina
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable{
    
    private List<CompteBancaire> compteList;  

    @Inject
    private GestionnaireCompte gestionnaireCompte;  

    public ListeComptes() {
    }
    
    /** 
     * Retourne la liste de tous les comptes.
    */  
    public List<CompteBancaire> getAllComptes() {
      if (compteList == null) {
        compteList = gestionnaireCompte.getAllComptes();
      }
      return compteList;
    }  
}

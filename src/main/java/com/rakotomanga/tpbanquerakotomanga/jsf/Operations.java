/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.jsf;

import com.rakotomanga.tpbanquerakotomanga.entities.CompteBancaire;
import com.rakotomanga.tpbanquerakotomanga.entities.OperationBancaire;
import com.rakotomanga.tpbanquerakotomanga.service.GestionnaireCompte;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hasina
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    /**
     * Creates a new instance of Operations
     */
    
    private Long Id;
    private CompteBancaire compteBancaire;
    private List<OperationBancaire> listOperations;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public Operations() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    
    public void getCompte() {
        compteBancaire = gestionnaireCompte.findById(Id);
    }
    
    public List<OperationBancaire> getListOperations() {
        return this.getCompteBancaire().getOperations();
    }
    
    public void setListOperations(List<OperationBancaire> operations) {
        this.listOperations = operations;
    }
    
}

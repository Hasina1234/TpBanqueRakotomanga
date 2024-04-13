/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.congif;

import com.rakotomanga.tpbanquerakotomanga.entities.CompteBancaire;
import com.rakotomanga.tpbanquerakotomanga.service.GestionnaireCompte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;

/**
 *
 * @author Hasina
 */
@Named(value = "Init")
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        // Vérifier si la table des comptes est vide en utilisant nbComptes()
        long nbComptes = gestionnaireCompte.nbComptes();
        if (nbComptes == 0) {
            // Créer les 4 comptes au démarrage de l'application
            this.gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }

}

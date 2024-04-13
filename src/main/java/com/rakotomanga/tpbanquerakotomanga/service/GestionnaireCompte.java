/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.rakotomanga.tpbanquerakotomanga.service;

import com.rakotomanga.tpbanquerakotomanga.entities.CompteBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Hasina
 */

@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              
    password="root", 
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)

@Named(value = "gestionnaireCompte")
@RequestScoped
public class GestionnaireCompte {
    
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    /**
     * Creates a new instance of GestionnaireCompte
     */
    public GestionnaireCompte() {
    }
    
    
    public List<CompteBancaire> getAllCustomers() {
       Query query = em.createNamedQuery("CompteBancaire.findAll");
       return query.getResultList();
    }
    
    @Transactional
    public void persist(CompteBancaire compte) {
       em.persist(compte);
    }
}

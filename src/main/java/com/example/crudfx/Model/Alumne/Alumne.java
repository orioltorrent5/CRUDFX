package com.example.crudfx.Model.Alumne;

public class Alumne {
    //Variables
    private String id;
    private String nom;
    private String cognoms;
    private String dataNaix;
    private String Curs;
    private String Progenitors;

    //Constructors
    public Alumne(String nom, String cognoms, String dataNaix, String curs, String progenitors) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaix = dataNaix;
        Curs = curs;
        Progenitors = progenitors;
    }

    public Alumne() {
        this.nom = "nom";
        this.cognoms = "cognoms";
        this.dataNaix = "dataNaix";
        Curs = "curs";
        Progenitors = null;
    }

    //Getters Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(String dataNaix) {
        this.dataNaix = dataNaix;
    }

    public String getCurs() {
        return Curs;
    }

    public void setCurs(String curs) {
        Curs = curs;
    }


    public String getProgenitors() {
        return Progenitors;
    }

    public void setProgenitors(String progenitors) {
        Progenitors = progenitors;
    }
}

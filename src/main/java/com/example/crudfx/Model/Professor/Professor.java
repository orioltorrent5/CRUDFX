package com.example.crudfx.Model.Professor;

public class Professor {
    //Variables
    private String nom, cognom;
    private String id;

    //Constructor
    public Professor(String nom, String cognom) {
        this.nom = nom;
        this.cognom = cognom;
    }

    public Professor() {
        this.nom = "nom";
        this.cognom = "cognom";
    }

    public Professor(String nom, String cognom, String id) {
        this.nom = nom;
        this.cognom = cognom;
        this.id = id;
    }

    //Getters Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

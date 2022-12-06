package com.example.crudfx.Model.Modul;

public class Modul {
    //Variables
    private String id;
    private String nom;
    private String idProfessor;

    //Constructors
    public Modul(String nom, String idProfessor){
        this.nom = nom;
        this.idProfessor = idProfessor;
    }

    public Modul(){
        this.id = "0";
        this.nom = "null";
        this.idProfessor = "0";
    }

    //Getters i Setters

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

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

}

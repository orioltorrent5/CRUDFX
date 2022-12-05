package com.example.crudfx.Model.Modul;

public class Modul {
    //Variables
    private int id;
    private String nom;
    private int idProfessor;

    //Constructors
    public Modul(String nom, int idProfessor){
        this.nom = nom;
        this.idProfessor = idProfessor;
    }

    public Modul(){
        this.id = 0;
        this.nom = "null";
        this.idProfessor = 0;
    }

    //Getters i Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

}

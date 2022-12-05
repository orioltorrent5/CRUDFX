package com.example.crudfx.Model.Professor;

import javafx.collections.ObservableList;

public interface IProfessorDAO {
    public void afegirProfessor(Professor professor);
    public void eliminarProfessor(String id);
    public void updateProfessor(String nom, String cognoms, String id);
    public ObservableList<Professor> mostrarProfessors();
}

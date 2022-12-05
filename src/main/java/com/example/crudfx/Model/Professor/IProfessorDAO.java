package com.example.crudfx.Model.Professor;

import com.example.crudfx.Model.Alumne.Alumne;
import javafx.collections.ObservableList;

public interface IProfessorDAO {
    public void afegirProfessor(Professor professor);
    public void eliminarProfessor(String id);
    public void updateProfessor(Professor professor, String id);
    public ObservableList<Professor> mostrarProfessors();
    public Professor buscarProfessor(String id);
}

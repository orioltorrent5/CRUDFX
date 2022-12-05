package com.example.crudfx.Model.Alumne;

import javafx.collections.ObservableList;

public interface IAlumneDAO {
    public void addAlumne(Alumne alumne);
    public ObservableList<Alumne> getAllAlumnes();
    public void updateAlumnes(Alumne alumne, String id);
    public void deleteAlumne(String id);
    public Alumne buscarAlumne(String id);
}

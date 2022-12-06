package com.example.crudfx.Model.Modul;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IModulDAO {
   public void addModul(Modul modul);
   public void deleteModul(String id) throws SQLException;
   public void updateModul(Modul modul, String id);
   public Modul buscarModul(String id);
   public ObservableList<Modul> llistarModuls();
}

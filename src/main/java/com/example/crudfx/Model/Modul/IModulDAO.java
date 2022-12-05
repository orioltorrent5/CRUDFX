package com.example.crudfx.Model.Modul;

import java.sql.SQLException;

public interface IModulDAO {
   public void addModul(Modul modul);
   public void deleteModul(String id) throws SQLException;
   public void updateModul(String nom, int idProfessor, String id);
   public void llistarModuls();
}

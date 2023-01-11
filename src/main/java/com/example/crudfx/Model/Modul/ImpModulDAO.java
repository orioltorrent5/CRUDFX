package com.example.crudfx.Model.Modul;

import com.example.crudfx.Model.Professor.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class ImpModulDAO implements IModulDAO{

    private final Connection sqlConnect;

    //Definim el constructor de la classe impAlumne creei la connexió
    public ImpModulDAO() throws SQLException {
        //Definim la connexió
        String url = "jdbc:mysql://localhost:3306/dam2";
        String user = "root";
        String password = "";

        sqlConnect = DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addModul(Modul modul) {
        try {
            //Definim les variables
            String nom = modul.getNom();
            String idProfessor = modul.getIdProfessor();

            //Creem la sentencia SQL que utilitzarem
            String insert = "insert into moduls(nom, id_professor) values(?,?)";

            //Executem la sentencia
            PreparedStatement pe = sqlConnect.prepareStatement(insert);

            //Per cada interrogant assignara el valor
            pe.setString(1, nom);
            pe.setInt(2, Integer.parseInt(idProfessor));

            pe.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteModul(String id) throws SQLException {
        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaDELETE = "DELETE FROM moduls WHERE id = ?";

            //Executem la sentencia
            PreparedStatement preparedStatement = sqlConnect.prepareStatement(sentenciaDELETE);

            //Per cada interrogant assignara el valor
            preparedStatement.setInt(1, Integer.parseInt(id));

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateModul(Modul modul, String id) {
        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaUPDATE = "UPDATE moduls SET nom = ?, id_professor = ? WHERE id = ?";

            //Executem la sentencia
            PreparedStatement preparedStatement = sqlConnect.prepareStatement(sentenciaUPDATE);

            //Per cada interrogant assignara el valor
            preparedStatement.setString(1, modul.getNom());
            preparedStatement.setInt(2, Integer.parseInt(modul.getIdProfessor()));
            preparedStatement.setInt(3, Integer.parseInt(id));

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Modul buscarModul(String id) {
        Modul modul = new Modul();
        try {
            //Variables
            String nom, idProfessor;
            Boolean existeixID = false;

            //Creem la sentencia SQL que utilitzarem
            String sentenciaRead = "SELECT *  FROM moduls m";

            //Executem la sentencia
            Statement statement = sqlConnect.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaRead);

            while (rs.next()){
                if (rs.getString("id").equals(id)){

                    modul.setNom(rs.getString("nom"));
                    modul.setIdProfessor(rs.getString("id_professor"));

                    existeixID = true;
                }
            }

            if (!existeixID){
                Alert alert = new Alert(Alert.AlertType.ERROR, "l'id introduit no es correcta", ButtonType.CLOSE);
                alert.setHeaderText("      ID INVALID!!");
                alert.show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return modul;
    }

    @Override
    public ObservableList<Modul> llistarModuls() {
        ObservableList<Modul> listView = FXCollections.observableArrayList();
        try {
            //Variables
            String id, nom, idprofessor, nomprofessor;

            //Creem la sentencia SQL que utilitzarem
            String sentenciaRead = "select moduls.id, moduls.nom, moduls.id_professor, professors.nom AS nomprofessor from moduls INNER JOIN professors ON moduls.id_professor = professors.id";

            //Executem la sentencia
            Statement statement = sqlConnect.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaRead);

            while (rs.next()) {
                Modul modul = new Modul();

                modul.setId(rs.getString("id"));
                modul.setNom(rs.getString("nom"));
                modul.setIdProfessor(rs.getString("id_professor"));
                modul.setNomProfessor(rs.getString("nomprofessor"));

                listView.add(modul);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listView;
    }
}

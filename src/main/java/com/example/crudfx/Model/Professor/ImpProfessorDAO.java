package com.example.crudfx.Model.Professor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ImpProfessorDAO implements IProfessorDAO{

    private final Connection sqlConnect;

    //Definim el constructor de la classe impAlumne creei la connexió
    public ImpProfessorDAO() throws SQLException {
        //Definim la connexió
        String url = "jdbc:mysql://localhost:3306/dam2";
        String user = "root";
        String password = "";

        sqlConnect = DriverManager.getConnection(url, user, password);
    }
    @Override
    public void afegirProfessor(Professor professor) {
        try {
            //Definim les variables
            String nom = professor.getNom();
            String cognoms = professor.getCognom();

            //Executem la sentencia
            PreparedStatement pe = sqlConnect.prepareStatement("insert into professors(nom, cognoms) values(?,?)");

            //Per cada interrogant assignara el valor
            pe.setString(1, nom);
            pe.setString(2, cognoms);
            pe.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminarProfessor(String id) {
        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaDELETE = "DELETE FROM professors WHERE id = ?";

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
    public void updateProfessor(String nom, String cognoms, String id) {
        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaUPDATE = "UPDATE professors SET nom = ?, cognoms = ? WHERE id = ?";

            //Executem la sentencia
            PreparedStatement preparedStatement = sqlConnect.prepareStatement(sentenciaUPDATE);


            //Per cada interrogant assignara el valor
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, cognoms);
            preparedStatement.setInt(3, Integer.parseInt(id));

            System.out.println();
            System.out.println("--VALORS NOUS INTRODUITS--");
            System.out.println("ID ↣ " + id);
            System.out.println("Nom ↣ " + nom);
            System.out.println("Cognoms ↣ " + cognoms);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Professor> mostrarProfessors() {
        ObservableList<Professor> listView = FXCollections.observableArrayList();
        try {
            //Variables
            int i = 1;
            String id, nom, cognoms;

            //Creem la sentencia SQL que utilitzarem
            String sentenciaRead = "SELECT *  FROM professors";

            //Executem la sentencia
            Statement statement = sqlConnect.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaRead);

            while (rs.next()) {
                Professor professor = new Professor();

                professor.setId(rs.getString("id"));
                professor.setNom(rs.getString("nom"));
                professor.setCognom(rs.getString("cognoms"));

                listView.add(professor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listView;
    }
}

package com.example.crudfx.Model.Modul;

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
            int idProfessor = modul.getIdProfessor();

            //Creem la sentencia SQL que utilitzarem
            String insert = "insert into moduls(nom, id_professor) values(?,?)";

            //Executem la sentencia
            PreparedStatement pe = sqlConnect.prepareStatement(insert);

            //Per cada interrogant assignara el valor
            pe.setString(1, nom);
            pe.setInt(2, idProfessor);

            pe.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteModul(String id) throws SQLException {
        //Creem la sentencia SQL que utilitzarem
        String sentenciaDELETE = "DELETE FROM moduls WHERE id = ?";

        //Executem la sentencia
        PreparedStatement preparedStatement = sqlConnect.prepareStatement(sentenciaDELETE);

        //Per cada interrogant assignara el valor
        preparedStatement.setInt(1, Integer.parseInt(id));

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateModul(String nom, int idProfessor, String id) {
        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaUPDATE = "UPDATE moduls SET nom = ?, id_professor = ? WHERE id = ?";

            //Executem la sentencia
            PreparedStatement preparedStatement = sqlConnect.prepareStatement(sentenciaUPDATE);

            //Per cada interrogant assignara el valor
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, idProfessor);
            preparedStatement.setInt(3, Integer.parseInt(id));

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void llistarModuls() {
        try{
            String id ,nom_modul, nom, cognoms;
            int i = 1;

            //Creem la sentencia SQL que utilitzarem
            String sentenciaREAD = "SELECT m.id, m.nom AS nom_modul, p.nom, p.cognoms  FROM moduls m INNER JOIN professors p ON  m.id_professor = p.id";

            //Executem la sentencia
            Statement statement = sqlConnect.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaREAD);

            System.out.println("---LLISTA PROFESSORS---");
            while (rs.next()) {
                id = rs.getString("id");
                nom = rs.getString("nom");
                cognoms = rs.getString("cognoms");
                nom_modul = rs.getString("nom_modul");


                System.out.println();
                System.out.println("Modul " + i + ": ");
                System.out.println("ID → " + id);
                System.out.println("Módul → " + nom_modul);
                System.out.println("Nom Professor → " + nom);
                System.out.println("Cognoms Professor → " + cognoms);
                System.out.println();

                i++;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

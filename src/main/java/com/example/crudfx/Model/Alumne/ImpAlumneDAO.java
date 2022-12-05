package com.example.crudfx.Model.Alumne;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ImpAlumneDAO implements IAlumneDAO {

    private final Connection postgresConnect;

    //Definim el constructor de la classe impAlumne creei la connexió
    public ImpAlumneDAO() throws SQLException {
        //Definim la connexió
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "a";

        postgresConnect = DriverManager.getConnection(url, user, password);
    }


    /**
     * Inserim un alumne a la taula alumnes de la BD de postgres
     *
     * @param alumne alumne que afegirem
     */
    public void addAlumne(Alumne alumne) {
        try {

            //Definim les variables
            String nom = alumne.getNom();
            String cognoms = alumne.getCognoms();
            String date = alumne.getDataNaix();
            String curs = alumne.getCurs();
            String progenitors = alumne.getProgenitors();
            String[] llistaProgenitors = progenitors.split(",");

            Array progenitorsArray = postgresConnect.createArrayOf("varchar", llistaProgenitors);

            //Creem la sentencia SQL que utilitzarem
            String sentenciaInsert = "insert into public.alumnes(nom, cognoms, dataNaix, curs, progenitors) values(?,?,?,?::\"curs\",?)";

            //Executem la sentencia
            PreparedStatement pe = postgresConnect.prepareStatement(sentenciaInsert);

            //Per cada interrogant assignara el valor
            pe.setString(1, nom);
            pe.setString(2, cognoms);
            pe.setDate(3, Date.valueOf(date));
            pe.setString(4, curs);
            pe.setArray(5, progenitorsArray);

            pe.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Et permet agafar una llista d'alumnes de la BD
     * @return et retorna una llista de alumnes
     */
    public ObservableList<Alumne> getAllAlumnes() {
        ObservableList<Alumne> listView = FXCollections.observableArrayList();
        try {
            //Variables
            int i = 1;
            String id, nom, cognoms, data, curs;

            //Creem la sentencia SQL que utilitzarem
            String sentenciaRead = "SELECT *  FROM alumnes a";

            //Executem la sentencia
            Statement statement = postgresConnect.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaRead);

            while (rs.next()) {
                Alumne alumne = new Alumne();

                alumne.setId(rs.getString("id"));
                alumne.setNom(rs.getString("nom"));
                alumne.setCognoms(rs.getString("cognoms"));
                alumne.setDataNaix(rs.getString("datanaix"));
                alumne.setCurs(rs.getString("curs"));
                alumne.setProgenitors(rs.getString("progenitors"));

                listView.add(alumne);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listView;
    }

    /**
     * Et permet actualiztar un alumne
     * @param alumne alumne amb les noves dades
     * @param id alumne que modificarem
     */
    @Override
    public void updateAlumnes(Alumne alumne, String id) {

        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaUPDATE = "UPDATE alumnes SET nom = ?, cognoms = ?, datanaix = ?, curs = ?::\"curs\", progenitors = ? WHERE id = ?";

            //Executem la sentencia
            PreparedStatement preparedStatement = postgresConnect.prepareStatement(sentenciaUPDATE);

            String[]llistaProgen = alumne.getProgenitors().split(",");

            //Preparem l'array
            Array array1 = postgresConnect.createArrayOf("varchar",llistaProgen);

            int idInt = Integer.parseInt(id);
            //Per cada interrogant assignara el valor
            preparedStatement.setString(1, alumne.getNom());
            preparedStatement.setString(2, alumne.getCognoms());
            preparedStatement.setDate(3, Date.valueOf(alumne.getDataNaix()));
            preparedStatement.setString(4, alumne.getCurs());
            preparedStatement.setArray(5, array1);
            preparedStatement.setInt(6, idInt);


            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Et peremt eliminar un alumne de la BD
     * @param id
     */
    @Override
    public void deleteAlumne(String id) {
        try {
            //Creem la sentencia SQL que utilitzarem
            String sentenciaDELETE = "DELETE FROM alumnes WHERE id = ?";

            //Executem la sentencia
            PreparedStatement preparedStatement = postgresConnect.prepareStatement(sentenciaDELETE);

            //Per cada interrogant assignara el valor
            preparedStatement.setInt(1, Integer.parseInt(id));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Et permet seleccionar un alumne amb les seves dades
     * @param idAlumne alumne que volem seleccionar
     * @return retorna l'alumne que hem seleccionat
     */
    @Override
    public Alumne buscarAlumne(String idAlumne) {
        Alumne alumne = new Alumne();
        try {
            //Variables
            String nom, cognoms, data, curs;
            Boolean existeixID = false;

            //Creem la sentencia SQL que utilitzarem
            String sentenciaRead = "SELECT *  FROM alumnes a";

            //Executem la sentencia
            Statement statement = postgresConnect.createStatement();
            ResultSet rs = statement.executeQuery(sentenciaRead);

        while (rs.next()){
            if (rs.getString("id").equals(idAlumne)){ //Ho vaig provar de fer amb un preparedstatment però hem saltaba un error

                alumne.setId(rs.getString("id"));
                alumne.setNom(rs.getString("nom"));
                alumne.setCognoms(rs.getString("cognoms"));
                alumne.setDataNaix(rs.getString("datanaix"));
                alumne.setCurs(rs.getString("curs"));
                alumne.setProgenitors(rs.getString("progenitors"));
                existeixID = true;
            }
        }

            if (!existeixID){
                Alert alert = new Alert(Alert.AlertType.ERROR, "l'id introduit no es correcta", ButtonType.CLOSE);
                alert.setHeaderText("      ID INVALID!!");
                alert.show();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return alumne;
    }
}


package com.example.crudfx.Controllers;

import com.example.crudfx.Efects.*;
import com.example.crudfx.Model.Alumne.Alumne;
import com.example.crudfx.Model.Alumne.ImpAlumneDAO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class AlumneController implements Initializable {

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnHome;

    @FXML
    private TextField cognomAlumne;


    @FXML
    private TableColumn<Alumne, String> cognomsAlumneC;

    String[] cursOpcions = {"ESO", "Batxillerat", "Cicles Formatius"};
    @FXML
    private ChoiceBox<String> cursAlumne;

    @FXML
    private TableColumn<Alumne, String> cursAlumneC;

    @FXML
    private DatePicker dataAlumne;

    @FXML
    private TextField idAlumne;

    @FXML
    private TableColumn<Alumne, LocalDate> datanaixamentAlumneC;

    @FXML
    private TableColumn<Alumne, Integer> idAlumneC;

    @FXML
    private TextField nomAlumne;

    @FXML
    private TableColumn<Alumne, String> nomAlumneC;

    @FXML
    private TextField progenitorsAlumne1;

    @FXML
    private TextField progenitorsAlumne2;

    @FXML
    private TableColumn<Alumne, Array> progenitorsAlumneC;

    @FXML
    private TableView<Alumne> taulaAlumnes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llistarAlumnes();
        cursAlumne.getItems().addAll(cursOpcions);
    }

    @FXML
    public void insertarAlumne(ActionEvent event) {
        if (!nomAlumneC.getText().equals("") && !cognomsAlumneC.getText().equals("") && !datanaixamentAlumneC.getText().equals("")
        && !cursAlumne.getItems().equals("") && !progenitorsAlumneC.getText().equals("")){


            Alumne alumne = new Alumne();
            alumne.setNom(nomAlumne.getText());
            alumne.setCognoms(cognomAlumne.getText());
            alumne.setDataNaix(String.valueOf(dataAlumne.getValue()));
            alumne.setCurs(cursAlumne.getValue());

            String progenitors = progenitorsAlumne1.getText() + "," + progenitorsAlumne2.getText();
            alumne.setProgenitors(progenitors);

            try {
                ImpAlumneDAO alumneDAO = new ImpAlumneDAO();
                alumneDAO.addAlumne(alumne);
            }catch (Exception e){
                e.printStackTrace();
            }
            listView.clear();
            llistarAlumnes();
        }

    }

    private final ObservableList<Alumne> listView = FXCollections.observableArrayList();

    /**
     * Et permet mostrar la llista de alumnes per pantalla
     */
    public void llistarAlumnes(){
        try {
            listView.clear();
            idAlumneC.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomAlumneC.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cognomsAlumneC.setCellValueFactory(new PropertyValueFactory<>("cognoms"));
            datanaixamentAlumneC.setCellValueFactory(new PropertyValueFactory<>("dataNaix"));
            cursAlumneC.setCellValueFactory(new PropertyValueFactory<>("Curs"));
            progenitorsAlumneC.setCellValueFactory(new PropertyValueFactory<>("Progenitors"));

            objAlumne.bind(taulaAlumnes.getSelectionModel().selectedItemProperty());

            ImpAlumneDAO daoAlumnes = new ImpAlumneDAO();
            listView.setAll(daoAlumnes.getAllAlumnes());
            taulaAlumnes.setItems(listView);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private Stage stage;
    private Scene scene;

    /**
     * Et permet tornar al menu home
     * @param event deteca el click
     */
    @FXML
    void backMenu(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/crudfx/Menu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * Ens mostra una animació si no s'ha seleccionat cap alumne i el volem eliminar, i si el seleccionaem el podem eliminar.
     */
    private ObjectProperty<Alumne> objAlumne = new SimpleObjectProperty<>();
    @FXML
    void eliminarAlumne(ActionEvent event){

        try {
            if (objAlumne.get()==null){
                Metodes.errorAnimacioAlumne(taulaAlumnes);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "VOLS ELIMINAR AQUEST REGISTRE?!", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(this.objAlumne.get().getId());
            if (alert.showAndWait().get() == ButtonType.YES){
                ImpAlumneDAO impAlumneDAO = new ImpAlumneDAO();
                impAlumneDAO.deleteAlumne(objAlumne.get().getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        listView.clear();
        llistarAlumnes();
    }

    /**
     * Et permet modificar un alumne
     */
    @FXML
    void updateAlumne(ActionEvent event) throws SQLException {

        ImpAlumneDAO impAlumneDAO = new ImpAlumneDAO();


        String progentiors = progenitorsAlumne1.getText() + "," + progenitorsAlumne2.getText();

        Alumne alumne = new Alumne(nomAlumne.getText(), cognomAlumne.getText(), String.valueOf(dataAlumne.getValue()), cursAlumne.getValue(), progentiors);

        impAlumneDAO.updateAlumnes(alumne, idAlumne.getText());

        listView.clear();
        llistarAlumnes();
    }


    /**
     * Et permet ficar les dades de un Alumne en els camps per aixi poder modifcar-lo
     */
    @FXML
    void buscarAlumne(ActionEvent event) throws SQLException, ParseException {
        Alumne alumne = new Alumne();
        ImpAlumneDAO impAlumneDAO = new ImpAlumneDAO();
        String progenitor1 = "";
        String progenitor2 = "";

        if (!idAlumne.getText().equals("")){
           alumne = impAlumneDAO.buscarAlumne(idAlumne.getText());
           nomAlumne.setText(alumne.getNom());
           cognomAlumne.setText(alumne.getCognoms());
           dataAlumne.setValue(LocalDate.parse(alumne.getDataNaix()));
           cursAlumne.setValue(alumne.getCurs());

           progenitor1 = alumne.getProgenitors().split(",")[0];

           if (alumne.getProgenitors().contains(",")) {
               progenitor2 = alumne.getProgenitors().split(",")[1];
               progenitor1 = progenitor1.substring(1);
               progenitor2 = progenitor2.substring(0,progenitor2.length()-1);
           }else {
               progenitor1 = progenitor1.substring(1, progenitor1.length()-1);
               progenitor2 = "-vacio-";
           }
           progenitorsAlumne1.setText(progenitor1);
           progenitorsAlumne2.setText(progenitor2);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "l'id no ha estat introduit", ButtonType.CLOSE);
            alert.setHeaderText("      ID NO INTRODUIT!!");
            alert.show();
        }

    }

}

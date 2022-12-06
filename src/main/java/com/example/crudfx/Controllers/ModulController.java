package com.example.crudfx.Controllers;

import com.example.crudfx.Model.Modul.ImpModulDAO;
import com.example.crudfx.Model.Modul.Modul;
import com.example.crudfx.Model.Professor.ImpProfessorDAO;
import com.example.crudfx.Efects.*;
import com.example.crudfx.Model.Professor.Professor;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModulController implements Initializable {
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Modul, String> idProfModulC;

    @FXML
    private TextField idModul;

    @FXML
    private TableColumn<Modul, String> idModulC;

    @FXML
    private TextField idProfessor;

    @FXML
    private TextField nomModul;

    @FXML
    private TableColumn<Modul, String> nomModulC;

    @FXML
    private TableView<Modul> taulaModuls;


    /**
     * Et permet tornar al menu principal
     */
    private Stage stage;
    private Scene scene;
    @FXML
    void backMenu(ActionEvent event) {
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
     * Et permet seleccionar un modul per poder-lo modificar amb més facilitat
     */
    @FXML
    void buscarModul(ActionEvent event) throws SQLException {
        Modul modul;
        ImpModulDAO modulDAO = new ImpModulDAO();

        if (!idModul.getText().equals("")){
            modul = modulDAO.buscarModul(idModul.getText());
            nomModul.setText(modul.getNom());
            idProfessor.setText(modul.getIdProfessor());

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "l'id no ha estat introduit", ButtonType.CLOSE);
            alert.setHeaderText("      ID NO INTRODUIT!!");
            alert.show();
        }

    }

    /**
     * Ens permet eliminar un modul de la llista
     * @param event
     */
    @FXML
    void eliminarModul(ActionEvent event) {
        try {
            if (objModul.get()==null){
                Metodes.errorAnimacioModuls(taulaModuls);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "VOLS ELIMINAR AQUEST REGISTRE?!", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(this.objModul.get().getId());
            if (alert.showAndWait().get() == ButtonType.YES){
                ImpModulDAO modulDAO = new ImpModulDAO();
                modulDAO.deleteModul(objModul.get().getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        listView.clear();
        llistarModuls();
    }

    /**
     * Ens permet afegir un modul de la llista
     * @param event
     */
    @FXML
    void insertarModul(ActionEvent event) {
        if (!idProfModulC.getText().equals("") && !nomModulC.getText().equals("") && !idProfModulC.getText().equals("")){

            Modul modul = new Modul();

            modul.setNom(nomModul.getText());
            modul.setIdProfessor(idProfessor.getText());

            try {
                ImpModulDAO modulDAO = new ImpModulDAO();
                modulDAO.addModul(modul);
            }catch (Exception e){
                e.printStackTrace();
            }
            listView.clear();
            llistarModuls();
        }

    }

    /**
     * Et permet actualizar un modul
     */
    @FXML
    void updateModul(ActionEvent event) throws SQLException {
        ImpModulDAO modulDAO = new ImpModulDAO();

        Modul modul = new Modul(nomModul.getText(), idProfessor.getText());
        modul.setId(idModul.getText());

        modulDAO.updateModul(modul, modul.getId());

        listView.clear();
        llistarModuls();
    }



    private ObjectProperty<Modul> objModul = new SimpleObjectProperty<>();
    private final ObservableList<Modul> listView = FXCollections.observableArrayList();

    /**
     * Et permet mostrar els moduls per pantalla
     */
    public void llistarModuls(){
        try {
            listView.clear();
            idModulC.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomModulC.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idProfModulC.setCellValueFactory(new PropertyValueFactory<>("idProfessor"));

            objModul.bind(taulaModuls.getSelectionModel().selectedItemProperty());

            ImpModulDAO modulDAO = new ImpModulDAO();
            listView.setAll(modulDAO.llistarModuls());
            taulaModuls.setItems(listView);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llistarModuls();
    }
}

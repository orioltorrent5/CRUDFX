package com.example.crudfx.Controllers;

import com.example.crudfx.Model.Professor.ImpProfessorDAO;
import com.example.crudfx.Model.Professor.Professor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfessorController implements Initializable {

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
    private TextField cognomProfessor;

    @FXML
    private TableColumn<Professor, String> cognomsProfessorC;

    @FXML
    private TableColumn<Professor, String> idProfessorC;

    @FXML
    private TextField idProfessor;

    @FXML
    private TableColumn<Professor, String> nomProfessorC;

    @FXML
    private TextField nomProfessor;

    @FXML
    private TableView<Professor> taulaProfessor;

    @FXML
    void backMenu(ActionEvent event) {

    }

    @FXML
    void buscarProfessor(ActionEvent event) {

    }

    @FXML
    void eliminarProfessor(ActionEvent event) {

    }

    @FXML
    void insertarProfessor(ActionEvent event) {

    }

    @FXML
    void updateProfessor(ActionEvent event) {

    }


    private ObjectProperty<Professor> objProfessor = new SimpleObjectProperty<>();

    private final ObservableList<Professor> listView = FXCollections.observableArrayList();
    /**
     * Et permet mostrar la llista de alumnes per pantalla
     */
    public void llistarAlumnes(){
        try {
            listView.clear();
            idProfessorC.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomProfessorC.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cognomsProfessorC.setCellValueFactory(new PropertyValueFactory<>("cognom"));

            objProfessor.bind(taulaProfessor.getSelectionModel().selectedItemProperty());

            ImpProfessorDAO professorDAO = new ImpProfessorDAO();
            listView.setAll(professorDAO.mostrarProfessors());
            taulaProfessor.setItems(listView);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llistarAlumnes();
    }
}


package com.example.crudfx.Controllers;

import com.example.crudfx.Efects.*;
import com.example.crudfx.Model.Alumne.Alumne;
import com.example.crudfx.Model.Alumne.ImpAlumneDAO;
import com.example.crudfx.Model.Professor.ImpProfessorDAO;
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
import java.time.LocalDate;
import java.util.Objects;
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

    @FXML
    void buscarProfessor(ActionEvent event) throws SQLException {
        Professor professor;
        ImpProfessorDAO professorDAO = new ImpProfessorDAO();

        if (!idProfessor.getText().equals("")){
            professor = professorDAO.buscarProfessor(idProfessor.getText());
            nomProfessor.setText(professor.getNom());
            cognomProfessor.setText(professor.getCognom());

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "l'id no ha estat introduit", ButtonType.CLOSE);
            alert.setHeaderText("      ID NO INTRODUIT!!");
            alert.show();
        }
    }

    @FXML
    void eliminarProfessor(ActionEvent event) {
        try {
            if (objProfessor.get()==null){
                Metodes.errorAnimacioProfessor(taulaProfessor);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "VOLS ELIMINAR AQUEST REGISTRE?!", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(this.objProfessor.get().getId());
            if (alert.showAndWait().get() == ButtonType.YES){
                ImpProfessorDAO professorDAO = new ImpProfessorDAO();
                professorDAO.eliminarProfessor(objProfessor.get().getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        listView.clear();
        llistarProfessors();
    }

    @FXML
    void insertarProfessor(ActionEvent event) {
        if (!nomProfessorC.getText().equals("") && !cognomsProfessorC.getText().equals("")){

            Professor professor = new Professor();

            professor.setNom(nomProfessor.getText());
            professor.setCognom(cognomProfessor.getText());

            try {
                ImpProfessorDAO professorDAO = new ImpProfessorDAO();
                professorDAO.afegirProfessor(professor);
            }catch (Exception e){
                e.printStackTrace();
            }
            listView.clear();
            llistarProfessors();
        }

    }

    @FXML
    void updateProfessor(ActionEvent event) throws SQLException {
        ImpProfessorDAO professorDAO = new ImpProfessorDAO();

        Professor professor = new Professor(nomProfessor.getText(), cognomProfessor.getText(), idProfessor.getText());

        professorDAO.updateProfessor(professor, professor.getId());

        listView.clear();
        llistarProfessors();
    }


    private ObjectProperty<Professor> objProfessor = new SimpleObjectProperty<>();

    private final ObservableList<Professor> listView = FXCollections.observableArrayList();
    /**
     * Et permet mostrar la llista de alumnes per pantalla
     */
    public void llistarProfessors(){
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
        llistarProfessors();
    }
}


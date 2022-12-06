package com.example.crudfx.Efects;

import com.example.crudfx.Model.Alumne.Alumne;
import com.example.crudfx.Model.Modul.Modul;
import com.example.crudfx.Model.Professor.Professor;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.util.Duration;

public class Metodes {

    public static void errorAnimacioAlumne(TableView<Alumne> node){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), node);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setFromAngle(-3);
        rotateTransition.setToAngle(3);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                node.setRotate(0);
            }
        });
        rotateTransition.play();
    }

    public static void errorAnimacioProfessor(TableView<Professor> node){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), node);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setFromAngle(-3);
        rotateTransition.setToAngle(3);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                node.setRotate(0);
            }
        });
        rotateTransition.play();
    }

    public static void errorAnimacioModuls(TableView<Modul> node){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), node);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setFromAngle(-3);
        rotateTransition.setToAngle(3);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                node.setRotate(0);
            }
        });
        rotateTransition.play();
    }
}

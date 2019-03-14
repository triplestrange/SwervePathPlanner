package com.mammen.ui.javafx.dialog.add_waypoint;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

//MODFIED FOR SWERVE...ADDED TEXTFIELD FOR ANGLE FACING AND GETTER METHOD
public class AddWaypointDialogController {
    @FXML
    private TextField txtWX, txtWY, txtWA, txtWR;

    @FXML
    private void initialize() {
        txtWX.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txtWY.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txtWA.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        txtWR.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
    }

    public TextField getTxtWX() {
        return txtWX;
    }

    public TextField getTxtWY() {
        return txtWY;
    }

    public TextField getTxtWA() {
        return txtWA;
    }

    public TextField getTxtWR() {
        return txtWR;
    }
}

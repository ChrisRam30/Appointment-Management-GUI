package controller;


import helper.JDBC;
import helper.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.*;

public class Login implements Initializable {
    public TextField userNameField;
    public TextField passwordField;
    public Button loginButton;
    public Label timeZone;
    public Button Close;
    public Label userNameLabel;
    public Label PasswordLabel;
    public RadioButton englishRadioButton;
    public RadioButton frenchRadioButton;
    public ToggleGroup langRadiobuttonGroup;
    public Label timeZoneLabel;
    public Label languageLabel;

    @Override


    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZone.setText(ZoneId.systemDefault().toString());

        ResourceBundle rb = ResourceBundle.getBundle("Lang", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"));

    }



    public void loginButtonAction(ActionEvent actionEvent) throws SQLException {

        try {
            String sql = "SELECT * FROM USERS WHERE User_Name=? AND Password =?;";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            preparedStatement.setString(1, userNameField.getText());
            preparedStatement.setString(2, passwordField.getText());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == true) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Appointment Menu");
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    if (frenchRadioButton.isSelected()) {
                        alert.setContentText("Identifiant et/ou mot de passe incorrect, veuillez réessaye.");
                    } else {
                        if(englishRadioButton.isSelected()) {
                            alert.setContentText("Incorrect Login and/or Password, please try again.");
                        }
                    }
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        }

    public void languageTabAction(ActionEvent actionEvent) {
    }

    public void closeButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (englishRadioButton.isSelected()) {
            alert.setContentText("Would you like to Exit?");
         } else {
            if (frenchRadioButton.isSelected()) {
                alert.setContentText("Voulez-vous sortir ?");
                }
            }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public void englishRadioClick(ActionEvent actionEvent) {
        if (englishRadioButton.isSelected()) {
            userNameLabel.setText("UserName");
            PasswordLabel.setText("Password");
            loginButton.setText("Login");
            languageLabel.setText("Language");
            timeZoneLabel.setText("Time Zone");
            Close.setText("Close");

        }
    }

    public void frenchRadioClick(ActionEvent actionEvent) {
        if (frenchRadioButton.isSelected()) {
            userNameLabel.setText("Nom d'utilisateur");
            PasswordLabel.setText("Mot de passe");
            loginButton.setText("Connexion");
            languageLabel.setText("Langue");
            timeZoneLabel.setText("Fuseau Horaire");
            Close.setText("Proche");
        }
    }
}

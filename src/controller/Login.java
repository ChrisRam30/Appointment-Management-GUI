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
    public Label timeZoneLabel;
    public Label languageLabel;
    public Label displayLanguage;
    ResourceBundle rb = ResourceBundle.getBundle("Lang", Locale.getDefault());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZone.setText(ZoneId.systemDefault().toString());

        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            userNameLabel.setText(rb.getString("userName"));
            PasswordLabel.setText(rb.getString("passwordLabel"));
            loginButton.setText(rb.getString("loginButton"));
            languageLabel.setText(rb.getString("languageLabel"));
            timeZoneLabel.setText(rb.getString("timeZoneLabel"));
            Close.setText(rb.getString("Close"));
            displayLanguage.setText(rb.getString("displayLang"));

        }

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
                if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                        alert.setContentText(rb.getString("incorrectLogin"));
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
        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            alert.setContentText(rb.getString("exit"));
            }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Locale.setDefault(new Locale("en", "US")); //used to check if languages are auto changing
            System.exit(0);
        }
    }
}

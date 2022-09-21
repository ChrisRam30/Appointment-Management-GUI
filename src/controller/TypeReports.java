package controller;

import helper.AppointmentsCRUD;
import helper.ContactsCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class TypeReports implements Initializable {

    public TableColumn typeColumn;
    public Button backToAppointmentsButton;
    public ComboBox<Timestamp> monthComboBox;
    public TableView appointmentCountTable;
    public TableColumn monthColumn;
    public TableColumn countColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthComboBox.setItems(AppointmentsCRUD.getAllMonths());

        appointmentCountTable.setItems(AppointmentsCRUD.getAllAppointments());

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));


    }

    /*public void typeComboBoxClick(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getTypeAppointments(typeComboBox.getValue()));

    }*/

    public void backToAppointmentsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportsMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }


    public void monthComboBoxClick(ActionEvent actionEvent) {
        appointmentCountTable.setItems(AppointmentsCRUD.getAppointmentTMCount(monthComboBox.getValue()));

    }
}

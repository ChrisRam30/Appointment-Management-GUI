package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import helper.AppointmentsCRUD;
import javafx.scene.control.*;
import model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AppointmentMenu implements Initializable {

    public TableColumn titleColumn;
    public TableColumn descriptionColumn;
    public TableColumn contactColumn;
    public TableColumn typeColumn;
    public TableColumn locationColumn;
    public TableColumn startDateTimeColumn;
    public TableColumn endDateTimeColumn;
    public TableColumn customerIdColumn;
    public TableColumn userIdColumn;
    public TableView<Appointments> appointmentTable;
    public Button reportsButton;
    public Button addAppointmentButton;
    public Button modifyAppointmentButton;
    public Button deleteAppointmentButton;
    public Button logoutButton;
    public TableColumn appointmentIdColumn;
    public Button customerTableButton;
    public RadioButton viewAllRadioButton;
    public ToggleGroup viewRbGroup;
    public RadioButton viewByWeekRadioButton;
    public RadioButton viewByMonthRadioButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentTable.setItems(AppointmentsCRUD.getAllAppointments());

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));


        /*LocalTime currentTime = LocalTime.now();
        long timeDifference = ChronoUnit.MINUTES.between(startDateTimeColumn, currentTime);
        long interval = (timeDifference + -1) * -1;

        if(interval > 0 && interval <=15) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment Warning");
            alert.setContentText("Appointment in 15 Minutes");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("No Upcoming Appointments");
            alert.showAndWait();
        }*/





    }


    public void reportsClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportsMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    public void addAppointmentClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void modifyAppointmentClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        //Parent root = FXMLLoader.load(getClass().getResource("/view/modifyAppointment.fxml"));
        loader.setLocation(getClass().getResource("/view/modifyAppointment.fxml"));
        loader.load();
        modifyAppointment modAppController = loader.getController();
        modAppController.receiveAppointmentData(appointmentTable.getSelectionModel().getSelectedItem());


        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setTitle("Modify Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void deleteAppointmentClick(ActionEvent actionEvent) throws SQLException {

        Appointments AP = appointmentTable.getSelectionModel().getSelectedItem();

        AppointmentsCRUD.deleteAppointment(AP.getAppointmentId());
        appointmentTable.setItems(AppointmentsCRUD.getAllAppointments());

    }

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void customerTableButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerTable.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void viewAllRadioButtonClick(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getAllAppointments());

    }

    public void viewByWeekRadioButtonClick(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getAppointmentsByWeek());

    }

    public void viewByMonthRadioButtonClck(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getAppointmentsByMonth());

    }
}
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import helper.AppointmentsCRUD;
import helper.JDBC;
import javafx.collections.ObservableList;
import model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentTable.setItems(AppointmentsCRUD.getAllAppointments());

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));





    }


    public void viewAllButton(ActionEvent actionEvent) {

    }

    public void reportsClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
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
        modAppController.recieveAppointmentData(appointmentTable.getSelectionModel().getSelectedItem());


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
}
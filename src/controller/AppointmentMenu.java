package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
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

/**This controller displays all appointments and allows access to all other functionality of the application*/
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

/**This method initializes the controller and populates the appointment table with the appointment data from the SQL database*/
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








    }

/**This method takes you to the reports menu page on click*/
    public void reportsClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportsMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }
/**This method takes you to the add appointment menu on click*/
    public void addAppointmentClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }
/**This method takes you to the modify appointment menu on click
 * If no appointment is selected an error message will display*/
    public void modifyAppointmentClick(ActionEvent actionEvent) throws IOException {
        if (appointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Select an Appointment to Modify.");
            alert.showAndWait();

        } else {
            FXMLLoader loader = new FXMLLoader();
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
    }
/*This method allows you to delete an appointment on click
If no appointment is selected a warning will display and confirmation will be asked when trying to delete an appointment
 */
    public void deleteAppointmentClick(ActionEvent actionEvent) throws SQLException {

        Appointments AP = appointmentTable.getSelectionModel().getSelectedItem();

        if(AP == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Select Appointment to delete.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment delete Confirmation");
            alert.setContentText("Are you sure you want to delete this Appointment?");
            Optional<ButtonType> confirmation = alert.showAndWait();

            if (confirmation.get() == ButtonType.OK) {
                AppointmentsCRUD.deleteAppointment(AP.getAppointmentId());
                appointmentTable.setItems(AppointmentsCRUD.getAllAppointments());
            }
        }
    }
/**This method allows you to logout on click*/
    public void logoutClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
/**This method takes you to the customer table on click*/
    public void customerTableButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerTable.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
/**This method will display all appointments when selected*/
    public void viewAllRadioButtonClick(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getAllAppointments());

    }
/**This method will display only appointments that occur in the current week when selected*/
    public void viewByWeekRadioButtonClick(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getAppointmentsByWeek());

    }
/**This method will display only appointments that occur this month when selected*/
    public void viewByMonthRadioButtonClck(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentsCRUD.getAppointmentsByMonth());

    }
}
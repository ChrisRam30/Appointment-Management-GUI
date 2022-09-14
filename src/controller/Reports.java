package controller;

import helper.AppointmentsCRUD;
import helper.ReportsCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    public TableColumn customerIdColumn;
    public TableColumn appointmentIdColumn;
    public TableColumn titleColumn;
    public TableColumn typeColumn;
    public TableColumn descriptionColumn;
    public TableColumn startDateTimeColumn;
    public TableColumn endDateTimeColumn;
    public Button backToAppointmentsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*ReportsCRUD.setItems(ReportsCRUD.getAllAppointmentsByMonth());

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));*/
    }


    public void backToAppointmentsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }
}

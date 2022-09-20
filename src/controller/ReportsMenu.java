package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsMenu {
    public Button contactScheduleBox;
    public Button customerAppointmentBox;
    public Button backToAppointments;
    public Button monthAppointmentBox;
    public Button typeAppointmentBox1;

    public void contactScheduleBoxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/contactsReport.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Contacts Report");
        stage.setScene(scene);
        stage.show();
    }

    public void customerAppointmentBoxClick(ActionEvent actionEvent) {

    }

    public void backToAppointmentsClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void monthAppointmentBoxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/monthReports.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Months Report");
        stage.setScene(scene);
        stage.show();
    }

    public void typeAppointmentBoxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/typeReports.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Type Reports");
        stage.setScene(scene);
        stage.show();
    }
}

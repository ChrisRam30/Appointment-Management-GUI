package controller;

import helper.AppointmentsCRUD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class modifyAppointment implements Initializable {
    public TextField modifyAppointmentIdBox;
    public TextField modifyTitleBox;
    public TextField modifyDescriptionBox;
    public TextField modifyTypeBox;
    public DatePicker modifyStartDateBox;
    public DatePicker modifyEndDateBox;
    public Button modifySaveButtonBox;
    public TextField modifyLocationBox;
    public Button modifyCancelButton;
    public ComboBox<LocalTime> modifyStartTimeComboBox;
    public ComboBox<LocalTime> modifyEndTimeComboBox;
    public ComboBox modifyContactIDComboBox;
    public ComboBox modifyCustomerIdComboBox;
    public ComboBox modifyUserIdComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(22,0);

        while(start.isBefore(end.plusSeconds(1))) {
            modifyStartTimeComboBox.getItems().add(start);
            modifyEndTimeComboBox.getItems().add(start);
            start = start.plusMinutes(10);
        }
        modifyStartTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
        modifyEndTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));

        modifyContactIDComboBox.setItems(AppointmentsCRUD.getAllAppointments());
        modifyCustomerIdComboBox.setItems(AppointmentsCRUD.getAllAppointments());
        modifyUserIdComboBox.setItems(AppointmentsCRUD.getAllAppointments());


    }

    public void recieveAppointmentData(Appointments data) {


        modifyAppointmentIdBox.setText(String.valueOf(data.getAppointmentId()));
        modifyTitleBox.setText(data.getTitle());
        modifyLocationBox.setText(data.getLocation());
        modifyTypeBox.setText(data.getType());
        modifyDescriptionBox.setText(data.getDescription());
        modifyStartTimeComboBox.setValue((data.getStartDateTime().toLocalDateTime().toLocalTime()));
        modifyStartDateBox.setValue(data.getStartDateTime().toLocalDateTime().toLocalDate());
        modifyEndTimeComboBox.setValue((data.getEndDateTime().toLocalDateTime().toLocalTime()));
        modifyEndDateBox.setValue(data.getStartDateTime().toLocalDateTime().toLocalDate());
        modifyCustomerIdComboBox.setValue(data.getCustomerId());
        modifyUserIdComboBox.setValue(data.getUserId());
        modifyContactIDComboBox.setValue(data.getContact());
    }

    public void modifySaveButtonClick(ActionEvent actionEvent) throws SQLException {

        Timestamp starttime = Timestamp.valueOf(LocalDateTime.of(modifyStartDateBox.getValue(), modifyStartTimeComboBox.getValue()));
        Timestamp endtime = Timestamp.valueOf(LocalDateTime.of(modifyStartDateBox.getValue(), modifyEndTimeComboBox.getValue()));

        AppointmentsCRUD.modifyAppointment(modifyTitleBox.getText(),
                modifyDescriptionBox.getText(), modifyLocationBox.getText(), modifyTypeBox.getText(),
                starttime,endtime, Integer.parseInt(String.valueOf(modifyCustomerIdComboBox.getValue())),
                Integer.parseInt(String.valueOf(modifyUserIdComboBox.getValue())),
                Integer.parseInt(String.valueOf(modifyCustomerIdComboBox.getValue())));


    }

    public void modifyCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void contactIDComboBoxClick(ActionEvent actionEvent) {
    }

    public void customerIdComboBoxClick(ActionEvent actionEvent) {
    }

    public void userIdComboBox(ActionEvent actionEvent) {
    }
}

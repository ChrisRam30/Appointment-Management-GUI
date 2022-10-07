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

/**Controller displays the amount of each type of appointment by month
 *
 */
public class TypeReports implements Initializable {

    public TableColumn typeColumn;
    public Button backToAppointmentsButton;
    public TableView appointmentCountTable;
    public TableColumn monthColumn;
    public TableColumn countColumn;

    /**Initializes the controller and populates the table with the information.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        appointmentCountTable.setItems(AppointmentsCRUD.getAppointmentTMCount());

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("monthName"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

    }

    /**Method takes user back to the reports menu on click
     *
     * @param actionEvent
     * @throws IOException
     */
    public void backToAppointmentsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reportsMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }

}

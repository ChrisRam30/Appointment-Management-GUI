package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**Controller for the reports menu, allows for navigation between reports
 *
 */
public class ReportsMenu {
    public Button contactScheduleBox;
    public Button backToAppointments;
    public Button typeAppointmentBox1;
    public Button appointmentsMCButton;

    /**Method takes user to contact reports
     *
     * @param actionEvent
     * @throws IOException
     */
    public void contactScheduleBoxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/contactsReport.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Contacts Report");
        stage.setScene(scene);
        stage.show();
    }

    /**Method takes user to appointsments menu on click
     *
     * @param actionEvent
     * @throws IOException
     */
    public void backToAppointmentsClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**Method takes user to appointments by type screen
     *
     * @param actionEvent
     * @throws IOException
     */
    public void typeAppointmentBoxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/typeReports.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Type Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**Method takes the user to the number of appointments for each customer by month view
     *
     * @param actionEvent
     * @throws IOException
     */
    public void appointmentsMCButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/monthlyCustomerAppointmentReport.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Appointments by Month");
        stage.setScene(scene);
        stage.show();
    }
}

package controller;

import helper.AppointmentsCRUD;
import helper.CustomerCRUD;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
/**This controller displays all customers*/
public class CustomerTable implements Initializable {
    public TableView <Customers> customerTable;
    public TableColumn customerIdColumn;
    public TableColumn customerNameColumn;
    public TableColumn phoneColumn;
    public TableColumn addressColumn;
    public TableColumn postalCodeColumn;
    public TableColumn divisionIdColumn;
    public Button backToAppointmentsButton;
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public TableColumn countryColumn;
    public TableColumn stateProvinceColumn;
/**This method initializes the controller and populates the table with the customer data*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerTable.setItems(CustomerCRUD.getAllCustomers());

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        stateProvinceColumn.setCellValueFactory(new PropertyValueFactory<>("divisionName"));


    }
/*This method takes you back to the appointment menu on click*/
    public void backToAppointmentsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }
/**This method takes you to the add customer menu on click*/
    public void addCustomerButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**This method takes you to the modify customer menu on click.
     * If no customer is selected the proper error message will dispaly.
     */
    public void modifyCustomerButtonClick(ActionEvent actionEvent) throws IOException {
        if (customerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Select a Customer to Modify.");
            alert.showAndWait();

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/modifyCustomer.fxml"));
            loader.load();
            ModifyCustomer modAppController = loader.getController();
            modAppController.receiveCustomerData(customerTable.getSelectionModel().getSelectedItem());


            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Modify Customer");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
/*This method will delete a customer and all associated appointments on click
* If no customer is selected an appropriate error message displays.
* */
    public void deleteCustomerButtonClick(ActionEvent actionEvent) throws SQLException {
        Customers CP = customerTable.getSelectionModel().getSelectedItem();

        if (CP == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Select a Customer to delete.");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Customer Delete Confimation");
            alert.setContentText("This will Delete all Appointments as well as Customer records, would you like to proceed?");
            Optional<ButtonType> confirmation = alert.showAndWait();

            if (confirmation.get() == ButtonType.OK) {
                CustomerCRUD.deleteCustomer(CP.getCustomerId());
                customerTable.setItems(CustomerCRUD.getAllCustomers());

            }
        }
    }

}

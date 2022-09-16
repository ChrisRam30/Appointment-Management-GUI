package controller;

import helper.AppointmentsCRUD;
import helper.CustomerCRUD;
import helper.JDBC;
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
import model.Appointments;
import model.Countries;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    public void backToAppointmentsButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void addCustomerButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void modifyCustomerButtonClick(ActionEvent actionEvent) throws IOException {
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

    public void deleteCustomerButtonClick(ActionEvent actionEvent) throws SQLException {

        Customers CP = customerTable.getSelectionModel().getSelectedItem();

        CustomerCRUD.deleteCustomer(CP.getCustomerId());
        customerTable.setItems(CustomerCRUD.getAllCustomers());
    }

}

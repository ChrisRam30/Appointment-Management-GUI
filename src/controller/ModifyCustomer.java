package controller;

import helper.AppointmentsCRUD;
import helper.CountriesCRUD;
import helper.CustomerCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Appointments;
import model.Countries;
import model.Customers;
import model.Divisions;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyCustomer implements Initializable {
    public Button saveButton;
    public Button cancelButton;
    public TextField customerIdBox;
    public TextField customerNameBox;
    public TextField addressBox;
    public TextField postalCodeBox;
    public TextField phoneBox;
    public ComboBox<Countries> countryComboBox;
    public ComboBox<Divisions> stateProvinceComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(CountriesCRUD.getAllCountries());


    }

    public void recieveCustomerData(Customers data) {

        customerIdBox.setText(String.valueOf(data.getCustomerId()));
        customerNameBox.setText(data.getCustomerName());
        addressBox.setText(data.getAddress());
        postalCodeBox.setText(data.getPostalCode());
        phoneBox.setText(data.getPhone());
        //stateProvinceComboBox.setValue(data.getDivisionId());

    }

    public void saveButtonClick(ActionEvent actionEvent) throws SQLException {
        CustomerCRUD.modifyCustomer(customerNameBox.getText(),
                addressBox.getText(), postalCodeBox.getText(), phoneBox.getText(),
                Integer.parseInt(String.valueOf(stateProvinceComboBox.getValue())));
    }

    public void cancelButtonClick(ActionEvent actionEvent) {
    }

    public void countryComboBoxClick(ActionEvent actionEvent) {
    }

}

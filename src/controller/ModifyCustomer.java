package controller;

import helper.AppointmentsCRUD;
import helper.CountriesCRUD;
import helper.CustomerCRUD;
import helper.DivisionCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;
import model.Customers;
import model.Divisions;

import java.io.IOException;
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

    public void receiveCustomerData(Customers data) {

        customerIdBox.setText(String.valueOf(data.getCustomerId()));
        customerNameBox.setText(data.getCustomerName());
        addressBox.setText(data.getAddress());
        postalCodeBox.setText(data.getPostalCode());
        phoneBox.setText(data.getPhone());
        Countries c = CountriesCRUD.getCountryByDivision(data.getDivisionId());
        countryComboBox.setValue(c);
        stateProvinceComboBox.setItems(DivisionCRUD.getCountryDivisions(c.getCountryId()));
        Divisions d = DivisionCRUD.getDivision(data.getDivisionId());
        stateProvinceComboBox.setValue(d);

    }

    public void saveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        CustomerCRUD.modifyCustomer(customerNameBox.getText(),
                addressBox.getText(), postalCodeBox.getText(), phoneBox.getText(),
                stateProvinceComboBox.getValue().getDivisionId(), Integer.parseInt(customerIdBox.getText()));

        Parent root = FXMLLoader.load(getClass().getResource("/view/customerTable.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customerTable.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void countryComboBoxClick(ActionEvent actionEvent) {
    }

}

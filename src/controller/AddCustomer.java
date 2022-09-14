package controller;

import helper.AppointmentsCRUD;
import helper.CountriesCRUD;
import helper.CustomerCRUD;
import helper.DivisionCRUD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Divisions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    public Button saveButton;
    public Button cancelButton;
    public TextField customerIdBox;
    public TextField customerNameBox;
    public TextField addressBox;
    public TextField postalCodeBox;
    public TextField phoneBox;
    public ComboBox<Divisions> stateProvinceComboBox;
    public ComboBox<Countries> countryComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(CountriesCRUD.getAllCountries());

    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        /*CustomerCRUD.insertCustomer(customerNameBox.getText(), addressBox.getText(),
                Integer.parseInt(postalCodeBox.getText()), Integer.parseInt(phoneBox.getText()),
                Integer.parseInt(String.valueOf(stateProvinceComboBox.getValue())),
                Integer.parseInt(String.valueOf(countryComboBox.getValue())));

        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
        stage.setScene(scene);
        stage.show();*/
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
        int countryId = countryComboBox.getValue().getCountryId();
        System.out.println("Country ID = " + countryId);
        ObservableList<Divisions> list = DivisionCRUD.getCountryDivisions(countryId);

        stateProvinceComboBox.setItems(DivisionCRUD.getCountryDivisions(countryId));
    }


}

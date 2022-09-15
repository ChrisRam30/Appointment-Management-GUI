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
import java.sql.SQLException;
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
    public TextField divisionIdBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(CountriesCRUD.getAllCountries());

    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException, SQLException {
        CustomerCRUD.insertCustomer(customerNameBox.getText(), addressBox.getText(),
                postalCodeBox.getText(), phoneBox.getText(),
               stateProvinceComboBox.getValue().getDivisionId());

        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointment Menu");
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
        int countryId = countryComboBox.getValue().getCountryId();
        ObservableList<Divisions> list = DivisionCRUD.getCountryDivisions(countryId);

        stateProvinceComboBox.setItems(DivisionCRUD.getCountryDivisions(countryId));
    }


}

package controller;


import Lambdas.TimeZone;
import helper.AppointmentsCRUD;
import helper.JDBC;
import helper.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.User;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
/**This controller allows for login to the application
 * The view will automatically change language to french or english dependent on local machine settings.*/
public class Login implements Initializable {
    public TextField userNameField;
    public TextField passwordField;
    public Button loginButton;
    public Label timeZone;
    public Button Close;
    public Label userNameLabel;
    public Label PasswordLabel;
    public Label timeZoneLabel;
    public Label languageLabel;
    public Label displayLanguage;
    ResourceBundle rb = ResourceBundle.getBundle("Lang", Locale.getDefault());

    /**LAMBDA USED in the initialize to change the zoneID based on the the local machines settings, This method initializes the controller.
     * Method will convert the langauge to FR or ENG dependent on local machine settings. Time zone is updated to display local machine settings.
     * @param resourceBundle Language resources bundles utilizes to convert languge to eng or FR
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //This labmda is utilized to update/convert zoneID's based on the local machines time and display it.
        TimeZone time = now -> {
            String product = now.toString();
            return product;
        };

        timeZone.setText(time.getTime(ZoneId.systemDefault()));

        //timeZone.setText(ZoneId.systemDefault().toString());


        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            userNameLabel.setText(rb.getString("userName"));
            PasswordLabel.setText(rb.getString("passwordLabel"));
            loginButton.setText(rb.getString("loginButton"));
            languageLabel.setText(rb.getString("languageLabel"));
            timeZoneLabel.setText(rb.getString("timeZoneLabel"));
            Close.setText(rb.getString("Close"));
            displayLanguage.setText(rb.getString("displayLang"));
        }



    }


    /**This method logs the user into application to access the appointments.
     *Method will check for correct username/password and if there are any appointments within 15 minutes of logging in.
     */
    public void loginButtonAction(ActionEvent actionEvent) throws SQLException, IOException {

        try {
            User vUser = UserCRUD.validateUser(userNameField.getText(), passwordField.getText());
            if (vUser != null) {

                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime laterTime = currentTime.plusMinutes(15);
                boolean noUpcoming = true;

                for(Appointments appt : AppointmentsCRUD.getAllAppointments()) {
                    if(vUser.getId() == appt.getUserId()) {
                        if((appt.getStartDateTime().toLocalDateTime().isEqual(currentTime) ||
                                appt.getStartDateTime().toLocalDateTime().isAfter(currentTime))
                        && (appt.getStartDateTime().toLocalDateTime().isEqual(laterTime) ||
                                appt.getStartDateTime().toLocalDateTime().isBefore(laterTime))){
                            noUpcoming = false;
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Appointment Warning");
                            alert.setContentText("Appointment in 15 Minutes\n ID:"+ appt.getAppointmentId() + " " + appt.getStartDateTime());
                            alert.showAndWait();
                            break;
                        }
                    }
                }

                if(noUpcoming) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("No Upcoming Appointments");
                    alert.showAndWait();
                }

                Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentMenu.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Appointment Menu");
                stage.setScene(scene);
                stage.show();
                String userName = userNameField.getText();

                loginTracker(userName, true);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                        alert.setContentText(rb.getString("incorrectLogin"));
                    String userName = userNameField.getText();
                    loginTracker(userName, false);

                }
                    Optional<ButtonType> result = alert.showAndWait();
            }
            }catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        }

    public void languageTabAction(ActionEvent actionEvent) {
    }
/**This method will close the program.*/
    public void closeButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            alert.setContentText(rb.getString("exit"));
            }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Locale.setDefault(new Locale("en", "US")); //used to check if languages are auto changing
            System.exit(0);
        }
    }
/**This method tracks successful and failed logins.
 * The login history is stored in a file named login_activity.txt.
 * @param userName Username that is inputted.
 * @param login If login was successful or failed.
 * */
    public void  loginTracker(String userName, boolean login) throws IOException {
        String filename = "login_activity.txt";
        Scanner keyboard = new Scanner(System.in);
        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        if (login == true) {
            outputFile.println(userName + " " + LocalDate.now() + " " + LocalTime.now() + " Login Success!");
        }
        if (login == false) {
            outputFile.println(userName + " " + LocalDate.now() + " " + LocalTime.now() + " Login Failure.");
        }
        outputFile.close();
    }
}

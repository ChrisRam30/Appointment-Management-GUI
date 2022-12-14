package Main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;

import java.util.Scanner;
import java.util.TimeZone;

/**This class creates an appointment application*/
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }


/**This is the main method. This is used to open a connection to the mysql workbench database via JDBC, this also sets the local timezone based on local computer settings. */
    public static void main(String[] args)  {

        //Locale.setDefault(new Locale("fr", "FR")); //used to check if languages are auto changing

        JDBC.openConnection();

        LocalDate parisDate = LocalDate.of(2019, 10, 26);
        LocalTime parisTime = LocalTime.of(01,00);
        ZoneId parisZoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime parisZDT = ZonedDateTime.of(parisDate, parisTime, parisZoneId);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        Instant parisToGMTInstant = parisZDT.toInstant();
        ZonedDateTime parisToLocalZDT = parisZDT.withZoneSameInstant(localZoneId);
        ZonedDateTime gmtToLocalZDT = parisToGMTInstant.atZone(localZoneId);

        launch(args);

        JDBC.closeConnection();
    }
}
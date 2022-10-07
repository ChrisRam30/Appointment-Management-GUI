package model;

import helper.AppointmentsCRUD;
import helper.ContactsCRUD;
import helper.CountriesCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalTime;

/**Class for appointment objects
 *
 */
public class Appointments {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private int contactId;
    private String type;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private int customerId;
    private int userId;

    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();


    /**Method defines the appointment object type
     *
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param customerId
     * @param userId
     */
    public Appointments(int appointmentId, String title, String description, String location, int contactId,
                        String type, Timestamp startDateTime, Timestamp endDateTime, int customerId, int userId) {

        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**appointmentID getter.
     *
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**AppointmentID setter
     *
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**title getter
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**title setter
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**description getter
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**description setter
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**location getter
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**location setter
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**contactID getter
     *
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**contactID setter
     *
     * @param contact
     */
    public void setContactId(int contact) {
        this.contactId = contactId;
    }

    /**type getter
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**type setter
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**startdatetime getter
     *
     * @return
     */
    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    /**startdatetime stter
     *
     * @param startDateTime
     */
    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**enddatetime getter
     *
     * @return
     */
    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    /**enddatetime setter
     *
     * @param endDateTime
     */
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**customerID getter
     *
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**customerID setter
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**userID getter
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**userID setter
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }


    /**contact getter
     *
     * @return
     */
    public Contacts getContacts() {
        return null;
    }

    /**contact setter
     *
     * @return
     */
    public Contacts getContactName() {
        return ContactsCRUD.getContacts(contactId);
    }


    /**Method that converts contact ID into a string
     *
     * @return
     */
    @Override
    public String toString() {

        return(Integer.toString(getContactId()));

    }

    /**method that creates a list of local times in a list
     *
     * @return
     */
    public static ObservableList<LocalTime> getZoneTime() {
        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(1, 00);
        LocalTime end = LocalTime.MIDNIGHT.minusHours(1);
        while (start.isBefore(end.plusSeconds(1))) {
            timeList.add(start);
            start = start.plusMinutes(30);

        }
        return timeList;
    }

}
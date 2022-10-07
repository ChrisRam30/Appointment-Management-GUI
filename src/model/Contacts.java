package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class for contact objects
 *
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**Method defines contact objects
     *
     * @param contactId
     * @param contactName
     * @param email
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** Getter for contact ID
     *
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /** Setter for contact ID
     *
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**getter for contact name
     *
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**setter for contact name
     *
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** getter for email
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**setter for email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**Converts contact name into string
     *
     * @return
     */
    @Override
    public String toString() {
        return(getContactName());
    }

}

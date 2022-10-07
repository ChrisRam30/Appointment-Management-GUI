package model;

import helper.CountriesCRUD;
import helper.DivisionCRUD;

/**Class for customer objects
 *
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String phone;
    private String postalCode;
    private int divisionId;

    /**method defines customer objects
     *
     * @param customerId
     * @param customerName
     * @param phone
     * @param address
     * @param postalCode
     * @param divisionId
     */
    public Customers(int customerId, String customerName, String phone, String address, String postalCode, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.divisionId = divisionId;
    }

    /**getter for customer id
     *
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**setter for customer id
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**getter for customername
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**setter for customer name
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**getter for phone
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**setter for phone
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**getter for address
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**setter for address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**getter for postal code
     *
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**setter for postal code
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**getter for division id
     *
     * @return
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**setter for division id
     *
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**getter for division
     *
     * @return
     */
    public Divisions getDivision() {
        return null;
    }

    /**method that calls the country CRUD getcountrybydivision
     *
     * @return
     */
    public Countries getCountry() {
        return CountriesCRUD.getCountryByDivision(divisionId);
    }

    /**method that calls the division CRUD get division by country id
     *
     * @return
     */
    public Divisions getDivisionName() { return DivisionCRUD.getDivisionByCountryId(divisionId);
    }

    /**converts customer name to string
     *
     * @return
     */
    @Override
    public String toString() {
        return(getCustomerName());
    }
}


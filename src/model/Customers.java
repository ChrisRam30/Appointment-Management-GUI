package model;

import helper.CountriesCRUD;
import helper.DivisionCRUD;

public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String phone;
    private String postalCode;
    private int divisionId;

    public Customers(int customerId, String customerName, String phone, String address, String postalCode, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.divisionId = divisionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public Divisions getDivision() {
        return null;
    }

    public Countries getCountry() {
        return CountriesCRUD.getCountryByDivision(divisionId);
    }

    public Divisions getDivisionName() { return DivisionCRUD.getDivisionByCountryId(divisionId);
    }

    @Override
    public String toString() {
        return(getCustomerName());
    }
}


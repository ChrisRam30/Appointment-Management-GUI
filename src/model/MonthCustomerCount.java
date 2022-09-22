package model;

public class MonthCustomerCount {
    String monthName;
    int customerId;
    int count;

    public MonthCustomerCount(String monthName, int customerId, int count) {
        this.monthName = monthName;
        this.customerId = customerId;
        this.count = count;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerName(int customerId) {
        this.customerId = customerId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

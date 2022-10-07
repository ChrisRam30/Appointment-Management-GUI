package model;

/**Class for customer count by month reports table
 *
 */
public class MonthCustomerCount {
    String monthName;
    int customerId;
    int count;

    /**Monthcustomercount objects are defined
     *
     * @param monthName
     * @param customerId
     * @param count
     */
    public MonthCustomerCount(String monthName, int customerId, int count) {
        this.monthName = monthName;
        this.customerId = customerId;
        this.count = count;
    }

    /**getter for getmonthname
     *
     * @return
     */
    public String getMonthName() {
        return monthName;
    }

    /**setter for getmonthname
     *
     * @param monthName
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    /**getter for customerid
     *
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**setter for customerid
     *
     * @param customerId
     */
    public void setCustomerName(int customerId) {
        this.customerId = customerId;
    }

    /**getter for count
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**setter for count
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
}

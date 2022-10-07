package model;

/**class for the amount of type by month reports table
 *
 */
public class MonthTypeCount {
    String monthName;
    String typeName;
    int count;

    /**monthtypecount objects defined
     *
     * @param monthName
     * @param typeName
     * @param count
     */
    public MonthTypeCount(String monthName, String typeName, int count) {
        this.monthName = monthName;
        this.typeName = typeName;
        this.count = count;
    }

    /**monthname getter
     *
     * @return
     */
    public String getMonthName() {
        return monthName;
    }

    /**setter monthname
     *
     * @param monthName
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    /**typename getter
     *
     * @return
     */
    public String getTypeName() {
        return typeName;
    }

    /**typename setter
     *
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**count getter
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**count setter
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
}

package model;

/**Class for division objects
 *
 */
public class Divisions {

    private int divisionId;
    private String divisionName;
    private int countryId;

    /**division objects are defined
     *
     * @param divisionId
     * @param divisionName
     * @param countryId
     */
    public Divisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
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

    /**getter for division name
     *
     * @return
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**setter for division name
     *
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**getter for countryid
     *
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**setter for countryid
     *
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**converts division name into a string
     *
     * @return
     */
   @Override
    public String toString() {
        return(getDivisionName());
    }
}

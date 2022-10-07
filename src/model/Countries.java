package model;

/**Class for country objects
 *
 */
public class Countries {
    private int countryId;
    private String countryName;

    /**Method defines country objects
     *
     * @param countryId
     * @param countryName
     */
    public Countries(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**getter for country id
     *
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**setter for countryID
     *
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**Getter for country name
     *
     * @return
     */
    public String getCountryName() {
        return countryName;
    }

    /**setter for country name
     *
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**converts countryname into a string
     *
     * @return
     */
    @Override
    public String toString() {
        return(getCountryName());
    }
}

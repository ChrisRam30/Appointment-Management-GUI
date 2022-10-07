package model;

/**class for user objects
 *
 */
public class User {

    private int id;
    private String userName;
    private String password;

    /**user objects defined
     *
     * @param userId
     * @param userName
     * @param userPassword
     */
    public User(int userId, String userName, String userPassword) {
        this.id = userId;
        this.userName = userName;
        this.password = userPassword;
    }

    /**id getter
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**id setter
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**username getter
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**username setter
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**password getter
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**password setter
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** method converts username into a string
     *
     * @return
     */
    @Override
    public String toString() {
        return(getUserName());
    }
}

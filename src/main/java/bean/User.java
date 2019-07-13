package bean;

public class User {
    private String id = null;
    private String userName = null;
    private String pwd = null;
    private String phoneNumber = null;
    private String Email = null;
    private Setting setting = null;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }

    public User(String phoneNumber, String pwd) {
        this.phoneNumber = phoneNumber;
        this.pwd = pwd;
    }

    public Setting getSetting() {
        return setting;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return Email;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
package ro.sapientia.ms.sapientiaorarend.models;

public class User {

    private String name;
    private String email;
    private String phonenumber;
    private String deparment;

    public User(String name, String email, String phonenumber, String deparment) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.deparment = deparment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }
}

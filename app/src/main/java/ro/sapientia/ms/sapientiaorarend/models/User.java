package ro.sapientia.ms.sapientiaorarend.models;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String phonenumber;
    private String deparment;
    private TimeTable timetable;




    public User(String name, String email, String phonenumber, String deparment, TimeTable timetable) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.deparment = deparment;
        this.timetable = timetable;

    }




    public TimeTable getTimetable() {
        return timetable;
    }

    public void setTimetable(TimeTable timetable) {
        this.timetable = timetable;
    }

    public User(String name, String email, String phonenumber, String deparment) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.deparment = deparment;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", deparment='" + deparment + '\'' +
                ", timetable=" + timetable +
                '}';
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

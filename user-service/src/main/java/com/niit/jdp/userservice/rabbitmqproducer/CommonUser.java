/*
 * Author Name: Mohini
 * Date: 1/17/2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.userservice.rabbitmqproducer;
import java.util.List;
public class CommonUser {
    private String id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String role;
    private List<String> interest;
    private long phone;

    public CommonUser() {
    }

    public CommonUser(String id, String name, String email, String password, String city, String role, List<String> interest, long phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.role = role;
        this.interest = interest;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", role='" + role + '\'' +
                ", interest=" + interest +
                ", phone=" + phone +
                '}';}


}

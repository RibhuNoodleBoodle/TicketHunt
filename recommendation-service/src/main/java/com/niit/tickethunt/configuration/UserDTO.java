package com.niit.tickethunt.configuration;

import org.json.simple.JSONObject;

import java.util.List;


public class UserDTO {
    private String name;
    private String email;
    private String city;
    private String role;
    private List<String> interest;
    private long phone;

    private JSONObject jsonObject;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String city, String role, List<String> interest, long phone) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.role = role;
        this.interest = interest;
        this.phone = phone;
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

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}

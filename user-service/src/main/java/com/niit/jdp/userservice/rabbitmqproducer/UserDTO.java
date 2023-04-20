/*
 * Author Name: Mohini
 * Date: 1/18/2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.userservice.rabbitmqproducer;

public class UserDTO {
    private String email;
    private String password;
    private String role;


    public UserDTO() {
    }

    public UserDTO(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
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


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}

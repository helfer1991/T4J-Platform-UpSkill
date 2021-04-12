/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.domain;

/**
 *
 * @author joaor
 */
public class User {

    private String username;
    private String password;
    private String telefone;
    private Email email;
    private Role role;

    /**
     * User builder with the following parameters:
     *
     * @param username
     * @param password
     * @param email
     * @param role
     */
    public User(String username, String password, String telefone, Email email, Role role) {
        setUsername(username);
        setPassword(password);
        setTelefone(telefone);
        setEmail(email);
        setRole(role);
    }

    public User(String username, String password, Email email, String telefone) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setTelefone(telefone);
    }

    /**
     * Method for obtaining a username.
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Validates a username.
     *
     * @param username
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for obtaining a password.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Validates a password.
     *
     * @param password
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for obtaining an email.
     *
     * @return
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Validates an email.
     *
     * @param email
     */
    private void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Method for obtaining a role.
     *
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     * Validates a role.
     *
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    public String getTelefone() {
        return telefone;
    }

    private void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Method to check if two objects (users) are the same.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getUsername().equals(user.getUsername())
                && getPassword().equals(user.getPassword())
                && getEmail().equals(user.getEmail())
                && getRole() == user.getRole();
    }

    /**
     * Returns a string representation with very concise but precise information
     * about the object and its attributes.
     *
     * @return a string representation of the object (user).
     *
     */
    @Override
    public String toString() {
        return "User/Utilizador{"
                + "username='" + username + '\''
                + ", email=" + email
                + ", role=" + role
                + '}';
    }

}

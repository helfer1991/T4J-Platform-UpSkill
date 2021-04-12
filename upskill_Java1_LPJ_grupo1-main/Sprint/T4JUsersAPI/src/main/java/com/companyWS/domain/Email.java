/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author joaor
 */
public class Email {

    
    private String email;

    /**
     * Instantiates a new Email with set parameter.
     *
     * @param email the email
     */
    public Email(String email) {
        setEmail(email);
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email invalido.");
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation on non-null
     * object references:
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email1 = (Email) o;
        return Objects.equals(getEmail(), email1.getEmail());
    }

    /**
     * Returns a string representation of the object.
     *
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return email;
    }
}

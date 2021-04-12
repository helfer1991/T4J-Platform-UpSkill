/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.domain;

import java.sql.Date;
import java.time.LocalDate;
/**
 *
 * @author joaor
 */
public class Session {

    private User user;
    private Context context;
    private Date loginDate;

    public Session(User user, Context context) {
        setUser(user);
        setContext(context);
        setDate(Date.valueOf(LocalDate.now()));
    }
    
      public Session(User user, Context context, Date date) {
        setUser(user);
        setContext(context);
        setDate(date);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Date getDate() {
        return loginDate;
    }

    public void setDate(Date date) {
        this.loginDate = date;
    }

    public void setContextInvalid() {
        this.context.setValid(false);
    }

    public boolean isValid() {
        return this.context.isValid();
    }
}

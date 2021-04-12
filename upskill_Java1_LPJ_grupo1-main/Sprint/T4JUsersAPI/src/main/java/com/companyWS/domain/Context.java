/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.domain;

import com.companyWS.exception.InvalidAppKeyException;
import java.util.Random;

/**
 *
 * @author joaor
 */
public class Context {

    private String context;
    private boolean valid;

    private static final String APP_KEY = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";

    public Context (String appKey) {
        if (appKey.equals(APP_KEY)) {
            setContext(generateContext());
            setValid(true);
        } else {
            throw new InvalidAppKeyException("App Key invalida");
        }
    }

    public Context (String contextKey, boolean valido) {
        this.context = contextKey;
        this.valid = valido;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String generateContext() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

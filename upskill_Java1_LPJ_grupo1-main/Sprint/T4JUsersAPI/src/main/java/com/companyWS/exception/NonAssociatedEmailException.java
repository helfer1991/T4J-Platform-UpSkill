/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.exception;

/**
 *
 * @author joaor
 */
public class NonAssociatedEmailException extends IllegalArgumentException  {

    public NonAssociatedEmailException(String s) {
        super(s);
    }
}

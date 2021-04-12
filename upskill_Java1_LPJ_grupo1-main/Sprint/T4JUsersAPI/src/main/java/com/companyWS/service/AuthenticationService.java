/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.service;

import com.companyWS.domain.Context;
import com.companyWS.domain.Email;
import com.companyWS.domain.Session;
import com.companyWS.domain.User;
import com.companyWS.dto.ContextDTO;
import com.companyWS.dto.LoginDTO;
import com.companyWS.dto.Mapper;
import com.companyWS.dto.SessionDTO;
import com.companyWS.exception.ConversionException;
import com.companyWS.exception.InvalidAppKeyException;
import java.sql.SQLException;
import com.companyWS.persistence.RepositorioAuthentication;
import com.companyWS.persistence.RepositorioUser;

/**
 *
 * @author joaor
 */
public class AuthenticationService {

    public static ContextDTO generateContext(String appKey) throws Exception {
        Context context = null;

        try {
            context = new Context(appKey);
        } catch (InvalidAppKeyException apkex) {
            return null;
        }

        ContextDTO contextDTO = Mapper.context2ContextDTO(context);

        if (contextDTO != null) {
            RepositorioAuthentication.getInstance().insertContext(context);
            return contextDTO;
        } else {
            throw new ConversionException("ContextDTO");
        }
        
    }

    public static boolean validateContext(ContextDTO contextDTO) throws Exception {
        Context context = RepositorioAuthentication.getInstance().getContextByString(contextDTO.getAppContext());

        return context.isValid();
    }

    public static void login(LoginDTO loginDTO, ContextDTO contextDTO) throws Exception {
        User user = RepositorioUser.getInstance().getUtilizadorByEmail(new Email(loginDTO.getEmail()));

        if (!(user != null && user.getPassword().equals(loginDTO.getPassword()))) {
            throw new IllegalArgumentException("Utilizador não existe ou password invalida");
        }

        Context context = RepositorioAuthentication.getInstance().getContextByString(contextDTO.getAppContext());
        Session session = new Session(user, context);
        SessionDTO sessionDTO = Mapper.session2SessionDTO(session);

        if (sessionDTO != null) {
            try {
                RepositorioAuthentication.getInstance().insertSession(session);
            } catch (SQLException e) {
                throw new SQLException(e.getMessage());
            }
        } else {
            throw new ConversionException("ContextDTO");
        }
    }

    public static void closeContext(ContextDTO contextDTO) throws Exception {
        try {
            RepositorioAuthentication.getInstance().closeContext(contextDTO.getAppContext());
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static SessionDTO getSession(ContextDTO contextDTO) throws Exception {
        Session session = RepositorioAuthentication.getInstance().getSessionByContext(contextDTO.getAppContext());

        if (session == null) {
            return null;
        }

        SessionDTO sessionDTO = Mapper.session2SessionDTO(session);
        if (sessionDTO != null) {
            return sessionDTO;
        } else {
            throw new ConversionException("SessionDTO");
        }
    }
    

}

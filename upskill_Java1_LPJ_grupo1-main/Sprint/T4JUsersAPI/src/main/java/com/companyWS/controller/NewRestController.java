 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyWS.controller;

import com.companyWS.domain.Email;
import com.companyWS.domain.Role;
import com.companyWS.dto.ContextDTO;
import com.companyWS.dto.ErroDTO;
import com.companyWS.dto.LoginDTO;
import com.companyWS.dto.RoleDTO;
import com.companyWS.dto.RoleListDTO;
import com.companyWS.dto.SessionDTO;
import com.companyWS.dto.UserDTO;
import com.companyWS.persistence.RepositorioRoles;
import com.companyWS.service.AuthenticationService;
import com.companyWS.service.UsersService;
import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pbs
 */
@RestController
public class NewRestController {

    @RequestMapping(value = "/test",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole("desc");
        roleDTO.setDescricao("design");
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/context",
            method = RequestMethod.GET,
            params = {"app_key"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContext(@RequestParam("app_key") String appKey) {
        try {

            ContextDTO contextDTO = AuthenticationService.generateContext(appKey);
            if (contextDTO != null) {
                return new ResponseEntity<>(contextDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/registerUser",
            method = RequestMethod.POST,
            params = {"app_context", "username", "email", "telefone", "password"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestParam("app_context") String appContext,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("password") String password) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(username);
            Email Emailobj = new Email(email);
            userDTO.setEmail(Emailobj);
            userDTO.setPassword(password);
            userDTO.setTelefone(telefone);

            UsersService.registerUser(userDTO);
            AuthenticationService.closeContext(contextDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/registerUserWithRoles",
            method = RequestMethod.POST,
            params = {"app_context", "username", "email", "telefone", "password", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUserWithRoles(@RequestParam("app_context") String appContext,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("password") String password,
            @RequestParam("rolenames") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserDTO userDTO = new UserDTO();
            Role role = RepositorioRoles.getInstance().getRole(rolename);
            userDTO.setUserName(username);
            Email Emailobj = new Email(email);
            userDTO.setEmail(Emailobj);
            userDTO.setTelefone(telefone);
            userDTO.setPassword(password);
            userDTO.setRole(role);

            UsersService.registerUserWithRoles(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            params = {"app_context", "user_id", "password"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestParam("app_context") String appContext,
            @RequestParam("user_id") String email,
            @RequestParam("password") String password) {
        try {
            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setEmail(email);
            loginDTO.setPassword(password);

            try {
                AuthenticationService.login(loginDTO, contextDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (SQLException ex) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/logout",
            method = RequestMethod.POST,
            params = {"app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@RequestParam("app_context") String appContext) {
        try {
            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            try {
                AuthenticationService.closeContext(contextDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (SQLException ex) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @RequestMapping(value = "/userRoles",
            method = RequestMethod.GET,
            params = {"app_context", "user_id"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserRoles(@RequestParam("app_context") String appContext,
            @RequestParam("user_id") String email) {

        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RoleDTO roleDTO = UsersService.getUserRoles(email);
            if (roleDTO != null) {
                return new ResponseEntity<>(roleDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles",
            method = RequestMethod.POST,
            params = {"app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoleToUser(@RequestParam("app_context") String appContext,
            @RequestParam("user_id") String email,
            @RequestParam("rolenames") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UsersService.deleteRoleFromUser(email, rolename);
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles",
            method = RequestMethod.DELETE,
            params = {"app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRoleFromUser(@RequestParam("app_context") String appContext,
            @RequestParam("user_id") String email,
            @RequestParam("rolenames") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UsersService.deleteRoleFromUser(email, rolename);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            params = {"app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userRoles(@RequestParam("app_context") String appContext) {
        try {
            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RoleListDTO listaRoleDTO = UsersService.getRoles();
            if (listaRoleDTO != null) {
                return new ResponseEntity<>(listaRoleDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            params = {"app_context", "rolename", "description"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserRole(@RequestParam("app_context") String appContext,
            @RequestParam("rolename") String role,
            @RequestParam("description") String descricao) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRole(role);
            roleDTO.setDescricao(descricao);

            UsersService.createUserRole(roleDTO);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.DELETE,
            params = {"app_context", "rolename"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserRole(@RequestParam("app_context") String appContext,
            @RequestParam("rolename") String rolename) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UsersService.deleteUserRole(rolename);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/session",
            method = RequestMethod.GET,
            params = {"app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSession(@RequestParam("app_context") String appContext) {
        try {

            ContextDTO contextDTO = new ContextDTO(appContext);

            if (!AuthenticationService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            SessionDTO sessionDTO = UsersService.getSession(contextDTO);
            if (sessionDTO != null) {
                return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

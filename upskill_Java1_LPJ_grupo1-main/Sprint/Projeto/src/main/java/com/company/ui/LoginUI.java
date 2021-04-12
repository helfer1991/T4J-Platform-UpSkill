/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.model.Plataforma;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.setUserAgentStylesheet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class LoginUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();


    private Stage novoOrgStage;
    private Stage mainAppStage;

    @FXML
    private Button btnRegistarOrg;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtLoginEmail;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("");
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_ORG_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novoOrgStage = new Stage();
            novoOrgStage.initModality(Modality.APPLICATION_MODAL);
            novoOrgStage.setTitle(Constantes.REGISTO_ORG_TITLE);
            novoOrgStage.setResizable(false);
            
            if(tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }
            
            novoOrgStage.setScene(scene);

            RegistarOrgUI registarOrgUI = loader.getController();
            registarOrgUI.associarParentUI(this);
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }

    }

    @FXML
    private void actionRegistarOrg(ActionEvent event) {
        novoOrgStage.show();
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
        Window window = txtLoginEmail.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void actionLogin(ActionEvent event) {
        Plataforma plat = Plataforma.getInstance();
        if (plat.getUsersAPI().login(txtLoginEmail.getText(), txtPassword.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.MAIN_APP_FILE));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                mainAppStage = new Stage();
                mainAppStage.initModality(Modality.APPLICATION_MODAL);
                mainAppStage.setTitle(Constantes.MAIN_APP_TITLE);
                mainAppStage.setResizable(false);
                
                if(tema.isEnable()) {
                    scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
                } else {
                    scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
                }
                
                mainAppStage.setScene(scene);

                mainAppStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                                "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                            event.consume();
                        }
                    }
                });

                MainSceneUI mainSceneUI = loader.getController();
                mainSceneUI.associarParentUI(this);

                Window window = txtLoginEmail.getScene().getWindow();
                window.hide();

                mainAppStage.show();
            } catch (IOException ex) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
            }
        } else {
            Alert loginError
                    = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO, "Erro no Login", "Email/Password Errado");

            loginError.showAndWait();

        }

    }
}

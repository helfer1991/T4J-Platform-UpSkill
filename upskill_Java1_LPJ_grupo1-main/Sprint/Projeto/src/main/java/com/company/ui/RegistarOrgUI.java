/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarOrganizacaoController;
import static com.company.ui.App.TITULO_APLICACAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class RegistarOrgUI implements Initializable {

    private RegistarOrganizacaoController registarOrgController;
    private LoginUI loginUI;

    @FXML
    private Button btnRegistarOrg;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtSignUpManagerEmail;
    @FXML
    private TextField txtSignUpOrgName;
    @FXML
    private TextField txtSignUpOrgNIF;
    @FXML
    private TextField txtSignUpOrgZipCode;
    @FXML
    private TextField txtSignUpOrgLocale;
    @FXML
    private TextField txtSignUpOrgWebSite;
    @FXML
    private TextField txtSignUpOrgEmail;
    @FXML
    private TextField txtSignUpManagerName;
    @FXML
    private TextField txtSignUpManagerPhone;
    @FXML
    private TextField txtSignUpAdress;
    @FXML
    private TextField txtSignUpOrgPhone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnRegistarOrgAction(ActionEvent event) {
        try {
            RegistarOrganizacaoController controller = new RegistarOrganizacaoController();
            controller.registarOrganizacao(txtSignUpOrgName.getText(), txtSignUpOrgNIF.getText(), txtSignUpOrgWebSite.getText(),
                    txtSignUpOrgPhone.getText(), txtSignUpOrgEmail.getText(), txtSignUpAdress.getText(), txtSignUpOrgZipCode.getText(),
                    txtSignUpOrgLocale.getText(), txtSignUpManagerName.getText(), txtSignUpManagerEmail.getText(), txtSignUpManagerPhone.getText());
                
            showAACreationSuccess();
            encerrarNovaOrgUI(event);
        } catch (Exception e) {
            showAACreationError(e);
        }
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Organização adicionado");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showAACreationError(Exception e) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Dados de Organização Errados", e.getMessage());
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    @FXML
    private void btnCancelAction(ActionEvent event) {
        if (txtSignUpManagerEmail.getText().isEmpty() && txtSignUpOrgName.getText().isEmpty() && txtSignUpOrgNIF.getText().isEmpty()
                && txtSignUpOrgPhone.getText().isEmpty() && txtSignUpOrgZipCode.getText().isEmpty() && txtSignUpOrgLocale.getText().isEmpty()
                && txtSignUpOrgWebSite.getText().isEmpty() && txtSignUpOrgEmail.getText().isEmpty() && txtSignUpManagerName.getText().isEmpty()
                && txtSignUpManagerPhone.getText().isEmpty() && txtSignUpAdress.getText().isEmpty()) {
            encerrarNovaOrgUI(event);
        } else {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                    "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                event.consume();
            } else {
                encerrarNovaOrgUI(event);
            }
        }
    }

    private void encerrarNovaOrgUI(ActionEvent event) {
        this.txtSignUpManagerEmail.clear();
        this.txtSignUpOrgName.clear();
        this.txtSignUpOrgNIF.clear();
        this.txtSignUpOrgPhone.clear();
        this.txtSignUpOrgZipCode.clear();
        this.txtSignUpOrgLocale.clear();
        this.txtSignUpOrgWebSite.clear();
        this.txtSignUpOrgEmail.clear();
        this.txtSignUpManagerName.clear();
        this.txtSignUpManagerPhone.clear();
        this.txtSignUpAdress.clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    void associarParentUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    @FXML
    private void txtSignUpManagerEmailMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtSignUpManagerEmail.getText().length() > 49) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Tamanho de Email é 50 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpOrgNameMaxSizeisLetter(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;

            if (event.getCode().isDigitKey()) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Nome tem de ser to tipo Alfabetico (A-Z)");

            } else if (txtSignUpOrgName.getText().length() > 49) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Tamanho de Nome é 50 caracteres");
            }

            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpOrgNIFMaxSizeisDigit(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;

            if (!event.getCode().isDigitKey()) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "NIF tem de ser Numerico (0-9)");
            } else if (txtSignUpOrgNIF.getText().length() > 8) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo numeros", "NIF tem de conter 9 digitos");
            }

            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpOrgZipCodeMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {

            if (txtSignUpOrgZipCode.getText().length() > 7) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo numeros", "ZIP pode conter no maximo 8 digitos e Tem de ser do Tipo XXXX-XXX (x = Numero)");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpOrgLocaleMaxSizeisLetter(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;

            if (event.getCode().isDigitKey()) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Localidade tem de ser to tipo Alfabetico (A-Z)");
            } else if (txtSignUpOrgLocale.getText().length() > 49) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Tamanho de Localidade máximo é 50 caracteres");
            }
            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpOrgWebSiteMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtSignUpOrgWebSite.getText().length() > 49) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Tamanho Maximo de Website é 50 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpOrgEmailMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtSignUpOrgEmail.getText().length() > 49) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Tamanho Maximo de Email é 50 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpManagerNameMaxSizeisLetter(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;

            if (event.getCode().isDigitKey()) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Nome de Gestor tem de ser to tipo Alfabetico (A-Z)");
            } else if (txtSignUpManagerName.getText().length() > 49) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Tamanho de Nome de Gestor é 50 caracteres");
            }

            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpManagerPhoneMaxSizeisDigit(KeyEvent event) {

        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;
            if (!event.getCode().isDigitKey()) {

                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Telefone de Gestor tem de ser Numerico (0-9)");
            }
            if (txtSignUpManagerPhone.getText().length() > 14) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo numeros", "Telefone de Gestor pode conter no maximo 15 digitos");
            }

            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtSignUpAdressMaxSize(KeyEvent event) {

        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtSignUpAdress.getText().length() > 49) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo numeros", "Morada pode conter no maximo 50 Caracteress");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }

        }
    }

    @FXML
    private void txtSignUpOrgPhoneMaxSizeisDigit(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;
            if (!event.getCode().isDigitKey()) {

                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Telefone de Organização tem de ser Numerico (0-9)");
            }
            if (txtSignUpOrgPhone.getText().length() > 14) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo numeros", "Telefone de Organização pode conter no maximo 15 digitos");
            }
            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }

        }

    }

}

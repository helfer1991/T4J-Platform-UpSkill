/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarColaboradorController;
import static com.company.ui.App.TITULO_APLICACAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarColaboradorUI implements Initializable {

    private TabColaboradoresSceneUI tabColaboradoresSceneUI;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnCancel;

    private RegistarColaboradorController cCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabColaboradoresSceneUI = new TabColaboradoresSceneUI();
    }

    void setCtrl(RegistarColaboradorController ctrl) {
        this.cCtrl = ctrl;
    }

    @FXML
    private void actionAdicionarNovoColaborador(ActionEvent event) {
        try {
            cCtrl.registaColaborador(txtName.getText().trim(), txtPhone.getText().trim(), txtEmail.getText().trim());
            showAACreationSuccess();
            tabColaboradoresSceneUI.updateList();
            encerrarNovoColaboradorUI(event);

        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        if (txtName.getText().isEmpty() && txtEmail.getText().isEmpty() && txtPhone.getText().isEmpty()) {
            encerrarNovoColaboradorUI(event);
        } else {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                    "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                event.consume();
            } else {
                encerrarNovoColaboradorUI(event);
            }
        }
    }

    void associarParentUI(TabColaboradoresSceneUI tabColaboradoresSceneUI) {
        this.tabColaboradoresSceneUI = tabColaboradoresSceneUI;
    }

    private void encerrarNovoColaboradorUI(ActionEvent event) {
        this.txtName.clear();
        this.txtEmail.clear();
        this.txtPhone.clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Colaborador adicionado");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao adicionar colaborador", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    @FXML
    private void txtNomeMaxSizeIsLetter(KeyEvent event) {
        Alert alerta = null;
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (event.getCode().isDigitKey()) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Nome tem de ser to tipo Alfabetico (A-Z)");
            } else if (txtName.getText().length() > 49) {
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
    private void txtEmailMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;
            if (txtPhone.getText().length() > 49) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Tamanho de Email é 50 caracteres");
            }
            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtPhoneMaxSizeisDigit(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            Alert alerta = null;
            if (!event.getCode().isDigitKey()) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Telefone tem de ser Numerico (0-9)");
            } else if (txtPhone.getText().length() > 14) {
                alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo numeros", "Telefone pode conter no maximo 15 digitos");
            }
            if (alerta != null) {
                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }
}

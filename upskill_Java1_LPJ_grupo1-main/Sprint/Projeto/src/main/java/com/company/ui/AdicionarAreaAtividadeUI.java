/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarAreaAtividadeController;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarAreaAtividadeUI implements Initializable {

    private TabAreasAtividadeUI tabAreasAtividadeUI;

    @FXML
    private TextField txtCodUnico;
    @FXML
    private TextField txtDescBreve;
    @FXML
    private TextArea txtDescDetalhada;
    @FXML
    private Button btnAdicionarNovaAreaTarefa;
    
    private RegistarAreaAtividadeController aaCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.aaCtrl = new RegistarAreaAtividadeController();
        tabAreasAtividadeUI = new TabAreasAtividadeUI();
    }

    @FXML
    private void actionAdicionarNovaAreaTarefa(ActionEvent event) {
        try {
            aaCtrl.registarAreaAtividade(txtCodUnico.getText(), txtDescBreve.getText(), txtDescDetalhada.getText());
            showAACreationSuccess();
            tabAreasAtividadeUI.updateList();
            encerrarNovaAreaAtividadeUI(event);
        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    void associarParentUI(TabAreasAtividadeUI tabAreasAtividadeUI) {
        this.tabAreasAtividadeUI = tabAreasAtividadeUI;
    }

    private void encerrarNovaAreaAtividadeUI(ActionEvent event) {
        this.txtCodUnico.clear();
        this.txtDescBreve.clear();
        this.txtDescDetalhada.clear();

        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Area adicionada");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO,
                "Erro ao Adicionar Area de Atividade.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    //Verificador de tamanho dos text Fields
    @FXML
    private void actionLimitTxtCodUnico(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (!event.getCode().isDigitKey()) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Codigo Unico tem de ser Numerico (0-9)");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void actionLimitTxtDescBreve(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtDescBreve.getText().length() > 24) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Breve é 25 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void actionLimitTxtDescDetalhada(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtDescDetalhada.getText().length() > 99) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Detalhada é 100 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

}

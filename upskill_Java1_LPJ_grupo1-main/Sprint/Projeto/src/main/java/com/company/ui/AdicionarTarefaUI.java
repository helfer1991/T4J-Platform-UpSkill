/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarTarefaController;
import static com.company.ui.App.TITULO_APLICACAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarTarefaUI implements Initializable {

    private TabTarefasUI tabTarefasUI;

    @FXML
    private TextField txtNovaTarefaRef;
    @FXML
    private TextField txtNovaTarefaDesign;
    @FXML
    private TextField txtNovaTarefaDuracao;
    @FXML
    private TextField txtNovaTarefaCusto;
    @FXML
    private TextArea txtNovaTarefaDescInformal;
    @FXML
    private TextArea txtNovaTarefaDescTecnica;
    @FXML
    private ComboBox<String> cmbNovaTarefaCatTarefa;

    private RegistarTarefaController tarCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void actionAddNovaTarefa(ActionEvent event) {
        try {
            tarCtrl.novaTarefa(txtNovaTarefaRef.getText().trim(), txtNovaTarefaDesign.getText().trim(), txtNovaTarefaDescInformal.getText().trim(),
                    txtNovaTarefaDescTecnica.getText().trim(), Integer.parseInt(txtNovaTarefaDuracao.getText().trim()),
                    Double.parseDouble(txtNovaTarefaCusto.getText().trim()), tarCtrl.getCategoriaTarefa(cmbNovaTarefaCatTarefa.getValue().split(" ")[0]), tarCtrl.getColaborador());

            showAACreationSuccess();
            tabTarefasUI.updateList();
            encerrarNovaTarefaUI(event);

        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }

    }

    void updateCatTarCMB() throws Exception {
        tarCtrl = new RegistarTarefaController();
        ObservableList obList = FXCollections.observableList(tarCtrl.getListaCatTarefaIdDesc());
        this.cmbNovaTarefaCatTarefa.getItems().clear();
        this.cmbNovaTarefaCatTarefa.setItems(obList);
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Tarefa Adicionada com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Tarefa.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    void associarParentUI(TabTarefasUI tabTarefasUI, RegistarTarefaController tarCtrl) {
        this.tabTarefasUI = tabTarefasUI;
        this.tarCtrl = tarCtrl;
    }

    @FXML
    private void actionCancel(ActionEvent event) {

        if (txtNovaTarefaRef.getText().isEmpty() && txtNovaTarefaDesign.getText().isEmpty() && txtNovaTarefaDuracao.getText().isEmpty()
                && txtNovaTarefaCusto.getText().isEmpty() && txtNovaTarefaDescInformal.getText().isEmpty() && txtNovaTarefaDescTecnica.getText().isEmpty()) {
            encerrarNovaTarefaUI(event);
        } else {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                    "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

            if (alerta.showAndWait().get() == ButtonType.CANCEL) {

                event.consume();
            } else {
                encerrarNovaTarefaUI(event);
            }
        }

    }

    private void encerrarNovaTarefaUI(ActionEvent event) {
        this.txtNovaTarefaRef.clear();
        this.txtNovaTarefaDesign.clear();
        this.txtNovaTarefaDuracao.clear();
        this.txtNovaTarefaCusto.clear();
        this.txtNovaTarefaDescInformal.clear();
        this.txtNovaTarefaDescTecnica.clear();
        this.cmbNovaTarefaCatTarefa.getSelectionModel().clearSelection();
        this.cmbNovaTarefaCatTarefa.setValue(null);

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void txtNovaTarefaRefMaxSize(KeyEvent event) {
    }

    @FXML
    private void txtNovaTarefaDesignacaoMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtNovaTarefaDescInformal.getText().length() > 49) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Informal é 50 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }

    }

    @FXML
    private void txtNovaTarefaDuracaoInfMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (!event.getCode().isDigitKey()) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Duração tem de ser Numerico (0-9)");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtNovaTarefaCustoMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.PERIOD)) {
            if (!event.getCode().isDigitKey()) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Caracter Invalido", "Custo tem de ser Numerico (0-9)");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtNovaTarefaDescInfMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtNovaTarefaDescInformal.getText().length() > 49) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Informal é 50 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }

    @FXML
    private void txtNovaTarefaDescTecMaxSize(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.ESCAPE)) {
            if (txtNovaTarefaDescTecnica.getText().length() > 199) {
                Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO,
                        "Maximo Caracteres", "Maximo Carateres de Descrição Tecnica é 199 caracteres");

                if (alerta.showAndWait().get() == ButtonType.OK) {
                    event.consume();
                }
            }
        }
    }
}

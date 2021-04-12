/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarSeriacaoController;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.Colaborador;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarColaboradoresSeriacaoUI implements Initializable {

    private AdicionarSeriacaoUI adicionarSeriacaoUI;
    private RegistarSeriacaoController sCtrl;

    @FXML
    private Button btnAddPSeriacao;
    @FXML
    private ListView<Colaborador> listViewColab;
    @FXML
    private ListView<Colaborador> listViewColabSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            encerrarNovaSeriacaoColaboradoresUI(event);
        }
    }

    @FXML
    private void actionAddPSeriacao(ActionEvent event) {
        try {
            List<Colaborador> colabs = listViewColabSelected.getItems();
            sCtrl.RegistaProcessoSeriacao(colabs, adicionarSeriacaoUI.getSelected());
            showAACreationSuccess();
            encerrarNovaSeriacaoColaboradoresUI(event);
        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Processo Seriacao adicionado!");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert
                = AlertaUI.criarAlerta(Alert.AlertType.ERROR, Constantes.TAB_MANAGE_CATTAREFA_TITLE, "Erro ao adicionar Processo Seriacao!", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    @FXML
    private void actionAddColab(ActionEvent event) {
        if (listViewColab.getSelectionModel().getSelectedItem() != null) {
            listViewColabSelected.getItems().add(listViewColab.getSelectionModel().getSelectedItem());
            listViewColab.getItems().remove(listViewColab.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void actionRemoverColab(ActionEvent event) {
        if (listViewColabSelected.getSelectionModel().getSelectedItem() != null) {
            listViewColab.getItems().add(listViewColabSelected.getSelectionModel().getSelectedItem());
            listViewColabSelected.getItems().remove(listViewColabSelected.getSelectionModel().getSelectedItem());
        }
    }

    private void encerrarNovaSeriacaoColaboradoresUI(ActionEvent event) {
        listViewColab.getItems().clear();
        listViewColabSelected.getItems().clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    void updateList() throws Exception {
        listViewColab.getItems().clear();
        listViewColabSelected.getItems().clear();

        ObservableList<Colaborador> candList = FXCollections.observableArrayList(sCtrl.getColaboradoresByOrg());
        listViewColab.setItems(candList);

        ObservableList<Colaborador> candListSelect = FXCollections.observableArrayList();
        listViewColabSelected.setItems(candListSelect);
    }

    void associarParentUI(AdicionarSeriacaoUI adicionarSeriacaoUI, RegistarSeriacaoController sCtrl) {
        this.adicionarSeriacaoUI = adicionarSeriacaoUI;
        this.sCtrl = sCtrl;
    }

}

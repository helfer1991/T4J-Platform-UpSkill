/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarCandidaturaController;
import com.company.model.Anuncio;
import com.company.model.Freelancer;
import static com.company.ui.App.TITULO_APLICACAO;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdicionarCandidaturaUI implements Initializable {

    private TabAnunciosDisponiveisUI tabAnunciosDisponiveisUI;

    private RegistarCandidaturaController candCtrl;

    @FXML
    private TextField txtValorPretendido;
    @FXML
    private TextField txtNrDias;
    @FXML
    private TextArea txtApr;
    @FXML
    private TextArea txtMotiv;
    @FXML
    private Button btnAdicionarCandidatura;
    @FXML
    private Button btnCancel;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void actionAdicionarCandidatura(ActionEvent event) throws Exception {
        candCtrl = new RegistarCandidaturaController();
        Freelancer free = candCtrl.getFreelancerByEmail();

        try {
            candCtrl.registarCandidatura(tabAnunciosDisponiveisUI.tableViewAnuncio(), Double.parseDouble(txtValorPretendido.getText()), Integer.parseInt(txtNrDias.getText()), txtApr.getText(), txtMotiv.getText(), free);
            showAACreationSuccess();
            tabAnunciosDisponiveisUI.updateList();
            encerrarCandidaturaUI(event);

        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    void LimparNovaCandidaturaUI(ActionEvent event) {
        this.txtValorPretendido.clear();
        this.txtValorPretendido.clear();
        this.txtNrDias.clear();
        this.txtApr.clear();
        this.txtMotiv.clear();
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                "Irá perder os dados inseridos.", "Deseja mesmo encerrar o registo?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {

            event.consume();
        } else {
            encerrarCandidaturaUI(event);
        }
    }

    void associarParentUI(TabAnunciosDisponiveisUI tabAnunciosDisponiveisUI) {
        this.tabAnunciosDisponiveisUI = tabAnunciosDisponiveisUI;
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Candidatura Adicionada com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao adicionar candidatura.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    private void encerrarCandidaturaUI(ActionEvent event) {

        this.txtValorPretendido.clear();
        this.txtNrDias.clear();
        this.txtApr.clear();
        this.txtMotiv.clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarCompetenciaTecnicaController;
import com.company.controller.RegistarFreelancerController;
import com.company.model.CompetenciaTecnica;
import com.company.model.GrauProficiencia;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarReconhecimentoCTUI implements Initializable {

    private AdicionarHabilitacoesAcademicasUI adicionarHabilitacoesAcademicasUI;
    private RegistarFreelancerController fCtrl;

    @FXML
    private DatePicker reconDate;
    @FXML
    private ComboBox<String> cmbCompTecs;
    @FXML
    private ComboBox<String> cmbGrauCompTec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void updateCT() throws Exception {

        ObservableList obList = FXCollections.observableList(fCtrl.getListaCompTecnicaIdDescBreve());

        this.cmbCompTecs.getItems().clear();
        this.cmbCompTecs.setItems(obList);
    }

    @FXML
    private void actionFinalFreelancerAdd(ActionEvent event) throws Exception {
        try {
            fCtrl.RegistaReconhecimentoCT(Date.valueOf(reconDate.getValue()),
                    fCtrl.getSelectedGrau(cmbGrauCompTec.getValue().split(" ")[1] + " " + cmbGrauCompTec.getValue().split(" ")[2]));
            fCtrl.RegistaFreelancer(adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(0),
                    adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(3),
                    adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(1),
                    adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(2),
                    adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(4),
                    adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(5),
                    adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().getAddFreelancerUiTexts().get(6));

            adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().showAACreationSuccess();
            adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().LimparNovoFreelancerUI(event);
            adicionarHabilitacoesAcademicasUI.limparNovaHabAcademicaUI(event);
            adicionarHabilitacoesAcademicasUI.getAddFreelancerUI().updateTabInfo();
            limparNovoReconhecimentoCTUI(event);
            encerrarNovoReconhecimentoCTUI(event);
        } catch (Exception e) {
            showIncorrectInformation(e);
        }
    }

    @FXML
    private void actionAddOutroReconCT(ActionEvent event) {
        try {
            GrauProficiencia gp = fCtrl.getSelectedGrau(cmbGrauCompTec.getValue().split(" ")[1] + " " + cmbGrauCompTec.getValue().split(" ")[2]);
            fCtrl.RegistaReconhecimentoCT(Date.valueOf(reconDate.getValue()),
                    fCtrl.getSelectedGrau(cmbGrauCompTec.getValue().split(" ")[1] + " " + cmbGrauCompTec.getValue().split(" ")[2]));
                 showAACreationSuccess();
                limparNovoReconhecimentoCTUI(event);
            
        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    void setCtrl(RegistarFreelancerController fCtrl) {
        this.fCtrl = fCtrl;
    }

    void associarParentUI(AdicionarHabilitacoesAcademicasUI adicionarHabilitacoesAcademicasUI) {
        this.adicionarHabilitacoesAcademicasUI = adicionarHabilitacoesAcademicasUI;
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Reconhecimento de Competencia Tecnica Adicionada com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Reconhecimento de Competencia Tecnica.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    void limparNovoReconhecimentoCTUI(ActionEvent event) {
        this.reconDate.getEditor().clear();
        this.cmbCompTecs.getSelectionModel().clearSelection();
        this.cmbGrauCompTec.getSelectionModel().clearSelection();
    }

    void encerrarNovoReconhecimentoCTUI(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void CompTecSelec(ActionEvent event) throws Exception {
        if (cmbCompTecs.getValue() != null) {
            CompetenciaTecnica ct = fCtrl.getCompTecnicaByID(cmbCompTecs.getSelectionModel().getSelectedItem().split(" ")[1]);

            ObservableList obList2 = FXCollections.observableList(fCtrl.getListaGrauProfValorDesig(ct));

            this.cmbGrauCompTec.getItems().clear();
            this.cmbGrauCompTec.setItems(obList2);
        }
    }
}

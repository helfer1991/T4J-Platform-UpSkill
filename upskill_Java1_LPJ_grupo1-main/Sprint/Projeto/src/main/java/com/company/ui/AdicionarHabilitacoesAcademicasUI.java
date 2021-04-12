/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarFreelancerController;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarHabilitacoesAcademicasUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private AdicionarFreelancerUI adicionarFreelancerUI;
    private Stage novoReconhecimentoCT;
    private RegistarFreelancerController fCtrl;
    @FXML
    private TextField txtNomeCurso;
    @FXML
    private TextField txtFaculdade;
    @FXML
    private TextField txtGrau;
    @FXML
    private TextField txtMedia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionAddReconhecimentoCT(ActionEvent event) throws SQLException, Exception {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_RECONHECIMENTOCT_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novoReconhecimentoCT = new Stage();
            novoReconhecimentoCT.initModality(Modality.APPLICATION_MODAL);
            novoReconhecimentoCT.setTitle(Constantes.REGISTO_RECONHECIMENTOCT_TITLE);
            novoReconhecimentoCT.setResizable(false);

            if (tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }

            novoReconhecimentoCT.setScene(scene);

            AdicionarReconhecimentoCTUI adicionarReconhecimentoCTUI = loader.getController();
            adicionarReconhecimentoCTUI.associarParentUI(this);
            adicionarReconhecimentoCTUI.setCtrl(fCtrl);
            adicionarReconhecimentoCTUI.updateCT();

            if (!(txtNomeCurso.getText().isEmpty() && txtFaculdade.getText().isEmpty() && txtGrau.getText().isEmpty() && txtMedia.getText().isEmpty())) {
                fCtrl.RegistaHabilitacaoAcademica(txtNomeCurso.getText(), txtFaculdade.getText(), txtGrau.getText(), txtMedia.getText());
                showAACreationSuccess();
                limparNovaHabAcademicaUI(event);
            }
            encerrarNovaHabAcademicaUI(event);

            novoReconhecimentoCT.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage()).showAndWait();
        }

    }

    @FXML
    private void actionAddOutraHabAcad(ActionEvent event) {
        try {
            fCtrl.RegistaHabilitacaoAcademica(txtNomeCurso.getText(), txtFaculdade.getText(), txtGrau.getText(), txtMedia.getText());
            showAACreationSuccess();
            limparNovaHabAcademicaUI(event);

        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    public AdicionarFreelancerUI getAddFreelancerUI() {
        return adicionarFreelancerUI;
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Habilitação Academica Adicionada com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Habilitação Academica.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    void limparNovaHabAcademicaUI(ActionEvent event) {
        this.txtNomeCurso.clear();
        this.txtFaculdade.clear();
        this.txtGrau.clear();
        this.txtMedia.clear();
    }

    void encerrarNovaHabAcademicaUI(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    void setCtrl(RegistarFreelancerController fCtrl) {
        this.fCtrl = fCtrl;
    }

    void associarParentUI(AdicionarFreelancerUI adicionarFreelancerUI) {
        this.adicionarFreelancerUI = adicionarFreelancerUI;
    }

    @FXML
    private void txtNomeCheckSize(KeyEvent event) {
    }

    @FXML
    private void txtNomeFaculdadeCheckSizeNotNumber(KeyEvent event) {
    }

    @FXML
    private void txtGrauCheckSize(KeyEvent event) {
    }

    @FXML
    private void txtMediaCheckNumber(KeyEvent event) {
    }
}

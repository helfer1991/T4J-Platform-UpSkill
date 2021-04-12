/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarFreelancerController;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class AdicionarFreelancerUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();

    private TabFreelancersUI tabFreelancersUI;
    private Stage novaHabilitacaoAcademica;
    private RegistarFreelancerController fCtrl;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtNif;
    @FXML
    private TextField txtMorada;
    @FXML
    private TextField txtCodPostal;
    @FXML
    private TextField txtLocalidade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionAddHabilitaçõesLiterarias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_HABILITACAOACADEMICA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaHabilitacaoAcademica = new Stage();
            novaHabilitacaoAcademica.initModality(Modality.APPLICATION_MODAL);
            novaHabilitacaoAcademica.setTitle(Constantes.REGISTO_HABILITACAOACADEMICA_TITLE);
            novaHabilitacaoAcademica.setResizable(false);

            if (tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }

            novaHabilitacaoAcademica.setScene(scene);

            AdicionarHabilitacoesAcademicasUI adicionarHabilitacoesAcademicasUI = loader.getController();
            adicionarHabilitacoesAcademicasUI.associarParentUI(this);
            adicionarHabilitacoesAcademicasUI.setCtrl(fCtrl);
            EncerrarNovoFreelancerUI(event);

            novaHabilitacaoAcademica.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        closingWarning(event);
    }

    void LimparNovoFreelancerUI(ActionEvent event) {
        this.txtName.clear();
        this.txtEmail.clear();
        this.txtPhone.clear();
        this.txtNif.clear();
        this.txtMorada.clear();
        this.txtCodPostal.clear();
        this.txtLocalidade.clear();
    }

    void EncerrarNovoFreelancerUI(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    ArrayList<String> getAddFreelancerUiTexts() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add(this.txtName.getText());
        tmp.add(this.txtEmail.getText());
        tmp.add(this.txtPhone.getText());
        tmp.add(this.txtNif.getText());
        tmp.add(this.txtMorada.getText());
        tmp.add(this.txtCodPostal.getText());
        tmp.add(this.txtLocalidade.getText());
        return tmp;
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Freelancer Adicionado com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Freelancer.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    public void updateTabInfo() throws Exception {
        tabFreelancersUI.updateList();
    }

    void closingWarning(ActionEvent event) {
        if (txtName.getText().isEmpty()
                && txtEmail.getText().isEmpty()
                && txtPhone.getText().isEmpty()
                && txtNif.getText().isEmpty()
                && txtMorada.getText().isEmpty()
                && txtCodPostal.getText().isEmpty()
                && txtLocalidade.getText().isEmpty()) {
            LimparNovoFreelancerUI(event);
            EncerrarNovoFreelancerUI(event);
        } else {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                    "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                event.consume();
            } else {
                LimparNovoFreelancerUI(event);
                EncerrarNovoFreelancerUI(event);
            }
        }
    }

    @FXML
    private void txtNomeMaxSizeIsLetter(KeyEvent event) {
    }

    @FXML
    private void txtEmailMaxSize(KeyEvent event) {
    }

    @FXML
    private void txtPhoneMaxSizeisDigit(KeyEvent event) {
    }

    void associarParentUI(TabFreelancersUI tabFreelancersUI) {
        this.tabFreelancersUI = tabFreelancersUI;
    }

    void setCtrl(RegistarFreelancerController fCtrl) {
        this.fCtrl = fCtrl;
    }
}

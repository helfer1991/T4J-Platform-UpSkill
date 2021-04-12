package com.company.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.controller.RegistarCandidaturaController;
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

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AlterarCandidaturaUI implements Initializable {
    
    private TabCandidaturasUI tabCandidaturasUI;

    private RegistarCandidaturaController candCtrl;

    @FXML
    private TextField valorPretendido;
    @FXML
    private TextField nrDias;
    @FXML
    private TextArea txtApr;
    @FXML
    private TextArea txtMotiv;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAlterar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionCancelar(ActionEvent event) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                "Irá perder os dados inseridos.", "Deseja mesmo encerrar a alteracao?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {

            event.consume();
        } else {
            encerrarCandidaturaUI(event);
        }
    }

    @FXML
    private void actionAlterar(ActionEvent event) throws Exception {
        candCtrl = new RegistarCandidaturaController();
        try{
            System.out.println(tabCandidaturasUI.getSelected().getId());
            candCtrl.alterarCandidatura(tabCandidaturasUI.getSelected(),Double.parseDouble(valorPretendido.getText().trim()),
                    Integer.parseInt(nrDias.getText().trim()), txtApr.getText().trim(),txtMotiv.getText().trim());
            showAACreationSuccess();
            tabCandidaturasUI.updateList();
            encerrarCandidaturaUI(event);
        }catch(Exception e){
            showIncorrectInformation(e);
        }
    }
    
    void associarParentUI(TabCandidaturasUI tabCandidaturasUI) {
        this.tabCandidaturasUI = tabCandidaturasUI;
    }
    
    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Candidatura Alterada com Sucesso");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao alterar candidatura.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    private void encerrarCandidaturaUI(ActionEvent event) {
        this.valorPretendido.clear();
        this.nrDias.clear();
        this.txtApr.clear();
        this.txtMotiv.clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}

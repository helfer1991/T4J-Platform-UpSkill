/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarCompetenciaTecnicaController;
import com.company.model.AreaAtividade;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarCompetenciaTecnicaUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();

    TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI;
    private Stage novoGrauProfStage;

    @FXML
    private TextField txtCodUnico;
    @FXML
    private TextField txtDescBreve;
    @FXML
    private TextArea txtDescDetalhada;
    @FXML
    private ComboBox<String> cmbAreaAtividade;
    
    private RegistarCompetenciaTecnicaController ctCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ctCtrl = new RegistarCompetenciaTecnicaController();
    }

    void updateAA() throws SQLException, Exception {
        ctCtrl = new RegistarCompetenciaTecnicaController();

        ObservableList obList = FXCollections.observableList(ctCtrl.getListaAreaAtividadeIdDescBreve());

        this.cmbAreaAtividade.getItems().clear();
        this.cmbAreaAtividade.setItems(obList);
    }

    @FXML
    private void actionAdicionarNovaCompetenciaTecnica(ActionEvent event) {

        ctCtrl = new RegistarCompetenciaTecnicaController();
        if (cmbAreaAtividade.getValue() != null) {
            try {
                AreaAtividade aa = ctCtrl.getAreaAtividadeById(cmbAreaAtividade.getValue());
                ctCtrl.RegistaCompetenciaTecnica(txtCodUnico.getText(), txtDescBreve.getText(), txtDescDetalhada.getText(), aa);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_GRAUPROF_FILE));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);

                    novoGrauProfStage = new Stage();
                    novoGrauProfStage.initModality(Modality.APPLICATION_MODAL);
                    novoGrauProfStage.setTitle(Constantes.REGISTO_GRAUPROF_TITLE);
                    novoGrauProfStage.setResizable(false);

                    if (tema.isEnable()) {
                        scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
                    } else {
                        scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
                    }

                    novoGrauProfStage.setScene(scene);

                    AdicionarGrauProficienciaUI adicionarGrauProficienciaUI = loader.getController();
                    adicionarGrauProficienciaUI.associarParentUI(this);
                    adicionarGrauProficienciaUI.associarParentCtrl(ctCtrl);

                    novoGrauProfStage.show();

                    encerrarNovaCompetenciaTecnicaUI(event);

                } catch (IOException ex) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage()).showAndWait();
                }

            } catch (Exception ex) {
                showIncorrectInformation(ex);
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO, "Insira uma Area de Atividade", "Não pode criar uma Competencia Tecnica sem uma Area de Atividade").show();
        }

    }

    void associarParentUI(TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI) {
        this.tabCompetenciaTecnicaUI = tabCompetenciaTecnicaUI;
    }

    void encerrarNovaCompetenciaTecnicaUI(ActionEvent event) {
        this.txtCodUnico.clear();
        this.txtDescBreve.clear();
        this.txtDescDetalhada.clear();
        this.cmbAreaAtividade.getSelectionModel().clearSelection();
        this.cmbAreaAtividade.setValue(null);

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Competencia Tecnica adicionada");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao Adicionar Competencia Tecnica.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

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

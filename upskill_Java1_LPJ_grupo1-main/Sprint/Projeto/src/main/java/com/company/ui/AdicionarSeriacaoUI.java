/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarSeriacaoController;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.model.Classificacao;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarSeriacaoUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private TabAnunciosUI tabAnunciosUI;
    private RegistarSeriacaoController sCtrl;
    private Stage novoColaboradoresSeriacaoStage;

    @FXML
    private Button btnAddColabs;
    @FXML
    private ListView<Candidatura> listViewCandExistentes;
    @FXML
    private ListView<Classificacao> listViewClassifSelected;
    @FXML
    private Button btnAddClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sCtrl = new RegistarSeriacaoController();
        } catch (Exception ex) {
            Logger.getLogger(AdicionarSeriacaoUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void associarParentUI(TabAnunciosUI tabAnunciosUI) throws Exception {
        this.tabAnunciosUI = tabAnunciosUI;
        sCtrl.setListaCand(this.tabAnunciosUI.getAnuncioSelected());
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
        if (sCtrl.getListClassif().size() > 0) {
            encerrarNovaSeriacaoUI(event);
        } else {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                    "Ira Perder os dados inseridos.", "Deseja mesmo encerrar o Registo?");

            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                event.consume();
            } else {
                encerrarNovaSeriacaoUI(event);
            }
        }
    }

    @FXML
    private void actionAddColabParticipantes(ActionEvent event) throws Exception {
        if (tabAnunciosUI.getAnuncioSelected().getRegimento().getId() == 2) {
            if (listViewClassifSelected.getItems().size() == 0) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", "Tem de adicionar pelo menos 1 candidatura para classificação.");
                event.consume();
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_COLABORADORESSERIACAO_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novoColaboradoresSeriacaoStage = new Stage();
            novoColaboradoresSeriacaoStage.initModality(Modality.APPLICATION_MODAL);
            novoColaboradoresSeriacaoStage.setTitle(Constantes.REGISTO_COLABORADORESSERIACAO_TITLE);
            novoColaboradoresSeriacaoStage.setResizable(false);

            if (tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }

            novoColaboradoresSeriacaoStage.setScene(scene);

            AdicionarColaboradoresSeriacaoUI adicionarColaboradoresSeriacaoUI = loader.getController();
            adicionarColaboradoresSeriacaoUI.associarParentUI(this, sCtrl);
            adicionarColaboradoresSeriacaoUI.updateList();
            encerrarNovaSeriacaoUI(event);

            novoColaboradoresSeriacaoStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage()).showAndWait();
        }
    }

    @FXML
    private void actionAddCand(ActionEvent event) {
        if (listViewCandExistentes.getSelectionModel().getSelectedItem() != null) {
            sCtrl.addCandToList(listViewCandExistentes.getSelectionModel().getSelectedItem());
            addremoveUpdate();
        }
    }

    @FXML
    private void actionRemoverCand(ActionEvent event) {
        if (listViewClassifSelected.getSelectionModel().getSelectedItem() != null) {
            sCtrl.removeClassFromList(listViewClassifSelected.getSelectionModel().getSelectedItem());
            addremoveUpdate();
        }
    }

    void addremoveUpdate() {
        listViewCandExistentes.getItems().clear();
        listViewClassifSelected.getItems().clear();
        
        listViewCandExistentes.getItems().addAll(sCtrl.getListCandidaturas());
        listViewClassifSelected.getItems().addAll(sCtrl.getListClassif());
    }

    void updateListView() throws SQLException {
        listViewCandExistentes.getItems().clear();
        listViewClassifSelected.getItems().clear();

        ObservableList<Candidatura> candList = FXCollections.observableArrayList(sCtrl.getListCandidaturas());
        listViewCandExistentes.setItems(candList);

        ObservableList<Classificacao> classList = FXCollections.observableArrayList();
        listViewClassifSelected.setItems(classList);
    }

    void encerrarNovaSeriacaoUI(ActionEvent event) {
        listViewCandExistentes.getItems().clear();
        listViewClassifSelected.getItems().clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    Anuncio getSelected() {
        return tabAnunciosUI.getAnuncioSelected();
    }

}

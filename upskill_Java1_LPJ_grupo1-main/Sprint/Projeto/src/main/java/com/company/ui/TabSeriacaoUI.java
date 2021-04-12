/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarSeriacaoController;
import com.company.model.Classificacao;
import com.company.model.ProcessoSeriacao;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabSeriacaoUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();
    private MainSceneUI mainSceneUI;
    private Stage novaAtribuicaoStage;
    private RegistarSeriacaoController sCtrl;

    @FXML
    private TableView<ProcessoSeriacao> tableViewAnunciosSeriac;
    @FXML
    private TableColumn<ProcessoSeriacao, String> tableViewAnunciosSeriacData;
    @FXML
    private TableColumn<ProcessoSeriacao, String> tableViewAnunciosSeriacNifColab;
    @FXML
    private TableColumn<ProcessoSeriacao, String> tableViewAnunciosSeriacidAnuncio;
    @FXML
    private TableView<Classificacao> tableViewClassific;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassificPos;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassificidCand;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassifNifFreelancer;
    @FXML
    private Button btnAddAtribuicao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabs();
        btnAddAtribuicao.setVisible(false);
        try {
            sCtrl = new RegistarSeriacaoController();
        } catch (Exception ex) {
            Logger.getLogger(TabTarefasUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickedAnuncio(MouseEvent event) throws Exception {
        if (tableViewAnunciosSeriac.getSelectionModel().getSelectedItem() != null) {
            tableViewClassific.getItems().clear();
            loadClassific();
            if (sCtrl.triggerBtnAddAtribuicao(tableViewAnunciosSeriac.getSelectionModel().getSelectedItem())) {
                btnAddAtribuicao.setVisible(true);
            } else {
                btnAddAtribuicao.setVisible(false);
            }
        }

    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabSeriacaoUI(this);
    }

    private void loadTabs() {
        tableViewAnunciosSeriacData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProcessoSeriacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProcessoSeriacao, String> p) {
                return new SimpleStringProperty(p.getValue().getDataRealizacao().toString());
            }

        });
        tableViewAnunciosSeriacNifColab.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProcessoSeriacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProcessoSeriacao, String> p) {
                return new SimpleStringProperty(p.getValue().getColab().getEmail());
            }
        });
        tableViewAnunciosSeriacidAnuncio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProcessoSeriacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProcessoSeriacao, String> p) {
                return new SimpleStringProperty(p.getValue().getAnuncio().getTarefa().getRef());
            }

        });
        tableViewClassificPos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty(p.getValue().getLugarClassificacao() + "º");
            }
        });
        tableViewClassificidCand.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                SimpleStringProperty builtString = null;
                builtString = new SimpleStringProperty("" + p.getValue().getCandidatura().getId());
                return builtString;
            }

        });
        tableViewClassifNifFreelancer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty("Nome: " + p.getValue().getCandidatura().getFree().getNome() + "\n"
                        + "NIF: " + p.getValue().getCandidatura().getFree().getNif());
            }

        });

    }

    void updateList() throws Exception {
        sCtrl = new RegistarSeriacaoController();
        ObservableList<ProcessoSeriacao> serList = FXCollections.observableArrayList(sCtrl.getListaSeriados());
        tableViewAnunciosSeriac.setItems(serList);
    }

    private void loadClassific() {
        try {
            List<Classificacao> tmp = new ArrayList<Classificacao>();
            tmp = sCtrl.getClassificacaoByProccSer(tableViewAnunciosSeriac.getSelectionModel().getSelectedItem());
            if (tmp != null && tmp.size() != 0) {
                ObservableList<Classificacao> classProfList = FXCollections.observableArrayList(tmp);
                tableViewClassific.setItems(classProfList);
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro no load de Graus Prof", e.getMessage());
        }
    }

    @FXML
    private void actionAddAtribuicao(ActionEvent event) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_ATRIBUICAO_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaAtribuicaoStage = new Stage();
            novaAtribuicaoStage.initModality(Modality.APPLICATION_MODAL);
            novaAtribuicaoStage.setTitle(Constantes.REGISTO_ATRIBUICAO_TITLE);
            novaAtribuicaoStage.setResizable(false);

            if (tema.isEnable()) {

                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }

            novaAtribuicaoStage.setScene(scene);

            AdicionarAtribuicaoUI adicionarAtribuicaoUI = loader.getController();
            adicionarAtribuicaoUI.associarParentUI(this);
            adicionarAtribuicaoUI.updateListView(tableViewAnunciosSeriac.getSelectionModel().getSelectedItem());

            novaAtribuicaoStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

}

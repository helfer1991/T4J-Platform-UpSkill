/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarSeriacaoController;
import com.company.controller.RegistarAnuncioController;
import com.company.model.Anuncio;
import com.company.model.Colaborador;
import com.company.ui.MainSceneUI;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.control.ButtonType;
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
 * @author Asus
 */
public class TabAnunciosUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;
    private RegistarSeriacaoController sCtrl;
    private Stage novoProcessoSeriacao;
    @FXML
    private TableView<Anuncio> tableViewAnuncio;
    @FXML
    private TableColumn<Anuncio, String> tableViewAnuncioId;
    @FXML
    private TableColumn<Anuncio, String> tableViewTarefa;
    @FXML
    private TableColumn<Anuncio, String> tableViewTipoRegimento;
    @FXML
    private TableColumn<Anuncio, String> tableViewDtInicioSer;
    @FXML
    private TableColumn<Anuncio, String> tableViewDtFimSer;
    @FXML
    private Button btnSeriar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadColumns();
        btnSeriar.setDisable(true);
        try {
            sCtrl = new RegistarSeriacaoController();
        } catch (Exception ex) {
            Logger.getLogger(TabAnunciosUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabAnunciosUI(this);
    }

    public void updateList() throws Exception {
        RegistarAnuncioController anuncioCtrl = new RegistarAnuncioController();
        if (anuncioCtrl.getAllAnuncios() != null) {
            ObservableList<Anuncio> list = FXCollections.observableArrayList(anuncioCtrl.getAllAnuncios());
            tableViewAnuncio.setItems(list);
        }
    }

    private void loadColumns() {
        tableViewAnuncioId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty(p.getValue().getTarefa().getRef());
            }

        });
        tableViewTarefa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty("Designação: " + p.getValue().getTarefa().getDesignacao());
            }

        });

        tableViewDtInicioSer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p) {
                return new SimpleStringProperty((p.getValue().getdInicioSeriacao().toString()));
            }

        });

        tableViewDtFimSer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Anuncio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Anuncio, String> p){
                return new SimpleStringProperty(p.getValue().getdFimSeriacao().toString());
            }
        });

        tableViewTipoRegimento.setCellValueFactory(new PropertyValueFactory<Anuncio, String> ("regimento"));

    }

    @FXML
    private void clkAnuncio(MouseEvent event) throws Exception {
        if (tableViewAnuncio.getSelectionModel().getSelectedItem() != null) {
            if (sCtrl.hasProcSeriacao(tableViewAnuncio.getSelectionModel().getSelectedItem())) {
                btnSeriar.setDisable(true);
            } else {
                btnSeriar.setDisable(false);
            }
        }
    }

    Anuncio getAnuncioSelected() {
        return tableViewAnuncio.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void actionSeriar(ActionEvent event) throws Exception {

        if (getAnuncioSelected().getRegimento().getId() == 3) {
            try {
                sCtrl = new RegistarSeriacaoController();
                sCtrl.RegistaProcessoSeriacao(new ArrayList<>(), getAnuncioSelected());
                showAACreationSuccess();
            } catch (Exception ex) {
                showIncorrectInformation(ex);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_SERIACAO_FILE));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                novoProcessoSeriacao = new Stage();
                novoProcessoSeriacao.initModality(Modality.APPLICATION_MODAL);
                novoProcessoSeriacao.setTitle(Constantes.REGISTO_SERIACAO_TITLE);
                novoProcessoSeriacao.setResizable(false);

                if (tema.isEnable()) {
                    scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
                } else {
                    scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
                }

                novoProcessoSeriacao.setScene(scene);

                AdicionarSeriacaoUI adicionarSeriacaoUI = loader.getController();
                adicionarSeriacaoUI.associarParentUI(this);
                adicionarSeriacaoUI.updateListView();

                novoProcessoSeriacao.show();
            } catch (IOException ex) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage()).showAndWait();
            }
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarColaboradorController;
import com.company.model.Colaborador;
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabColaboradoresSceneUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;
    private Stage novoColaboradorStage;

    @FXML
    private TableView<Colaborador> tableViewColaboradores;
    @FXML
    private TableColumn<Colaborador, String> tableViewColaboradoresNome;
    @FXML
    private TableColumn<Colaborador, String> tableViewColaboradoresEmail;
    @FXML
    private TableColumn<Colaborador, String> tableViewColaboradoresTelefone;

    RegistarColaboradorController cCtrl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableViewTabs();
        try {
            cCtrl = new RegistarColaboradorController();
        } catch (SQLException ex) {
            Logger.getLogger(TabColaboradoresSceneUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void actionAdicionarNovoColaborador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_COLABORADOR_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novoColaboradorStage = new Stage();
            novoColaboradorStage.initModality(Modality.APPLICATION_MODAL);
            novoColaboradorStage.setTitle(Constantes.REGISTO_COLABORADOR_TITLE);
            novoColaboradorStage.setResizable(false);
            
             if(tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }
             
            novoColaboradorStage.setScene(scene);

            AdicionarColaboradorUI adicionarColaboradorUI = loader.getController();
            adicionarColaboradorUI.associarParentUI(this);
            adicionarColaboradorUI.setCtrl(cCtrl);

            novoColaboradorStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabColaboradoresSceneUI(this);
    }

    public void updateList() throws SQLException, Exception {

        List<Colaborador> tmp = new ArrayList<>();
        tmp = cCtrl.getListColaboradores();

        if (tmp != null && tmp.size() != 0) {
            ObservableList<Colaborador> ctList = FXCollections.observableArrayList(tmp);
            tableViewColaboradores.setItems(ctList);
        }
    }

    private void loadTableViewTabs() {
        tableViewColaboradoresNome.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("nome"));
        tableViewColaboradoresEmail.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("email"));
        tableViewColaboradoresTelefone.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("telefone"));
    }
}

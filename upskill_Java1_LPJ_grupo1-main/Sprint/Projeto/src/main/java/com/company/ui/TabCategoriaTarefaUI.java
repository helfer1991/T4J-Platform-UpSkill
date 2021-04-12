/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarCategoriaTarefaController;
import com.company.model.CaraterCT;
import com.company.model.CategoriaTarefa;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabCategoriaTarefaUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;
    private Stage novaCatTarefaStage;
    @FXML
    private TableView<CategoriaTarefa> tableViewCategoriaTarefa;
    @FXML
    private TableColumn<CategoriaTarefa, String> tableViewCatTarefaDesc;
    @FXML
    private TableColumn<CategoriaTarefa, String> tableViewCatTarefaAreaAtividade;
    @FXML
    private TableView<CaraterCT> listViewCatTarefaListCompTecnica;
    @FXML
    private TableColumn<CaraterCT, String> listViewCatTarefaCatTecnica;
    @FXML
    private TableColumn<CaraterCT, String> listViewCatTarefaCatTecnicaMandatory;
    @FXML
    private TableColumn<CaraterCT, String> listViewCatTarefaCatTecnicaGrau;

    private RegistarCategoriaTarefaController catTarefaCtrll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabs();
       
        
    }

    @FXML
    private void actionAdicionarNovaCatTarefa(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_CATTAREFA_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novaCatTarefaStage = new Stage();
            novaCatTarefaStage.initModality(Modality.APPLICATION_MODAL);
            novaCatTarefaStage.setTitle(Constantes.REGISTO_CATTAREFA_TITLE);
            novaCatTarefaStage.setResizable(false);
            
             if(tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }
             
            novaCatTarefaStage.setScene(scene);

            AdicionarCategoriaTarefaUI adicionarCategoriaTarefaUI = loader.getController();
            adicionarCategoriaTarefaUI.associarParentUI(this);
            adicionarCategoriaTarefaUI.updateAA();

            novaCatTarefaStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabCategoriaTarefaUI(this);
    }

    public void updateList() throws Exception {
        catTarefaCtrll = new RegistarCategoriaTarefaController();
        List<CategoriaTarefa> tmp = catTarefaCtrll.getListaCatTarefa();
        System.out.println(tmp.size());
        if (tmp != null) {
            ObservableList<CategoriaTarefa> catTarefaList = FXCollections.observableArrayList(tmp);
            tableViewCategoriaTarefa.setItems(catTarefaList);
        } else{
            ObservableList<CategoriaTarefa> catTarefaList = FXCollections.observableArrayList();
            tableViewCategoriaTarefa.setItems(catTarefaList);
        }
    }

    @FXML
    private void mouseTabCat(MouseEvent event) {
        listViewCatTarefaListCompTecnica.getItems().clear();
        loadCarCT();

    }

    private void loadCarCT() {
        try {
            catTarefaCtrll = new RegistarCategoriaTarefaController();
            List<CaraterCT> tmp = new ArrayList<>();
            tmp = (catTarefaCtrll.getListCaraterCT(tableViewCategoriaTarefa.getSelectionModel().getSelectedItem()));
            if (tmp != null && tmp.size() != 0) {
                ObservableList<CaraterCT> CaraterCTList = FXCollections.observableArrayList(tmp);
                listViewCatTarefaListCompTecnica.setItems(CaraterCTList);
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro no load de competências técnicas", e.getMessage());
        }
    }

    private void loadTabs() {
        tableViewCatTarefaDesc.setCellValueFactory(new PropertyValueFactory<CategoriaTarefa, String>("descricao"));
        tableViewCatTarefaAreaAtividade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoriaTarefa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoriaTarefa, String> p) {
                return new SimpleStringProperty("ID: " + p.getValue().getAreaAtividade().getId()
                        + "\n" + "Descrição: " + p.getValue().getAreaAtividade().getDescBreve());
            }

        });

        listViewCatTarefaCatTecnica.setCellValueFactory(new PropertyValueFactory<CaraterCT,String>("codCompetenciaTecnica"));
        listViewCatTarefaCatTecnicaGrau.setCellValueFactory(new PropertyValueFactory<CaraterCT,String>("grauMinimoProficiencia"));
        
        listViewCatTarefaCatTecnicaMandatory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CaraterCT, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CaraterCT, String> p) {
                String mandatory = "";
                if(p.getValue().getObrigatorio() == 0){
                    mandatory = "Opcional";
                }else{
                    mandatory = "Obrigatorio";
                }
                return new SimpleStringProperty(mandatory);
            }
        });
    }

}

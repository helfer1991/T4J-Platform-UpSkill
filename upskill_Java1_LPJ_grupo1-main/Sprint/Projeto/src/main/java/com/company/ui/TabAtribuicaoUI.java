/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarAtribuicaoController;
import com.company.model.Atribuicao;
import com.company.model.CategoriaTarefa;
import com.company.utils.TemaApp;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabAtribuicaoUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;

    @FXML
    private TableView<Atribuicao> tableViewAtribuicao;
    @FXML
    private TableColumn<Atribuicao, String> tableViewIdAtrib;
    @FXML
    private TableColumn<Atribuicao, String> tableViewAtribNifFree;
    @FXML
    private TableColumn<Atribuicao, String> tableViewAtribPeriod;
    @FXML
    private TableColumn<Atribuicao, String> tableViewAtribIdAnuncio;
    @FXML
    private TableColumn<Atribuicao, String> tableAtribValor;
    @FXML
    private TableColumn<Atribuicao, String> tableAtrbDescTarefa;

    private RegistarAtribuicaoController atribCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabs();
    }

    @FXML
    private void tableViewAtrib(MouseEvent event) {
    }

    private void loadTabs() {
        tableViewIdAtrib.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atribuicao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Atribuicao, String> p) {
                return new SimpleStringProperty(p.getValue().getId());
            }
        });

        tableViewAtribNifFree.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atribuicao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Atribuicao, String> p) {
                return new SimpleStringProperty(p.getValue().getPickedClass().getCandidatura().getFree().getNif());
            }
        });

        tableViewAtribPeriod.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atribuicao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Atribuicao, String> p) {
                Date now = Date.valueOf(LocalDate.now());
                
                
                return new SimpleStringProperty("Inicio: " + addDays(p.getValue().getPickedClass().getCandidatura().getAnuncio().getdFimSeriacao(),1) + "\n"
                        + "Fim: " + addDays(p.getValue().getPickedClass().getCandidatura().getAnuncio().getdFimSeriacao(),p.getValue().getPickedClass().getCandidatura().getNrDias()));
            }
        });

        tableViewAtribIdAnuncio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atribuicao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Atribuicao, String> p) {
                return new SimpleStringProperty(p.getValue().getPickedClass().getCandidatura().getAnuncio().getTarefa().getRef());
            }
        });
        tableAtribValor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atribuicao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Atribuicao, String> p) {
                return new SimpleStringProperty(p.getValue().getPickedClass().getCandidatura().getValorPretendido() + " POTs");
            }
        });

        tableAtrbDescTarefa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Atribuicao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Atribuicao, String> p) {
                return new SimpleStringProperty(p.getValue().getPickedClass().getCandidatura().getAnuncio().getTarefa().getDescrTecnica());
            }
        });
    }

    void updateList() throws Exception {
        atribCtrl = new RegistarAtribuicaoController();
        List<Atribuicao> tmp = atribCtrl.getListAtribuicoes();
        if (tmp != null) {
            ObservableList<Atribuicao> atribuicaoList = FXCollections.observableArrayList(tmp);
            tableViewAtribuicao.setItems(atribuicaoList);
        } else {
            ObservableList<Atribuicao> atribuicaoList = FXCollections.observableArrayList();
            tableViewAtribuicao.setItems(atribuicaoList);
        }
    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabAtribuicaoUI(this);
    }

       private static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }
}

package com.company.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.company.controller.RegistarAtribuicaoController;
import com.company.controller.RegistarSeriacaoController;
import com.company.controller.RegistarTarefaController;
import com.company.model.Classificacao;
import com.company.model.ProcessoSeriacao;
import com.company.model.Tarefa;
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarAtribuicaoUI implements Initializable {

    private TabSeriacaoUI tabSeriacaoUI;

    @FXML
    private TableView<Classificacao> tableViewClass;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassPos;
    @FXML
    private TableColumn<Classificacao, String> tableViewIdCand;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassNifFree;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassValor;
    @FXML
    private TableColumn<Classificacao, String> tableViewClassDuracao;

    private RegistarAtribuicaoController rAtCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTabs();
    }

    @FXML
    private void actionAtribuir(ActionEvent event) {
        if (tableViewClass.getSelectionModel().getSelectedItem() != null) {
            try {
                rAtCtrl.addAtribuicao(tableViewClass.getSelectionModel().getSelectedItem());
                showAACreationSuccess();
                tabSeriacaoUI.updateList();
                LimparAdicionarAtribuicaoScene(event);
            } catch (Exception ex) {
                showIncorrectInformation(ex);
            }
        } else {
            Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                    "Erro", "Selecione uma das Classificações para atribuir este processo.");
            if (alert.showAndWait().get() == ButtonType.OK) {
                alert.close();
            }
        }
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        LimparAdicionarAtribuicaoScene(event);
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Atribuicao bem sucedida");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao adicionar atribuicao.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    void LimparAdicionarAtribuicaoScene(ActionEvent event) {
        tableViewClass.getItems().clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void tableViewCandClass(MouseEvent event) {
    }

    void updateListView(ProcessoSeriacao pS) throws Exception {
        rAtCtrl = new RegistarAtribuicaoController();
        ObservableList<Classificacao> classList = FXCollections.observableArrayList(rAtCtrl.getListaClassificacoesSelectedAnuncio(pS));
        tableViewClass.setItems(classList);
    }

    void associarParentUI(TabSeriacaoUI tabSeriacaoUI) {
        this.tabSeriacaoUI = tabSeriacaoUI;
    }

    private void loadTabs() {
        tableViewClassPos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty(p.getValue().getLugarClassificacao() + "º");
            }
        });

        tableViewIdCand.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getCandidatura().getId()));
            }
        });
        tableViewClassNifFree.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty(p.getValue().getCandidatura().getFree().getNif());
            }
        });
        tableViewClassValor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty(p.getValue().getCandidatura().getValorPretendido()+" POTs");
            }
        });
        tableViewClassDuracao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Classificacao, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Classificacao, String> p) {
                return new SimpleStringProperty(p.getValue().getCandidatura().getNrDias()+" Dias");
            }
        });
    }
}


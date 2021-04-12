/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarCategoriaTarefaController;
import com.company.model.AreaAtividade;
import com.company.model.CompetenciaTecnica;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarCategoriaTarefaUI implements Initializable {

    private TabCategoriaTarefaUI tabCategoriaTarefaUI;
    @FXML
    private TextArea txtCategoriaTarefaDesc;
    @FXML
    private ComboBox<String> cmbCategoriaTarefaAreaAtividade;
    @FXML
    private ListView<String> listViewCompTecnicaExistentes;
    @FXML
    private ListView<String> listViewCompTecnicaSelected;
    @FXML
    private Button btnAddOpcional;
    @FXML
    private Button btnAddObrigatorio;
    @FXML
    private ComboBox<String> cmbGrauProficiencia;
    @FXML
    private Button btnAdicionarCategoria;

    ObservableList listAreasAtividade = FXCollections.observableArrayList();
    ObservableList listCompTecExist = FXCollections.observableArrayList();
    ObservableList listCompTecSelec = FXCollections.observableArrayList();

    private RegistarCategoriaTarefaController catTarefaCtrll;
    private AreaAtividade areaAtividade;
    private String compTecExistenteSelec; // é o que escolhi!!!!!
    private String compTecSelecionadasSelec; // a que quero remover

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.catTarefaCtrll = new RegistarCategoriaTarefaController();
        } catch (Exception ex) {
            this.catTarefaCtrll = null;
        }
        btnAddOpcional.setDisable(true);
        btnAddObrigatorio.setDisable(true);

    }

    /**
     * Atualiza a lista de áreas de atividade na comboBox.
     */
    public void updateAA() throws Exception {
        ObservableList obList = FXCollections.observableList(catTarefaCtrll.getListaAreaAtividadeDescBreve());

        this.cmbCategoriaTarefaAreaAtividade.getItems().clear();
        this.cmbCategoriaTarefaAreaAtividade.setItems(obList);
    }

    /**
     * Atualiza a lista de graus de proficiencia na comboBox.
     */
    public void updateGrauProficiencia(CompetenciaTecnica ct) throws SQLException {
        ObservableList obList2 = FXCollections.observableList(ct.getListaGrausProficienciaString());

        this.cmbGrauProficiencia.getItems().clear();
        this.cmbGrauProficiencia.setItems(obList2);
    }

    // adiciona uma nova categoria de tarefa e apresenta mensagem de erro caso não seja possível
    @FXML
    private void actionAddNovaCategoriaTarefa(ActionEvent event) {
        try {
            if (catTarefaCtrll.registaCategoriaTarefa(txtCategoriaTarefaDesc.getText(), areaAtividade, catTarefaCtrll.getListaCaraterCTCtrl())) {
                showAACreationSuccess();
                tabCategoriaTarefaUI.updateList();
                encerrarNovaCatTarefaUI(event);
            }
        } catch (Exception ex) {
            showIncorrectInformation(ex);
        }
    }

    // verifica qual a área de atividade selecionada e apresenta as respectivas competências técnicas
    @FXML
    private void cmbSelecionarAreaAtividade(ActionEvent event) throws Exception {
        if ((!cmbCategoriaTarefaAreaAtividade.getValue().isEmpty())) {

            try {
                areaAtividade = catTarefaCtrll.getAreaAtividade(cmbCategoriaTarefaAreaAtividade.getValue().split(" ")[1]);
            } catch (Exception ex) {
                showIncorrectInformation(ex);
            }

            listViewCompTecnicaExistentes.getItems().clear();
            listCompTecExist.clear();
            listViewCompTecnicaSelected.getItems().clear();
            listCompTecSelec.clear();

            try {
                //Obtem uma lista de competencias técnicas cujo id de área de atividade seja igual ao id da AreaAtividade escolhida na comboBox e
                //adiciona a uma lista para representar no ListView
                listCompTecExist.addAll(catTarefaCtrll.getListaCompetenciasTecnicasByIdAreaAtividade(areaAtividade.getId()));
                listViewCompTecnicaExistentes.getItems().addAll(listCompTecExist);
            } catch (Exception e) {
                Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                        "Não existem competências técnicas associadas à área de atividade escolhida", e.getMessage());
                if (alert.showAndWait().get() == ButtonType.OK) {
                    alert.close();
                }
            }
        }
    }

    @FXML
    private void cmbSelecionarGrauProficiencis(ActionEvent event) {
        if (cmbGrauProficiencia.getValue() != null) {
            btnAddOpcional.setDisable(false);
            btnAddObrigatorio.setDisable(false);
        }
    }

    void associarParentUI(TabCategoriaTarefaUI tabCategoriaTarefaUI) {
        this.tabCategoriaTarefaUI = tabCategoriaTarefaUI;
    }

    private void encerrarNovaCatTarefaUI(ActionEvent event) {
        this.txtCategoriaTarefaDesc.clear();
        this.listViewCompTecnicaExistentes.getItems().clear();
        this.listViewCompTecnicaSelected.getItems().clear();

        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void showAACreationSuccess() {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Sucesso", "Categoria de Tarefa adicionada");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            alerta.close();
        }
    }

    public void showIncorrectInformation(Exception e) {
        Alert alert = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO,
                "Erro ao adicionar Categoria de tarefa.", e.getMessage());
        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        encerrarNovaCatTarefaUI(event);
    }

    private void addCompetenciaTecnicaCT(String descricao, int obrigatoriedade, String grauProficiencia) throws Exception {
        for (CompetenciaTecnica ct : catTarefaCtrll.getListaCompetenciasTecnicas()) {
            if (ct.getAreaAtividade().equals(areaAtividade) && ct.getDescBreve().equalsIgnoreCase(descricao)) {
                catTarefaCtrll.addCompTecnicaToList(ct, obrigatoriedade, grauProficiencia);
            }
        }
    }

    private void removeCompetenciaTecnicaCT(String descricao) throws Exception {
        for (CompetenciaTecnica ct : catTarefaCtrll.getListaCompetenciasTecnicas()) {
            if (ct.getAreaAtividade().equals(areaAtividade) && ct.getDescBreve().equalsIgnoreCase(descricao)) {
                catTarefaCtrll.RemoveCompTecnicaFromList(ct);
            }
        }
    }

    @FXML
    private void actionAddCTObrigatorio(ActionEvent event) throws Exception {
        if (!(compTecExistenteSelec == null || compTecExistenteSelec.isEmpty() || listViewCompTecnicaSelected.getItems().contains(compTecExistenteSelec))) {
            listViewCompTecnicaSelected.getItems().add(compTecExistenteSelec);
            listViewCompTecnicaExistentes.getItems().remove(compTecExistenteSelec);
            addCompetenciaTecnicaCT(compTecExistenteSelec, 1, cmbGrauProficiencia.getValue());
            compTecExistenteSelec = null;
            compTecSelecionadasSelec = null;
        }
    }

    @FXML
    private void actionAddCTOpcional(ActionEvent event) throws Exception {
        if (!(compTecExistenteSelec == null || compTecExistenteSelec.isEmpty() || listViewCompTecnicaSelected.getItems().contains(compTecExistenteSelec))) {
            listViewCompTecnicaSelected.getItems().add(compTecExistenteSelec);
            listViewCompTecnicaExistentes.getItems().remove(compTecExistenteSelec);
            addCompetenciaTecnicaCT(compTecExistenteSelec, 0, cmbGrauProficiencia.getValue());
            compTecExistenteSelec = null;
            compTecSelecionadasSelec = null;
        }
    }

    @FXML
    private void actionRemoverCT(ActionEvent event) throws Exception {
        if (compTecSelecionadasSelec != null) {
            if (!(listViewCompTecnicaExistentes.getItems().contains(compTecSelecionadasSelec))) {
                listViewCompTecnicaSelected.getItems().remove(compTecSelecionadasSelec);
                listViewCompTecnicaExistentes.getItems().add(compTecSelecionadasSelec);
                removeCompetenciaTecnicaCT(compTecSelecionadasSelec);
                compTecSelecionadasSelec = null;
            }
        } else {
            event.consume();
        }
    }

    @FXML
    private void mouseListViewCompTecnicaExistentes(MouseEvent event) throws Exception {
        compTecExistenteSelec = listViewCompTecnicaExistentes.getSelectionModel().getSelectedItem();
        try{
        updateGrauProficiencia(catTarefaCtrll.getCompetenciaTecnicaByDescBreve(compTecExistenteSelec));
        } catch (Exception e) {
            System.out.print("");
        }
        btnAddOpcional.setDisable(true);
        btnAddObrigatorio.setDisable(true);
    }

    @FXML
    private void mouseListViewCompTecnicaSelec(MouseEvent event) {
        compTecSelecionadasSelec = listViewCompTecnicaSelected.getSelectionModel().getSelectedItem();
    }

}

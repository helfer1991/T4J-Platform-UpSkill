/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarCandidaturaController;
import com.company.model.Anuncio;
import com.company.model.Candidatura;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class TabCandidaturasUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private MainSceneUI mainSceneUI;
    private Stage novaCandidaturaStage;
    private Stage alterarCandidaturaStage;
    private Stage removerCandidaturaStage;

    @FXML
    private TableView<Candidatura> tableViewCandidatura;
    @FXML
    private TableColumn<Candidatura, String> tableViewIdAnuncio;
    @FXML
    private TableColumn<Candidatura, String> tableViewDataCand;
    @FXML
    private TableColumn<Candidatura, String> tableViewValorPretendido;
    @FXML
    private TableColumn<Candidatura, String> tableViewNrDias;

    private RegistarCandidaturaController candCtrl;
    @FXML
    private Button btnAlterarCandidatura;
    @FXML
    private Button btnRemoverCandidatura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadColumns();
        btnAlterarCandidatura.setVisible(false);
        btnRemoverCandidatura.setVisible(false);

    }

    void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabCandidaturaUI(this);
    }

    private void loadColumns() {
        tableViewIdAnuncio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Candidatura, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Candidatura, String> p) {
                return new SimpleStringProperty(p.getValue().getAnuncio().getTarefa().getRef());
            }

        });
        tableViewDataCand.setCellValueFactory(new PropertyValueFactory<Candidatura, String>("dtCandidatura"));
        tableViewValorPretendido.setCellValueFactory(new PropertyValueFactory<Candidatura, String>("valorPretendido"));
        tableViewNrDias.setCellValueFactory(new PropertyValueFactory<Candidatura, String>("nrDias"));
    }

    public void updateList() throws SQLException, Exception {
        candCtrl = new RegistarCandidaturaController();
        if (candCtrl.getAllCandidaturasByFreelancerEmail() != null) {
            ObservableList<Candidatura> list = FXCollections.observableArrayList(candCtrl.getAllCandidaturasByFreelancerEmail());
            tableViewCandidatura.getItems().clear();
            tableViewCandidatura.setItems(list);
        }
    }

    public Candidatura getSelected() {
        return tableViewCandidatura.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void actionAlterarCandidatura(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.ALTERAR_CAND_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            alterarCandidaturaStage = new Stage();
            alterarCandidaturaStage.initModality(Modality.APPLICATION_MODAL);
            alterarCandidaturaStage.setTitle(Constantes.ALTERAR_CAND_TITLE);
            alterarCandidaturaStage.setResizable(false);

            if (tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }
            alterarCandidaturaStage.setScene(scene);

            AlterarCandidaturaUI changeCandUI = loader.getController();
            changeCandUI.associarParentUI(this);

            alterarCandidaturaStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage()).showAndWait();
        }
    }

    @FXML

    private void actionRemoverCandidatura(ActionEvent event) throws Exception {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, App.TITULO_APLICACAO, "Tem a certeza que pretende eliminar esta candidatura?", "Não será possivel recuperar os dados");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            candCtrl.removerCandidatura(getSelected());
            updateList();
        } else {
            alerta.close();
            event.consume();
        }
    }

    @FXML
    private void tableViewCand(MouseEvent event) {
        if (getSelected() != null) {
            if (candCtrl.checkDate(getSelected())) {
                btnAlterarCandidatura.setVisible(true);
                btnRemoverCandidatura.setVisible(true);
            } else {
                btnAlterarCandidatura.setVisible(false);
                btnRemoverCandidatura.setVisible(false);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.RegistarFreelancerController;
import com.company.model.Freelancer;
import com.company.ui.MainSceneUI;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class TabFreelancersUI implements Initializable {
    
    private TemaApp tema = TemaApp.getInstance();

    
    private MainSceneUI mainSceneUI;
    private Stage novoFreelancerStage;

    @FXML
    private TableView<Freelancer> tableViewFreelancer;
    @FXML
    private TableColumn<Freelancer, String> tableViewNomeFreelancer;
    @FXML
    private TableColumn<Freelancer, String> tableViewNifFreelancer;
    @FXML
    private TableColumn<Freelancer, String> tableViewEmailFreelancer;
    @FXML
    private TableColumn<Freelancer, String> tableViewTlfFreelancer;
    @FXML
    private TableColumn<Freelancer, String> tableViewEndPostalFreelancer;
    
    private RegistarFreelancerController fCtrl;
    @FXML
    private Button btnAdicionarFreelancer1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableViewTabs();
    }

    @FXML
    private void actionAdicionarFreelancer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.REGISTO_FREELANCER_FILE));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            novoFreelancerStage = new Stage();
            novoFreelancerStage.initModality(Modality.APPLICATION_MODAL);
            novoFreelancerStage.setTitle(Constantes.REGISTO_FREELANCER_TITLE);
            novoFreelancerStage.setResizable(false);
            
            if(tema.isEnable()) {
                scene.getStylesheets().add(Constantes.CSS_DARK_THEME);
            } else {
                scene.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
            }
            
            novoFreelancerStage.setScene(scene);
            
            fCtrl = new RegistarFreelancerController();

            AdicionarFreelancerUI adicionarFreelancerUI = loader.getController();
            adicionarFreelancerUI.associarParentUI(this);
            adicionarFreelancerUI.setCtrl(fCtrl);

            novoFreelancerStage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, App.TITULO_APLICACAO, "Erro.", ex.getMessage());
        }

    }

    private void loadTableViewTabs() {
        
        tableViewNomeFreelancer.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("nome"));
        tableViewEmailFreelancer.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("email"));
        tableViewTlfFreelancer.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("telefone"));
        tableViewNifFreelancer.setCellValueFactory(new PropertyValueFactory<Freelancer, String>("nif"));
        tableViewEndPostalFreelancer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Freelancer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Freelancer, String> p) {
                return new SimpleStringProperty("Morada: "+ p.getValue().getEndPostal().getRua() 
                        + "\n" + "Codigo Postal: " + p.getValue().getEndPostal().getCodPostal()
                        + "\n" + "Localidade: " + p.getValue().getEndPostal().getLocalidade());
            }

        });
    }
     void associarParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        mainSceneUI.associarChildTabFreelancersUI(this);
    }
     
     public void updateList() throws Exception {
        fCtrl = new RegistarFreelancerController();

        List<Freelancer> tmp = new ArrayList<>();
        tmp = fCtrl.getListFreelancers();

        if (tmp != null) {
            ObservableList<Freelancer> ctList = FXCollections.observableArrayList(tmp);
            tableViewFreelancer.setItems(ctList);
        }

    }
}

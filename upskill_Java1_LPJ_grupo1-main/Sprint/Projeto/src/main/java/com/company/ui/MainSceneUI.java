/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.ui;

import com.company.controller.MainSceneController;
import static com.company.ui.App.TITULO_APLICACAO;
import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class MainSceneUI implements Initializable {

    private TemaApp tema = TemaApp.getInstance();

    private LoginUI loginUI;
    private TabCandidaturasUI tabCandidaturasUI;
    private TabSeriacaoUI tabSeriacaoUI;
    private TabAnunciosUI tabAnunciosUI;
    private TabAreasAtividadeUI tabAreasAtividadeUI;
    private TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI;
    private TabCategoriaTarefaUI tabCategoriaTarefaUI;
    private TabFreelancersUI tabFreelancersUI;
    private TabAnunciosDisponiveisUI tabAnunciosDisponiveisUI;
    private TabColaboradoresSceneUI tabColaboradoresSceneUI;
    private TabTarefasUI tabTarefasUI;
    private TabAtribuicaoUI tabAtribuicaoUI;
    private MainSceneController mSC;

    @FXML
    private TabPane tabPane;

    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private MenuItem menuItemLight;
    @FXML
    private MenuItem menuItemDark;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mSC = new MainSceneController();
        loadTabsByUserRole(mSC.getRole());
    }

    public void loadTabsByUserRole(String role) {
        try {
            unloadAllTabs();
            switch (role) {
                case Constantes.ROLE_ADMINISTRADOR:
                    loadTab(Constantes.TAB_MANAGE_AREAATIVIDADE_FILE, Constantes.TAB_MANAGE_AREAATIVIDADE_TITLE);
                    loadTab(Constantes.TAB_MANAGE_COMPTECNICA_FILE, Constantes.TAB_MANAGE_COMPTECNICA_TITLE);
                    loadTab(Constantes.TAB_MANAGE_CATTAREFA_FILE, Constantes.TAB_MANAGE_CATTAREFA_TITLE);
                    loadTab(Constantes.TAB_MANAGE_FREELANCERS_FILE, Constantes.TAB_MANAGE_FREELANCERS_TITLE);
                    break;
                case Constantes.ROLE_GESTOR_ORGANIZACAO:
                    loadTab(Constantes.TAB_MANAGE_COLABORADOR_FILE, Constantes.TAB_MANAGE_COLABORADOR_TITLE);
                    loadTab(Constantes.TAB_MANAGE_TAREFAS_FILE, Constantes.TAB_MANAGE_TAREFAS_TITLE);
                    loadTab(Constantes.TAB_MANAGE_ANUNCIOS_FILE, Constantes.TAB_MANAGE_ANUNCIOS_TITLE);
                    loadTab(Constantes.TAB_MANAGE_SERIACAO_FILE, Constantes.TAB_MANAGE_SERIACAO_TITLE);
                    loadTab(Constantes.TAB_MANAGE_ATRIBUICAO_FILE, Constantes.TAB_MANAGE_ATRIBUICAO_TITLE);
                    break;
                case Constantes.ROLE_COLABORADOR_ORGANIZACAO:
                    loadTab(Constantes.TAB_MANAGE_TAREFAS_FILE, Constantes.TAB_MANAGE_TAREFAS_TITLE);
                    loadTab(Constantes.TAB_MANAGE_ANUNCIOS_FILE, Constantes.TAB_MANAGE_ANUNCIOS_TITLE);
                    loadTab(Constantes.TAB_MANAGE_SERIACAO_FILE, Constantes.TAB_MANAGE_SERIACAO_TITLE);
                    loadTab(Constantes.TAB_MANAGE_ATRIBUICAO_FILE, Constantes.TAB_MANAGE_ATRIBUICAO_TITLE);
                    break;
                case Constantes.ROLE_FREELANCER:
                    loadTab(Constantes.TAB_MANAGE_ANUNCIOSDISPONIVEIS_FILE, Constantes.TAB_MANAGE_ANUNCIOSDISPONIVEIS_TITLE);
                    loadTab(Constantes.TAB_MANAGE_CANDIDATURAS_FILE, Constantes.TAB_MANAGE_CANDIDATURAS_TITLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTab(String TAB_FILE, String TAB_TITLE) {
        try {
            Tab tab = new Tab();
            tab.setText(TAB_TITLE);
            tabPane.getTabs().add(tab);

            //Load tab 
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(TAB_FILE));
            tab.setContent(loader.load());

            switch (TAB_FILE) {
                case (Constantes.TAB_MANAGE_COMPTECNICA_FILE):
                    TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI = loader.getController();
                    tabCompetenciaTecnicaUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_AREAATIVIDADE_FILE):
                    TabAreasAtividadeUI tabAreasAtividadeUI = loader.getController();
                    tabAreasAtividadeUI.associarParentUI(this);
                    tabAreasAtividadeUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_COLABORADOR_FILE):
                    TabColaboradoresSceneUI tabColaboradoresSceneUI = loader.getController();
                    tabColaboradoresSceneUI.associarParentUI(this);
                    tabColaboradoresSceneUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_CATTAREFA_FILE):
                    TabCategoriaTarefaUI tabCategoriaTarefaUI = loader.getController();
                    tabCategoriaTarefaUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_TAREFAS_FILE):
                    TabTarefasUI tabTarefasUI = loader.getController();
                    tabTarefasUI.associarParentUI(this);
                    if(mSC.isColaborador()){
                        tabTarefasUI.updateList();
                    }

                    break;
                case (Constantes.TAB_MANAGE_FREELANCERS_FILE): // DESCOMENTAR QUANDO A UI ESTIVER COMPLETA
                    TabFreelancersUI tabFreelancersUI = loader.getController();
                    tabFreelancersUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_ANUNCIOS_FILE):
                    TabAnunciosUI tabAnunciosUI = loader.getController();
                    tabAnunciosUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_CANDIDATURAS_FILE):
                    TabCandidaturasUI tabCandidaturasUI = loader.getController();
                    tabCandidaturasUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_ANUNCIOSDISPONIVEIS_FILE):
                    TabAnunciosDisponiveisUI tabAnunciosDisponiveisUI = loader.getController();
                    tabAnunciosDisponiveisUI.associarParentUI(this);
                    tabAnunciosDisponiveisUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_SERIACAO_FILE):
                    TabSeriacaoUI tabSeriacaoUI = loader.getController();
                    tabSeriacaoUI.associarParentUI(this);
                    break;
                case (Constantes.TAB_MANAGE_ATRIBUICAO_FILE):
                    TabAtribuicaoUI tabAtribuicaoUI = loader.getController();
                    tabAtribuicaoUI.associarParentUI(this);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void unloadAllTabs() {
        try {
            tabPane.getTabs().removeAll(tabPane.getTabs());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menuItemLogoutAction(ActionEvent event) {

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                "Confirmação da ação.", "Deseja mesmo efectuar logout?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            if (mSC.logout()) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, TITULO_APLICACAO, "Logout bem sucedido", "").showAndWait();
                Stage newLogin = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.LOGIN_FILE));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);

                    newLogin.setTitle(Constantes.LOGIN_TITLE);
                    newLogin.setScene(scene);

                    newLogin.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                                    "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                                event.consume();
                            }
                        }
                    });

                    Window window = tabPane.getScene().getWindow();
                    window.hide();

                    mSC.restartUAPI();
                    newLogin.show();
                } catch (IOException ex) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO,
                            "Problemas no arranque da aplicação.", ex.getMessage()).show();
                }
            }
        }

    }

    @FXML
    private void menuItemAboutAction(ActionEvent event) {
    }

    void associarParentUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    void associarChildTabCandidaturaUI(TabCandidaturasUI tabCandidaturasUI) {
        this.tabCandidaturasUI = tabCandidaturasUI;
    }

    void associarChildTabSeriacaoUI(TabSeriacaoUI tabSeriacaoUI) {
        this.tabSeriacaoUI = tabSeriacaoUI;
    }

    void associarChildTabAnunciosDisponiveisUI(TabAnunciosDisponiveisUI tabAnunciosDisponiveisUI) {
        this.tabAnunciosDisponiveisUI = tabAnunciosDisponiveisUI;
    }

    void associarChildTabAnunciosUI(TabAnunciosUI tabAnunciosUI) {
        this.tabAnunciosUI = tabAnunciosUI;
    }

    void associarChildTabAreasAtividadeUI(TabAreasAtividadeUI tabAreasAtividadeUI) {
        this.tabAreasAtividadeUI = tabAreasAtividadeUI;
    }

    void associarChildTabCategoriaTarefaUI(TabCategoriaTarefaUI tabCategoriaTarefaUI) {
        this.tabCategoriaTarefaUI = tabCategoriaTarefaUI;
    }

    void associarChildTabCompetenciaTecnicaUI(TabCompetenciaTecnicaUI tabCompetenciaTecnicaUI) {
        this.tabCompetenciaTecnicaUI = tabCompetenciaTecnicaUI;
    }

    void associarChildTabColaboradoresSceneUI(TabColaboradoresSceneUI tabColaboradoresSceneUI) {
        this.tabColaboradoresSceneUI = tabColaboradoresSceneUI;
    }

    void associarChildTabFreelancersUI(TabFreelancersUI tabFreelancersUI) {
        this.tabFreelancersUI = tabFreelancersUI;
    }

    void associarChildTabTarefasUI(TabTarefasUI tabTarefasUI) {
        this.tabTarefasUI = tabTarefasUI;
    }
    
    void associarChildTabAtribuicaoUI(TabAtribuicaoUI tabAtribuicaoUI) {
        this.tabAtribuicaoUI = tabAtribuicaoUI;
    }

    @FXML
    private void menuItemLightAction(ActionEvent event) {
        if (tema.isEnable()) {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Tema alterado para Light Theme.", "Por favor, reinicie a sessão para que este tema seja aplicado.");

            if (alerta.showAndWait().get() == ButtonType.OK) {
                alerta.close();
            }
        }
        tema.disableTema();
    }

    @FXML
    private void menuItemDarkAction(ActionEvent event) {
        if (!tema.isEnable()) {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, App.TITULO_APLICACAO, "Tema alterado para Dark Theme.", "Por favor, reinicie a sessão para que este tema seja aplicado.");

            if (alerta.showAndWait().get() == ButtonType.OK) {
                alerta.close();
            }
        }
        tema.enableTema();
    }

    @FXML
    private void clickedTabPane(MouseEvent event) {

        try {
            switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
                case (Constantes.TAB_MANAGE_COMPTECNICA_TITLE):
                    tabCompetenciaTecnicaUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_AREAATIVIDADE_TITLE):
                    tabAreasAtividadeUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_COLABORADOR_TITLE):
                    tabColaboradoresSceneUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_CATTAREFA_TITLE):
                    tabCategoriaTarefaUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_TAREFAS_TITLE):
                    tabTarefasUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_FREELANCERS_TITLE):
                    tabFreelancersUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_ANUNCIOS_TITLE):
                    tabAnunciosUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_CANDIDATURAS_TITLE):
                    tabCandidaturasUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_ANUNCIOSDISPONIVEIS_TITLE):
                    tabAnunciosDisponiveisUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_SERIACAO_TITLE):
                    tabSeriacaoUI.updateList();
                    break;
                case (Constantes.TAB_MANAGE_ATRIBUICAO_TITLE):
                    tabAtribuicaoUI.updateList();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

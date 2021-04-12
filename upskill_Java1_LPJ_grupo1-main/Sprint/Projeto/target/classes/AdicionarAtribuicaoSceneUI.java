/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.model.Classificacao;
import com.company.ui.AlertaUI;
import static com.company.ui.App.TITULO_APLICACAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author joaor
 */
public class AdicionarAtribuicaoSceneUI implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionAtribuir(ActionEvent event) {
    }

    @FXML
    private void actionCancel(ActionEvent event) {
            LimparAdicionarAtribuicaoScene(event);
    }

    void LimparAdicionarAtribuicaoScene(ActionEvent event) {
    }

    @FXML
    private void tableViewCandClass(MouseEvent event) {
    }
}

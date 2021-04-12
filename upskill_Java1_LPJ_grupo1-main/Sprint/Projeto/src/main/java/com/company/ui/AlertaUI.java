package com.company.ui;

import com.company.utils.Constantes;
import com.company.utils.TemaApp;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class AlertaUI {

    public static Alert criarAlerta(Alert.AlertType tipoAlerta, String titulo, String cabecalho, String mensagem) {
        
        TemaApp tema = TemaApp.getInstance();

        Alert alerta = new Alert(tipoAlerta);
        
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        
        DialogPane dialogPane = alerta.getDialogPane();
        
        if(tema.isEnable()) {
            dialogPane.getStylesheets().add(Constantes.CSS_DARK_THEME);
        } else {
            dialogPane.getStylesheets().add(Constantes.CSS_LIGHT_THEME);
        }
        
        if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Sim");
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Não");
        }
        
        return alerta;
    }
}

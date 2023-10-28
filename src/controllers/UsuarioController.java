package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.BDDGestorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UsuarioController implements Initializable {

    @FXML
    private TextField txtFUsuario;

    @FXML
    private TextField txtFPasswd;

    @FXML
    private Button btnLogin;

    @FXML
    void iniciarSesion(ActionEvent event) {
    	BDDGestorUsuario gstrUsuario = new BDDGestorUsuario();
    	if (gstrUsuario.comprobarUsuario(txtFUsuario.getText().toString(), txtFPasswd.getText().toString())) {
    		Main aeropuertosTabla = new Main();
    		aeropuertosTabla.changeScene("/fxml/aeropuertosTabla.fxml");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
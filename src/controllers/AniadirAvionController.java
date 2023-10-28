package controllers;

import dao.AvionesDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Aeropuertos;
import model.Aviones;

public class AniadirAvionController {

	@FXML
	private ComboBox<Aeropuertos> cmbxAeropuertos;

	@FXML
	private RadioButton rdBtnActivado;

	@FXML
	private ToggleGroup actDes;

	@FXML
	private RadioButton rdBtnDesactivado;

	@FXML
	private TextField txtFModelo;

	@FXML
	private TextField txtFAsientos;

	@FXML
	private TextField txtFVelocidadMax;

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnCancelar;

	AeropuertosTabla aeropuertosT;

	AvionesDao avionDao;

	ObservableList<Aeropuertos> obLstAeropuetos;

	public void setParent(AeropuertosTabla parent) {
		avionDao = new AvionesDao();

		aeropuertosT = parent;

		obLstAeropuetos = aeropuertosT.getAeropuertoD().cargarAeropuertosPrivados();
		obLstAeropuetos.addAll(aeropuertosT.getAeropuertoD().cargarAeropuertosPublicos());

		cmbxAeropuertos.setItems(obLstAeropuetos);
	}

	@FXML
	void guardarAvion(ActionEvent event) {

		if (txtFModelo.getText().isEmpty() || txtFAsientos.getText().isEmpty()
				|| !txtFAsientos.getText().matches("[0-9]*") || txtFVelocidadMax.getText().isEmpty()
				|| !txtFVelocidadMax.getText().matches("[0-9]*")) {
			
			Alert aviso = new Alert(AlertType.ERROR);
			aviso.setTitle("Error");
			aviso.setHeaderText(null);
			
			aviso.setHeaderText(null);
			String mensaje = "";
			if (txtFModelo.getText().isEmpty()) 
				mensaje += "El campo Modelo es Obligatorio \n";
			if (txtFAsientos.getText().isEmpty()) 
				mensaje += "El campo Numero Asientos es Obligatorio \n";
			if (!txtFAsientos.getText().matches("[0-9]*")) 
				mensaje += "El campo Edad debe ser númerico \n";
			if (txtFVelocidadMax.getText().isEmpty()) 
				mensaje += "El campo Velocidad Máxima es Obligatorio \n";
			if (!txtFVelocidadMax.getText().matches("[0-9]*")) 
				mensaje += "El campo Velocidad Máxima debe ser númerico \n";
			aviso.setContentText(mensaje);
			aviso.showAndWait();
			
		} else {
			int activo = -1;
			if (rdBtnActivado.isSelected())
				activo = 1;
			else
				activo = 0;

			Aviones avion = new Aviones(txtFModelo.getText(), Integer.parseInt(txtFAsientos.getText().toString()),
					Integer.parseInt(txtFVelocidadMax.getText().toString()), activo,
					cmbxAeropuertos.getSelectionModel().getSelectedItem().getId());

			ObservableList<Aviones> lstAviones = avionDao
					.cargarAvion(cmbxAeropuertos.getSelectionModel().getSelectedItem().getId());

			boolean existe = true;

			for (Aviones a : lstAviones) {
				if (!(a.getModelo().equals(avion.getModelo()) && a.getIdAeropuerto() == avion.getIdAeropuerto())) {
					existe = false;
				}
			}

			if (existe) {
				Alert aviso = new Alert(AlertType.ERROR);
				aviso.setTitle("Error");
				aviso.setHeaderText(null);
				aviso.setContentText("Este modelo de avión ya existe en el aeropuerto seleccionado");
				aviso.showAndWait();
			} else {
				avionDao.aniadirAviones(avion);
				txtFModelo.setText("");
				txtFAsientos.setText("");
				txtFVelocidadMax.setText("");
			}
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();

		Stage stage = (Stage) n.getScene().getWindow();

		stage.close();
	}
}

package controllers;

import dao.AvionesDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Aeropuertos;
import model.Aviones;

public class BorrarAvionController {

	@FXML
	private ComboBox<Aeropuertos> cmbxAeropuertos;

	@FXML
	private ComboBox<Aviones> cmBxAviones;

	@FXML
	private Button btnBorrar;

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

		/**
		 * Si se selcceciona u8n aeropuerto cargará el combobox de aviones con los
		 * aviones de esté
		 */
		cmbxAeropuertos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cmBxAviones
						.setItems(avionDao.cargarAvion(cmbxAeropuertos.getSelectionModel().getSelectedItem().getId()));
			}
		});
	}

	@FXML
	void borrarAvion(ActionEvent event) {

		if (cmBxAviones.getSelectionModel().getSelectedItem() != null) {
			avionDao.eliminarAviones(cmBxAviones.getSelectionModel().getSelectedItem());

			Node n = (Node) event.getSource();
			Stage stage = (Stage) n.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

}

package controllers;

import dao.AvionesDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Aeropuertos;
import model.Aviones;

public class ActivarDesactivarAvionController {

	@FXML
	private ComboBox<Aeropuertos> cmbxAeropuertos;

	@FXML
	private ComboBox<Aviones> cmBxAviones;

	@FXML
	private RadioButton rdBtnActivado;

	@FXML
	private ToggleGroup actDes;

	@FXML
	private RadioButton rdBtnDesactivado;

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

		cmBxAviones.setOnAction(e -> selectRadioButton(cmBxAviones.getSelectionModel().getSelectedItem()));

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

	/**
	 * Seleccionarará el radio button privado o público dependiendo de el avion
	 * seleccionado en el combobox
	 * 
	 * @param avion
	 */
	private void selectRadioButton(Aviones avion) {
		if (avion.getActivado() == 1)
			rdBtnActivado.setSelected(true);
		else
			rdBtnDesactivado.setSelected(true);
	}

	/**
	 * Actualizará la informaciçión del avion
	 * 
	 * @param event
	 */
	@FXML
	void guardarAvion(ActionEvent event) {
		if (rdBtnActivado.isSelected())
			avionDao.activarDeasctivarAviones(cmBxAviones.getSelectionModel().getSelectedItem(), 1);
		else
			avionDao.activarDeasctivarAviones(cmBxAviones.getSelectionModel().getSelectedItem(), 0);

		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}

	@FXML
	void cancelar(ActionEvent event) {
		Node n = (Node) event.getSource();
		Stage stage = (Stage) n.getScene().getWindow();
		stage.close();
	}
}

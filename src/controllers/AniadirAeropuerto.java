package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Aeropuertos;

public class AniadirAeropuerto {

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label lblFinanciacion;

	@FXML
	private Label lblNumTrabajadores;

	@FXML
	private TextField txtFNombre;

	@FXML
	private TextField txtFPais;

	@FXML
	private TextField txtFCiudad;

	@FXML
	private TextField txtFCalle;

	@FXML
	private TextField txtFNumero;

	@FXML
	private TextField txtFAnio;

	@FXML
	private TextField txtFCapacidad;

	@FXML
	private TextField txtFFinanciacion;

	@FXML
	private TextField txtFNumTrabajadores;

	@FXML
	private RadioButton rdBtnPublico;

	@FXML
	private ToggleGroup categoria;

	@FXML
	private RadioButton rdBtnPrivado;

	@FXML
	private TextField txtFNumSocios;

	@FXML
	private Label lblNumSocios;

	private AeropuertosTabla aeropuertosT;

	private Aeropuertos aeropuerto;

	public void setParent(AeropuertosTabla parent, Aeropuertos aero) {
		this.aeropuertosT = parent;
		this.aeropuerto = aero;

		rdBtnPublico.setOnAction(e -> getTextFields(e));
		rdBtnPrivado.setOnAction(e -> getTextFields(e));

		if (aeropuerto != null) {

			txtFNombre.setText(aeropuerto.getNombre());
			txtFPais.setText(aeropuerto.getPais());
			txtFCiudad.setText(aeropuerto.getPais());
			txtFCalle.setText(aeropuerto.getCalle());
			txtFNumero.setText(aeropuerto.getNumero() + "");
			txtFAnio.setText(aeropuerto.getAnio() + "");
			txtFCapacidad.setText(aeropuerto.getCapacidad() + "");

			if (aeropuertosT.privado(aeropuerto)) {
				rdBtnPrivado.setSelected(true);
				rdBtnPrivado.setDisable(true);
				rdBtnPublico.setDisable(true);
				txtFNumSocios.setText(aeropuerto.getNumSocios() + "");
			} else {
				rdBtnPublico.setSelected(true);
				rdBtnPrivado.setDisable(true);
				rdBtnPublico.setDisable(true);
				txtFFinanciacion.setText(aeropuerto.getFinanciacion() + "");
				txtFNumTrabajadores.setText(aeropuerto.getNumTrabajadores() + "");
			}
			ActionEvent e = new ActionEvent();
			getTextFields(e);
		}
	}

	private void getTextFields(ActionEvent event) {
		if (rdBtnPublico.isSelected()) {
			lblNumSocios.setVisible(false);
			txtFNumSocios.setVisible(false);
			lblFinanciacion.setVisible(true);
			txtFFinanciacion.setVisible(true);
			lblNumTrabajadores.setVisible(true);
			txtFNumTrabajadores.setVisible(true);
		}
		if (rdBtnPrivado.isSelected()) {
			lblNumSocios.setVisible(true);
			txtFNumSocios.setVisible(true);
			lblFinanciacion.setVisible(false);
			txtFFinanciacion.setVisible(false);
			lblNumTrabajadores.setVisible(false);
			txtFNumTrabajadores.setVisible(false);
		}
	}

	@FXML
	void guardarAeropuerto(ActionEvent event) {

		/* Comprueba que todos los datos introducidos están correctos */
		boolean error = false;

		Alert alertWindows = new Alert(Alert.AlertType.ERROR);

		String mensaje = "";

		if (txtFNombre.getText().isEmpty() || txtFPais.getText().isEmpty() || txtFCiudad.getText().isEmpty()
				|| txtFCalle.getText().isEmpty() || txtFNumero.getText().isEmpty()
				|| !txtFNumero.getText().matches("[0-9]*") || txtFAnio.getText().isEmpty()
				|| !txtFAnio.getText().matches("[0-9]*") || txtFCapacidad.getText().isEmpty()
				|| !txtFCapacidad.getText().matches("[0-9]*")) {

			error = true;

			if (txtFNombre.getText().isEmpty()) {
				mensaje += "El campo Nombre es Obligatorio \n";
			}
			if (txtFPais.getText().isEmpty()) {
				mensaje += "El campo Pais es Obligatorio \n";
			}
			if (txtFCiudad.getText().isEmpty()) {
				mensaje += "El campo Ciuad es Obligatorio \n";
			}
			if (txtFCalle.getText().isEmpty()) {
				mensaje += "El campo Calle es Obligatorio \n";
			}

			if (txtFNumero.getText().isEmpty()) {
				mensaje += "El campo Numero es Obligatorio \n";
			}
			if (!txtFNumero.getText().matches("[0-9]*")) {
				mensaje += "El campo Numero debe ser númerico \n";
			}

			if (txtFAnio.getText().isEmpty()) {
				mensaje += "El campo Año es Obligatorio \n";
			}
			if (!txtFAnio.getText().matches("[0-9]*")) {
				mensaje += "El campo Año debe ser númerico \n";
			}

			if (txtFCapacidad.getText().isEmpty()) {
				mensaje += "El campo Capacidad es Obligatorio \n";
			}
			if (!txtFCapacidad.getText().matches("[0-9]*")) {
				mensaje += "El campo Capacidad debe ser númerico \n";
			}
		}

		/* Comprueba los datos del nuevo Aeropuerto Privado */
		if (txtFNumSocios.getText().isEmpty() && rdBtnPrivado.isSelected()) {
			error = true;
			if (txtFNumSocios.getText().isEmpty()) {
				mensaje += "El campo Num Socios es Obligatorio \n";
			}
			if (txtFNumSocios.getText().matches("[0-9]*")) {
				mensaje += "El campo Num Socios debe ser númerico \n";
			}
		}

		/* Comprueba los datos del nuevo Aeropuerto Público */
		if ((txtFFinanciacion.getText().isEmpty() || txtFNumTrabajadores.getText().isEmpty())
				&& rdBtnPublico.isSelected()) {
			error = true;
			if (txtFFinanciacion.getText().isEmpty()) {
				mensaje += "El campo Financiacion es Obligatorio \n";
			}
			if (!txtFFinanciacion.getText().matches("[0-9]*")) {
				mensaje += "El campo Financiacion debe ser númerico \n";
			}
			if (txtFNumTrabajadores.getText().isEmpty()) {
				mensaje += "El campo Num Trabajadores es Obligatorio \n";
			}
			if (!txtFNumTrabajadores.getText().matches("[0-9]*")) {
				mensaje += "El campo Num Trabajadores debe ser númerico \n";
			}
		}

		if (error == true) {
			Stage stage = (Stage) alertWindows.getDialogPane().getScene().getWindow();
			alertWindows.setHeaderText(null);
			alertWindows.setContentText(mensaje);
			alertWindows.showAndWait();

		} else {

			/*
			 * Si se han introducido los datos correctamente se guarfará el aeropuerto en la
			 * base de datos
			 */
			Aeropuertos a;

			String nombre = txtFNombre.getText().toString();
			String pais = txtFPais.getText().toString();
			String ciudad = txtFCiudad.getText().toString();
			String calle = txtFCalle.getText().toString();
			int numero = Integer.parseInt(txtFNumero.getText().toString());
			int anio = Integer.parseInt(txtFAnio.getText().toString());
			int capacidad = Integer.parseInt(txtFCapacidad.getText().toString());

			if (rdBtnPrivado.isSelected()) {
				int numSocios = Integer.parseInt(txtFNumSocios.getText().toString());

				if (aeropuerto != null) {
					a = new Aeropuertos(aeropuerto.getId(), nombre, pais, ciudad, calle, numero, anio, capacidad,
							numSocios);
					aeropuertosT.getAeropuertoD().modificarAeropuerto(a, true);
				} else {
					a = new Aeropuertos(nombre, pais, ciudad, calle, numero, anio, capacidad, numSocios);
					aeropuertosT.getAeropuertoD().aniadirAeropuerto(a, true);
				}
			}

			if (rdBtnPublico.isSelected()) {
				int financiacion = Integer.parseInt(txtFFinanciacion.getText().toString());
				int numTrabajadores = Integer.parseInt(txtFNumTrabajadores.getText().toString());

				if (aeropuerto != null) {
					a = new Aeropuertos(aeropuerto.getId(), nombre, pais, ciudad, calle, numero, anio, capacidad,
							financiacion, numTrabajadores);
					aeropuertosT.getAeropuertoD().modificarAeropuerto(a, false);
				} else {
					a = new Aeropuertos(nombre, pais, ciudad, calle, numero, anio, capacidad, financiacion,
							numTrabajadores);
					aeropuertosT.getAeropuertoD().aniadirAeropuerto(a, false);
				}
			}

			ActionEvent e = new ActionEvent();
			aeropuertosT.getTabla(e);

			// Una vez guardada la persona se cerrara la ventana
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

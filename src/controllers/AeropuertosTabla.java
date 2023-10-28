package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import dao.AvionesDao;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Aeropuertos;
import model.Aviones;

public class AeropuertosTabla implements Initializable {

	@FXML
	private MenuItem mnItAniadirAeropuerto;

	@FXML
	private MenuItem mnItEditarAeropuerto;

	@FXML
	private MenuItem mnItBorrarAeropuerto;

	@FXML
	private MenuItem mnItMostarAeropuerto;

	@FXML
	private MenuItem mnItAniadirAvion;

	@FXML
	private MenuItem mnItActivarAvion;

	@FXML
	private MenuItem mnItBorrarAvion;

	@FXML
	private RadioButton rdBtnPublico;

	@FXML
	private ToggleGroup categoria;

	@FXML
	private RadioButton rdBtnPrivados;

	@FXML
	private TextField txtVNombre;

	@FXML
	private TableView<Aeropuertos> tbViewAeropuertos;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColId;

	@FXML
	private TableColumn<Aeropuertos, String> tbColNombre;

	@FXML
	private TableColumn<Aeropuertos, String> tbColPais;

	@FXML
	private TableColumn<Aeropuertos, String> tbColCiudad;

	@FXML
	private TableColumn<Aeropuertos, String> tbColCalle;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColNumero;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColAnio;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColCapacidad;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColNumSocios;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColFinanciacion;

	@FXML
	private TableColumn<Aeropuertos, Integer> tbColNumTrabajadores;

	private AeropuertoDao aeropuertoD;

	private AniadirAeropuerto addAeropuertoVentana;

	private int aeropuertoIndex = -1;

	void selectAeropuerto(MouseEvent event) {
		if (tbViewAeropuertos.getSelectionModel().getSelectedItem() != null) {
			aeropuertoIndex = tbViewAeropuertos.getSelectionModel().getSelectedIndex();
		} else {
			aeropuertoIndex = -1;
		}
	}

	@FXML
	void filtrarNombre(ActionEvent event) {
		txtVNombre.textProperty().addListener(e -> {
            /* Creamos una FilteredList con los datos de la tabla */
            FilteredList<Aeropuertos> filteredData = new FilteredList<Aeropuertos>(tbViewAeropuertos.getItems());
            /* Establecemos la regla del filtro: Si no contiene el texto en el textfield no se muestra */
            filteredData.setPredicate(s -> s.getNombre().contains(txtVNombre.getText()));
            /* Ordenamos la lista con una SortedList*/
            SortedList<Aeropuertos> filteredSortedData = new SortedList<Aeropuertos>(filteredData);
            tbViewAeropuertos.setItems(filteredSortedData); // Añadimos la lista ordenada a la tabla
        });;
	}

	/**
	 * Habrirá una ventana para que se añada un nuevo aeropuerto
	 * 
	 * @param event
	 */
	@FXML
	void aniadirAeropuerto(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirAeropuerto.fxml"));
			Parent root = loader.load();
			/* Le dice a la nueva ventana cual es su ventana padre */
			addAeropuertoVentana = loader.getController();
			addAeropuertoVentana.setParent(this, null);

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("AVIONES-AÑADIR AEROPUERTOS");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Habrirá una nueva ventana para editar los valores del aeropuerto
	 * seleccionado.
	 * 
	 * @param event
	 */
	@FXML
	void editarAeropuerto(ActionEvent event) {
		if (aeropuertoIndex != -1) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aniadirAeropuerto.fxml"));
				Parent root = loader.load();
				/* Le dice a la nueva ventana cual es su ventana padre */
				addAeropuertoVentana = loader.getController();
				addAeropuertoVentana.setParent(this, tbViewAeropuertos.getItems().get(aeropuertoIndex));

				Stage agregarStage = new Stage();
				agregarStage.setScene(new Scene(root));
				agregarStage.setResizable(false);
				agregarStage.setTitle("AVIONES-EDITAR AEROPUERTOS");
				agregarStage.showAndWait();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Muestrá la información del aeropuerto seleccionado y la información de los
	 * aviones que tiene
	 * 
	 * @param event
	 */
	@FXML
	void mostrarAeropuerto(ActionEvent event) {
		if (aeropuertoIndex != -1) {
			Aeropuertos aero = tbViewAeropuertos.getItems().get(aeropuertoIndex);

			Alert info = new Alert(AlertType.INFORMATION);
			info.setHeaderText(null);
			info.setTitle("informacion");
			info.setHeaderText(null);
			String mensaje = "Nombre: " + aero.getNombre() + "\n" 
							+ "Pais: " + aero.getPais() + "\n" 
							+ "Dirección: C/" + aero.getCalle() + " " + aero.getNumero() + ", " + aero.getPais() + "\n" 
							+ "Año de inaugiración: " + aero.getAnio() + "\n" 
							+ "Capacidad: " + aero.getCapacidad() + "\n" 
							+ "Aviones: \n";
			
			AvionesDao avionesDao = new AvionesDao();
			for (Aviones avio : avionesDao.cargarAvion(aero.getId())) {
				mensaje += "\t Modelo: " + avio.getModelo() + "\n"
						+ "\t Numero de asientos: " + avio.getNumeroAsientos() + "\n"
						+ "\t Velocidad máxima: " + avio.getVelocidadMaxima() + "\n";
				if (avio.getActivado() == 1) {
					mensaje += "\t Activado \n";
				} else {
					mensaje += "\t Desactivado \n";
				}
			}
			
			if (privado(aero)) {
				mensaje += "Privado \n"
						+ "Número de socios: " + aero.getNumSocios();
			} else {
				mensaje += "Público \n" 
						+ "Financiación: " + aero.getFinanciacion() + "\n"
						+ "Número de trabajadores: " + aero.getNumTrabajadores();
			}
			
			info.setContentText(mensaje);
			info.showAndWait();

		} else {
			aeropuertoIndex = -1;
		}

	}
	
	/**
	 * Borra el aeropuerto seleccionado
	 * @param event
	 */
	@FXML
	void borrarAeropuerto(ActionEvent event) {
		if (aeropuertoIndex != -1) {
			aeropuertoD.borrarAeropuerto(tbViewAeropuertos.getItems().get(aeropuertoIndex));
			ActionEvent e = new ActionEvent();
			getTabla(e);
		}
	}

	@FXML
	void aniadirAvion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AniadirAvion.fxml"));
			Parent root = loader.load();
			/* Le dice a la nueva ventana cual es su ventana padre */
			AniadirAvionController aAvionController = loader.getController();
			aAvionController.setParent(this);

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("AVIONES-AÑADIR AVIÓN");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void activarAvion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ActivarDesactivarAcion.fxml"));
			Parent root = loader.load();
			/* Le dice a la nueva ventana cual es su ventana padre */
			ActivarDesactivarAvionController actDesAvionController = loader.getController();
			actDesAvionController.setParent(this);

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("AVIONES-ACTIVAR/DESACTIVAR AVIÓN");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void borrarAvion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BorrarAvion.fxml"));
			Parent root = loader.load();
			/* Le dice a la nueva ventana cual es su ventana padre */
			BorrarAvionController borrarAvionCont = loader.getController();
			borrarAvionCont.setParent(this);

			Stage agregarStage = new Stage();
			agregarStage.setScene(new Scene(root));
			agregarStage.setResizable(false);
			agregarStage.setTitle("AVIONES-ACTIVAR/DESACTIVAR AVIÓN");
			agregarStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargará la tabla de aeropuertos públicos o privados dependiendo de que
	 * RadioButton esté seleccionado.
	 * 
	 * @param event
	 */
	public void getTabla(ActionEvent event) {
		if (rdBtnPublico.isSelected()) {
			tbColNumSocios.setVisible(false);
			tbColFinanciacion.setVisible(true);
			tbColNumTrabajadores.setVisible(true);

			tbViewAeropuertos.setItems(aeropuertoD.cargarAeropuertosPublicos());
		}
		if (rdBtnPrivados.isSelected()) {
			tbColNumSocios.setVisible(true);
			tbColFinanciacion.setVisible(false);
			tbColNumTrabajadores.setVisible(false);

			tbViewAeropuertos.setItems(aeropuertoD.cargarAeropuertosPrivados());
		}
	}

	/**
	 * Determina si un aeropuerto es privado o no
	 * 
	 * @param aero
	 * @return
	 */
	public boolean privado(Aeropuertos aero) {

		ObservableList<Aeropuertos> aeropuetosPrivados = aeropuertoD.cargarAeropuertosPrivados();

		for (Aeropuertos a : aeropuetosPrivados) {
			if (a.getId() == aero.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve el DAO de aeropuerto.
	 * 
	 * @return
	 */
	public AeropuertoDao getAeropuertoD() {
		return aeropuertoD;
	}

	/**
	 * Inicializará las acciones de los Radio Buttons. Determinará el tipo de
	 * información que tendrá las Table Columns. Mostrará la información de los
	 * aeropuertos privados en la tabla.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		rdBtnPublico.setOnAction(e -> getTabla(e));
		rdBtnPrivados.setOnAction(e -> getTabla(e));

		tbViewAeropuertos.setOnMouseClicked(e -> selectAeropuerto(e));

		// TODO Auto-generated method stub
		tbColId.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("id"));
		tbColNombre.setCellValueFactory(new PropertyValueFactory<Aeropuertos, String>("Nombre"));
		tbColPais.setCellValueFactory(new PropertyValueFactory<Aeropuertos, String>("pais"));
		tbColCiudad.setCellValueFactory(new PropertyValueFactory<Aeropuertos, String>("Ciudad"));
		tbColCalle.setCellValueFactory(new PropertyValueFactory<Aeropuertos, String>("Calle"));
		tbColNumero.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("numero"));
		tbColAnio.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("anio"));
		tbColCapacidad.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("Capacidad"));
		// Tabla Aeropuerto Privado
		tbColNumSocios.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("numSocios"));
		// Tabla Aeropuertos Público
		tbColFinanciacion.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("financiacion"));
		tbColNumTrabajadores.setCellValueFactory(new PropertyValueFactory<Aeropuertos, Integer>("numTrabajadores"));

		aeropuertoD = new AeropuertoDao();

		tbViewAeropuertos.setItems(aeropuertoD.cargarAeropuertosPrivados());
	}
}

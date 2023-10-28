package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aeropuertos;
import model.Direccion;

public class AeropuertoDao {

	private ConexionBDD conexion;
	
	private Direccion dirAeropuerto(int idAeropuerto) {
		try {
			conexion = new ConexionBDD();
			
			String consultaDireccion = "SELECT * FROM direcciones WHERE id = " + idAeropuerto;
			
			PreparedStatement pstmtDireccion = conexion.getConexion().prepareStatement(consultaDireccion);
			ResultSet rsDireccion = pstmtDireccion.executeQuery();
			rsDireccion.next();
			
			String pais = rsDireccion.getString("pais");
			String ciudad = rsDireccion.getString("ciudad");
			String calle = rsDireccion.getString("calle");
			int numero = rsDireccion.getInt("numero");
			
			rsDireccion.close();
			conexion.CloseConexion();
			
			return new Direccion(pais, ciudad, calle, numero);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Devuelve una lista de los aeropuertos privados almacenadas en la base de
	 * datos
	 */
	public ObservableList<Aeropuertos> cargarAeropuertosPrivados() {

		ObservableList<Aeropuertos> aeropuertosPrivados = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();

			/*
			 * Recogemos la información de los IDs y número de socios de los aeropuertos
			 * privados
			 */
			String consulta = "select * from aeropuertos_privados";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			HashMap<Integer, Integer> mapPrivados = new HashMap<Integer, Integer>();

			while (rs.next()) {
				int idAeropuertos = rs.getInt("id_aeropuerto");
				int numSocios = rs.getInt("numero_socios");
				mapPrivados.put(idAeropuertos, numSocios);
			}
			rs.close();

			/* Recogemos los aeropuertos que su id este dentro del HashMap mapPrivados */

			consulta = "SELECT * FROM aeropuertos";
			pstmt = conexion.getConexion().prepareStatement(consulta);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (mapPrivados.containsKey(rs.getInt("id"))) {

					Direccion dir = dirAeropuerto(rs.getInt("id"));

					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String pais = dir.getPais();
					String ciudad = dir.getCiudad();
					String calle = dir.getCalle();
					int numero = dir.getNumero();
					int anio = rs.getInt("anio_inauguracion");
					int capacidad = rs.getInt("capacidad");
					int numSocios = mapPrivados.get(rs.getInt("id"));

					Aeropuertos a = new Aeropuertos(id, nombre, pais, ciudad, calle, numero, anio, capacidad,
							numSocios);

					aeropuertosPrivados.add(a);
				}
			}

			rs.close();

			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertosPrivados;
	}

	/*
	 * Devuelve una lista de los aeropuertos publicos almacenadas en la base de
	 * datos
	 */
	public ObservableList<Aeropuertos> cargarAeropuertosPublicos() {

		ObservableList<Aeropuertos> aeropuertosPublicos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();

			/*
			 * Recogemos la información de los IDs y número de socios de los aeropuertos
			 * publicos
			 */
			String consulta = "select * from aeropuertos_publicos";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			HashMap<Integer, Integer[]> mapPublicos = new HashMap<Integer, Integer[]>();

			while (rs.next()) {
				int idAeropuertos = rs.getInt("id_aeropuerto");
				int financiacion = rs.getInt("financiacion");
				int numTrabajadores = rs.getInt("num_trabajadores");
				Integer[] infoPublicos = new Integer[2];
				infoPublicos[0] = financiacion;
				infoPublicos[1] = numTrabajadores;
				mapPublicos.put(idAeropuertos, infoPublicos);
			}
			rs.close();

			/* Recogemos los aeropuertos que su id este dentro del HashMap mapPublicos */

			consulta = "SELECT * FROM aeropuertos";
			pstmt = conexion.getConexion().prepareStatement(consulta);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (mapPublicos.containsKey(rs.getInt("id"))) {

					Direccion dir = dirAeropuerto(rs.getInt("id"));

					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String pais = dir.getPais();
					String ciudad = dir.getCiudad();
					String calle = dir.getCalle();
					int numero = dir.getNumero();
					int anio = rs.getInt("anio_inauguracion");
					int capacidad = rs.getInt("capacidad");
					int financiacion = mapPublicos.get(rs.getInt("id"))[0];
					int numTrabajadores = mapPublicos.get(rs.getInt("id"))[1];

					Aeropuertos a = new Aeropuertos(id, nombre, pais, ciudad, calle, numero, anio, capacidad,
							financiacion, numTrabajadores);

					aeropuertosPublicos.add(a);
				}
			}

			rs.close();

			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertosPublicos;
	}
	
	/**
	 * 
	 * Añadirá un aeropuerto nuevo a la base de datos.
	 * 
	 * @param aeropuerto
	 * @param privado
	 */
	public void aniadirAeropuerto(Aeropuertos aeropuerto, boolean privado) {

		try {
			
			int idDireccion = direccionAeropuerto(aeropuerto);
			
			conexion = new ConexionBDD();

			String consulta;
			PreparedStatement pstmt;

			consulta = "INSERT INTO aeropuertos(nombre,anio_inauguracion, capacidad, id_direccion) VALUES('"
					+ aeropuerto.getNombre() + "'," + aeropuerto.getAnio() + "," + aeropuerto.getCapacidad() + ","
					+ idDireccion + ")";
			pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			int idAeropuerto = 0;
			
			consulta = "SELECT * FROM aeropuertos ORDER BY id DESC";
			pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				idAeropuerto = rs.getInt("id");
			}
			
			/*
			 * Añade el nuevo aeropuerto al aeropuerto público o privado dependiendo de cual
			 * se haya seleccionado
			 */
			if (privado) {
				consulta = "INSERT INTO aeropuertos_privados VALUES(" + idAeropuerto + ","
						+ aeropuerto.getNumSocios() + ")";

			} else {
				consulta = "INSERT INTO aeropuertos_publicos VALUES(" + idAeropuerto + ","
						+ aeropuerto.getFinanciacion() + "," + aeropuerto.getNumTrabajadores() + ")";
			}

			pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Añade la direccion del aeropuerto */
	private void aniadirDireccion(String pais, String ciudad, String calle, int numero) {

		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO direcciones(pais,ciudad,calle,numero) VALUES('" + pais + "','" + ciudad + "','"
					+ calle + "'," + numero + ");";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Modifica el aeropuerto que le pasemos */
	public void modificarAeropuerto(Aeropuertos aeropuerto, boolean privado) {

		try {
			
			String consulta;
			PreparedStatement pstmt;
			int idDireccion = direccionAeropuerto(aeropuerto);

			conexion = new ConexionBDD();
			consulta = "UPDATE aeropuertos SET nombre = '" + aeropuerto.getNombre() + "', anio_inauguracion = "
					+ aeropuerto.getAnio() + ", capacidad = " + aeropuerto.getCapacidad() + ", id_direccion = "
					+ idDireccion + " WHERE id = " + aeropuerto.getId() + ";";
			pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			if (privado == true) {
				consulta = "UPDATE aeropuertos_privados SET numero_socios = " + aeropuerto.getNumSocios() + " WHERE id_aeropuerto = " + aeropuerto.getId() + ";";
			} else {
				consulta = "UPDATE aeropuertos_publicos SET financiacion = " + aeropuerto.getFinanciacion() + ", num_trabajadores = " + aeropuerto.getNumTrabajadores() + " WHERE id_aeropuerto = " + aeropuerto.getId() + ";";
			}
			pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Metodo que comprueba si la direccion del aeropuerto existe o no y si no
	 * existe lo crea. Devolvera el id de la direccion
	 */
	private int direccionAeropuerto(Aeropuertos aeropuerto) throws SQLException {

		/* Recoge la ip de la direccion */
		conexion = new ConexionBDD();

		String consulta = "SELECT id FROM direcciones WHERE pais LIKE '" + aeropuerto.getPais() + "' AND ciudad LIKE '"
				+ aeropuerto.getCalle() + "' AND calle LIKE '" + aeropuerto.getCalle() + "' AND numero = " + aeropuerto.getNumero();
		PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
		ResultSet rs = pstmt.executeQuery();

		int idDireccion = 0;

		/* Si no existe la direccion, creará una nueva */
		if (rs.next() == false) {
			aniadirDireccion(aeropuerto.getPais(), aeropuerto.getCalle(), aeropuerto.getCalle(),
					aeropuerto.getNumero());
			rs.close();
			/* Recoge la ip de la nueva direccion */
			pstmt = conexion.getConexion().prepareStatement(consulta);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idDireccion = rs.getInt("id");
			}
			
			rs.close();

			/* Si ya existe la direccion guardará el id de está */
		} else {
			idDireccion = rs.getInt("id");
			rs.close();
		}
		conexion.CloseConexion();

		return idDireccion;
	}
	
	public void borrarAeropuerto(Aeropuertos aeropuerto) {
		
		int idAeropuerto = aeropuerto.getId();
		
		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM aeropuertos WHERE id = " + idAeropuerto + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			
			conexion.CloseConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
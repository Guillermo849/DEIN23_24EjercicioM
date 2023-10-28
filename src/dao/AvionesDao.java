package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aviones;

public class AvionesDao {
	private ConexionBDD conexion;

	/* Devuelve una lista de los aviones almacenados en la base de datos */
	public ObservableList<Aviones> cargarAvion(int id) {

		ObservableList<Aviones> aviones = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBDD();
			String consulta = "select * from aviones where id_aeropuerto = " + id;
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idAvion = rs.getInt("id");
				String modelo = rs.getString("modelo");
				int numAsientos = rs.getInt("numero_asientos");
				int velMaxima = rs.getInt("velocidad_maxima");
				int activado = rs.getInt("activado");
				int idAeropuerto = rs.getInt("id_aeropuerto");

				Aviones a = new Aviones(idAvion, modelo, numAsientos, velMaxima, activado, idAeropuerto);
				aviones.add(a);
			}
			rs.close();
			conexion.CloseConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aviones;
	}
	
	
	
	/* Insertará un nuevo avion en la base de datos */
	public void aniadirAviones(Aviones avion) {

		String modelo = avion.getModelo();
		int numAsientos = avion.getNumeroAsientos();
		int velMaxima = avion.getVelocidadMaxima();
		int activado = avion.getActivado();
		int idAeropuerto = avion.getIdAeropuerto();

		try {
			conexion = new ConexionBDD();
			String consulta = "INSERT INTO aviones(modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto) VALUES('"
					+ modelo + "'," + numAsientos + "," + velMaxima + "," + activado + "," + idAeropuerto + ");";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Cambiará si un avion está acitvado o desactivado */
	public void activarDeasctivarAviones(Aviones avion, int activado) {
		try {
			conexion = new ConexionBDD();
			String consulta = "UPDATE aviones SET activado = " + activado + " WHERE id = " + avion.getId() + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Eliminará un Avion de la base de datos */
	public void eliminarAviones(Aviones avion) {

		int idAvion = avion.getId();

		try {
			conexion = new ConexionBDD();
			String consulta = "DELETE FROM aviones WHERE id = " + idAvion + ";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();

			conexion.CloseConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

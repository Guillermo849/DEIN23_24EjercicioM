package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Programa que comprueba si el usuario y contraseña pasado al 
 * método comprobar Usuario está dentro de la Tabla Usuario
 */

import conexion.ConexionBDD;

public class BDDGestorUsuario {

	private ConexionBDD conexion;

	public BDDGestorUsuario() {
		// TODO Auto-generated constructor stub
		conexion = new ConexionBDD();
	}

	public boolean comprobarUsuario(String usu, String passwd) {
		try {
			String consulta = "select * from usuarios";
			PreparedStatement pstmt;
			pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				if (usu.equals(res.getString("usuario")) && passwd.equals(res.getString("password"))) {
					res.close();
					conexion.CloseConexion();
					return true;
				}
			}
			res.close();
			conexion.CloseConexion();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conexion.CloseConexion();
		return false;
	}
}

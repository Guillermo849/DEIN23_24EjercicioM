package model;

public class Aviones {
	
	private int id;
	private String modelo;
	private int numeroAsientos;
	private int velocidadMaxima;
	private int activado;
	private int idAeropuerto;
	
	public Aviones( String modelo, int numeroAsientos, int velocidadMaxima, int activado, int idAeropuerto) {
		this.modelo = modelo;
		this.numeroAsientos = numeroAsientos;
		this.velocidadMaxima = velocidadMaxima;
		this.activado = activado;
		this.idAeropuerto = idAeropuerto;
	}
	
	public Aviones(int id, String modelo, int numeroAsientos, int velocidadMaxima, int activado, int idAeropuerto) {
		this.id = id;
		this.modelo = modelo;
		this.numeroAsientos = numeroAsientos;
		this.velocidadMaxima = velocidadMaxima;
		this.activado = activado;
		this.idAeropuerto = idAeropuerto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumeroAsientos() {
		return numeroAsientos;
	}

	public void setNumeroAsientos(int numeroAsientos) {
		this.numeroAsientos = numeroAsientos;
	}

	public int getVelocidadMaxima() {
		return velocidadMaxima;
	}

	public void setVelocidadMaxima(int velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	public int getActivado() {
		return activado;
	}

	public void setActivado(int activado) {
		this.activado = activado;
	}

	public int getIdAeropuerto() {
		return idAeropuerto;
	}

	public void setIdAeropuerto(int idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return modelo;
	}
}

package model;

public class Aeropuertos {
	private int id;
	private String nombre;
	private String pais;
	private String ciudad;
	private String calle;
	private int numero;
	private int anio;
	private int capacidad;
	private int numSocios;
	private int financiacion;
	private int numTrabajadores;
	
	public Aeropuertos( String nombre, String pais, String ciudad, String calle, int numero, int anio,
			int capacidad, int financiacion, int numTrabajadores) {
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.anio = anio;
		this.capacidad = capacidad;
		this.financiacion = financiacion;
		this.numTrabajadores = numTrabajadores;
	}
	
	public Aeropuertos( String nombre, String pais, String ciudad, String calle, int numero, int anio,
			int capacidad, int numSocios) {
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.anio = anio;
		this.capacidad = capacidad;
		this.numSocios = numSocios;
	}
	
	public Aeropuertos(int id, String nombre, String pais, String ciudad, String calle, int numero, int anio,
			int capacidad, int numSocios) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.anio = anio;
		this.capacidad = capacidad;
		this.numSocios = numSocios;
	}
	
	public Aeropuertos(int id, String nombre, String pais, String ciudad, String calle, int numero, int anio,
			int capacidad, int financiacion, int numTrabajadores) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.anio = anio;
		this.capacidad = capacidad;
		this.financiacion = financiacion;
		this.numTrabajadores = numTrabajadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getNumSocios() {
		return numSocios;
	}

	public void setNumSocios(int numSocios) {
		this.numSocios = numSocios;
	}

	public int getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(int financiacion) {
		this.financiacion = financiacion;
	}

	public int getNumTrabajadores() {
		return numTrabajadores;
	}

	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}
}

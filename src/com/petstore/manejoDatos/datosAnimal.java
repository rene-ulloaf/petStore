package com.petstore.manejoDatos;

public class datosAnimal {
	private Long _Id;
	private String _nombre; 
	private Integer _idTipo;
	private Integer _idEspecie;
	private Integer _idRaza;
	private Integer _peso;
	private String _edad;
	private Integer _precio;
	private boolean _vigente;
	
	public datosAnimal(){
		set_nombre("");
		set_edad("");
	}
	
	public void set_id(Long id){
		_Id = id;
	}
	
	public Long get_id(){
		return _Id;
	}

	
	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public Integer get_idTipo() {
		return _idTipo;
	}

	public void set_idTipo(Integer _idTipo) {
		this._idTipo = _idTipo;
	}

	public Integer get_idEspecie() {
		return _idEspecie;
	}

	public void set_idEspecie(Integer _idEspecie) {
		this._idEspecie = _idEspecie;
	}

	public Integer get_idRaza() {
		return _idRaza;
	}

	public void set_idRaza(Integer _idRaza) {
		this._idRaza = _idRaza;
	}

	public Integer get_peso() {
		return _peso;
	}

	public void set_peso(Integer _peso) {
		this._peso = _peso;
	}

	public String get_edad() {
		return _edad;
	}

	public void set_edad(String _edad) {
		this._edad = _edad;
	}

	public boolean is_vigente() {
		return _vigente;
	}

	public void set_vigente(boolean _vigente) {
		this._vigente = _vigente;
	}

	public Integer get_precio() {
		return _precio;
	}

	public void set_precio(Integer _precio) {
		this._precio = _precio;
	}
}
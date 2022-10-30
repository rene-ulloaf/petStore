package com.petstore.manejoDatos;

public class datosCMB{
	int _id;
	String nombre;
	//Constructor
	public datosCMB(int id, String nombre) {
		super();
		this._id = id;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return nombre;
	}
	public int getId() {
		return _id;
	}
}
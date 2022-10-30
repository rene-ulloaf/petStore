package com.petstore.manejoDatos;

public class datosTipo {
	private Integer _idTipo;
	private String _glosa;
	private Integer _orden;
	private Integer _vigente;
	
	public datosTipo(){
		_glosa = "";
	}
	
	public void setIdTipo(Integer idTipo){
		_idTipo = idTipo;
	}
	
	public Integer getIdTipo(){
		return _idTipo;
	}

	public void setGlosa(String glosa){
		_glosa = glosa;
	}
	
	public String getGlosa(){
		return _glosa;
	}
	
	public void setOrden(Integer orden){
		_orden = orden;
	}
	
	public Integer getOrden(){
		return _orden;
	}
	
	public void setVigente(Integer vigente){
		_vigente = vigente;
	}
	
	public Integer getVigente(){
		return _vigente;
	}
}

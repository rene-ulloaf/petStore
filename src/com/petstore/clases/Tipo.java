package com.petstore.clases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.petstore.DB.BaseDatosHelper;
import com.petstore.manejoDatos.datosCMB;
import com.petstore.manejoDatos.datosTipo;

public class Tipo {
	private BaseDatosHelper BDHelper;
	
	public Tipo(Context context){
		BDHelper = new BaseDatosHelper(context);
	}
	
	private void databaseIsOpen() throws IOException{
		if(!BDHelper.getDatabase().isOpen())
		{
			throw new IOException("Cannot open a new conection");
		}
	}

	public LinkedList<datosCMB> ObtenerCMB() throws IOException{
		LinkedList<datosCMB> tipos = new LinkedList<datosCMB>();
		Cursor c;

		databaseIsOpen();
		c = BDHelper.getDatabase().query("Tipo", new String[] {"_id", "glosa"}, null, null, "orden", null, null);
		
		//Iteramos a traves de los registros del cursor
		c.moveToFirst();
	    while (c.isAfterLast() == false) {
	    	tipos.add(new datosCMB(c.getInt(0),c.getString(1)));
	        c.moveToNext();
	    }
	    
		c.close();
	     
		return tipos;
	}
	
	public List<datosTipo> Obtener() throws IOException{
		List<datosTipo> tipos = new ArrayList<datosTipo>();
		datosTipo tipo;
		Cursor c;

		databaseIsOpen();
		c = BDHelper.getDatabase().query("Tipo", new String[] {"_id", "glosa"}, null, null, "orden", null, null);
		
		//Iteramos a traves de los registros del cursor
		c.moveToFirst();
	    while (c.isAfterLast() == false) {
	    	tipo = new datosTipo();
	    	
	    	tipo.setIdTipo(c.getInt(0));
	    	tipo.setGlosa(c.getString(1));
	        
	    	tipos.add(tipo);
	        c.moveToNext();
	    }
	    
		c.close();
	     
		return tipos;
	}
}

package com.petstore.clases;

import java.io.IOException;
import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;

import com.petstore.DB.BaseDatosHelper;
import com.petstore.manejoDatos.datosCMB;

public class Especie {
	private BaseDatosHelper BDHelper;
	
	public Especie(Context context){
		BDHelper = new BaseDatosHelper(context);
	}
	
	private void databaseIsOpen() throws IOException{
		if(!BDHelper.getDatabase().isOpen())
		{
			throw new IOException("Cannot open a new conection");
		}
	}

	public LinkedList<datosCMB> ObtenerCMB(Integer idTipo) throws IOException{
		LinkedList<datosCMB> especies = new LinkedList<datosCMB>();
		Cursor c;

		databaseIsOpen();
		c = BDHelper.getDatabase().query("Especie", new String[] {"_id", "glosa"}, "idTipo=" + idTipo, null, "orden", null, null);
		
		//Iteramos a traves de los registros del cursor
		c.moveToFirst();
	    while (c.isAfterLast() == false) {
	    	especies.add(new datosCMB(c.getInt(0),c.getString(1)));
	        c.moveToNext();
	    }
	    
		c.close();
	     
		return especies;
	}
}

package com.petstore.clases;

import java.io.IOException;
import java.util.LinkedList;

import android.content.Context;
import android.database.Cursor;

import com.petstore.DB.BaseDatosHelper;
import com.petstore.manejoDatos.datosCMB;

public class Raza {
	private BaseDatosHelper BDHelper;
	
	public Raza(Context context){
		BDHelper = new BaseDatosHelper(context);
	}
	
	private void databaseIsOpen() throws IOException{
		if(!BDHelper.getDatabase().isOpen())
		{
			throw new IOException("Cannot open a new conection");
		}
	}

	public LinkedList<datosCMB> ObtenerCMB(Integer idEspecie) throws IOException{
		LinkedList<datosCMB> razas = new LinkedList<datosCMB>();
		Cursor c;

		databaseIsOpen();
		c = BDHelper.getDatabase().query("Raza", new String[] {"_id", "glosa"}, "idEspecie=" + idEspecie, null, "orden", null, null);
		
		//Iteramos a traves de los registros del cursor
		c.moveToFirst();
	    while (c.isAfterLast() == false) {
	    	razas.add(new datosCMB(c.getInt(0),c.getString(1)));
	        c.moveToNext();
	    }
	    
		c.close();
	     
		return razas;
	}
}

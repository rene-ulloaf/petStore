package com.petstore.clases;

import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.petstore.DB.BaseDatosHelper;
import com.petstore.manejoDatos.datosAnimal;

public class Animal {
	private BaseDatosHelper BDHelper;
	
	public Animal(Context context){
		BDHelper = new BaseDatosHelper(context);
	}
	
	private void databaseIsOpen() throws IOException{
		if(!BDHelper.getDatabase().isOpen())
		{
			throw new IOException("Cannot open a new conection");
		}
	}

	public Long Agregar(datosAnimal animal) throws IOException{
		databaseIsOpen();
		
		ContentValues cv = new ContentValues();
	    //cv.put("_id", animal.get_id());
	    cv.put("nombre", animal.get_nombre());
	    cv.put("idTipo", animal.get_idTipo());
	    cv.put("idEspecie", animal.get_idEspecie());
	    cv.put("idRaza", animal.get_idRaza());
	    cv.put("peso", animal.get_peso());
	    cv.put("edad", animal.get_edad());
	    cv.put("precio", animal.get_precio());
	    //cv.put("vigente", animal.is_vigente());
	    
	    //Realizamos la consulta
	    long rowid = BDHelper.getDatabase().insert("Animal", null, cv);
	 
	    return rowid;
	}
	
	public boolean Modificar(datosAnimal animal) throws IOException{
		databaseIsOpen();
		
		ContentValues cv = new ContentValues();
	    
		cv.put("_id", animal.get_id());
	    cv.put("nombre", animal.get_nombre());
	    cv.put("idTipo", animal.get_idTipo());
	    cv.put("idEspecie", animal.get_idEspecie());
	    cv.put("idRaza", animal.get_idRaza());
	    cv.put("peso", animal.get_peso());
	    cv.put("edad", animal.get_edad());
	    cv.put("precio", animal.get_precio());
	    cv.put("vigente", animal.is_vigente());

	    //Realizamos la consulta
	    int fa = BDHelper.getDatabase().update("Animal", cv, "_id=?", new String[]{String.valueOf(animal.get_id())});
	 
	    if(fa > 0){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	public datosAnimal Obtener(Long idAnimal) throws IOException{
		datosAnimal animal = new datosAnimal();
		Cursor c;

		databaseIsOpen();
		c = BDHelper.getDatabase().query("Animal", new String[] {"nombre", "idTipo", "idEspecie", "idRaza", "peso", "edad", "precio", "vigente"}, "_id=" + idAnimal, null, null, null, null);
		
		//Iteramos a traves de los registros del cursor
		c.moveToFirst();
		
	    while (c.isAfterLast() == false) {
	    	animal.set_nombre(c.getString(0));
	    	animal.set_idTipo(c.getInt(1));
	    	animal.set_idEspecie(c.getInt(2));
	    	animal.set_idRaza(c.getInt(3));
	    	animal.set_peso(c.getInt(4));
	    	animal.set_edad(c.getString(5));
	    	animal.set_precio(c.getInt(6));
	    	animal.set_vigente((c.getInt(7) == 1 ? true : false));
	    	
	        c.moveToNext();
	    }
	    
		c.close();
	     
		return animal;
	}
}
package com.petstore.DB;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
/**
* Clase que facilita el acceso a una Base de Datos SQLite creando la Base de datos a partir de un fichero
* en la carpeta Assets
**/
public class BaseDatosHelper extends SQLiteOpenHelper {
        private static String DB_NAME = "PetStoreDB";
        //private SQLiteDatabase PetStore;
        
     	public BaseDatosHelper(Context context) {
    		//Constructor de la bd, aqui solo le paso el Context, luego el nommbre de la variable
     		super(context, DB_NAME, null, 7);
    	}
     	
     	/* retorna una conexion a la DB de lectura y escritura*/
    	public SQLiteDatabase getDatabase()
    	{
    		return super.getWritableDatabase();
    	}  
    	    
		@Override
		public void onCreate(SQLiteDatabase db) {
			//db.execSQL("CREATE TABLE android_metadata (locale TEXT DEFAULT 'es_ES');");
			db.execSQL("CREATE TABLE Raza (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,idEspecie INTEGER NOT NULL,glosa TEXT NOT NULL,orden INTEGER,vigente INTEGER NOT NULL DEFAULT (1));");
			db.execSQL("CREATE TABLE Especie (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,idTipo INTEGER NOT NULL,glosa TEXT NOT NULL,orden INTEGER,vigente INTEGER NOT NULL DEFAULT (1));");
			db.execSQL("CREATE TABLE Tipo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,glosa TEXT NOT NULL,orden INTEGER,vigente INTEGER NOT NULL DEFAULT (1));");
			db.execSQL("CREATE TABLE Animal (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, idTipo INTEGER NOT NULL, idEspecie INTEGER NOT NULL, idRaza INTEGER NOT NULL, peso TEXT, edad TEXT, precio INTEGER NOT NULL, vigente INTEGER NOT NULL DEFAULT (1));");
			
			/*db.execSQL("CREATE TABLE sqlite_sequence(name,seq);");
			
			db.execSQL("CREATE UNIQUE INDEX indx_primary_animal on animal (_id ASC);");
			db.execSQL("CREATE UNIQUE INDEX indx_primary_especie on especie (_id ASC);");
			db.execSQL("CREATE UNIQUE INDEX indx_primary_raza on raza (_ida ASC);");
			db.execSQL("CREATE UNIQUE INDEX indx_primary_tipo on tipo (_id ASC);");
			
			//db.execSQL("INSERT INTO android_metadata VALUES ('es_ES');");*/

			db.execSQL("Insert Into Tipo(glosa, orden)Values('No Definido', 1);");
			db.execSQL("Insert Into Tipo(glosa, orden)Values('Mamifero', 2);");
			db.execSQL("Insert Into Tipo(glosa, orden)Values('Aves', 3);");
			db.execSQL("Insert Into Tipo(glosa, orden)Values('Reptiles', 4);");

			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(1, 'No Definido', 1);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(2, 'No Definido', 1);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(2, 'Perro', 2);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(2, 'Gato', 3);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(3, 'No Definido', 1);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(3, 'Canario', 2);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(4, 'No Definido', 1);");
			db.execSQL("Insert Into Especie(idTipo, glosa, orden)Values(4, 'Tortuga', 2);");

			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(1, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(2, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(3, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(3, 'Doberman', 2);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(4, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(4, 'Siames', 2);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(5, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(6, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(7, 'No Definido', 1);");
			db.execSQL("Insert Into Raza(idEspecie, glosa, orden)Values(8, 'No Definido', 1);");
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.v("Actualizando aplicación", "La aplicación se está actualizando por lo cual se borraran todos los datos");
			
			db.execSQL("DROP TABLE IF EXISTS Animal;");
			db.execSQL("DROP TABLE IF EXISTS Tipo;");
			db.execSQL("DROP TABLE IF EXISTS Especie;");
			db.execSQL("DROP TABLE IF EXISTS Raza;");
			
			onCreate(db);
		}
}
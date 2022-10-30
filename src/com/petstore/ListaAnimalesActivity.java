package com.petstore;

import com.petstore.DB.BaseDatosHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaAnimalesActivity extends Activity {
	protected static final int REQUEST_CODE = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_animales);
 
        this.CargaAnimales();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_animales, menu);
		return true;
	}
	
	private void CargaAnimales(){
		try{
			ListView lstAnimales = (ListView) findViewById(R.id.lstAnimales);
			
			BaseDatosHelper BDHelper = new BaseDatosHelper(this);
			Cursor c;

			BDHelper.getDatabase().isOpen();
			c = BDHelper.getDatabase().query("Animal", new String[] {"_id", "nombre"}, "vigente=1", null, null, null, null);
			
		    
		    String[] from = new String[] { "nombre" };
	        int[] to = new int[] { android.R.id.text1 };

	        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1, c, from, to);
	        mAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
	        lstAnimales.setAdapter(mAdapter);
			
	        lstAnimales.setOnItemClickListener(new OnItemClickListener(){
	            @Override
	            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
	            	Intent intent = new Intent(ListaAnimalesActivity.this, Ingreso.class);
	            	 
	                // damos valor al parámetro a pasar
	                intent.putExtra("idAnimal", id);
	                /*
	                 * Inicia una actividad que devolverá un resultado cuando
	                 * haya terminado. Cuando la actividad termina, se llama al método
	                 * onActivityResult() con el requestCode dado.
	                 * El uso de un requestCode negativo es lo mismo que llamar a 
	                 * startActivity(intent) (la actividad no se iniciará como una
	                 * sub-actividad).
	                 */
	                startActivity(intent);
	                //Toast.makeText(getApplicationContext(), "El tipo seleccionado es " + id + " en la posicion: " + pos, Toast.LENGTH_LONG).show();
	            }
	         
	        });
		}catch(Exception e){
			Toast.makeText(this, e.toString() + " -- " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}

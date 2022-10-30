package com.petstore;

import java.io.IOException;

import com.petstore.DB.BaseDatosHelper;
import com.petstore.clases.Animal;
import com.petstore.manejoDatos.datosAnimal;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Ingreso extends Activity {
	private Animal animal;
	private Button btnGuardar;
	private Button btnCancelar;
	private Long vlId=(long) 0;
	
	public Ingreso(){
		super();
		animal=new Animal(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingreso);
		
		btnGuardar = (Button) findViewById(R.id.btnGuardar);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		final AlertDialog.Builder alertaCompuesta = new AlertDialog.Builder(this);
		
		if(getIntent().getExtras() != null){
			Bundle reicieveParams = getIntent().getExtras();
			vlId = Long.valueOf(reicieveParams.getLong("idAnimal"));
		}
		
		btnGuardar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(Validar()){
					datosAnimal datAnimal = null;
					EditText nombreAnimal = (EditText) findViewById(R.id.txtNombreMascota);
					Spinner cmbTipo = (Spinner) findViewById(R.id.cmbTipo);
					Spinner cmbEspecie = (Spinner) findViewById(R.id.cmbEspecie);
					Spinner cmbRaza = (Spinner) findViewById(R.id.cmbRaza);
					EditText txtPeso = (EditText) findViewById(R.id.txtPeso);
					EditText txtEdad = (EditText) findViewById(R.id.txtEdad);
					EditText txtPrecio = (EditText) findViewById(R.id.txtPrecio);
					
					
					try{
						datAnimal = new datosAnimal();
						
						datAnimal.set_nombre(nombreAnimal.getText().toString().trim());
						datAnimal.set_idTipo((int) cmbTipo.getSelectedItemId());
						datAnimal.set_idEspecie((int) cmbEspecie.getSelectedItemId());
						datAnimal.set_idRaza((int) cmbRaza.getSelectedItemId());
						datAnimal.set_peso(Integer.parseInt(txtPeso.getText().toString().trim()));
						datAnimal.set_edad(txtEdad.getText().toString().trim());
						datAnimal.set_precio(Integer.parseInt(txtPrecio.getText().toString().trim()));
						datAnimal.set_vigente(true);
					    
						if(vlId == 0){
							vlId = animal.Agregar(datAnimal);
							Toast.makeText(v.getContext(), "Guardado correctamente codigo: " + vlId, Toast.LENGTH_LONG).show();
						}else{
							datAnimal.set_id(vlId);
							if(animal.Modificar(datAnimal)){
								Toast.makeText(v.getContext(), "Modificado correctamente", Toast.LENGTH_LONG).show();
							}else{
								Toast.makeText(v.getContext(), "Error al Modificar", Toast.LENGTH_LONG).show();
							}
						}
					}catch(Exception ex){
						Log.e(ex.getClass().getName(), ex.getMessage());
						Toast.makeText(v.getContext(), "Ocurrio un error no identificado", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
		btnCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				alertaCompuesta.show();
			}
		});
		
		
	 	alertaCompuesta.setTitle("Información");
		alertaCompuesta.setMessage("¿Desea cancelar y perder todos los datos ingresados?");
		alertaCompuesta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Inicio();
		    }
		});
		
		alertaCompuesta.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		this.Inicio();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingreso, menu);
		return true;
	}
	
	private void Inicio(){
		EditText nombreAnimal = (EditText) findViewById(R.id.txtNombreMascota);
		EditText txtPeso = (EditText) findViewById(R.id.txtPeso);
		EditText txtEdad = (EditText) findViewById(R.id.txtEdad);
		EditText txtPrecio = (EditText) findViewById(R.id.txtPrecio);
		
		this.CargaTipo();
		
		if(vlId == 0){
			nombreAnimal.setText("");
			txtPeso.setText("0");
			txtEdad.setText("0");
			txtPrecio.setText("0");
		}else{
			try {
				this.CargaAnimal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		nombreAnimal.requestFocus();
	}
	
	private void CargaAnimal() throws IOException{
		datosAnimal datAnimal = new datosAnimal();
		EditText nombreAnimal = (EditText) findViewById(R.id.txtNombreMascota);
		Spinner cmbTipo = (Spinner) findViewById(R.id.cmbTipo);
		Spinner cmbEspecie = (Spinner) findViewById(R.id.cmbEspecie);
		Spinner cmbRaza = (Spinner) findViewById(R.id.cmbRaza);
		EditText txtPeso = (EditText) findViewById(R.id.txtPeso);
		EditText txtEdad = (EditText) findViewById(R.id.txtEdad);
		EditText txtPrecio = (EditText) findViewById(R.id.txtPrecio);
		
		datAnimal = animal.Obtener(vlId);
		
		nombreAnimal.setText(datAnimal.get_nombre());
		for(int i = 0; i < cmbTipo.getCount(); i++)
	    {
	        if (cmbTipo.getItemIdAtPosition(i) == datAnimal.get_idTipo())
	        {
	            cmbTipo.setSelection(i);
	            break;
	        }
	    }
		this.CargaEspecie(datAnimal.get_idTipo());
		for(int i = 0; i < cmbEspecie.getCount(); i++)
	    {
	        if (cmbEspecie.getItemIdAtPosition(i) == datAnimal.get_idEspecie())
	        {
	        	cmbEspecie.setSelection(i);
	            break;
	        }
	    }
		this.CargaRaza(datAnimal.get_idEspecie());
		for(int i = 0; i < cmbRaza.getCount(); i++)
	    {
	        if (cmbRaza.getItemIdAtPosition(i) == datAnimal.get_idRaza())
	        {
	        	cmbRaza.setSelection(i);
	            break;
	        }
	    }
		txtPeso.setText(datAnimal.get_peso().toString());
		txtEdad.setText(datAnimal.get_edad().toString());
		txtPrecio.setText(datAnimal.get_precio().toString());
	}
	
	private void CargaTipo(){
		try{
			Spinner cmbTipo = (Spinner) findViewById(R.id.cmbTipo);
			//LinkedList<datosCMB> datosTipos = tipo.ObtenerCMB();
			
			BaseDatosHelper BDHelper = new BaseDatosHelper(this);
			Cursor c;

			BDHelper.getDatabase().isOpen();
			c = BDHelper.getDatabase().query("Tipo", new String[] {"_id", "glosa"}, null, null, "orden", null, null);
			
		    
		    String[] from = new String[] { "glosa" };
	        int[] to = new int[] { android.R.id.text1 };

	        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from, to);
	        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        cmbTipo.setAdapter(mAdapter);
			
			/*
			//Creamos el adaptador
			ArrayAdapter<datosCMB> spinner_adapter = new ArrayAdapter<datosCMB>(this, android.R.layout.simple_spinner_item, datosTipos);
			//Añadimos el layout para el menú y se lo damos al spinner
			spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			cmbTipo.setAdapter(spinner_adapter);
			*/
	        
			cmbTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			    	//Toast.makeText(parent.getContext(), "El tipo seleccionado es " + id + " en la posicion: " + pos, Toast.LENGTH_LONG).show();
			    	CargaEspecie((int)id);
			    }
			    public void onNothingSelected(AdapterView<?> parent) {
			    }
			});
		}catch(Exception e){
			Toast.makeText(this, e.toString() + " -- " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	private void CargaEspecie(Integer tipo){
		try{
			Spinner cmbEspecie = (Spinner) findViewById(R.id.cmbEspecie);
			/*LinkedList<datosCMB> datosEspecies = especie.ObtenerCMB(tipo);
			
			//Creamos el adaptador
			ArrayAdapter<datosCMB> spinner_adapter = new ArrayAdapter<datosCMB>(this, android.R.layout.simple_spinner_item, datosEspecies);
			//Añadimos el layout para el menú y se lo damos al spinner
			spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			cmbEspecie.setAdapter(spinner_adapter);*/
			
			BaseDatosHelper BDHelper = new BaseDatosHelper(this);
			Cursor c;

			BDHelper.getDatabase().isOpen();
			c = BDHelper.getDatabase().query("Especie", new String[] {"_id", "glosa"}, "idTipo=" + tipo, null, "orden", null, null);
			
		    
		    String[] from = new String[] { "glosa" };
	        int[] to = new int[] { android.R.id.text1 };

	        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from, to);
	        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        cmbEspecie.setAdapter(mAdapter);
			
			cmbEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			    	//Toast.makeText(parent.getContext(), "La especie seleccionada es: " + parent.getItemAtPosition(pos) + " en la posicion: " + pos + " id: " + id, Toast.LENGTH_LONG).show();
			    	CargaRaza((int) id);
			    }
			    public void onNothingSelected(AdapterView<?> parent) {
			    }
			});
		}catch(Exception e){
			Toast.makeText(this, e.toString() + " -- " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	private void CargaRaza(Integer especie){
		try{
			Spinner cmbRaza = (Spinner) findViewById(R.id.cmbRaza);
			/*LinkedList<datosCMB> datosEspecies = raza.ObtenerCMB(especie);
			
			//Creamos el adaptador
			ArrayAdapter<datosCMB> spinner_adapter = new ArrayAdapter<datosCMB>(this, android.R.layout.simple_spinner_item, datosEspecies);
			//Añadimos el layout para el menú y se lo damos al spinner
			spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			cmbRaza.setAdapter(spinner_adapter);*/
			
			BaseDatosHelper BDHelper = new BaseDatosHelper(this);
			Cursor c;

			BDHelper.getDatabase().isOpen();
			c = BDHelper.getDatabase().query("Raza", new String[] {"_id", "glosa"}, "idEspecie=" + especie, null, "orden", null, null);
			
		    
		    String[] from = new String[] { "glosa" };
	        int[] to = new int[] { android.R.id.text1 };

	        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, from, to);
	        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        cmbRaza.setAdapter(mAdapter);
		}catch(Exception e){
			Toast.makeText(this, e.toString() + " -- " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	private boolean Validar(){
		EditText nombreAnimal = (EditText) findViewById(R.id.txtNombreMascota);
		Spinner cmbTipo = (Spinner) findViewById(R.id.cmbTipo);
		Spinner cmbEspecie = (Spinner) findViewById(R.id.cmbEspecie);
		//Spinner cmbRaza = (Spinner) findViewById(R.id.cmbRaza);
		EditText txtPeso = (EditText) findViewById(R.id.txtPeso);
		EditText txtEdad = (EditText) findViewById(R.id.txtEdad);
		EditText txtPrecio = (EditText) findViewById(R.id.txtPrecio);
		
		if(nombreAnimal.getText().toString().trim().equals("")){
			Toast.makeText(this, "Debe indicar un nombre", Toast.LENGTH_SHORT).show();
			nombreAnimal.requestFocus();
			
			return false;
		}
		
		if(cmbTipo.getSelectedItemId() == 1){
			Toast.makeText(this, "Debe seleccionar un tipo", Toast.LENGTH_SHORT).show();
			cmbTipo.requestFocus();
			
			return false;
		}
		
		if(cmbEspecie.getSelectedItemId() == 1){
			Toast.makeText(this, "Debe seleccionar una especie", Toast.LENGTH_SHORT).show();
			cmbEspecie.requestFocus();
			
			return false;
		}
		
		if(txtPeso.getText().toString().trim().equals("")){
			Toast.makeText(this, "Debe indicar el peso", Toast.LENGTH_SHORT).show();
			cmbEspecie.requestFocus();
			
			return false;
		}
		
		if(txtEdad.getText().toString().trim().equals("")){
			Toast.makeText(this, "Debe indicar la edad", Toast.LENGTH_SHORT).show();
			txtEdad.requestFocus();
			
			return false;
		}
		
		if(txtPrecio.getText().toString().trim().equals("")){
			Toast.makeText(this, "Debe indicar el precio", Toast.LENGTH_SHORT).show();
			txtPrecio.requestFocus();
			
			return false;
		}
	
		return true;
	}
}

package com.petstore;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends Activity {
	Button btnIngreso;
	Button btnBuscar;
	Button btnSalir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnIngreso = (Button) findViewById(R.id.btnIngreso);
		btnBuscar =  (Button) findViewById(R.id.btnBuscar);
		btnSalir = (Button) findViewById(R.id.btnVolver);
		final AlertDialog.Builder alertaCompuesta = new AlertDialog.Builder(this);
		
		btnIngreso.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, Ingreso.class);
		        startActivity(myIntent);
			}
		});
		
		btnBuscar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, ListaAnimalesActivity.class);
		        startActivity(myIntent);
			}
		});
		
		btnSalir.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				alertaCompuesta.show();
			}
		});
		
		alertaCompuesta.setTitle("Información");
		alertaCompuesta.setMessage("¿Seguro que desea salir?");
		alertaCompuesta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Salir();
		    }
		});
		
		alertaCompuesta.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void Salir(){
		finish();
		System.exit(0);
	}
}

package claro.cautnew.meuveiculo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class listComb extends AppCompatActivity {

 public Intent actCadComb;
 public ListView lista;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_list_comb);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

	lista = (ListView)findViewById(R.id.listView);

	bancoController crud = new bancoController(getBaseContext());
	Cursor cursor = crud.carregaComb();

	String[] nomeCampos = new String[] {"ID", "DSC"};
	int[] idViews = new int[] {R.id.idcomb, R.id.dscComb};

	SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
					R.layout.content_list_comb,cursor,nomeCampos,idViews, 0);
	lista.setAdapter(adaptador);

	actCadComb = new Intent( this, cadComb.class );

	FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
	fab.setOnClickListener(new View.OnClickListener() {
	 @Override
	 public void onClick(View view) {
		startActivity( actCadComb );
	 }
	});
 }

}

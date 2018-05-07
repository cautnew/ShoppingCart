package claro.cautnew.shoppingcart;

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

public class actListCadComb extends AppCompatActivity {

 private ListView lista;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_act_cad_comb);

	lista = (ListView) findViewById(R.id.listCombs);

	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

	bancoController banco = new bancoController( getBaseContext() );
	Cursor cursor = banco.carregaCombs();

	String[] campos = new String[]{ "ID", "DSC" };
	int[] idViews = new int[] {R.id.idComb, R.id.dscComb};

	SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
	R.layout.activity_act_cad_comb,cursor,campos,idViews,0);

	lista.setAdapter( adaptador );

	FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

	fab.setOnClickListener(new View.OnClickListener() {
	 @Override
	 public void onClick(View view) {
		Intent showCadComb = new Intent( view.getContext(), cadComb.class );
		startActivity( showCadComb );
	 }
	});
 }

}

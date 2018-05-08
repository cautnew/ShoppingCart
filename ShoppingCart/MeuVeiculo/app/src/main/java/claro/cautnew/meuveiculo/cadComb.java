package claro.cautnew.meuveiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cadComb extends AppCompatActivity {

 public Button btnAddComb, btnListComb;
 public Intent actListComb;
 public TextView idcomb, dsccomb;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_cad_comb);

	idcomb = (TextView) findViewById( R.id.idComb );
	dsccomb = (TextView) findViewById( R.id.dscComb );

	actListComb = new Intent( this, listComb.class );

	btnAddComb.setOnClickListener( new View.OnClickListener()
	{
	 public void onClick( View view )
	 {
		bancoController banco = new bancoController( getBaseContext() );

		showToast( banco.insereComb( idcomb.getText().toString(), dsccomb.getText().toString() ) );
		idcomb.setText( "" );
		dsccomb.setText( "" );
	 }
	});

	btnListComb.setOnClickListener( new View.OnClickListener()
	{
	 public void onClick( View view )
	 { startActivity( actListComb ); }
	});
 }

 private void showToast( String txt )
 { Toast.makeText( cadComb.this, txt, Toast.LENGTH_LONG ).show(); }
}

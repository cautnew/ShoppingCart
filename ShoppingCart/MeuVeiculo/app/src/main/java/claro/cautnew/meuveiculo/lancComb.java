package claro.cautnew.meuveiculo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class lancComb extends AppCompatActivity {

 public String codComb;
 public Button btnLanc, btnChooseComb;
 public TextView litros;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_lanc_comb);
	btnLanc = (Button) findViewById( R.id.btnLanc );
	litros = (TextView) findViewById( R.id.litros );
	btnLanc.setOnClickListener( new View.OnClickListener()
	{
	 public void onClick( View view )
	 {
		bancoController banco = new bancoController( getBaseContext() );

		showToast( banco.insereComb( litros.getText().toString(), codComb ) );
		litros.setText( "" );
	 }
	});
 }

 private void showToast( String txt )
 { Toast.makeText( lancComb.this, txt, Toast.LENGTH_LONG ).show(); }
}

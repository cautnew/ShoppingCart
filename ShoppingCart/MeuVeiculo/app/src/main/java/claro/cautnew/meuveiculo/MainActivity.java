package claro.cautnew.meuveiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

 public Button btnCadComb, btnLanc, btnMedia;
 public Intent actCadComb, actLancComb, actMediaRes;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	btnCadComb = (Button) findViewById( R.id.btnCadComb );
	btnLanc = (Button) findViewById( R.id.btnLanc );
	btnMedia = (Button) findViewById( R.id.btnMedia );

	actCadComb = new Intent( this, cadComb.class );
	actLancComb = new Intent( this, lancComb.class );
	actMediaRes = new Intent( this, mediaRes.class );

	btnCadComb.setOnClickListener( new View.OnClickListener()
	{
	 public void onClick( View view )
	 { startActivity( actCadComb ); }
	});

	btnLanc.setOnClickListener( new View.OnClickListener()
	{
	 public void onClick( View view )
	 { startActivity( actLancComb ); }
	});

	btnMedia.setOnClickListener( new View.OnClickListener()
	{
	 public void onClick( View view )
	 { startActivity( actMediaRes ); }
	});
 }
}

package claro.cautnew.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
 }

 public void showActCadComb(View v)
 {
	Intent ActCadComb = new Intent( this, actListCadComb.class );
	startActivity( ActCadComb );
 }

 public void showActLancAbast(View v)
 {
	Intent ActLancAbast = new Intent( this, lancAbast.class );
	startActivity( ActLancAbast );
 }

 public void showActMediaLanc(View v)
 {
	Intent ActMediaLanc = new Intent( this, mediaLanc.class );
	startActivity( ActMediaLanc );
 }
}
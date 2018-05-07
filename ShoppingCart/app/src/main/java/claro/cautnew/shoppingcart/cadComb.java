package claro.cautnew.shoppingcart;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cadComb extends AppCompatActivity {

 public TextView codcomb, dsc;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_cad_comb);
	Button btnAddComb = (Button) findViewById(R.id.btnAddComb);
	codcomb = (TextView) findViewById( R.id.codcomb );
	dsc = (TextView) findViewById( R.id.dsc );
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

	FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
	hideFloatingActionButton( fab );

	btnAddComb.setOnClickListener( new View.OnClickListener()
	{
	 @Override
	 public void onClick(View view) {
	  bancoController banco = new bancoController( getBaseContext() );

		showToast( banco.insereComb( dsc.getText().toString(), dsc.getText().toString() ) );
		codcomb.setText( "" );
		dsc.setText( "" );
	 }
	});
 }

 private void showToast( String txt )
 { Toast.makeText( cadComb.this, txt, Toast.LENGTH_LONG ).show(); }

 private void hideFloatingActionButton(FloatingActionButton fab) {
	CoordinatorLayout.LayoutParams params =
					(CoordinatorLayout.LayoutParams) fab.getLayoutParams();
	FloatingActionButton.Behavior behavior =
					(FloatingActionButton.Behavior) params.getBehavior();

	if (behavior != null) {
	 behavior.setAutoHideEnabled(false);
	}

	fab.hide();
 }

 private void showFloatingActionButton(FloatingActionButton fab) {
	fab.show();
	CoordinatorLayout.LayoutParams params =
					(CoordinatorLayout.LayoutParams) fab.getLayoutParams();
	FloatingActionButton.Behavior behavior =
					(FloatingActionButton.Behavior) params.getBehavior();

	if (behavior != null) {
	 behavior.setAutoHideEnabled(true);
	}
 }
}

package claro.cautnew.shoppingcart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

 private static final String nomeBanco = "bancoMeuVeiculo.db";
 private static final String tbLancAbast = "lancamentos";
 private static final String tbCombs = "combustiveis";
 private static final int versao = 1;

 public CriaBanco(Context context) {
	super(context, nomeBanco, null, versao);
 }

 @Override
 public void onCreate(SQLiteDatabase db)
 {
	String qCriaTbCombs = "CREATE TABLE " + tbCombs + "(";
	qCriaTbCombs += "ID integer primary key autoincrement,";
	qCriaTbCombs += "DSC text";
	qCriaTbCombs += ")";

	String qCriaTbLancAbast = "CREATE TABLE " + tbLancAbast + "(";
	qCriaTbLancAbast += "ID integer primary key autoincrement,";
	qCriaTbLancAbast += "KILOMETROS decimal,";
	qCriaTbLancAbast += "LITROS decimal,";
	qCriaTbLancAbast += "VALOR decimal,";
	qCriaTbLancAbast += "ID_COMBUSTIVEL integer";
	qCriaTbLancAbast += ")";
	db.execSQL( qCriaTbCombs );
 }
 @Override
 public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
 {
	db.execSQL( "DROP TABLE " + tbLancAbast );
	db.execSQL( "DROP TABLE " + tbCombs );
 }
}

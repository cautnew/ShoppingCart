package claro.cautnew.meuveiculo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class bancoController {
 private SQLiteDatabase db;
 private CriaBanco banco;

 public bancoController(Context context)
 {
  banco = new CriaBanco( context );
 }

 public Cursor carregaComb()
 {
  Cursor cursor;
  String[] campos = { "ID", "DSC" };
  db = banco.getReadableDatabase();
  cursor = db.query( "combustiveis", campos, null, null, null, null, null, null );
  if( cursor != null )
   cursor.moveToFirst();
  db.close();
  return cursor;
 }

 public String insereLanc( String litros )
 {
  ContentValues valores;
  long resultado;

  db = banco.getWritableDatabase();
  valores = new ContentValues();
  valores.put( "LITROS", litros );

  resultado = db.insert( "lancamentos", null, valores );

  if( resultado == -1 )
   return "Erro ao inserir.";
  else
   return "Inserido com sucesso.";
 }

 public String insereComb(String id, String dsc )
 {
  ContentValues valores;
  long resultado;

  db = banco.getWritableDatabase();
  valores = new ContentValues();
  valores.put( "ID", id );
  valores.put( "DSC", dsc );

  resultado = db.insert( "combustiveis", null, valores );

  if( resultado == -1 )
   return "Erro ao inserir.";
  else
   return "Inserido com sucesso.";
 }
}

package claro.cautnew.enviasms_vaa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cautn on 09/02/2018.
 */

public class Recebe extends BroadcastReceiver
{
	//Como o projeto foi feito pra Jellybean, precisei pegar os codigos da versão kitkat para ler de forma eficiente as mensagens.
	@RequiresApi( api = Build.VERSION_CODES.KITKAT )

	@Override
	public void onReceive(Context context, Intent intent )
	{
		String
			instante = new SimpleDateFormat( "yMdHms" ).format( new Date() ),
			pathPrincipal = "/storage/emulated/0/VAA/"; //ainda não sei capturar informações entre telas

		//Cria o arquivo
		File fl = new File( pathPrincipal + "/TOREAD/msg" + instante + ".txt" );

		//Reservo o espaço pra guardar todo o texto da mensagem
		SmsMessage[] txtRec = Telephony.Sms.Intents.getMessagesFromIntent( intent );

		//Guardo os dados da mensagem (destinatário e texto)
		String rem = txtRec[ 0 ].getOriginatingAddress(), txt=null;

		//Percorre todas as mensagens que chegaram ( são todas 1 unica mensagem, é que a operadora reparte em vários pedaços/pacotes )
		for( SmsMessage msg: txtRec )
		{ txt += msg.getMessageBody(); }

		showToast( context, "Mensagem de: " + rem + "\nTexto: " + txt );

		try
		{
			BufferedWriter arquivo = new BufferedWriter( new FileWriter( fl ) );
			arquivo.write( rem );
			arquivo.newLine();
			arquivo.write( txt );
			arquivo.close();
		} catch (IOException e)
		{ showToast( context, "Não foi possivel escrever o arquivo." ); }
	}

	private void showToast( Context context, String txt )
	{ Toast.makeText( context, txt, Toast.LENGTH_SHORT ).show(); }
}

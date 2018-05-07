package claro.cautnew.enviasms_vaa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Envia extends AppCompatActivity
{

	public Integer timeElapse = 0, totSentMsg = 0;
	public Timer timer = new Timer();
	private Boolean allowSend = true;
	private TimerTask procTime;
	private TextView lblTime;
	private TextView lblQtdSentSms;
	private EditText timeWait;
	private EditText timeWaitSMS;
	private EditText txtIdSys;
	public EditText txtPathFiles;

	@Override
	protected void onCreate( Bundle savedInstanceState )
  {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_envia);

		timeWaitSMS = findViewById( R.id.qtdMsWait );
		timeWait = findViewById( R.id.TimeWait );
		timeWait.addTextChangedListener( new TextWatcher()
		{
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){ }

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){ setTimeElapse(); }

			@Override
			public void afterTextChanged( Editable editable ){}
		} );

		timeElapse = Integer.parseInt( timeWait.getText().toString() );
		txtPathFiles = findViewById( R.id.pathFilesMsg );
		lblTime = findViewById( R.id.tempoRestante );
		lblQtdSentSms = findViewById( R.id.lblQtdSentMsg );
		txtIdSys = findViewById( R.id.txtIdSistema );

		//Colocando os dados disponiveis para outras classes
		//Intent intent = new Intent( this, Envia.class );
		//intent.putExtra( "pathFolders", txtPathFiles.toString() );
		//startActivity( intent );

		lblTime.setText( timeElapse.toString() );

		procTime = new TimerTask() {
			@Override
			public void run()
			{
				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						if( !timeWait.hasFocus() && allowSend )
						{
							lblTime.setText(timeElapse.toString());
							if( timeElapse <= 0 )
								procEnviar();
							timeElapse -= 1;
						}
					}
        });
			}
		};

		timer.schedule( procTime, 0, 1000 );
	}

	public void EnviaSMS( View view )
	{ procEnviar(); }

	public void setTotalSent()
	{ lblQtdSentSms.setText( totSentMsg.toString() ); }

	private void setTimeElapse()
	{
		String val = timeWait.getText().toString();
		if( val.equals( "" ) || val.isEmpty() )
			timeElapse = 0;
		else
			timeElapse = Integer.parseInt( timeWait.getText().toString() );
	}

	private void showToast( String txt )
	{ Toast.makeText(Envia.this, txt, Toast.LENGTH_SHORT ).show(); }

	private void procEnviar()
	{
		SmsManager ev = SmsManager.getDefault();
		Integer countSent = 0;
		Boolean cont = false;
		allowSend = false;

		try
		{
			//Obtenho todos os arquivos da pasta TOSEND
			File fl = new File( ( txtPathFiles.getText().toString() + "TOSEND" ) );
			//Guardo a quantidade de arquivos obtidos
			Integer qtdFiles = fl.listFiles().length;
			//Guarda o resto da operação de envio para o caso de ter muitas mensagens e aí ir divindo em quantidades estipuladas pelo usuário
			Integer resto;

			//Se realmente tiver recebido arquivos
			if( qtdFiles > 0 )
			{
				//Percorre cada arquivo
        for( File file : fl.listFiles() )
        {
        	//Reserva o espaço pra guardar todo o texto do arquivo
					ArrayList<String> linhas = new ArrayList<>();

					//Tenta a leitura do arquivo
					try
					{
						//Abre o arquivo por stream para tratarmos cada caractere
						FileInputStream flR = new FileInputStream( file );
						//Abre a leitura do arquivo para tratar cada caractere com o charset apropriado
						InputStreamReader flInpStrReader = new InputStreamReader( flR, "ISO-8859-1" );
						//Abre o espaço para guardar cada linha recebida
						BufferedReader arquivo = new BufferedReader( flInpStrReader );
						//String para trabalhar cada linha
						String recebe_string;

						//Lê os telefones
						recebe_string = arquivo.readLine(); //primeira linha
						linhas.add( recebe_string ); //adiciona a primeira linha

						//Lê a mensagem
						while( ( recebe_string = arquivo.readLine() ) != null )
						{ linhas.add( recebe_string.replace( "\\n", "\n" ) ); }
						//Como o texto veio em um arquivo, não dá pra colocar o sinal de quebra de linha, por isso foi feito o String.replace().

						arquivo.close();
						//Indica que teve arquivo para ser lido
						cont = true;
					}
					catch( Exception e )
					{
						//Indica que não teve arquivo para ser lido
						cont = false;
						showToast( "Arquivo não pôde ser lido." );
					}

					if( cont )
					{
						try
						{
							EditText QtdEnviosVez = findViewById( R.id.qtdEnviosVez );
							String
								txt = linhas.get(1),
								dest = linhas.get(0),
								ValQtdPorVez = QtdEnviosVez.getText().toString(),
								id = txtIdSys.getText().toString();
						 //Obtem o tempo a esperar
						 Integer msWait = Integer.parseInt( timeWaitSMS.getText().toString() );

							Integer valQtdPorVez = Integer.parseInt( ValQtdPorVez );

							if( !id.isEmpty() )
								txt = "[" + id + "]: " + txt;

							//Divide o texto da mensagem em quantidades que o Android aguente mandar por vez
							ArrayList<String> msg = ev.divideMessage( txt );
							//Guarda os destinatários
							ArrayList<String> dests = new ArrayList<String>(Arrays.asList(dest.split(";")));

							//Faz o envio para cada destinatário (só pode fazer de 1 em 1)
							for( String dst : dests )
							{
								//Manda a mensagem
								ev.sendMultipartTextMessage(dst, null, msg, null, null);
								//Calcula se é necessário esperar alguns segundos para mandar pros demais
								resto = countSent % valQtdPorVez;

								//Se for necessário esperar para mandar pros demais, ele dá o tepo
								if( resto == 0 && countSent > 0 )
								{
									try
									{ Thread.sleep( msWait ); }
									catch( Exception e )
									{ showToast( "Falha ao realizar a pausa." ); }
								}

								//Incrementa os contadores
								countSent += 1;
								totSentMsg += 1;
							}

							showToast( "Envio realizado com sucesso." );
							//Move o arquivo para a pasta SENT
              File fDest = new File( txtPathFiles.getText().toString() + "/SENT/" + file.getName() );
							file.renameTo( fDest );

              setTotalSent();
            }
            catch( Exception e )
            { showToast( "Não foi possível enviar o SMS." ); }
          }
        }
			}
			else
			{ showToast( "Nada para mandar." ); }
		}
		catch( Exception e )
		{ showToast( "Nada para mandar." ); }

		setTimeElapse();
		//Volta a permitir o envio de mensagens
		allowSend = true;
	}
}

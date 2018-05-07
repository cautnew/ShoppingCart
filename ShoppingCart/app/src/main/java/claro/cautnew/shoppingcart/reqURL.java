package claro.cautnew.shoppingcart;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cautn on 29/04/2018.
 */

public class reqURL {
 public reqURL( String url )
 {
  try {
	 URL u = new URL( url );
	 HttpURLConnection uc = (HttpURLConnection) u.openConnection();
	 uc.setRequestMethod( "GET" );
	 uc.connect();
	}
	catch( Exception e )
	{

	}
 }
}

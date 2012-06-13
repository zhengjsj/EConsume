package com.e800800.UIModule;

import java.io.UnsupportedEncodingException;
import com.e800800.econsume.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        login("15106169580","123456");
    }
    
  
    
    private static final String NAMESPACE = "http://intf.emobile.com";

	// WebService地址
	private static String URL = "http://58.215.76.121:10086/emobile/services/emobile?wsdl";

	private static final String METHOD_NAME = "userLogin";

	private static String SOAP_ACTION = NAMESPACE + METHOD_NAME;

	private String result;

	private SoapObject detail;
	
	
	
	

	public void login(String username,String pwd) {
		try {
			System.out.println("rpc------");
			SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
			//System.out.println("rpc" + rpc);
			System.out.println("username is " + username);
			System.out.println("pwd is " + pwd);
			rpc.addProperty("in0", username);
			rpc.addProperty("in1", pwd);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			
					
			HttpTransportSE ht = new HttpTransportSE(URL);
			
			//AndroidHttpTransport ht = new AndroidHttpTransport(URL);
			ht.debug = true;

			ht.call(SOAP_ACTION, envelope);
			//ht.call(null, envelope);

			//SoapObject result = (SoapObject)envelope.bodyIn;
			//detail = (SoapObject) result.getProperty("getWeatherbyCityNameResult");

			detail =(SoapObject) envelope.getResponse();
			
			//System.out.println("result" + result);
			System.out.println("detail" + detail);
			Toast.makeText(this, detail.toString(), Toast.LENGTH_LONG).show();
			loginResponse(detail);

			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loginResponse(SoapObject detail)
			throws UnsupportedEncodingException {
		String date = detail.getProperty(0).toString();
		result = "login result:" + date.split(" ")[0];
		
		System.out.println(result);
		//Toast.makeText(this, weatherToday, Toast.LENGTH_LONG).show();

	}
	
    
}
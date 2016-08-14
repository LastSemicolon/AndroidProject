package com.example.e_voting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Camp_Login extends Activity{
	EditText eusername;
	EditText epwd;
	Button proced;
	String response;
	boolean flag=false;

	// String uname,psw;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camp_login);

		// final String uname="gunjan";
		// final String pwd="gunjan";

		eusername = (EditText) findViewById(R.id.editusername);
		epwd = (EditText) findViewById(R.id.editpwd);
		proced = (Button) findViewById(R.id.buttoncamplogin);

		proced.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String uname  =eusername.getText().toString().trim();
				String pwd =epwd.getText().toString().trim();
				new HttpAsyncTask()
						.execute("http://10.222.71.23:8181/E-Voting-technothan/camplogin/"+uname+"/"+pwd);

			

			}
		});

	}


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    
	    
	    public static String GET(String url){
		     InputStream inputStream = null;
		     String result = "";
		     
		     System.out.println("in getttttt");
		     try {

		         // create HttpClient
		         HttpClient httpclient = new DefaultHttpClient();

		         // make GET request to the given URL
		         HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

		         // receive response as inputStream
		         inputStream = httpResponse.getEntity().getContent();

		         // convert inputstream to string
		         if(inputStream != null)
		             result = convertInputStreamToString(inputStream);
		         else
		             result = "Did not work!";

		     } catch (Exception e) {
		        // Log.d("InputStream", e.getLocalizedMessage());
		     }

		     return result;
		 }

		 private static String convertInputStreamToString(InputStream inputStream) throws IOException{
		     BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		     String line = "";
		     String result = "";
		     while((line = bufferedReader.readLine()) != null)
		         result += line;

		     inputStream.close();
		     return result;

		 }


		 private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		     @Override
		     protected String doInBackground(String... urls) {

		         return GET(urls[0]);
		     }
		     // onPostExecute displays the results of the AsyncTask.
		     @Override
		     protected void onPostExecute(String result) {
		        
		         try {
						JSONObject json=new JSONObject(result);
						response =json.getJSONObject("jsString").getString("response");
						
						if (response.equalsIgnoreCase("PROCEED")) {
							Intent intent = new Intent(getBaseContext(), temp1.class);
							startActivity(intent);

						} else {

							Intent intent1 = new Intent(getBaseContext(),
									Exit_Page.class);
							intent1.putExtra("error",
									"invalid user name AND password !");
							startActivity(intent1);

						}
						
						//System.out.println("in try     .....     on post execute ....");
						
						
						//sendSMSMessage(contact);
						//Intent intent1=new Intent(getBaseContext(), Otp_Page.class);
						

						//intent1.putExtra("OTP", e1+","+uid);
						
						//startActivity(intent1);
						
						
						
						//startActivity(new Intent(SimpleLogin.this, Otp_Page.class)); 
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
		         
		         
		         
		    }
		 }


}

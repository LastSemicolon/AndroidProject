package com.example.e_voting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Otp_Page extends Activity {
	
	
	Button b1;
	EditText e1;
	String uid;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.otp_page);
	        
	        e1=(EditText) findViewById(R.id.editotp);
	        b1=(Button) findViewById(R.id.buttonproceed);
	        final String s=getIntent().getExtras().getString("OTP");
	       
	        final String otp = s.split(",")[0]
					.trim();
			uid = s.split(",")[1]
					.trim();
	       
	        
	        //e1.setText(s);
	        
	       	b1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) { 
			    // while(true){
			        if((e1.getText().toString().trim().equals(otp))){
			        	
			        	new HttpAsyncTask().execute("http://10.222.71.23:8181/E-Voting-technothan/" +
			    		 		"CandidateList/"+uid);
			        	
			      //  }
			     }
			   }
	       	});
			        	/*String response_string = ServiceCall
						.GET("http://10.222.70.220:8181/E-Voting-technothan/CandidateListt/"+uid);
			        	
			        	/*	JSONObject jsonRootObject;
						try {
							jsonRootObject = new JSONObject(
									response_string);

							// Get the instance of JSONArray that
							// contains JSONObjects
							JSONArray jsonArray = jsonRootObject
									.optJSONArray("JsString");

							for(int i=0; i < jsonArray.length(); i++){
					            JSONObject jsonObject = jsonArray.getJSONObject(i);
					            
					            int id = Integer.parseInt(jsonObject.optString("id").toString());
					            String name = jsonObject.optString("name").toString();
					            float salary = Float.parseFloat(jsonObject.optString("salary").toString());
					            
					            data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
					         }
							JSONObject jsonObject = jsonArray
									.getJSONObject(0);

							response_string = jsonObject.optString(
									"response").toString();

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
			        	
			        	
			        	 Intent intent1=new Intent(getBaseContext(),MainActivity.class);
			        	 startActivity(intent1);
			        	break;
			        //	Toast.makeText(getApplicationContext(), "Otp mismatched", Toast.LENGTH_LONG).show();
			         * 
			         * 
			        
			        
			        }
					
			     	}
				}
			});*/
	   
	        

	        
	    }


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	       
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    
	    
	    public static String GET(String url){
		     InputStream inputStream = null;
		     String result = "";
		     

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
		         //Toast.makeText(getBaseContext(), "Candidate list Received!", Toast.LENGTH_LONG).show();
		        // String s="bgfh";
		        
		    	// Toast.makeText(getBaseContext(), result.length(), Toast.LENGTH_LONG).show();
		         
		         
						Intent intent1=new Intent(Otp_Page.this, CndidateList.class);
						

						intent1.putExtra("candidatelist", result);
						intent1.putExtra("uid", uid);
						//Toast.makeText(getBaseContext(), result.length(), Toast.LENGTH_LONG).show();
						startActivity(intent1);
						
						
						
						//startActivity(new Intent(SimpleLogin.this, Otp_Page.class)); 
				//	} catch (JSONException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					//}
		         
		         
		         
		    }
		 }
			@Override
			public void onBackPressed() {
			    // Do Here what ever you want do on back press;
			}

		 }




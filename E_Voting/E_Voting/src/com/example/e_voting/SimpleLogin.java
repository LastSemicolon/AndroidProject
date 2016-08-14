package com.example.e_voting;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;

import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;


import android.app.Activity;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleLogin extends Activity implements OnClickListener{

	public String e1;
	boolean flagscan=false;
	boolean flagage=false;
	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUM = "0123456789";
	private static final String SPL_CHARS = "@#$%&";
	TextView con;
	TextView con1;
	String uid;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplelogin);

		// System.out.println("in simple login");
		// con=(TextView) findViewById(R.id.texttemp1);
		// con1=(TextView) findViewById(R.id.texttemp2);
b1=(Button)findViewById(R.id.button1);


	Intent intent = getIntent();
		final String s = intent.getExtras().getString("xml_content");
		// con.setText(intent.getExtras().getString("xml_content"));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();
			Document doc;

			doc = db.parse(new ByteArrayInputStream(s.getBytes("utf-8")));

			NodeList nlRecords = doc
					.getElementsByTagName("PrintLetterBarcodeData");
			int num = nlRecords.getLength();
			for (int i = 0; i < num; i++) {
				Element node = (Element) nlRecords.item(i);
				// System.out.println("List attributes for node: " +
				// node.getNodeName());
				// con.setText(node.getNodeName());
				// get a map containing the attributes of this node
				NamedNodeMap attributes = node.getAttributes();
				// get the number of nodes in this map
				int numAttrs = attributes.getLength();
				for (int j = 0; j < numAttrs; j++) {
					Attr attr = (Attr) attributes.item(j);
					String attrName = attr.getNodeName();

					String attrValue = attr.getNodeValue();
					// System.out.println(attrName);

					if (attrName.equals("uid")) {
						flagscan=true;
						 uid = attrValue;
						for (int k = 0; k < numAttrs; k++) {
							Attr attr1 = (Attr) attributes.item(k);
							String attrName1 = attr1.getNodeName();
							String attrValue1 = attr1.getNodeValue();
							if (attrName1.equals("yob")) {

								int year = Integer.parseInt(attrValue1);
								
								if (year < 1995) {
									
									flagage=true;
									b1.setOnClickListener(this);
								}}}}}}}
									catch (ParserConfigurationException e) {
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		finally{
			if(flagscan==false)
			{
				 Intent intent1=new Intent(this,Exit_Page.class);
					intent1.putExtra("error","please scan valid qr code");
		        	 startActivity(intent1);
				//Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
			}
			
			else if(flagage==false)
			{
				 Intent intent1=new Intent(this,Exit_Page.class);
				intent1.putExtra("error","You are not eligible for voting");
				startActivity(intent1);
			}
			
				}
		
		
	
								
		 
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
			Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG)
					.show();
			String s = "bgfh";

			System.out.println("In post execute");
			try {
				JSONObject json = new JSONObject(result);
				s = json.getJSONObject("jsString").getString("response");
				String contact = s.split(",")[0].toString();
				String uid = s.split(",")[1].toString();

				if (contact.equalsIgnoreCase("duplicate_voter")) {

					Intent intent1 = new Intent(getBaseContext(),
							Exit_Page.class);
					intent1.putExtra("error", "You have already voted !");

					startActivity(intent1);

				} else if (contact.equalsIgnoreCase("invalid_voter")) {
					Intent intent1 = new Intent(getBaseContext(),
							Exit_Page.class);
					intent1.putExtra("error", "Invalid AADHAR NUMBER !");

					startActivity(intent1);

				} else if (contact.equalsIgnoreCase("not_found")) {
					Intent intent1 = new Intent(getBaseContext(),
							Exit_Page.class);
					intent1.putExtra("error",
							"Contact not registered,Please go to your nearest election camp!");

					startActivity(intent1);
				}

				else {

					sendSMSMessage(contact);
					Intent intent1 = new Intent(getBaseContext(),
							Otp_Page.class);

					intent1.putExtra("OTP", e1 + "," + uid);

					startActivity(intent1);

				}

				// startActivity(new Intent(SimpleLogin.this, Otp_Page.class));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}
	 }

	
	private void sendSMSMessage(String contact) {
		// Log.i("Send SMS", "");
		//String phoneNo = "9175661618";
		char[] temp = generatePswd(5, 6, 2, 1, 1);
		e1 = new String(temp);

		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(contact, null, e1, null, null);
			// Toast.makeText(this, "SMS sent.", Toast.LENGTH_LONG).show();
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again.", Toast.LENGTH_LONG).show();
			//e.printStackTrace();
		}

	}

	public static char[] generatePswd(int minLen, int maxLen,
			int noOfCAPSAlpha, int noOfDigits, int noOfSplChars) {
		if (minLen > maxLen)
			throw new IllegalArgumentException("Min. Length > Max. Length!");
		if ((noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen)
			throw new IllegalArgumentException(
					"Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
		Random rnd = new Random();
		int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
		char[] pswd = new char[len];
		int index = 0;
		for (int i = 0; i < noOfCAPSAlpha; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSplChars; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
		}
		for (int i = 0; i < len; i++) {
			if (pswd[i] == 0) {
				pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
			}
		}
		// temppass=pswd;
		return pswd;

	}

	private static int getNextIndex(Random rnd, int len, char[] pswd) {
		int index = rnd.nextInt(len);
		while (pswd[index = rnd.nextInt(len)] != 0)
			;
		return index;

	}

	@Override
	public void onClick(View v) {
		 new HttpAsyncTask().execute("http://10.222.71.23:8181/E-Voting-technothan/" +
		 		"validateContact/"+uid);
	}



}

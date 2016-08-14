package com.example.e_voting;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Govt extends Activity {

	SimpleLogin simplelogin = new SimpleLogin();
	String uid, candidatelist;

	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getcandidate);

		b1 = (Button) findViewById(R.id.button1);

		// System.out.println("in simple login");
		// con=(TextView) findViewById(R.id.texttemp1);
		// con1=(TextView) findViewById(R.id.texttemp2);
		boolean flagscan = false;
		boolean flagage = false;
		Intent intent = getIntent();
		final String s = intent.getExtras().getString("xml_content");
		// Intent intent1=new Intent(getBaseContext(),ExitPage.class);
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

						flagscan = true;
						uid = attrValue;

						for (int k = 0; k < numAttrs; k++) {

							Attr attr1 = (Attr) attributes.item(k);
							String attrName1 = attr1.getNodeName();
							String attrValue1 = attr1.getNodeValue();
							if (attrName1.equals("yob")) {

								int year = Integer.parseInt(attrValue1);

								if (year < 1995) {
									// simplelogin.sendSMSMessage();
									flagage = true;
									// Intent intent1=new Intent(Govt.this,
									// CndidateList.class);

									b1.setOnClickListener(new View.OnClickListener() {

										@Override
										public void onClick(View v) {
											// while(true){

											new HttpAsyncTask()
													.execute("http://10.222.71.23:8181/E-Voting-technothan/"
															+ "CandidateList/"
															+ uid);

											// }

										}

									});
									// intent1.putExtra("candidatelist",
									// result);
									// startActivity(intenttemp);

								}

							}
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (flagscan == false) {
				Intent intent1 = new Intent(this, Exit_Page.class);
				intent1.putExtra("error", "please scan valid qr code");
				startActivity(intent1);

			}

			else if (flagage == false) {

				Intent intent1 = new Intent(this, Exit_Page.class);
				intent1.putExtra("error", "you are not valid for voting");
				startActivity(intent1);
			}

		}

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

	public static String GET(String url) {
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
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			// Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
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
			
			
			
			
			if(result==""){
				Toast.makeText(getApplicationContext(),"sgfsd", Toast.LENGTH_LONG).show();
				Intent intent1 = new Intent(getBaseContext(),
						Exit_Page.class);
				intent1.putExtra("error", "You have already voted !");

				startActivity(intent1);
			}
			
//			JSONObject json;
//			try {
//				json = new JSONObject(result);
//				String s = json.getJSONObject("jsString").getString("response");
//				String contact = s.split(",")[0].toString();
//				String uid = s.split(",")[1].toString();
//
//				if (contact.equalsIgnoreCase("duplicate_voter")) {
//
//					
//
//				} else if (contact.equalsIgnoreCase("invalid_voter")) {
//					Intent intent1 = new Intent(getBaseContext(),
//							Exit_Page.class);
//					intent1.putExtra("error", "Invalid AADHAR NUMBER !");
//
//					startActivity(intent1);
//
//				} 
			else {

				Toast.makeText(getApplicationContext(),"elseeeeee", Toast.LENGTH_LONG).show();
				
					Intent intent1 = new Intent(Govt.this, CndidateList.class);

					intent1.putExtra("candidatelist", result);
					intent1.putExtra("uid", uid);
					// Toast.makeText(getBaseContext(), result.length(),
					// Toast.LENGTH_LONG).show();
					startActivity(intent1);

				}
			} 
		}
	

}

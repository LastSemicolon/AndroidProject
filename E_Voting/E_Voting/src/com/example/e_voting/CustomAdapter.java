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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
	
    String [] result;
    String [] result1;
    Context context;
 int [] imageId;
 int[]candidateID;
 String uid;
      private static LayoutInflater inflater=null;
    public CustomAdapter(CndidateList mainActivity, String[] prgmNameList,String[] prgmNameList1, int[] prgmImages,int []ID,String id) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        result1=prgmNameList1;
        context=mainActivity;
        imageId=prgmImages;
        candidateID = ID;
        uid = id;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}



    public class Holder
    {
        TextView tv;
        TextView tv1;
        ImageView img;
    }
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 Holder holder=new Holder();
	        
		 
		 View rowView;       
	             rowView = inflater.inflate(R.layout.program_list, null);
	             holder.tv=(TextView) rowView.findViewById(R.id.textcadname);
	             holder.tv1=(TextView) rowView.findViewById(R.id.textcadparty);
	             holder.img=(ImageView) rowView.findViewById(R.id.imagesymbol);       
	         holder.tv.setText(result[position]);
	         holder.img.setImageResource(imageId[position]);  
	         holder.tv1.setText(result1[position]);
	         rowView.setOnClickListener(new View.OnClickListener() {            
	            @Override
	            public void onClick(View v) {
	            	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	        				context);

	        			// set title
	        			alertDialogBuilder.setTitle("Confirm Vote...");

	        			// set dialog message
	        			alertDialogBuilder
	        				.setMessage("Click yes to confirm your vote..")
	        				.setCancelable(false)
	        				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	        					public void onClick(DialogInterface dialog,int id) {
	        						 new HttpAsyncTask().execute("http://10.222.71.23:8181" +
	        						 		"/E-Voting-technothan/" +
	        							 		"update_votes/"+candidateID[position]+"/"+uid);
	        					}
	        				  })
	        				.setNegativeButton("No",new DialogInterface.OnClickListener() {
	        					public void onClick(DialogInterface dialog,int id) {
	        						// if this button is clicked, just close
	        						// the dialog box and do nothing
	        						dialog.cancel();
	        					}
	        				});

	        				// create alert dialog
	        				AlertDialog alertDialog = alertDialogBuilder.create();

	        				// show it
	        				alertDialog.show();
	            	
	               
	            }
	        });   
	        return rowView;
		
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
	        // Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
	    	 Intent intent1=new Intent(context, ThankYou.class);
			
				//Toast.makeText(getBaseContext(), result.length(), Toast.LENGTH_LONG).show();
			context.startActivity(intent1);
				
	         
	         
	         
	    }
	 }


}

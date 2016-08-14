package com.example.e_voting;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class CndidateList extends Activity {


	ListView lv;
    Context context;
    String uid;
    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.img14,R.drawable.img6};
    public static String [] prgmNameList={};
    public String [] list={};
    public int [] candidateID={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidatelist);
        
        String candidatelist = getIntent().getExtras().getString("candidatelist");
        uid = getIntent().getExtras().getString("uid");
       // Toast.makeText(getApplicationContext(), candidatelist.length(), Toast.LENGTH_LONG).show();
        JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(candidatelist);
			  // Getting JSON Array node
	        JSONArray json_candidateList = jsonObj.getJSONArray("candidates");
	        
	      //  Toast.makeText(getApplicationContext(), json_candidateList.length(), Toast.LENGTH_LONG).show();
	        
	         //list = new String[json_candidateList.length()];
	        // looping through All Contacts
	        
	        int len = json_candidateList.length();
	        list = new String[len];
	        prgmNameList = new String[len];
	        candidateID = new int[len];
	        for (int i = 0; i <len ; i++) {
	            JSONObject c = json_candidateList.getJSONObject(i);
	            list[i] = c.getString("uid");
	            prgmNameList[i] = c.getString("party_name");
	            candidateID[i] = c.getInt("candiate_id");
	           // prgmImages[i] = c.getInt("symbol");

	        }
	       // list[2] = "sdfjfjksgfjkgf";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
      
        context=this;
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList,list,prgmImages,candidateID,uid));
        
        
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
	@Override
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
	}
}


	
	
	
	
	
	
/*
	ListView lv;
    Context context;

    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.img14,R.drawable.img6,R.drawable.img7};
    public static String [] prgmNameList={"Let Us C","c++","JAVA"};
    public static String [] prgmNameList1={"PartyName1","PartyName2","PartyName3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidatelist);
        
        context=this;

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmNameList1,prgmImages));
        
        
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
}
*/

package com.example.e_voting;

import com.google.zxing.integration.android.IntentIntegrator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class About_Us extends Activity
{
	Button backButton;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	   
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.about_us);
	       // Toast.makeText(getApplicationContext(), "i am in exit page", Toast.LENGTH_LONG).show();
			backButton=(Button)findViewById(R.id.buttonaboutus);
			 backButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent(getBaseContext(),FirstScreen.class);
	      				startActivity(i);
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
}

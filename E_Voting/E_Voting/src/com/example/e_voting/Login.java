package com.example.e_voting;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Login extends Activity{



	
	ImageButton SimpleLogin;
	ImageButton CampLogin;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        SimpleLogin=(ImageButton) findViewById(R.id.imagesimple);
	        SimpleLogin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					Intent intent=new Intent(getBaseContext(),Temp.class);
					startActivity(intent);
				}
			});
	        
	        CampLogin =(ImageButton) findViewById(R.id.imagecamp);
	        CampLogin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					Intent intent=new Intent(getBaseContext(),Camp_Login.class);
					startActivity(intent);
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

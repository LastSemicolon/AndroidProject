package com.example.e_voting;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FirstScreen extends Activity{
	ImageButton btnVote;
	ImageButton btnAboutUs;
	ImageButton btnOpinion;
	ImageButton btnResult;
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.first_screen);
	        btnVote=(ImageButton) findViewById(R.id.imageButton1);
	        btnVote.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i=new Intent(getBaseContext(),Login.class);
					startActivity(i);
					
				}
			});
	        
	        btnAboutUs=(ImageButton) findViewById(R.id.imageButton3);
	        btnAboutUs.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(getBaseContext(),About_Us.class);
      				startActivity(i);
				}
			});
	        btnOpinion=(ImageButton) findViewById(R.id.imageButton4);
	        btnOpinion.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(getBaseContext(),Opinion.class);
      				startActivity(i);
				}
			});
	        btnResult=(ImageButton) findViewById(R.id.imageButton2);
	        btnResult.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i=new Intent(getBaseContext(),Result.class);
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

		@Override
		public void onBackPressed() {
		    // Do Here what ever you want do on back press;
		}
	
}

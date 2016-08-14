package com.example.e_voting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Exit_Page extends Activity {
	
	ImageButton btn;
	TextView t1;
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.exit_screen);
	      //  Toast.makeText(getApplicationContext(), "i am in exit page", Toast.LENGTH_LONG).show();
	        
	        btn=(ImageButton) findViewById(R.id.exitbutton1);
	        t1=(TextView) findViewById(R.id.texterror);
	        Intent i1=getIntent();
	        final String s = i1.getExtras().getString("error");
			t1.setText(s);
	  
	        btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//Intent i1=new Intent(getBaseContext(),FirstScreen.class);
					//startActivity(i1);
					//Toast.makeText(getApplicationContext(), "close the app",Toast.LENGTH_LONG).show();
					finish();
//					Intent intent=new Intent(Intent.ACTION_MAIN);
//					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					startActivity(intent);
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

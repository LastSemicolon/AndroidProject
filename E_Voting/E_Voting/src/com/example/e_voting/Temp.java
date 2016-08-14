package com.example.e_voting;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Temp extends Activity implements OnClickListener{
	
	Button SimpleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tempp);
        SimpleLogin=(Button) findViewById(R.id.buttonqrcode);
        SimpleLogin.setOnClickListener(this);
			
			
    }
    	@Override
		public void onClick(View v) {
    		if(v.getId()==R.id.buttonqrcode){
    			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
    			scanIntegrator.initiateScan();
    			}
    		
		}

    	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    		if (scanningResult != null) {
    			System.out.println("in resuly=t....");
    			
    			String scanContent = scanningResult.getContents();
    			String scanFormat = scanningResult.getFormatName();
    			Intent intent1=new Intent(this,SimpleLogin.class);
    			intent1.putExtra("xml_content",scanContent);
    			startActivity(intent1);
    	
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
}

package com.example.e_voting;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class temp1 extends Activity implements OnClickListener {

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
    			System.out.println("in result....");
    			
    			String scanContent = scanningResult.getContents();
    			String scanFosrmat = scanningResult.getFormatName();
    			Intent intent1=new Intent(this,Govt.class);
    			intent1.putExtra("xml_content",scanContent);
    			startActivity(intent1);
    	
    		}
}
}
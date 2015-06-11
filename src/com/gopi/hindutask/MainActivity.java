package com.gopi.hindutask;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends Activity {

	JSONArray grabIndJsonArray;
	Button listViewBtn,mapViewBtn;
	String responseString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listViewBtn = (Button)findViewById(R.id.btnListView);
		mapViewBtn= (Button)findViewById(R.id.btnMapView);
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://54.254.240.217:8080/app-task/projects/", new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				 responseString = new String(arg2);
				try {
					 grabIndJsonArray = new JSONArray(responseString);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
				Log.d("Response", "String" +responseString);
				Log.d("Response", "Array" +grabIndJsonArray.length());
			}

		  
		});
		
		listViewBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent moveToListView = new Intent(MainActivity.this,ListViewClass.class);
				moveToListView.putExtra("LISTARRAY", responseString);
				startActivity(moveToListView);
			}
		});
		
		mapViewBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent moveToMapView = new Intent(MainActivity.this,MapClass.class);
				moveToMapView.putExtra("LISTARRAY", responseString);
				startActivity(moveToMapView);
			}
		});
		
	}

}

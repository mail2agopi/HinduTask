package com.gopi.hindutask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gopi.hindutask.adapters.ListViewAdapter;

public class ListViewClass extends Activity{
	ListView listViewProperty;
	ListViewAdapter listViewPropertyAdapter;
	JSONArray propertyJsonArray;
	JSONObject propertyJsonObj;
	String value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		listViewProperty = (ListView)findViewById(R.id.listViewtotal);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		     value = extras.getString("LISTARRAY");
		    //Toast.makeText(getApplicationContext(), ""+value, Toast.LENGTH_LONG).show();
		    Log.d("TEST","==="+value);
		    
		    
		    listViewPropertyAdapter = new ListViewAdapter(getApplicationContext(),value);
		    listViewProperty.setAdapter(listViewPropertyAdapter);
		    
		}
		
		
	}

}

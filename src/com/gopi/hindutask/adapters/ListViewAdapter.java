package com.gopi.hindutask.adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;









import com.gopi.hindutask.MainActivity;
import com.gopi.hindutask.MapClass;
import com.gopi.hindutask.PropertyInDetail;
import com.gopi.hindutask.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter{
	private static LayoutInflater inflater = null;
	Context contextAdapter;
	String propertyValue;
	JSONArray propertyJsonArray;
	JSONObject propertyJsonObj;
	TextView txtPropertyName;
	String[] ID,NAME;
	//Constructor to get the value from ListView Class
	public ListViewAdapter(Context context, String value) {
		contextAdapter = context;
		propertyValue = value;
		inflater = (LayoutInflater) contextAdapter.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		try {
			propertyJsonArray = new JSONArray(propertyValue);
			ID = new String[propertyJsonArray.length()];
			NAME= new String[propertyJsonArray.length()];
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return propertyJsonArray.length();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View itemView = inflater.inflate(R.layout.act_listview_items, parent,false);
		txtPropertyName = (TextView) itemView.findViewById(R.id.txtPropName);
		
		try 
		{
			propertyJsonObj = (JSONObject)propertyJsonArray.get(position);
			txtPropertyName.setText(""+propertyJsonObj.getString("projectName"));
			NAME[position] =propertyJsonObj.getString("projectName");
			ID[position]=propertyJsonObj.getString("id");
			
		
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent moveToPropertyInDetail = new Intent(contextAdapter,PropertyInDetail.class);
				moveToPropertyInDetail.putExtra("ID",ID[position] );
				moveToPropertyInDetail.putExtra("NAME",NAME[position] );
				moveToPropertyInDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				contextAdapter.startActivity(moveToPropertyInDetail);
			
			}
		});
		
		return itemView;
	}

}

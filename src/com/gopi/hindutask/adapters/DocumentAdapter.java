package com.gopi.hindutask.adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gopi.hindutask.R;



import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DocumentAdapter extends BaseAdapter{
	
	private static LayoutInflater inflater = null;
	Context contextAdapter;
	String DOCUMENTS;
	JSONArray documentsJsonArray;
	JSONObject documentsJsonObj;
	TextView documentText,documentType;
	ImageView documentImage;
	
	
	public DocumentAdapter(Context context, String documents) {
		contextAdapter = context;
		DOCUMENTS = documents;
		inflater = (LayoutInflater) contextAdapter.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		try
		{
			documentsJsonArray = new JSONArray(DOCUMENTS);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return documentsJsonArray.length();
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
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View itemView = inflater.inflate(R.layout.doc_list_items, parent,false);
		documentType = (TextView)itemView.findViewById(R.id.docType);
		documentText = (TextView)itemView.findViewById(R.id.docText);
		documentImage= (ImageView)itemView.findViewById(R.id.docImageView);
			String docText,docType,docImage;	
		try
		{
			documentsJsonObj = (JSONObject)documentsJsonArray.get(position);
			
			docText=documentsJsonObj.getString("text");
			docType=documentsJsonObj.getString("type");
			docImage=documentsJsonObj.getString("reference");
			
			documentText.setText(""+docText);
			documentType.setText(""+docType);
			Picasso.with(contextAdapter).load(docImage).placeholder(R.drawable.chumma_dummy).error(R.drawable.chumma_dummy).into(documentImage);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemView;
	}

}

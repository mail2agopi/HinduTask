package com.gopi.hindutask;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gopi.hindutask.adapters.DocumentAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;


import com.meetme.android.horizontallistview.HorizontalListView;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyInDetail extends Activity{
	
	Bundle extrasId;
	TextView testId;
	String responseString;
	JSONArray grabIndJsonArray;
	String ID,NAME;
	ProgressDialog mProgressDialog;
	JSONObject jsonobject;
	
	String addressLine1,
			addressLine2,
			brochure,
			city,
			description,
			documents,
			hidePrice,
			landmark,
			listingId,
			listingName,
			locality,
			maxArea,
			maxPrice,
			maxPricePerSqft,
			minArea,
			minPrice,
			minPricePerSqft,
			noOfAvailableUnits,
			noOfBlocks,
			noOfUnits,
			otherInfo,
			packageId,
			posessionDate,
			projectType,
			propertyTypes,
			status,
			summary,
			url,
			videoLinks,
			amenities,
			approvalNumber,
			approvedBy,
			bankApprovals,
			builderCredaiStatus,
			builderDescription,
			builderId,
			builderLogo,
			builderName,
			builderUrl,
			electricityConnection,
			lastMileLandmark,
			lastMileLat,
			lastMileLon,
			otherAmenities,
			otherBanks,
			specification,
			waterTypes;
	
	ProgressDialog progressDialogHinduTask;
	
	TextView textAdress,textDescription,textViewPriceDetails,textAminities;
	HorizontalListView documentshorList;
	DocumentAdapter documentAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.property_details);
		extrasId = getIntent().getExtras();
		
		testId = (TextView)findViewById(R.id.testId);
		textAdress= (TextView)findViewById(R.id.txtAdress);
		textDescription= (TextView)findViewById(R.id.txtDesc);
		textViewPriceDetails = (TextView)findViewById(R.id.txtViewPriceDetails);
		textAminities= (TextView)findViewById(R.id.txtAmminDetails);
		
		documentshorList= (HorizontalListView)findViewById(R.id.documentHorizontalListView);
		
		progressDialogHinduTask = new ProgressDialog(PropertyInDetail.this);
		
		progressDialogHinduTask.setMessage("Hindu Task Loading...");
		progressDialogHinduTask.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialogHinduTask.setIndeterminate(false);
		progressDialogHinduTask.setCancelable(false);
		progressDialogHinduTask.show();
		
		ID= extrasId.getString("ID");
		NAME= extrasId.getString("NAME");
		getActionBar().setTitle(NAME); 
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://54.254.240.217:8080/app-task/projects/"+ID, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
				progressDialogHinduTask.cancel();
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				String responseString = new String(arg2);
				Log.d("Imternet","====="+responseString);
				JSONObject tempJSON;
				try {
					 //= tempJSON.getString("");
					tempJSON = new JSONObject(responseString);
					propertyTypes = tempJSON.getString("propertyTypes");//
					summary= tempJSON.getString("summary");//
					description= tempJSON.getString("description");
					addressLine1= tempJSON.getString("addressLine1");
					addressLine2= tempJSON.getString("addressLine2");
					brochure= tempJSON.getString("brochure");
					city= tempJSON.getString("city");
					description= tempJSON.getString("description");
					documents= tempJSON.getString("documents");//
					hidePrice= tempJSON.getString("hidePrice");
					landmark= tempJSON.getString("landmark");
					listingId= tempJSON.getString("listingId");
					listingName= tempJSON.getString("listingName");
					locality= tempJSON.getString("locality");
					maxArea= tempJSON.getString("maxArea");
					maxPrice= tempJSON.getString("maxPrice");
					maxPricePerSqft= tempJSON.getString("maxPricePerSqft");
					minArea= tempJSON.getString("minArea");
					minPrice= tempJSON.getString("minPrice");
					minPricePerSqft= tempJSON.getString("minPricePerSqft");
					
					documents= tempJSON.getString("documents");//
					amenities= tempJSON.getString("amenities");//
				
					if(documents.length()==0)
					{
						
					}
					else
					{
						documentAdapter = new DocumentAdapter(PropertyInDetail.this,documents);
						documentshorList.setAdapter(documentAdapter);
					}
					
					textAdress.setText(addressLine1+","+addressLine2+"\n"+locality+"\n"+city+"\nLand Mark : "+landmark);
					textDescription.setText(""+description);
					
					textViewPriceDetails.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							//Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
							showPriceDetails();
						}
					});
					
					
				} 
				catch (JSONException e)
				{
					Log.d("Error","1111111111111");
				}
			
				
						try
						{
							JSONArray jsonAamenities = new JSONArray(amenities);
							String tempAmenities = "";
							if(jsonAamenities.length()>0)
							{
							for(int i = 0 ;i<jsonAamenities.length();i++)
							{
								tempAmenities = tempAmenities + jsonAamenities.getString(i) +"\n";
								
							}
							textAminities.setText(tempAmenities);
							}
							else
							{
								textAminities.setText("NA");
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				progressDialogHinduTask.cancel();
				}

		  
		});
		
		
		if(propertyTypes != null)
		{
			try
			{
				JSONArray propTypesJson = new JSONArray(propertyTypes);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void showPriceDetails()
	{
		final Dialog dialog = new Dialog(PropertyInDetail.this,R.style.PauseDialog);
		dialog.setContentView(R.layout.dialog_pricedetails);
		dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
		dialog.setTitle(Html.fromHtml("<font color=\"#ffffff\">Price Details"));
		TextView txtViewPrice = (TextView)dialog.findViewById(R.id.textPriceDetails);
		
		txtViewPrice.setText(Html.fromHtml("<b>Maximum Area :</b>") + maxArea +" Sqft"
							+"\n"+Html.fromHtml("<b>Maximum Price :</b>")+" Rs."+maxPrice
							+"\n"+Html.fromHtml("<b>Maximum Price Per Square Feet :</b>")+" Rs."+maxPricePerSqft
							+"\n"+Html.fromHtml("<b>Minimum Area :</b>") + minArea +" Sqft"
							+"\n"+Html.fromHtml("<b>Minimum Price :</b>")+" Rs." + minPrice
							+"\n"+Html.fromHtml("<b>Minimum Price Per Square Feet :</b>")+" Rs."+minPricePerSqft);
		
		 
		dialog.show();
	}
	
}

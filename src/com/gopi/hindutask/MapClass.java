package com.gopi.hindutask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;




import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MapClass extends Activity {

	// Google Map
	private GoogleMap googleMap;
	String valueMaps;
	JSONArray mapViewJsonArray;
	JSONObject mapViewJsonObj;
	Bundle extrasMap;
	String[] propName, propId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		extrasMap = getIntent().getExtras();
		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {

		valueMaps = extrasMap.getString("LISTARRAY");

		String lat, longti;
		try {
			mapViewJsonArray = new JSONArray(valueMaps);

			propName = new String[mapViewJsonArray.length()];
			propId = new String[mapViewJsonArray.length()];

			for (int i = 0; i < mapViewJsonArray.length(); i++) {
				mapViewJsonObj = (JSONObject) mapViewJsonArray.get(i);

				lat = mapViewJsonObj.getString("lat");
				longti = mapViewJsonObj.getString("lon");

				double lati = Double.parseDouble(lat);
				double longLat = Double.parseDouble(longti);

				if (googleMap == null) {

					googleMap = ((MapFragment) getFragmentManager()
							.findFragmentById(R.id.map)).getMap();

				}

				if (googleMap != null) {
					// googleMap.addMarker(new MarkerOptions().position(new
					// LatLng(lati,longLat)).title("Hello").snippet("Test"));
					googleMap.addMarker(new MarkerOptions()
							.position(new LatLng(lati, longLat))
							.title(mapViewJsonObj.getString("projectName"))
							.snippet(mapViewJsonObj.getString("id"))
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.marker_icon)));

					googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
							new LatLng(lati, longLat), 14));

				}

				propName[i] = mapViewJsonObj.getString("projectName");
				propId[i] = mapViewJsonObj.getString("id");
			}
			
			googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
				
				@Override
				public void onInfoWindowClick(Marker marker) {
					
					String testOne = marker.getId().replace("m", "");
					int testNum = Integer.parseInt(testOne);
					
					//Toast.makeText(getApplicationContext(), "Toast"+propId[testNum], Toast.LENGTH_SHORT).show();
					Intent moveToPropertyInDetail = new Intent(MapClass.this,PropertyInDetail.class);
					moveToPropertyInDetail.putExtra("ID",propId[testNum] );
					moveToPropertyInDetail.putExtra("NAME",propName[testNum] );
					moveToPropertyInDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(moveToPropertyInDetail);
				}
			});

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

}
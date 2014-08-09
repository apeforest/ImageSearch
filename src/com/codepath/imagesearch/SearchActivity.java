package com.codepath.imagesearch;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultsArrayAdapter imageAdapter;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultsArrayAdapter(this, R.layout.item_image_result, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(), ImageDisplay.class);
				ImageResult imageResult = (ImageResult) imageResults.get(position);
				i.putExtra("url", imageResult.getFullUrl());
				startActivity(i);
			}
		});
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.search, menu);
//		return super.onCreateOptionsMenu(menu);
//	}
	
	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.searchField);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.searchButton);
		
	}
	
	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for image " + query, Toast.LENGTH_SHORT)
			.show();	
		
		AsyncHttpClient client = new AsyncHttpClient();
		//https://ajax.googleapis.com/ajax/services/search/images?q=Android&v=1.0
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
					"start=" + 0 + "&v=1.0&q=" + Uri.encode(query), 
					new JsonHttpResponseHandler() {	
						@Override
						public void onSuccess(int statusCode, Header[] headers,
								JSONObject response) {
							JSONArray imageJsonResults = null;
							try {
								imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
								imageResults.clear();
								imageAdapter.addAll(ImageResult.
										fromJSONArray(imageJsonResults));
								//imageAdapter.notify();
								Log.d("DEBUG", imageResults.toString());
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
	}
}

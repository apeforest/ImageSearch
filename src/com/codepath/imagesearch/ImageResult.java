package com.codepath.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult {

	private String fullUrl;
	private String tbUrl;
	
	public ImageResult(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");
			this.tbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.tbUrl = null;
		}		
	}
	
	public String getFullUrl() {
		return fullUrl;
	}
	
	public String getTbUrl() {
		return tbUrl;
	}
	
	public String toString() {
		return this.tbUrl;
	}

	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray array) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int i = 0; i < array.length(); i++) {
			try {
				results.add(new ImageResult(array.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
}

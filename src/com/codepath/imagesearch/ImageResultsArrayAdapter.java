package com.codepath.imagesearch;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultsArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultsArrayAdapter(Context context, int resource,
			ArrayList<ImageResult> imageResults) {
		super(context, resource, imageResults);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = getItem(position);
		SmartImageView ivImage;
		if (convertView == null) {
			ivImage = (SmartImageView) LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
		}
		else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getTbUrl());
		return ivImage;
	}
	
}

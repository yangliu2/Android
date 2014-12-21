package com.panzoto.android;

import android.app.Activity;

public class Object extends Activity{
	private String name;
	private double weight;
	
	public void setName(String setName) {
		name = setName;
	}

	public String getName() {
		return name;
	}
	
	public void setWeight(double setWeight) {
		weight = setWeight;
	}

	public double getWeight() {
		return weight;
	}
}

package com.panzoto.android;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BuyCarActivity extends Activity implements OnClickListener{

	static Person me = new Person();

	Button buy_civic;
	Button buy_hummer;
	Button buy_bike;
	Button buy_truck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		loadProfileE();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_car);

		buy_civic = (Button) findViewById(R.id.buy_civic);
		buy_civic.setOnClickListener(this);

		buy_hummer = (Button) findViewById(R.id.buy_hummer);
		buy_hummer.setOnClickListener(this);

		buy_bike = (Button) findViewById(R.id.buy_bike);
		buy_bike.setOnClickListener(this);
		
		buy_truck = (Button) findViewById(R.id.buy_truck);
		buy_truck.setOnClickListener(this);
	}
	
	// set the action of each button, corresponding to the button Id
	@Override
	public void onClick(View v) {
		int activityType = 0;
		String message = "";

		me.setOutput(""); // clear output string
		loadProfile(message); // load profile

		switch (v.getId()) {
		case R.id.buy_civic:
			me.buyCar("civic");
			activityType = 1;
			break;
		case R.id.buy_hummer:
			me.buyCar("hummer");
			activityType = 1;
			break;
		case R.id.buy_bike:
			me.buyCar("bike");
			activityType = 1;
			break;
		case R.id.buy_truck:
			me.buyCar("truck");
			activityType = 1;
			break;
		}

		// load simple activity
		if (activityType == 1) {
			Intent intent = new Intent(this, DisplayMessageActivity.class);
			saveProfile();
			message = me.getOutput();
			intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
			startActivity(intent);
		}
		
		// load extra activity
		if (activityType == 2){
			
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buy_car, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// write a string to a filename path
	// use getActivity() if it's a fragment rather than an activity
	public void saveFile(String filename, String writeString) {

		try {
			FileOutputStream fos = openFileOutput(filename,
					Context.MODE_PRIVATE);
			fos.write(writeString.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveProfile() {
		String writeString = me.save();
		saveFile("profile.dat", writeString);
	}

	public String loadFile(String filename) {

		String fileString = "";
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(openFileInput(filename)));
			String inputString;
			StringBuffer stringBuffer = new StringBuffer();
			while ((inputString = inputReader.readLine()) != null) {
				stringBuffer.append(inputString + "\n");
			}
			fileString = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileString;
	}

	public void loadProfile(String stringBuffer) {

		// load profile with the read buffer
		stringBuffer = loadFile("profile.dat");
		me.load(stringBuffer);
	}
	
	public void loadProfileE () {
		String stringBuffer = loadFile("profile.dat");
		me.load(stringBuffer);
	}
}

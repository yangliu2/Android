package com.panzoto.android;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class BuyBulletsActivity extends Activity implements OnClickListener {

	static Person me = new Person();
	Button buy_bullets;
	NumberPicker np;
	TextView tv;
	int bullets = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		loadProfileE();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_bullets);

		np = (NumberPicker) findViewById(R.id.numberPicker1);
		tv = (TextView) findViewById(R.id.textView3);

		np.setMinValue(1);
		np.setMaxValue(100);
		np.setWrapSelectorWheel(false);

		np.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				bullets = newVal;
				String New = "Bullet(s) to buy : ";
				tv.setText(New.concat(String.valueOf(newVal)));
			}
		});
		
		buy_bullets = (Button) findViewById(R.id.buy_bullets);
		buy_bullets.setOnClickListener(this);
	}

	// set the action of each button, corresponding to the button Id
		@Override
		public void onClick(View v) {
			String message = "";

			me.setOutput(""); // clear output string
			loadProfile(message); // load profile

			switch (v.getId()) {

			case R.id.buy_bullets:
				me.buyBullet(bullets);
				Intent intent = new Intent(this, DisplayMessageActivity.class);
				saveProfile();
				message = me.getOutput();
				intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
				startActivity(intent);
				break;
			}
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.buy_gun, menu);
		return true;
	}
	
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
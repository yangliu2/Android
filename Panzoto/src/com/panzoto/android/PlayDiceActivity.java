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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class PlayDiceActivity extends Activity implements OnClickListener {
	static Person me = new Person();
	Button roll_dice;
	NumberPicker np1;
	NumberPicker np2;
	TextView tv1;
	TextView tv2;
	int bet = 1;
	int amount = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		loadProfileE();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_dice);

		np1 = (NumberPicker) findViewById(R.id.numberPicker1);
		np2 = (NumberPicker) findViewById(R.id.numberPicker2);
		tv1 = (TextView) findViewById(R.id.textView2);
		tv2 = (TextView) findViewById(R.id.textView3);

		np1.setMinValue(1);
		np1.setMaxValue(6);
		np1.setWrapSelectorWheel(false);

		np1.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				bet = newVal;
				String New = "Dice : ";
				tv1.setText(New.concat(String.valueOf(newVal)));
			}
		});

		np2.setMinValue(1);
		np2.setMaxValue(me.getMoney());
		np2.setWrapSelectorWheel(false);

		np2.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				amount = newVal;
				String New = "Bet : ";
				tv2.setText(New.concat(String.valueOf(newVal)));
			}
		});

		roll_dice = (Button) findViewById(R.id.roll_dice);
		roll_dice.setOnClickListener(this);
	}

	// set the action of each button, corresponding to the button Id
	@Override
	public void onClick(View v) {
		String message = "";

		me.setOutput(""); // clear output string
		loadProfile(message); // load profile

		switch (v.getId()) {

		case R.id.roll_dice:
			me.playDice(bet, amount);
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
		getMenuInflater().inflate(R.menu.play_dice, menu);
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
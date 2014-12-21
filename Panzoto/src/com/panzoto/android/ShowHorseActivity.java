package com.panzoto.android;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.content.Intent;

public class ShowHorseActivity extends Activity {
	static Person me = new Person();

	TextView[] tv = new TextView[10];
	int[] horsePosition = new int[9];
	String[] horseLabel = new String[9];

	int bet = 0;
	int amount = 0;
	private Timer myTimer;
	int winner = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		loadProfileE();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_horse);

		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		String[] words = message.split(" ");

		bet = Integer.parseInt(words[0]);
		amount = Integer.parseInt(words[1]);

		System.out.println("bet is " + bet + " amount is " + amount);

		tv[0] = (TextView) findViewById(R.id.textView2);
		tv[1] = (TextView) findViewById(R.id.textView3);
		tv[2] = (TextView) findViewById(R.id.textView4);
		tv[3] = (TextView) findViewById(R.id.textView5);
		tv[4] = (TextView) findViewById(R.id.textView6);
		tv[5] = (TextView) findViewById(R.id.textView7);
		tv[6] = (TextView) findViewById(R.id.textView8);
		tv[7] = (TextView) findViewById(R.id.textView9);
		tv[8] = (TextView) findViewById(R.id.textView10);
		tv[9] = (TextView) findViewById(R.id.textView11);

		// do task for every 1 minute
		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}

		}, 0, 1000);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.show_horse, menu);
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

	public void loadProfileE() {
		String stringBuffer = loadFile("profile.dat");
		me.load(stringBuffer);
	}

	private void TimerMethod() {
		// This method is called directly by the timer
		// and runs in the same thread as the timer.

		// We call the method that will work with the UI
		// through the runOnUiThread method.
		this.runOnUiThread(Timer_Tick);
	}

	private Runnable Timer_Tick = new Runnable() {
		public void run() {

			if (winner == 0) {
				String horse = "Horse ";
				for (int i = 0; i < 9; i++) {
					horseLabel[i] = horse;
					horseLabel[i] = horseLabel[i] + (i + 1) + " : ";
					tv[i].setText(horseLabel[i]);

					// increase from 0 to 2
					horsePosition[i] += Person.randomNumber(0, 2);
					// System.out.println((i + 1) + " " + horsePosition[i]);

					for (int j = 0; j < horsePosition[i]; j++) {
						horseLabel[i] = horseLabel[i] + " ";
						tv[i].setText(horseLabel[i]);
					}
					horseLabel[i] = horseLabel[i] + "*";
					tv[i].setText(horseLabel[i]);
					// set winner if first reach end
					if (winner == 0 && horsePosition[i] >= 40) {
						winner = i + 1;
						tv[9].setText("Race finished. Horse " + winner
								+ " won!");
					}
				}

				if (winner == bet) {
					me.setMoney(me.getMoney() + amount
							* Person.randomNumber(2, 13));
					tv[9].setText("You won!");
				}
			}
		}
	};
}
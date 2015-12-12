package com.panzoto.android;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ActionsFragment extends Fragment implements OnClickListener {

	Person me = new Person();

	Button rob_a_store;
	Button labor;
	Button do_favor;
	Button buy_blood;
	Button buy_gun;
	Button buy_car;
	Button heist;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.activity_actions, container,
				false);

		rob_a_store = (Button) root.findViewById(R.id.rob_store);
		rob_a_store.setOnClickListener(this);

		labor = (Button) root.findViewById(R.id.labor);
		labor.setOnClickListener(this);

		do_favor = (Button) root.findViewById(R.id.do_favor);
		do_favor.setOnClickListener(this);

		buy_blood = (Button) root.findViewById(R.id.buy_blood);
		buy_blood.setOnClickListener(this);

		buy_gun = (Button) root.findViewById(R.id.buy_guns);
		buy_gun.setOnClickListener(this);

		buy_car = (Button) root.findViewById(R.id.buy_cars);
		buy_car.setOnClickListener(this);

		heist = (Button) root.findViewById(R.id.heist);
		heist.setOnClickListener(this);

		return root;
	}

	// set the action of each button, corresponding to the button Id
	@Override
	public void onClick(View v) {
		int activityType = 0;
		String message = "";

		loadProfile(message); // load profile
		me.setOutput(""); // clear output string
		Intent intent = new Intent();

		switch (v.getId()) {

		case R.id.rob_store:
			me.robStore();
			activityType = 1;
			break;
		case R.id.labor:
			me.labor();
			activityType = 1;
			break;
		case R.id.do_favor:
			me.doFavor();
			activityType = 1;
			break;
		case R.id.buy_blood:
			me.buyBlood();
			activityType = 1;
			break;
		case R.id.heist:
			me.heist();
			activityType = 1;
			break;
		case R.id.buy_guns:
			activityType = 2;
			intent = new Intent(getActivity(), BuyGunActivity.class);
			saveProfile();
			startActivity(intent);
			break;
		case R.id.buy_cars:
			activityType = 2;
			intent = new Intent(getActivity(), BuyCarActivity.class);
			saveProfile();
			startActivity(intent);
			break;
		}

		// load simple activity
		if (activityType == 1) {
			intent = new Intent(getActivity(), DisplayActivity.class);
			saveProfile();
			message = me.getOutput();
			intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
			startActivity(intent);
		}

		// load extra activity
		if (activityType == 2) {

		}
	}

	// write a string to a filename path
	// use getActivity() if it's a fragment rather than an activity
	public void saveFile(String filename, String writeString) {

		try {
			FileOutputStream fos = getActivity().openFileOutput(filename,
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
					new InputStreamReader(getActivity().openFileInput(filename)));
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
}
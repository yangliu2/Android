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

public class SettingsFragment extends Fragment implements OnClickListener {

	static Person me = new Person();

	Button create_profile;
	Button add_money;
	Button add_respect;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater
				.inflate(R.layout.activity_settings, container, false);

		create_profile = (Button) root.findViewById(R.id.new_profile);
		create_profile.setOnClickListener(this);
		
		add_money = (Button) root.findViewById(R.id.add_money);
		add_money.setOnClickListener(this);
		
		add_respect = (Button) root.findViewById(R.id.add_respect);
		add_respect.setOnClickListener(this);

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
 		case R.id.new_profile:
 			me.createPerson("You");
 		    message = "Created a new character\n";
            me.status();
			activityType = 1;
			break;
 		case R.id.add_money:
 			me.addMoney();
			activityType = 1;
 			break;
 		case R.id.add_respect:
 			me.addRespect();
			activityType = 1;
 			break;
 		}
		// load simple activity
		if (activityType == 1) {
			intent = new Intent(getActivity(), DisplayActivity.class);
			saveProfile();
			message += me.getOutput();
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

	public String saveProfile(String extraMessage) {
		String writeString = me.save();
		extraMessage += writeString;
		saveFile("profile.dat", writeString);
		return extraMessage;
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

	public String loadProfile(String stringBuffer) {

		// load profile with the read buffer
		stringBuffer = loadFile("profile.dat");
		me.load(stringBuffer);
		//me.status();
		return me.getOutput();
	}

}
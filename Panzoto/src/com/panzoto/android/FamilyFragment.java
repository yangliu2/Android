package com.panzoto.android;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.panzoto.android.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class FamilyFragment extends Fragment implements OnClickListener {
 
	Person me = new Person();
	
	Button look_family;
	Button join_family;
	Button list_skill;
	Button practice_skill;
	Button assassination;
	Button smuggling;
	Button extortion;
	Button prostitution;
	Button kidnaping;
	Button humanTrafficking;
	Button drugDealing;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.activity_family, container,
				false);

		look_family = (Button) root.findViewById(R.id.look_family);
		look_family.setOnClickListener(this);
		
		join_family = (Button) root.findViewById(R.id.join_family);
		join_family.setOnClickListener(this);

		list_skill = (Button) root.findViewById(R.id.list_skill);
		list_skill.setOnClickListener(this);
		
		practice_skill = (Button) root.findViewById(R.id.practice_skill);
		practice_skill.setOnClickListener(this);
		
		assassination = (Button) root.findViewById(R.id.assassination);
		assassination.setOnClickListener(this);
		
		smuggling = (Button) root.findViewById(R.id.smuggling);
		smuggling.setOnClickListener(this);
		
		extortion = (Button) root.findViewById(R.id.extortion);
		extortion.setOnClickListener(this);
		
		prostitution = (Button) root.findViewById(R.id.prostitution);
		prostitution.setOnClickListener(this);

		kidnaping = (Button) root.findViewById(R.id.kidnaping);
		kidnaping.setOnClickListener(this);
		
		humanTrafficking = (Button) root.findViewById(R.id.humanTrafficking);
		humanTrafficking.setOnClickListener(this);

		drugDealing = (Button) root.findViewById(R.id.drugDealing);
		drugDealing.setOnClickListener(this);
				
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
		
		case R.id.look_family:
			me.listFamily();
			activityType = 1;
			break;
		case R.id.list_skill:
			me.listSkill();
			activityType = 1;
			break;
		case R.id.practice_skill:
			me.practiceSkills();
			activityType = 1;
			break;
		case R.id.assassination:
			me.assassination();
			activityType = 1;
			break;
		case R.id.smuggling:
			me.smuggling();
			activityType = 1;
			break;
		case R.id.extortion:
			me.extortion();
			activityType = 1;
			break;
		case R.id.prostitution:
			me.prostitution();
			activityType = 1;
			break;
		case R.id.kidnaping:
			me.kidnaping();
			activityType = 1;
			break;
		case R.id.humanTrafficking:
			me.humanTrafficking();
			activityType = 1;
			break;
		case R.id.drugDealing:
			me.drugDealing();
			activityType = 1;
			break;
		case R.id.join_family:
			activityType = 2;
			intent = new Intent(getActivity(), JoinFamilyActivity.class);
			saveProfile();
			startActivity(intent);
			break;
		}

		// load simple activity
		if (activityType == 1) {
			intent = new Intent(getActivity(), DisplayMessageActivity.class);
			saveProfile();
			message = me.getOutput();
			intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
			startActivity(intent);
		}
		
		// load extra activity
		if (activityType == 2){
			
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
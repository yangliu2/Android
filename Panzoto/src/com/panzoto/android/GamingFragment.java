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

public class GamingFragment extends Fragment implements OnClickListener {

    Person me = new Person();

    Button play_dice;
    Button horseRacing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_gamble, container,
                false);

        play_dice = (Button) root.findViewById(R.id.play_dice);
        play_dice.setOnClickListener(this);

        horseRacing = (Button) root.findViewById(R.id.horseRacing);
        horseRacing.setOnClickListener(this);

        return root;
    }

    // set the action of each button, corresponding to the button Id
    @Override
    public void onClick(View v) {
        int activityType = 0;
        String message = "";

        loadProfile(message); // load profile
        me.setOutput(""); // clear output string
        me.checkAvailable();
        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.look_family:
                //me.listFamily();
                activityType = 1;
                break;
            case R.id.play_dice:
                if (me.getAvailable()) {
                    activityType = 2;
                    intent = new Intent(getActivity(), PlayDiceActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.horseRacing:
                if (me.getAvailable()) {
                    activityType = 2;
                    intent = new Intent(getActivity(), HorseRacingActivity.class);
                    startActivity(intent);
                }
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
        if (activityType == 2) {
            saveProfile();
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
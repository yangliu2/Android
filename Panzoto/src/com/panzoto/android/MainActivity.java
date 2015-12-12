package com.panzoto.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.panzoto.mobile.adapter.TabsPagerAdapter;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	public final static String EXTRA_MESSAGE = "com.panzoto.android.MESSAGE";

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	static Person me = new Person();
	private Timer myTimer;

	// Tab titles
	private String[] tabs = { "Actions", "Family", "Gamble", "Settings" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		loadAssetFiles();
		String message = "";
		loadProfile(message);

		// hide action bar
		// getActionBar().hide();

		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		// actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		// do task for every 1 minute
		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}

		}, 0, 60000);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
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

		if (id == R.id.action_character) {
			Intent intent = new Intent(this, DisplayActivity.class);
			String message = "Checking status...\n";
			message = loadProfile(message);
			intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
			startActivity(intent);

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	// 4 following methods used to copy asset files

	private void loadAssetFiles() {
		copyAssetFiles("car.dat");
		copyAssetFiles("gun.dat");
		copyAssetFiles("skill.dat");
		copyAssetFiles("family.dat");
		copyAssetFiles("profile.dat");
	}

	private void copyAssetFiles(String fileName) {
		Context Context = getApplicationContext();
		String DestinationFile = Context.getFilesDir().getPath()
				+ File.separator + fileName;
		if (!new File(DestinationFile).exists()) {
			try {
				CopyFromAssetsToStorage(Context, fileName, DestinationFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void CopyFromAssetsToStorage(Context Context, String SourceFile,
			String DestinationFile) throws IOException {
		InputStream IS = Context.getAssets().open(SourceFile);
		OutputStream OS = new FileOutputStream(DestinationFile);
		CopyStream(IS, OS);
		OS.flush();
		OS.close();
		IS.close();
	}

	private void CopyStream(InputStream Input, OutputStream Output)
			throws IOException {
		byte[] buffer = new byte[5120];
		int length = Input.read(buffer);
		while (length > 0) {
			Output.write(buffer, 0, length);
			length = Input.read(buffer);
		}
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

	public String loadProfile(String stringBuffer) {

		// load profile with the read buffer
		stringBuffer = loadFile("profile.dat");
		me.load(stringBuffer);
		me.status();
		return me.getOutput();
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

			// This method runs in the same thread as the UI.
			// Do something to the UI thread here
			if (me.getHP() < 100) {
				me.setHP(me.getHP() + 1);
				saveProfile();
				System.out.println("Hp increased.");
			}

			// recover 1 energy every 1 minute
			if (me.getEnergy() < 100) {
				me.setEnergy(me.getEnergy() + 10);
				saveProfile();
				System.out.println("Energy increased.");
			}

		}
	};

}

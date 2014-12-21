package com.panzoto.android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Family {

	private String familyName = "none";
	private String melee = "none";
	private String unique = "none";
	private int index = 0;

	public void setFamilyName(String setFamilyName) {
		familyName = setFamilyName;
	}

	public String getFamilyName() {
		return familyName;
	}
	
	public void setMelee(String setMelee) {
		melee = setMelee;
	}

	public String getMelee() {
		return melee;
	}
	
	public void setUnique(String setUnique) {
		unique = setUnique;
	}

	public String getUnique() {
		return unique;
	}
	
	public void setIndex(int setIndex) {
		index = setIndex;
	}

	public int getIndex() {
		return index;
	}

	public void loadProperties(int familyIndex) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("/data/data/com.panzoto.android/files/family.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can not find family data file.");
		}

		boolean foundType = false;

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			if (familyIndex == Integer.parseInt(saved[0])) {
				index = Integer.parseInt(saved[0]);
				familyName = saved[1];
				melee = saved[2];
				unique = saved[3];
				foundType = true;
			}
		}

		if (foundType == false)
			System.out.println("Can't find " + familyName);
		sc.close();
	}

	public void saveProperties() {
		// write properties for skill
		BufferedWriter writer = null;

		if (familyName != "none") {
			try {
				writer = new BufferedWriter(new FileWriter("/data/data/com.panzoto.android/files/family.dat", true));
				writer.write(index + " "+ familyName + " " + melee + " " + unique
						+ "\n");
			} catch (IOException e) {
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					System.out.println("Cannot write to file");
				}
			}
		}
		// System.out.println(name+" is saved");
	}

	public void displayProperties() {
		System.out.println("Type: " + familyName + " | Melee skill: " + melee + " | Unique skill: " + unique);
	}

}

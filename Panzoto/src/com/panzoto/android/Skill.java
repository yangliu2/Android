package com.panzoto.android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class Skill {

	private String type = "none";
	private String familyName = "none";
	
	private int level = 0;
	private int xp = 0;
	private int cost = 0;

	public void setFamilyName(String setFamilyName) {
		familyName = setFamilyName;
	}

	public String getFamilyName() {
		return familyName;
	}
	
	public void setType(String setType) {
		type = setType;
	}

	public String getType() {
		return type;
	}

	public void setLevel(int setLevel) {
		level = setLevel;
	}

	public int getLevel() {
		return level;
	}

	public void setXp(int setXp) {
		xp = setXp;
	}

	public int getXp() {
		return xp;
	}

	public void setCost(int setCost) {
		cost = setCost;
	}

	public int getCost() {
		return cost;
	}

    public String listSkill() {
        String output = "";
        output += "Family \t\t Close combat \t Special ability \n\n";
        output += "CosaNostra \t Short blade \t Gladiator \n";
        output += "BlackHand \t MMA \t\t TommyGun \n";
        output += "Triad \t\t Wing chun \t MassRush \n";
        output += "Cartel \t\t Boxing \t WildCall \n";
        output += "Amigos \t\t Jiu jitsu \t HumanShield \n";
        output += "Shqiptare \t Wrestling \t Disguise \n";
        output += "Bratva \t\t Sambo \t\t AK-47 \n";
        output += "Yakuza \t\t Ninja \t\t Snipe \n";
        return output;
    }

	public String checkLevel() {
		String output = "";
		while (xp > Math.pow(level, 2) * 10) {
			level++;
			output = "You felt more confidence in "+ type + ". \n";
		}
		return output;
	}
	
	public void loadProperties(String skillName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("/data/data/com.panzoto.android/files/skill.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can not find skill data file.");
		}

		boolean foundType = false;

		// disregard if you don't have a skill
		if (skillName.equals("none"))
			foundType = true;

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			if (saved[0].equals(skillName)) {
				type = saved[0];
				cost = Integer.parseInt(saved[3]);
				foundType = true;
			}
		}

		if (foundType == false)
			System.out.println("Can't find " + type);
		sc.close();
	}

	public void saveProperties() {
		// write properties for skill
		BufferedWriter writer = null;

		if (type != "none") {
			try {
				writer = new BufferedWriter(new FileWriter("/data/data/com.panzoto.android/files/skill.dat", true));
				writer.write(type + " " + cost
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
		System.out.println("Type: " + type + " | Cost: " + cost);
	}

}

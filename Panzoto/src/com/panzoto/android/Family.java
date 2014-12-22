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

    public String listFamily() {
        String output = "";
        output += "Here is a list of families you are free to join. \n";
        output += "You cannot leave a family once you join. \n\n";

        // Italian, Smuggling, short-blade (bleeding effect), gladiator (ignores
        // self-damage, double damage for short time)
        output += "1. Cosa Nostra \n The Itallian maifa. Specializes in sumggling. Uses short blade as melee weapon. Unique skills is Gladiator. \n\n";

        // US, extortion, MMA (close combat), tommy guns (mass damage, low
        // accuracy)
        output += "2. Black Hand \n The United States mafia. Specializes in exortion. Uses mixed martial arts for close combat. Unique weapon is Tommy gun.\n\n";

        // China, prostitution, wing-chun (fast attacks), mass-rush with steel
        // sticks (burst damage, cooldown)
        output += "3. Triad \n The Chinese mafia. Specializes in prostituion. Uses Wing Chun for close combat. Unique ability is mass ruch.\n\n";

        // Mexican, drugs, boxing (medium intensity), wild call (mass
        // reinforcement, restore HP, cooldown)
        output += "4. Cartel \n The Mexican mafia. Specializes in dealing drugs. Uses boxing for close combat. Unique ability is wild call.\n\n";

        // Brazilian, kidnapping, Brazilian jiu-jitsu (breaking hard hits), human
        // shielding (high defense)
        output += "5. Amigos \n The Brazillian mafia. Speciallizes in kidnapping. Uses Jiu Jitsu for close combat. Unique ability is human shield. \n\n";

        // Albanian, human trafficking, wrestling (close combat advantage),
        // disguise
        output += "6. Shqiptare \n The Albanian mafia. Speciallizes in human trafficking. Uses wrestling for close combat. Unique ability is disguse. \n\n";

        // Russian, bank robbery, Sambo (hard hitting), AK-47 (increase
        // accuracy, consistent damage)
        output += "7. Bratva \n The Russian mafia. Speciallizes in bank robbery. Uses Sambo for close combat. Unique weapon is AK-47. \n\n";

        // Japan, Assassination, ninja (cause enemy to miss, high evade),
        // sniping (highest single damage, long cooldown)
        output += "8. Yakuza \n The Japanese mafia. Speciallizes in assassination. Use Ninja as close combat. Unique ability is sniping. \n";

        return output;
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

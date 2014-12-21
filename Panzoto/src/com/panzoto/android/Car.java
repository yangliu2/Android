package com.panzoto.android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Car extends Object{

	private String type = "none";
	private int seat = 0;
	private int armor = 0;
	private int cost = 0;

	public void setType(String setType) {
		type = setType;
	}

	public String getType() {
		return type;
	}

	public void setSeat(int setSeat) {
		seat = setSeat;
	}

	public int getSeat() {
		return seat;
	}

	public void setArmor(int setArmor) {
		armor = setArmor;
	}

	public int getArmor() {
		return armor;
	}

	public void setCost(int setCost) {
		cost = setCost;
	}

	public int getCost() {
		return cost;
	}

	public void loadProperties(String carName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("/data/data/com.panzoto.android/files/car.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can not find gun data file.");
		}

		boolean foundType = false;

		// disregard if you don't have a car
		if (carName.equals("none"))
			foundType = true;

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			if (saved[0].equals(carName)) {
				type = saved[0];
				seat = Integer.parseInt(saved[1]);
				armor = Integer.parseInt(saved[2]);
				cost = Integer.parseInt(saved[3]);
				foundType = true;
			}
		}

		if (foundType == false)
			System.out.println("Can't find " + type);
		sc.close();
	}

	public void saveProperties() {
		// write properties for gun
		BufferedWriter writer = null;

		if (type != "none") {
			try {
				writer = new BufferedWriter(new FileWriter("/data/data/com.panzoto.android/files/car.dat", true));
				writer.write(type + " " + seat + " " + armor + " " + cost
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
		System.out.println("Type: " + type + " | Seat: " + seat + " | Armor: "
				+ armor + " | Cost: " + cost);
	}

}

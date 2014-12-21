package com.panzoto.android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Gun extends Object{
	private String type = "none";
	private int load = 0;
	private int power = 0;
	private double weight = 0;
	private int cost = 0;

	public void setType(String setType) {
		type = setType;
	}

	public String getType() {
		return type;
	}

	public void setLoad(int setLoad) {
		load = setLoad;
	}

	public int getLoad() {
		return load;
	}

	public void setPower(int setPower) {
		power = setPower;
	}

	public int getPower() {
		return power;
	}

	public void setWeight(double setWeight) {
		weight = setWeight;
	}

	public double getWeight() {
		return weight;
	}

	public void setCost(int setCost) {
		cost = setCost;
	}

	public int getCost() {
		return cost;
	}

	public void loadProperties(String gunName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("/data/data/com.panzoto.android/files/gun.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can not find gun data file.");
		}

		boolean foundType = false;
		
		// disregard if you don't have a gun
		if (gunName.equals("none"))
			foundType = true;
		
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			if (saved[0].equals(gunName)) {
				type = saved[0];
				load = Integer.parseInt(saved[1]);
				power = Integer.parseInt(saved[2]);
				weight = Double.parseDouble(saved[3]);
				cost = Integer.parseInt(saved[4]);
				foundType = true;
			}
		}
		
		if (foundType == false) 
			System.out.println("Can't find "+type);
		sc.close();
	}

	public void saveProperties() {
		// write properties for gun
		BufferedWriter writer = null;

		if (type != "none") {
			try {
				writer = new BufferedWriter(new FileWriter("/data/data/com.panzoto.android/files/gun.dat", true));
				writer.write(type + " " + load + " " + power + " " + weight
						+ " " + cost + "\n");
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
		System.out.println("Type: " + type + " | Load: " + load + " | Power: "
				+ power + " | Weight: " + (int) weight + " | Cost: " + cost);
	}

}

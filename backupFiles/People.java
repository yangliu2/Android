package com.panzoto.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Person {

	private String name = null;
	private int age = 0;
	private String charClass = null; // consider removal
	private int HP = 0;
	private int energy = 0;
	private int height = 0;
	private int weight = 0;
	private int bullet = 0;
	private int money = 0;
	private boolean alive = false;
	private boolean loggedIn = false;
	private boolean available = false;
	private Date birthTime = new Date();
	private Gun myGun = new Gun();
	private Car myCar = new Car();

	private double carryWeight = 0; // carryWeight is how much weight you
									// have on you
	private int respect = 0;
	private String rank = null;
	private int follower = 0;
	private int karma = 0;
	private String family = "none";
	private Skill melee = new Skill();
	private Skill unique = new Skill();

	private boolean inJail = false; // no outside access
	private long currentTime = 0; // no outside access
	private long inJailTime = 0; // no outside access
	private long jailTime = 0; // no outside access

	private boolean busy = false; // no outside access
	private long workTime = 0; // no outside access
	private long workStartTime = 0; // no outside access

	private String output = "";

	// The Runnable is for recover 1 HP every minutes

	// boolean for stop Runnable
	private volatile boolean hpStopRequested = false;

	// method to stop Runnable
	public void hpRequestStop() {
		hpStopRequested = true;
	}

	Runnable hpRecover = new Runnable() {
		public void run() {
			try {
				while (!hpStopRequested) {
					Thread.sleep(60000L);
					// System.out.println("You slowly recovered some health.");
					// recover 1 HP every 1 minute
					if (HP < 100) {
						HP += 1;
					}

					// recover 1 energy every 1 minute
					if (energy < 100) {
						energy += 10;
					}
				}
			} catch (InterruptedException iex) {
			}
		}
	};

	public String getOutput() {
		return output;
	}

	public void setOutput(String setOutput) {
		output = setOutput;
	}

	public boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean setLoggedIn) {
		loggedIn = setLoggedIn;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean setAlive) {
		alive = setAlive;
	}

	public void setAvailable(boolean setAvailable) {
		available = setAvailable;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setName(String setName) {
		name = setName;
	}

	public String getName() {
		return name;
	}

	public void setAge(int setAge) {
		age = setAge;
	}

	public int getAge() {
		return age;
	}

	public void setCharClass(String setClass) {
		charClass = setClass;
	}

	public String getCharClass() {
		return charClass;
	}

	public void setHP(int setHP) {
		HP = setHP;
	}

	public int getHP() {
		return HP;
	}

	public void setEnergy(int setEnergy) {
		energy = setEnergy;
	}

	public int getEnergy() {
		return energy;
	}

	public void setHeight(int setHeight) {
		height = setHeight;
	}

	public int getHeight() {
		return height;
	}

	public void setWeight(int setWeight) {
		weight = setWeight;
	}

	public int getWeight() {
		return weight;
	}

	public void setBullet(int setBullet) {
		bullet = setBullet;
	}

	public int getBullet() {
		return bullet;
	}

	public void setMoney(int setMoney) {
		money = setMoney;
	}

	public int getMoney() {
		return money;
	}

	public void setBirth(long setBirth) {
		birthTime.setTime(setBirth);
	}

	public void setGunType(String setGunType) {
		myGun.setType(setGunType);
	}

	public String getGunType() {
		return myGun.getType();
	}

	public void setCarryWeight(int setCarryWeigh) {
		carryWeight = setCarryWeigh;
	}

	public double getCarryWeight() {
		return carryWeight;
	}

	public void setRespect(int setRespect) {
		respect = setRespect;
	}

	public int getRespect() {
		return respect;
	}

	public void setCarType(String setCarType) {
		myCar.setType(setCarType);
	}

	public String getCarType() {
		return myCar.getType();
	}

	public void setKarma(int setKarma) {
		karma = setKarma;
	}

	public int getKarma() {
		return karma;
	}

	public void setFollower(int setFollower) {
		follower = setFollower;
	}

	public int getFollwer() {
		return follower;
	}

	public void setRank(String setRank) {
		rank = setRank;
	}

	public String getRank() {
		return rank;
	}

	public void setFamily(String setFamily) {
		family = setFamily;
	}

	public String getFamily() {
		return family;
	}

	public void listFamily() {
		output += "Here is a list of families you are free to join. \n";
		output += "You cannot leave a family once you join. \n\n";

		// Italian, Smuggling, short-blade (bleeding effect), gladiator (ignores
		// self-damage, double damage for short time)
		output += "Cosa Nostra \n The Itallian maifa. Specializes in sumggling. Uses short blade as melee weapon. Unique skills is Gladiator. \n\n";

		// US, extortion, MMA (close combat), tommy guns (mass damage, low
		// accuracy)
		output += "Black Hand \n The United States mafia. Specializes in exortion. Uses mixed martial arts for close combat. Unique weapon is Tommy gun.\n\n";

		// China, prostitution, wing-chun (fast attacks), mass-rush with steel
		// sticks (burst damage, cooldown)
		output += "Triad \n The Chinese mafia. Specializes in prostituion. Uses Wing Chun for close combat. Unique ability is mass ruch.\n\n";

		// Mexican, drugs, boxing (medium intensity), wild call (mass
		// reinforcement, restore HP, cooldown)
		output += "Cartel \n The Mexican mafia. Specializes in dealing drugs. Uses boxing for close combat. Unique ability is wild call.\n\n";

		// Brazilian, kidnaping, Brazilian jiu-jitsu (breaking hard hits), human
		// shielding (high defense)
		output += "Amigos \n The Brazillian mafia. Speciallizes in kidnaping. Uses Jiu Jitsu for close combat. Unique ability is human shield. \n\n";

		// Albanian, human trafficking, wrestling (close combat advantage),
		// disguise
		output += "Shqiptare \n The Albanian mafia. Speciallizes in human trafficking. Uses wrestling for close combat. Unique ability is disguse. \n\n";

		// Russian, bank robbery, Sambo (hard hitting), AK-47 (increase
		// accuracy, consistent damage)
		output += "Bratva \n The Russian mafia. Speciallizes in bank robbery. Uses Sambo for close combat. Unique weapon is AK-47. \n\n";

		// Japan, Assassination, ninja (cause enemy to miss, high evade),
		// sniping (highest single damage, long cooldown)
		output += "Yakuza \n The Japanese mafia. Speciallizes in assassination. Use Ninja as close combat. Unique ability is sniping. \n";
	}

	// need to fix the function because android require 1.6, and switch on
	// string is only allowed on java 1.7
		public void joinFamily(int familyIndex) {
		checkAvailable();

		if (alive && !inJail && !busy) {

			// only allow joining if not in a family
			if (family.equals("none")) {
				
				switch (familyIndex) {
				case "CosaNostra":
					melee.setType("ShortBlade");
					unique.setType("Gladiator");
					break;
				case "BlackHand":
					melee.setType("MMA");
					unique.setType("TommyGun");
					break;
				case "Triad":
					melee.setType("WingChun");
					unique.setType("MassRush");
					break;
				case "Cartel":
					melee.setType("Boxing");
					unique.setType("WildCall");
					break;
				case "Amigos":
					melee.setType("JiuJitsu");
					unique.setType("HuamnShield");
					break;
				case "Shqiptare":
					melee.setType("Wrestling");
					unique.setType("Disguise");
					break;
				case "Bratva":
					melee.setType("Sambo");
					unique.setType("AK-47");
					break;
				case "Yakuza":
					melee.setType("Ninja");
					unique.setType("Snipe");
					break;
				default:
					output = "This family does not exist.\n";
					break;
				}
				family = myFamily.getFamilyName();
				output += "You joined the " + family + " family. \n";
				melee.setLevel(1);
				melee.setXp(1);
				unique.setLevel(1);
				unique.setXp(1);
			} else {
				output += "You already have a family";
			}
		} else {
			notAvaliable("join family");
		}
		save();
	}

	public void listSkill() {
		output += "Family \t\t Close combat \t Special ability \n\n";
		output += "CosaNostra \t Short blade \t Gladiator \n";
		output += "BlackHand \t MMA \t\t TommyGun \n";
		output += "Triad \t\t Wing chun \t MassRush \n";
		output += "Cartel \t\t Boxing \t WildCall \n";
		output += "Amigos \t\t Jiu jitsu \t HumanShield \n";
		output += "Shqiptare \t Wrestling \t Disguise \n";
		output += "Bratva \t\t Sambo \t\t AK-47 \n";
		output += "Yakuza \t\t Ninja \t\t Snipe \n";
	}

	public void practiceSkills(String pSkill) {
		checkStatus();

		if (alive && !inJail && !busy && HP > 50) {
			if (pSkill.equals(melee.getType())) {
				output += "You started practicing " + pSkill + ".\n";
				HP = HP - 50;
				melee.setXp(melee.getXp() + 5);
				// checkLevel will increase level if xp is high enough, also
				// show output message
				output += melee.checkLevel();
				output += "You felt tired after practicing " + melee.getType()
						+ ". \n";
			} else if (pSkill.equals(unique.getType())) {
				// output += "You started practicing " + pSkill + ".\n";
				HP = HP - 50;
				unique.setXp(unique.getXp() + 5);
				// checkLevel will increase level if xp is high enough, also
				// show output message
				output += unique.checkLevel();
				output += "You felt tired after practicing " + unique.getType()
						+ ". \n";
			} else {
				output += "You do not know this skill. \n";
			}
		} else if (HP <= 50) {
			output = "You are too tired to practie any skills.\n";
		} else {
			notAvaliable("practice skill");
		}
		save();
	}

	public void doFavor() {
		checkStatus();
		if (alive && !inJail && !busy) {

			// help to do favors. need to change the favor vairable to the
			// number of cases
			int favor = 5;

			// if not godfather, cannot accept "favor on daughter's wedding day"
			if (!rank.equals("LongTou"))
				favor = favor - 1;

			switch (randomNumber(1, favor)) {
			case 1:
				output += "Johnny asked you to talk to his landlord into letting his dog to stay in his apartment. You promised to take care of it.\n";
				randomEvent(1);
				setWorkTime(convertToMS(1), currentTime);
				karma += 20;
				respect += 20;
				break;
			case 2:
				output += "Jeff's daugher was beat up by a bunch of punks. He asked you to teach them a lesson.\n";
				randomEvent(1);
				setWorkTime(convertToMS(1), currentTime);
				karma += 50;
				respect += 50;
				break;
			case 3:
				output += "Mark's father was robbed and stabbed. He wants to revenge his father's death.\n";
				randomEvent(2);
				setWorkTime(convertToMS(2), currentTime);
				karma += 100;
				respect += 100;
				break;
			case 4:
				output += "Your god son ask you to help him get a job. The only problem is that you have to convince his boss. No big deal, you made his boss an offer.\n";
				randomEvent(2);
				setWorkTime(convertToMS(2), currentTime);
				karma += 110;
				respect += 110;
				break;
			case 5:
				output += "You were asked for a favor on your daughter's wedding day. An offer you cannot refuse. People do not take this lightly.\n";
				randomEvent(2);
				setWorkTime(convertToMS(4), currentTime);
				karma += 200;
				respect += 200;
				break;
			default:
				System.out.println("Do favor has a problem.\n");
				break;
			}
		} else {
			notAvaliable("do favor");
		}
		save();
	}

	// work for money when crime doesn't pay
	public void labor() {
		checkStatus();
		if (available && energy >= 50) {
			int workFactor = 1;
			output += "You are tight on cash and need to make some money. \n";
			// work for money. need to change the work variable to the
			// number of types of work
			int favor = 5;

			switch (randomNumber(1, favor)) {
			case 1:
				output += "Dirty Old Bum Restaruant is looking for waiters. \n";
				setWorkTime(convertToMS(1), currentTime);
				energy = energy - 50;
				money += workFactor * 12;
				break;
			case 2:
				output += "Mom took petty on you and gave you some money.\n";
				setWorkTime(convertToMS(1), currentTime);
				energy = energy - 50;
				money += workFactor * 6;
				break;
			case 3:
				output += "Zoombie Hospital need someone to carry bodies. \n";
				setWorkTime(convertToMS(1), currentTime);
				energy = energy - 50;
				money += workFactor * 13;
				break;
			case 4:
				output += "Eat Poison Pharmaceuticals is looking for testing subjects. \n";
				setWorkTime(convertToMS(1), currentTime);
				energy = energy - 50;
				money += workFactor * 4;
				break;
			case 5:
				output += "Begging for change seems like a good career option. \n";
				setWorkTime(convertToMS(1), currentTime);
				energy = energy - 50;
				money += workFactor * 3;
				break;
			default:
				System.out.println("Do favor has a problem.\n");
				break;
			}
		} else if (energy < 50) {
			output += "You are too tired to work. \n";
		} else {
			notAvaliable("work");
		}
		save();
	}

	// take money from store
	public void robStore() {
		// checkStatus();
		int successChance = randomNumber(
				1 + melee.getLevel() + unique.getLevel(), 100);
		int taskChance = randomNumber(1, 100);

		// some skills increase success chance
		if (melee.getType().equals("ShortBlade")
				|| melee.getType().equals("MMA")
				|| melee.getType().equals("WingChun")
				|| melee.getType().equals("Boxing")
				|| melee.getType().equals("Wrestling")
				|| melee.getType().equals("Sambo")) {
			// System.out.println("Your melee skill was useful for this task.\n");
			successChance += melee.getLevel() / 3;
		}

		if (available) {
			output += "You entered a liquor store and demanded money.\n";

			// some skills lessen damage
			if (melee.getType().equals("JiuJitsu")
					|| melee.getType().equals("Ninja")) {
				HP = HP - randomNumber(0, 10) + follower;
				output += "You used " + melee.getType()
						+ " to lessen the damage.\n";
			} else {
				HP = HP - randomNumber(0, 20) + follower;
			}
			output += "The store owner fought back and you got hurt.\n";
			randomEvent(1);
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 50);
				output += "You got away with some cash.\n";
				respect = respect + 20;
				karma = karma - 20;
			} else if (successChance < taskChance) {
				output += "Being such an amateur, you did not get any money.\n";
				respect = respect + 20;
				karma = karma - 20;
			} else if (!alive) {
				output += "You did not make it. \n";
			} else {
				// save();
			}
			setWorkTime(convertToMS(1), currentTime);
		} else {
			notAvaliable("rob store");
		}
		// save();
	}

	public void heist() {
		checkStatus();
		int successChance = randomNumber(
				1 + melee.getLevel() / 2 + unique.getLevel() / 2, 100);
		int taskChance = randomNumber(1, 100);

		if (available && bullet >= 6) {
			output += "You found a buddy and try to get some unfortunate"
					+ " soul on the road to give you some money.\n";
			int damage = randomNumber(0, 50);
			damage = damage / myGun.getPower();
			HP = HP - damage + follower;
			int spent = randomNumber(1, 6);
			bullet = bullet - spent;
			carryWeight = carryWeight - spent / 63; // weight of bullet
			output += "That guy fought back.\n";
			randomEvent(2);
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 120) * respect / 40000;
				output += "You took some money from the pour soul.\n";
				respect = respect + 50;
				karma = karma - 50;
			} else if (successChance < taskChance) {
				output += "In the mist of the haste. Money was not grabed.\n";
				respect = respect + 50;
				karma = karma - 50;
			} else if (!alive) {
				output += "You were left by your partner to die.\n";
			} else
				save();
			setWorkTime(convertToMS(2), currentTime);
		} else if (bullet < 6) {
			output = "You don't have enough bullets.\n";
		} else {
			notAvaliable("heist");
		}
		save();
	}

	public void robBank() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// AK-47 skill increase the chance of bank robbery
		if (unique.getType().equals("AK-47")) {
			System.out.println("Your special ability " + unique.getType()
					+ "was useful for bank robbery.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && bullet >= 36 && myGun.getType().equals("tommy")
				&& myCar.getSeat() >= 4 && follower >= 4) {
			output += "You found 3 more guys to help you rob a bank.\n";
			int damage = randomNumber(0, 99);
			HP = HP - damage;
			int spent = randomNumber(1, 36);
			bullet = bullet - spent;
			carryWeight = carryWeight - spent / 63; // weight of bullet
			output += "Security guards called for police.\n";
			randomEvent(3); // increase dangerFactor for robbing a bank.
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "You got some money from the local bank.\n";
				respect = respect + 200;
				karma = karma - 200;
			} else if (successChance < taskChance) {
				output += "The bank have top notch security.\n";
				respect = respect + 200;
				karma = karma - 200;
			} else if (!alive) {
				output += "Cop shoot through your heart.\n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);
		} else if (bullet < 36) {
			output += "You don't have enough bullets.\n";
		} else if (!myGun.getType().equals("tommy")) {
			output += "You need a tommy gun to rob a bank.\n";
		} else if (myCar.getSeat() < 4) {
			output += "You need a car that seats at least 4 people.\n";
			output += "Your car seats " + myCar.getSeat() + " people.\n";
		} else if (follower < 4) {
			output += "You need at least 4 followers to rob a bank.\n";
		} else {
			notAvaliable("rob bank");
		}
		save();
	}

	public void assassination() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// Snipe skill increase the chance of assassination
		if (unique.getType().equals("Snipe")) {
			// System.out.println("Your special ability " + unique.getType()
			// + " was useful for assassination.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && bullet >= 10 && myGun.getType().equals("sniper")) {
			output += "You accepted a task to assassinate. \n";

			int spent = randomNumber(1, 10);
			bullet = bullet - spent;
			carryWeight = carryWeight - spent / 63;
			output += "You made an attempt on your target. \n";
			randomEvent(2); // increase dangerFactor for assassination
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "You got some money for the hit. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (successChance < taskChance) {
				output += "Your target survived. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (!alive) {
				output += "Your target retaliated. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (bullet < 10) {
			output += "You don't have enough bullets.\n";
		} else if (!myGun.getType().equals("sniper")) {
			output += "You need a sniper gun to assassinate. \n";
		} else {
			notAvaliable("assassination");
		}
		save();
	}

	public void smuggling() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// gladiator skill increase the chance of smuggling
		if (unique.getType().equals("Gladiator")) {
			// System.out.println("Your special ability " + unique.getType()
			// + " was useful for sumggling.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && myCar.getSeat() >= 5) {
			output += "You drove around and trying to find people to smuggle. \n";

			randomEvent(2); // increase dangerFactor for smuggling
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "There is enough people willing to get across the boarder. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (successChance < taskChance) {
				output += "Police checked your vehicle and people ran away. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (!alive) {
				output += "In the fire exchange, the rookie cop had a head shot. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (myCar.getSeat() < 5) {
			output += "You need a car that fits more than 4 people. \n";
		} else {
			notAvaliable("smuggling");
		}
		save();
	}

	public void extortion() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// Tommy gun skill increase the chance of extortion
		if (unique.getType().equals("TommyGun")) {
			// System.out.println("Your special ability " + unique.getType()
			// + " was useful for extortion.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && myGun.getType().equals("tommy") && bullet >= 100) {
			output += "Finding connections, you looked for government officials to blackmail. \n";

			randomEvent(2); // increase dangerFactor for extortion
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "You successfully blackmailed a senator for money. \n";
				respect = respect + 100;
				karma = karma - 100;
				bullet -= 100;
			} else if (successChance < taskChance) {
				output += "The senator informed FBI and came clean. \n";
				respect = respect + 100;
				karma = karma - 100;
				bullet -= 50;
			} else if (!alive) {
				output += "CIA tracked you down and you could not ran away. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (!myGun.getType().equals("tommy")) {
			output += "You need a Tommy gun to extort people. \n";
		} else if (bullet < 100) {
			output += "You need more bullets. \n";
		} else {
			notAvaliable("extortion");
		}
		save();
	}

	public void prostitution() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// Mass rush skill increase the chance of extortion
		if (unique.getType().equals("MassRush")) {
			// System.out.println("Your special ability " + unique.getType()
			// + " was useful for prostitution.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && follower >= 8 && respect >= 40000) {
			output += "You try to find more assets and collect renting fees. \n";

			randomEvent(2); // increase dangerFactor for extortion
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "Money is flowing in as more people appreciate your business. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (successChance < taskChance) {
				output += "Mothers Without Boarders is constantly de-valuing your business. \n";
				respect = respect + 100;
				karma = karma - 100;
				money += randomNumber(0, 1000) / 3;
			} else if (!alive) {
				output += "One of your unsatisfied assets manage to get close to you. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (follower < 8) {
			output += "There is not enough people running your business. \n";
		} else if (respect < 40000) {
			output += "People do not respect you.\n";
		} else {
			notAvaliable("prostitution");
		}
		save();

	}

	public void kidnaping() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// Human shield skill increase the chance of extortion
		if (unique.getType().equals("HumanShield")) {
			// System.out.println("Your special ability " + unique.getType() +
			// " was useful for kidnaping.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && follower >= 8 && respect >= 40000) {
			output += "One of your subordinates found a rich kid. \n";

			randomEvent(2); // increase dangerFactor for extortion
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "The father coughed up some dough. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (successChance < taskChance) {
				output += "The family refused to pay. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (!alive) {
				output += "The family body guard was a navy seal. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (follower < 8) {
			output += "Not enough people to run the show. \n";
		} else if (respect < 40000) {
			output += "Nobody gave you any tip for possible target. \n";
		} else {
			notAvaliable("kidnaping");
		}
		save();

	}

	public void humanTrafficking() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// disguse skill increase the chance of extortion
		if (unique.getType().equals("Disguise")) {
			// System.out.println("Your special ability " + unique.getType() +
			// " was useful for trafficking.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && follower >= 8 && respect >= 40000) {
			output += "Your connection gave you a tip for desperate people. \n";

			randomEvent(2); // increase dangerFactor for extortion
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "Package was delivered. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (successChance < taskChance) {
				output += "Some people ran away. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (!alive) {
				output += "People are vengeful. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (follower < 8) {
			output += "Not enough connections. \n";
		} else if (respect < 4000) {
			output += "Your guys are just idiots. \n";
		} else {
			notAvaliable("huamn trafficking");
		}
		save();
	}

	public void drugDealing() {
		checkStatus();
		int successChance = 0;
		int taskChance = randomNumber(1, 100);

		// wild call skill increase the chance of extortion
		if (unique.getType().equals("WildCall")) {
			// System.out.println("Your special ability " + unique.getType() +
			// " was useful for trafficking.\n");
			successChance = randomNumber(1 + unique.getLevel() / 2, 100);
		} else {
			successChance = randomNumber(1, 100);
		}

		if (available && follower >= 8 && respect >= 40000) {
			output += "Spread out your guys to sell some white flour. \n";

			randomEvent(2); // increase dangerFactor for extortion
			checkLive();
			if (alive && successChance >= taskChance) {
				money = money + randomNumber(0, 1000) * respect / 40000;
				output += "People are appreciating your white powder. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (successChance < taskChance) {
				output += "DEA saw your shipment and decided to take it. \n";
				respect = respect + 100;
				karma = karma - 100;
			} else if (!alive) {
				output += "One of the confederalis was useful. \n";
			} else
				save();
			setWorkTime(convertToMS(3), currentTime);

		} else if (follower < 8) {
			output += "Cannot sell without people on streets. \n";
		} else if (respect < 4000) {
			output += "The next family is taking your territories. \n";
		} else {
			notAvaliable("drug dealing");
		}
		save();
	}

	public void Donate(int will) {
		checkStatus();
		if (alive && !busy && !inJail) {
			money = money - will;
			karma = karma + will;
		} else {
			notAvaliable("donation");
		}
		save();
	}

	public void buyBullet(int number) {
		checkStatus();

		if (available && money > (number * 10)
				&& (carryWeight + number / 63) < weight) {
			money = money - 10 * number;
			bullet = bullet + number;
			carryWeight = carryWeight + (double) number / 63;
			output = "You bought some bullets.\n";
		} else if (money < (number * 10)) {
			output = "You don't have enough money.\n";
		} else if ((carryWeight + number) > weight) {
			output = "You can't carry that many bullets.\n";
		} else {
			notAvaliable("buying bullet");
		}
		save();
	}

	public void buyBlood() {
		checkStatus();
		int bloodPrice = 10;
		int cost = 100 - HP;

		if (available && money >= cost * bloodPrice && HP < 100) {
			money = money - cost * bloodPrice;
			HP = 100;
			output = "You saw a doctor and got treated.\n";
		} else if (alive && money < cost * bloodPrice && !inJail && !busy) {
			int difference = money / bloodPrice;
			HP = HP + difference;
			money = money - difference * bloodPrice;
			output += "You only got treated for the amount of money you have.\n";
		} else if (HP >= 100) {
			output += "You are tough. Why see a doctor?\n";
		} else if (money == 0) {
			output += "You don't have any money.\n";
		} else {
			notAvaliable("buy blood");
		}
		save();
	}

	public void buyGun(String gunName) {
		checkStatus();

		Gun oldGun = new Gun();
		oldGun.loadProperties(myGun.getType());
		Gun newGun = new Gun();
		newGun.loadProperties(gunName);
		if (!myGun.getType().equals(gunName)
				&& (carryWeight + newGun.getWeight()) < weight
				&& money > newGun.getCost() && available) {
			myGun.loadProperties(gunName);
			money = money - myGun.getCost();
			carryWeight = carryWeight - oldGun.getWeight();
			carryWeight = carryWeight + myGun.getWeight();
		} else if (myGun.getType().equals(gunName)) {
			output = "You have a " + gunName + " already.\n";
		} else if (money < newGun.getCost()) {
			output = "You don't have enough money.\n";
		} else if ((carryWeight + newGun.getWeight()) > weight) {
			output = "You are carrying too much weight.\n";
		} else {
			notAvaliable("buying gun");
		}
		save();
	}

	public void buyCar(String carType) {
		checkStatus();

		Car oldCar = new Car();
		oldCar.loadProperties(myCar.getType());
		Car newCar = new Car();
		newCar.loadProperties(carType);
		if (!myCar.getType().equals(carType) && money > newCar.getCost()
				&& available) {
			myCar.loadProperties(carType);
			money = money - myCar.getCost();
		} else if (myCar.getType().equals(carType)) {
			output = "You have a " + carType + " already.\n";
		} else if (money < newCar.getCost()) {
			output = "You don't have enough money.\n";
		} else {
			notAvaliable("buying car");
		}
		save();
	}

	public void drop(String thing, int number) {
		if (thing.equals("bullets")) {
			bullet = bullet - number;
		} else if (thing.equals(myGun.getType())) {
			myGun.loadProperties("none");
		} else {
			output += "Dropping has a problem.\n";
		}
		save();
	}

	// if it is a gun or car, then store it
	public void store(String thing) {
		checkStatus();

		if (available) {
			if (myGun.getType().equals(thing) || myCar.getType().equals(thing)) {
				BufferedWriter writer = null;
				Scanner sc = null;
				boolean itemFound = false;

				try {
					sc = new Scanner(new File(name + "_storage.sav"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					output += "Cannot find the storage file.\n";
				}

				while (sc.hasNext()) {
					String line = sc.nextLine();
					String[] saved = line.split("\\s+");
					if (saved[0].equals(thing)) {
						itemFound = true;
					}
				}
				sc.close();

				if (itemFound == false) {
					try {
						writer = new BufferedWriter(new FileWriter(name
								+ "_storage.sav", true));
						writer.write(thing + "\n");
						output += "You put " + thing + " in the storage.\n";

						// remove the thing after put it in storage
						if (myGun.getType().equals(thing)) {
							myGun.setType("none");
						} else if (myCar.getType().equals(thing)) {
							myCar.setType("none");
						}

					} catch (IOException e) {
					} finally {
						try {
							if (writer != null)
								writer.close();
						} catch (IOException e) {
							output += "Cannot write to file.\n";
						}
					}
				} else {
					output += "You already stored " + thing + ".\n";
				}
			} else if (!myGun.getType().equals(thing)
					|| !myCar.getType().equals(thing)) {
				output += "You don't have " + thing + ".\n";
			} else {
				output += "Storing has a problem.\n";
			}
		} else {
			notAvaliable("storing");
		}
		save();

	}

	public void listStorage() {
		checkStatus();

		Scanner sc = null;
		try {
			sc = new Scanner(new File(name + "_storage.sav"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			output += "Cannot find the storage file.\n";
		}

		output += "Your storage contains:\n";

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			output += saved[0] + "\n";
		}
		sc.close();
	}

	public void retrieve(String thing) {
		checkStatus();

		if (available) {
			Scanner sc = null;
			boolean itemFound = false;

			if (!myGun.getType().equals(thing)
					|| !myCar.getType().equals(thing)) {
				try {
					sc = new Scanner(new File(name + "_storage.sav"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					output += "Cannot find the storage file.\n";
				}

				while (sc.hasNext()) {
					String line = sc.nextLine();
					String[] saved = line.split("\\s+");
					if (saved[0].equals(thing)) {
						itemFound = true;
					}
				}
				sc.close();

				if (itemFound == true) {
					// retrieve gun
					retrieveGun(thing, myGun, name, "gun");

					// retrieve car
					retrieveCar(thing, myCar, name, "car");
				} else {
					output += "Cannot find what you are looking for.\n";
				}
			} else if (myGun.getType().equals(thing)) {
				output += "You already have " + thing + " gun on you.\n";
			} else if (myCar.getType().equals(thing)) {
				output += "You already driving " + thing + ".\n";
			} else {
				output += "Problem with retrieving.";
			}
		} else {
			notAvaliable("retrieving");
		}
		save();
	}

	public void retrieveGun(String thing, Gun myGun, String name, String data) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(data + ".dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			output += "Cannot find the gun file.\n";
		}
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			if (saved[0].equals(thing)) {
				if (myGun.getType().equals("none")) {
					myGun.setType(thing);
					removeLine(name + "_storage.sav", myGun.getType());
					output += thing + " was retrieved.";
				} else {
					output += "You must first store your gun in hand.\n";
				}
			}
		}
		sc.close();
	}

	public void retrieveCar(String thing, Car myCar, String name, String data) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(data + ".dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			output += "Cannot find the gun file.\n";
		}
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] saved = line.split("\\s+");
			if (saved[0].equals(thing)) {
				if (myCar.getType().equals("none")) {
					myCar.setType(thing);
					removeLine(name + "_storage.sav", myCar.getType());
					output += thing + " was retrieved.";
				} else {
					output += "You must first store the car currently driving.\n";
				}
			}
		}
		sc.close();
	}

	// give a random number from min to max
	public static int randomNumber(int min, int max) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(max - min + 1) + min;
		return randomNumber;
	}

	// give a random number according to normal distribution
	public static int randomDistribution(int min, int max) {
		int mean = (max - min) / 2;
		int stdev = (max - min) / 9;
		Random rand = new Random();
		int randomNumber = min + (int) (rand.nextGaussian() * stdev + mean);
		return randomNumber;
	}

	// need to fix karma factor. can't have negative number for RNG
	public void randomEvent(int dangerFactor) {

		if (randomNumber(0, 4) == 1) {
			if (!myGun.getType().equals("none") && bullet > 6) {
				int damage = randomNumber(0, 20);
				damage = damage / myGun.getPower();
				HP = HP - damage;
				bullet = bullet - randomNumber(1, 6);
				output += "Police arrived on the scene. \n"
						+ "You fired back at the policeman and got away.\n";
			} else {
				HP = HP - randomNumber(0, 50);
				output += "A policeman fired at you. You are hurt bad.\n";
			}
		}

		// positive karma prevent superhero catches, but negative karma increase
		// chance to get free stuff
		int karmaMod = Math.abs(karma);

		if (randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
				&& karma < 0) {
			if (unique.getType().equals("HumanShield")
					|| unique.getType().equals("WildCall")
					|| unique.getType().equals("MassRush")
					|| unique.getType().equals("Disguise")) {
				HP = HP * unique.getLevel() / 100;
				output += "You used " + unique.getType() + " to get away.\n";
			} else {
				HP = 1;
				output += "Bad luck. You just met Batman.\n";
			}
		}

		// put player in jail
		if (randomNumber(0, 10 / dangerFactor) == 1) {
			if (unique.getType().equals("Disguise")) {
				output += "You managed to reduce jail time by using "
						+ unique.getType() + " \n";
				setJailTime(convertToMS(1 - unique.getLevel() / 100),
						currentTime);
			} else {
				output += "You did not run fast enough. Police got you in jail.\n";
				setJailTime(convertToMS(1), currentTime);
			}
		}

		if (randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
				&& karma < 0) {
			if (unique.getType().equals("HumanShield")
					|| unique.getType().equals("WildCall")
					|| unique.getType().equals("MassRush")
					|| unique.getType().equals("Disguise")) {
				HP = HP * unique.getLevel() / 100;
				output += "You used " + unique.getType() + " to get away.\n";
			} else {
				HP = 1;
				output += "Bad luck. You just met Superman.\n";
			}
		}

		if (randomNumber(0, 1000 / dangerFactor) == 1) {
			HP = 1;
			output += "Lighting hit you.\n";
		}

		if (randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
				&& karma < 0) {
			money = money + 500;
			output += "Joker gave your some money.\n";
		}

		if (randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
				&& karma < 0) {
			if (myGun.getType().equals("none")) {
				myGun.setType("hand");
				output += "Golden Finger gave you a hand gun.\n";
			} else {
				output += "Golden Finger wants to give you a gun, but you "
						+ "already have one.\n";
			}
		}

		if (randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
				&& karma < 0) {
			bullet = bullet + 100;
			carryWeight = carryWeight + (double) 100 / 63;
			output += "Al Capone give you some bullets.\n";
		}

		if (randomNumber(0, 1000 / dangerFactor) == 1 && respect > 500) {
			respect = respect - 500;
			output += "There was a coup in your gang and you lost some respect.\n";
		}

		if (randomNumber(0, 1000 / dangerFactor) == 1 && karma < 0) {
			respect = respect + 500;
			output += "Some one high up must really like you.\n";
		}
	}

	public void checkStatus() {
		myCar.loadProperties(myCar.getType());
		updateTime();
		checkWeight();
		checkRespect();
		// checkInJail();
		// checkBusy();
		checkBlood();
		checkEnergy();
		checkAvailable();
		save();
	}

	public void status() {
		// checkStatus();

		output = "Name: " + capWord(name) + " | Age: " + age + " | Class: "
				+ charClass + " | Height: " + height + " cm" + " | Weight: "
				+ weight + " lb" + "\n" + "HP: " + HP + " | Energy: " + energy
				+ " | Karma: " + karma + " | Follower: " + follower
				+ " | Respect: " + respect + " | Rank: " + rank + "\n"
				+ "Bullets: " + bullet + " | Money: " + money + " | Gun: "
				+ capWord(myGun.getType()) + " | Car: "
				+ capWord(myCar.getType()) + " | Bag: " + (int) carryWeight
				+ " lb" + "\n" + "Family: " + family + " | Melee: "
				+ melee.getType() + " | Special: " + unique.getType()
				+ " | Available: " + available + "\n";
	}

	public void updateTime() {
		currentTime = new Date().getTime();
	}

	// return a word with first letter capitalized
	public String capWord(String word) {
		String capWord = word.substring(0, 1).toUpperCase() + word.substring(1);
		return capWord;
	}

	public void checkLive() {

		if (HP <= 0) {
			alive = false;
			// output += "You are dead.\n";
		}
	}

	public void notAvaliable(String functionName) {
		if (!alive) {
			output += "You are still dead.\n";
			checkLive();
		} else if (busy) {
			output += "You are still busy.\n";
			checkBusy();
		} else if (inJail) {
			output += "You are in still jail.\n";
			checkInJail();
		} else {
			output += functionName + " has a problem \n";
		}
	}

	public void checkAvailable() {
		if (!alive) {
			available = false;
		} else if (busy) {
			available = false;
		} else if (inJail) {
			available = false;
		} else
			available = true;
	}

	public void checkBlood() {
		if (HP > 100)
			HP = 100;
		else if (HP < 0) {
			HP = 0;
		}
	}

	public void checkEnergy() {
		if (energy > 100)
			energy = 100;
		else if (HP < 0) {
			HP = 0;
		}
	}

	public void checkWeight() {
		myGun.loadProperties(myGun.getType());
		// tally total weight, check if bullets spent weren't counted as dropped
		// weight
		carryWeight = (double) bullet / 63 + myGun.getWeight();
		// output += "bullet weight " + bullet / 63 + " gun weight "
		// + myGun.getWeight() + " carryWeight " + carryWeight);
		if (carryWeight > weight) {
			double difference = carryWeight - weight;
			System.out.println("difference is " + difference);
			bullet = bullet - (int) Math.round(difference * 63);
			System.out.println("bullet difference is "
					+ (int) Math.round(difference * 63) + "\n");
			carryWeight = carryWeight - difference;
			output += "You carried too much stuff and droped some bullets.\n";
		} else {
			// output += "You are not overloading.\n");
		}
	}

	public void checkInJail() {

		if (currentTime > inJailTime + jailTime) {
			inJail = false;
		} else {
			output += "Your sentence have "
					+ (jailTime - (currentTime - inJailTime)) / 1000
					+ " seconds remaining. \n";
		}
	}

	public void checkBusy() {

		if (currentTime > workStartTime + workTime) {
			busy = false;
		} else {
			output += "You need to rest for "
					+ (workTime - (currentTime - workStartTime)) / 1000
					+ " seconds before you can work again.\n";
		}
	}

	public void setWorkTime(long setWorkTime, long setStartTime) {
		busy = true;
		workTime = setWorkTime;
		workStartTime = setStartTime;

		output += "Your need " + workTime / 1000 / 60
				+ " minutes until you can do you next task.\n";
	}

	public void setJailTime(long setJailTime, long setInJailTime) {
		inJail = true;
		inJailTime = setInJailTime;
		jailTime = setJailTime;

		output += "Your need " + jailTime / 1000 / 60
				+ " minutes until you can get out of jail.\n";
	}

	// convert minutes into milliseconds for date calculation
	public long convertToMS(double minute) {
		return (long) minute * 60 * 1000;
	}

	public void checkRespect() {
		if (respect < 5000) {
			rank = "BaiXing";
		} else if (respect >= 5000 && respect < 10000) {
			rank = "YaoMan";
			follower = 1;
		} else if (respect >= 10000 && respect < 20000) {
			rank = "JiangKou";
			follower = 2;
		} else if (respect >= 20000 && respect < 30000) {
			rank = "ShouShan";
			follower = 4;
		} else if (respect >= 30000 && respect < 40000) {
			rank = "BuYi";
			follower = 6;
		} else if (respect >= 40000 && respect < 50000) {
			rank = "YinFeng";
			follower = 8;
		} else if (respect >= 50000 && respect < 60000) {
			rank = "XunFeng";
			follower = 10;
		} else if (respect >= 60000 && respect < 70000) {
			rank = "HongQi";
			follower = 12;
		} else if (respect >= 70000 && respect < 80000) {
			rank = "JinFeng";
			follower = 14;
		} else if (respect >= 80000 && respect < 90000) {
			rank = "DangJia";
			follower = 16;
		} else if (respect >= 90000 && respect < 100000) {
			rank = "ShengXian";
			follower = 18;
		} else if (respect >= 100000 && respect < 110000) {
			rank = "XinFu";
			follower = 20;
		} else if (respect >= 110000 && respect < 120000) {
			rank = "XingTang";
			follower = 22;
		} else if (respect >= 120000 && respect < 130000) {
			rank = "ZhiTang";
			follower = 24;
		} else if (respect >= 130000 && respect < 140000) {
			rank = "LiTang";
			follower = 26;
		} else if (respect >= 140000 && respect < 150000) {
			rank = "PeiTang";
			follower = 28;
		} else if (respect >= 150000 && respect < 160000) {
			rank = "ZuoTang";
			follower = 30;
		} else if (respect >= 160000 && respect < 170000) {
			rank = "FuLongTou";
			follower = 32;
		} else if (respect >= 170000) {
			rank = "LongTou";
			follower = 34;
		} else
			System.out.println("Problem with ranking.\n");

	}

	public void removeLine(String file, String lineToRemove) {

		try {
			File inFile = new File(file);
			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}
			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				if (!line.trim().startsWith(lineToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void load(String stringBuffer) {
		/*
		 * // read data for person Scanner sc = null; try { sc = new Scanner(new
		 * File(name + ".sav")); } catch (FileNotFoundException e) {
		 * e.printStackTrace();
		 * System.out.println("This char does not exits.\n"); }
		 * 
		 * String line = sc.nextLine();
		 */

		String[] saved = stringBuffer.split("\\s+");
		name = saved[0];
		alive = Boolean.parseBoolean(saved[1]);
		age = Integer.parseInt(saved[2]);
		charClass = saved[3];
		height = Integer.parseInt(saved[4]);
		weight = Integer.parseInt(saved[5]);
		HP = Integer.parseInt(saved[6]);
		energy = Integer.parseInt(saved[7]);
		bullet = Integer.parseInt(saved[8]);
		money = Integer.parseInt(saved[9]);
		myGun.setType(saved[10]);
		carryWeight = Double.parseDouble(saved[11]);
		respect = Integer.parseInt(saved[12]);
		myCar.setType(saved[13]);
		karma = Integer.parseInt(saved[14]);
		inJailTime = Long.parseLong(saved[15]);
		jailTime = Long.parseLong(saved[16]);
		workStartTime = Long.parseLong(saved[17]);
		workTime = Long.parseLong(saved[18]);
		family = saved[19];
		melee.setType(saved[20]);
		melee.setLevel(Integer.parseInt(saved[21]));
		melee.setXp(Integer.parseInt(saved[22]));
		unique.setType(saved[23]);
		unique.setLevel(Integer.parseInt(saved[24]));
		unique.setXp(Integer.parseInt(saved[25]));
		// sc.close();
		output = name + " is loaded.\n";

		// checkStatus(); //this statement had a bunch of errors need to fix
	}

	public String save() {

		String writeString = "";
		writeString = name + " " + alive + " " + age + " " + charClass + " "
				+ height + " " + weight + " " + HP + " " + energy + " "
				+ bullet + " " + money + " " + myGun.getType() + " "
				+ carryWeight + " " + respect + " " + myCar.getType() + " "
				+ karma + " " + inJailTime + " " + jailTime + " "
				+ workStartTime + " " + workTime + " " + family + " "
				+ melee.getType() + " " + melee.getLevel() + " "
				+ melee.getXp() + " " + unique.getType() + " "
				+ unique.getLevel() + " " + unique.getXp() + "\n";

		// write data for person
		BufferedWriter writer = null;

		if (name != null) {
			try {
				writer = new BufferedWriter(
						new FileWriter(name + ".sav", false));
				writer.write(name + " " + alive + " " + age + " " + charClass
						+ " " + height + " " + weight + " " + HP + " " + energy
						+ " " + bullet + " " + money + " " + myGun.getType()
						+ " " + carryWeight + " " + respect + " "
						+ myCar.getType() + " " + karma + " " + inJailTime
						+ " " + jailTime + " " + workStartTime + " " + workTime
						+ " " + family + " " + melee.getType() + " "
						+ melee.getLevel() + " " + melee.getXp() + " "
						+ unique.getType() + " " + unique.getLevel() + " "
						+ unique.getXp() + "\n");
			} catch (IOException e) {
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					System.out.println("Cannot write to file.\n");
				}
			}
		}
		// output += name+" is saved");
		return writeString;
	}

	// initialize person, this is what a person have when just born
	public void createPerson(String setName) {
		alive = true;
		age = 0;
		name = setName;
		charClass = "human";
		HP = 100;
		energy = 0;
		height = randomDistribution(110, 230);
		weight = randomDistribution(height - 90, height + 60);
		money = 0;
		bullet = 0;
		birthTime.setTime(0);
		myGun.setType("none");
		carryWeight = 0;
		myCar.setType("none");
		karma = 0;
		respect = 0;
		rank = "";
		follower = 0;
		available = true;
	}

}

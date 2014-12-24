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
    private double carryWeight = 0; // carryWeight is how much weight you have on you
    private int respect = 0;
    private String rank = null;
    private int follower = 0;
    private int karma = 0;
    private Family myFamily = new Family();
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

    public Person() {
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

    public boolean getInJail() {
        return inJail;
    }

    public void setInJail(boolean setInJail) {
        inJail = setInJail;
    }

    public boolean getBusy() {
        return busy;
    }

    public void setBusy(boolean setBusy) {
        busy = setBusy;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean setAvailable) {
        available = setAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String setName) {
        name = setName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int setAge) {
        age = setAge;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String setClass) {
        charClass = setClass;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int setHP) {
        HP = setHP;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int setEnergy) {
        energy = setEnergy;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int setHeight) {
        height = setHeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int setWeight) {
        weight = setWeight;
    }

    public int getBullet() {
        return bullet;
    }

    public void setBullet(int setBullet) {
        bullet = setBullet;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int setMoney) {
        money = setMoney;
    }

    public void setBirth(long setBirth) {
        birthTime.setTime(setBirth);
    }

    public Gun getGun() {
        return myGun;
    }

    public void setGun(Gun setGun) {
        myGun = setGun;
    }

    public Car getCar() {
        return myCar;
    }

    public void setCar(Car setCar) {
        myCar = setCar;
    }

    public Skill getMelee() {
        return melee;
    }

    public void setMelee(Skill setMelee) {
        melee = setMelee;
    }

    public Skill getUnique() {
        return unique;
    }

    public void setUnique(Skill setUnique) {
        unique = setUnique;
    }

    public double getCarryWeight() {
        return carryWeight;
    }

    public void setCarryWeight(double setCarryWeigh) {
        carryWeight = setCarryWeigh;
    }

    public int getRespect() {
        return respect;
    }

    public void setRespect(int setRespect) {
        respect = setRespect;
    }

    public String getCarType() {
        return myCar.getType();
    }

    public void setCarType(String setCarType) {
        myCar.setType(setCarType);
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int setKarma) {
        karma = setKarma;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int setFollower) {
        follower = setFollower;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String setRank) {
        rank = setRank;
    }

    public String getFamilyName() {
        return myFamily.getFamilyName();
    }

    public void setFamilyName(String setFamilyName) {
        myFamily.setFamilyName(setFamilyName);
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getInJailTime() {
        return inJailTime;
    }

    public void setInJailTime(long inJailTime) {
        this.inJailTime = inJailTime;
    }

    public long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    public long getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(long workStartTime) {
        this.workStartTime = workStartTime;
    }


    public void joinFamily(int familyIndex) {
        checkAvailable();

        if (available) {

            // only allow joining if not in a family
            if (family.equals("none")) {
                myFamily.loadProperties(familyIndex);
                melee.setType(myFamily.getMelee());
                unique.setType(myFamily.getUnique());
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
            notAvailable("join family");
        }
        save();
    }

    public void practiceSkills() {
        checkAvailable();

        if (available && energy > 50) {
            if (!melee.getType().equals("none")) {
                output += "You started practicing " + melee.getType() + " and "
                        + unique.getType() + ".\n";
                energy = energy - 80;
                melee.setXp(melee.getXp() + 5);
                unique.setXp(unique.getXp() + 5);
                // checkLevel will increase level if xp is high enough, also
                // show output message
                output += melee.checkLevel();
                // checkLevel will increase level if xp is high enough, also
                // show output message
                output += unique.checkLevel();
                output += "You felt tired after practicing. \n";
            } else {
                output = "You don't have any skills.\n";
            }
        } else if (energy < 80) {
            output = "You are too tired to practice any skills.\n";
        } else {
            notAvailable("practice skill");
        }
        save();
    }

    public void doFavor() {
        checkAvailable();
        if (available) {

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
                    setWorkTime(1);
                    karma += 20;
                    respect += 20;
                    break;
                case 2:
                    output += "Jeff's daugher was beat up by a bunch of punks. He asked you to teach them a lesson.\n";
                    randomEvent(1);
                    setWorkTime(1);
                    karma += 50;
                    respect += 50;
                    break;
                case 3:
                    output += "Mark's father was robbed and stabbed. He wants to revenge his father's death.\n";
                    randomEvent(2);
                    setWorkTime(1);
                    karma += 100;
                    respect += 100;
                    break;
                case 4:
                    output += "Your god son ask you to help him get a job. The only problem is that you have to convince his boss. No big deal, you made his boss an offer.\n";
                    randomEvent(2);
                    setWorkTime(1);
                    karma += 110;
                    respect += 110;
                    break;
                case 5:
                    output += "You were asked for a favor on your daughter's wedding day. An offer you cannot refuse. People do not take this lightly.\n";
                    randomEvent(2);
                    setWorkTime(1);
                    karma += 200;
                    respect += 200;
                    break;
                default:
                    System.out.println("Do favor has a problem.\n");
                    break;
            }
        } else {
            notAvailable("do favor");
        }
        save();
    }

    // work for money when crime doesn't pay
    public void labor() {

        checkAvailable();
        if (available && energy >= 50) {
            int increase = 0;
            int workFactor = 1;
            output += "You are tight on cash and need to make some money. \n";
            // work for money. need to change the work variable to the
            // number of types of work
            int favor = 5;

            switch (randomNumber(1, favor)) {
                case 1:
                    output += "Dirty Old Bum Restaurant is looking for waiters. \n";
                    setWorkTime(1);
                    energy = energy - 50;
                    increase = workFactor * 12;
                    money += increase;
                    output += "You got $" + increase + " for your service.\n";
                    break;
                case 2:
                    output += "Mom took petty on you and gave you some money.\n";
                    setWorkTime(1);
                    energy = energy - 50;
                    increase += workFactor * 6;
                    money += increase;
                    output += "You got $" + increase + " for your service.\n";
                    break;
                case 3:
                    output += "Zoombie Hospital need someone to carry bodies. \n";
                    setWorkTime(1);
                    energy = energy - 50;
                    increase += workFactor * 13;
                    money += increase;
                    output += "You got $" + increase + " for your service.\n";
                    break;
                case 4:
                    output += "Eat Poison Pharmaceuticals is looking for testing subjects. \n";
                    setWorkTime(1);
                    energy = energy - 50;
                    increase += workFactor * 4;
                    money += increase;
                    output += "You got $" + increase + " for your service.\n";
                    break;
                case 5:
                    output += "Begging for change seems like a good career option. \n";
                    setWorkTime(1);
                    energy = energy - 50;
                    increase += workFactor * 3;
                    money += increase;
                    output += "You got $" + increase + " for your service.\n";
                    break;
                default:
                    System.out.println("Do favor has a problem.\n");
                    break;
            }
        } else if (energy < 50) {
            output += "You are too tired to work. \n";
        } else {
            notAvailable("work");
        }
        save();
    }

    public void takeDamage(int damage) {
        damage = damage / myGun.getPower();
        HP = HP - randomNumber(0, damage) + follower;
    }

    public void spendBullet(int bullet) {
        int spent = randomNumber(1, bullet);
        bullet = bullet - spent;
        carryWeight = carryWeight - spent / 63; // weight of bullet
    }

    public void increaseMoney(int increase) {
        money = money + randomNumber(0, increase);
    }

    public void increaseRespect(int increase) {
        respect = respect + randomNumber(0, increase);
    }

    public void decreaseKarma(int decrease) {
        karma = karma - randomNumber(0, decrease);
    }

    public void rewardCrime(int moneyIncrease, int respectIncrease, int karmaDecrease) {
        increaseMoney(moneyIncrease);
        increaseRespect(respectIncrease);
        decreaseKarma(karmaDecrease);
    }

    // the higher the effectiveness, the less useful the skills is
    public int calcChance(int meleeEffect, int uniqueEffect) {
        int successChance = randomNumber(1 + melee.getLevel() / meleeEffect
                + unique.getLevel() / uniqueEffect, 100);

        return successChance;
    }

    public int addChance(String uniqueSkill, int successChance) {
        if (unique.getType().equals(uniqueSkill)) {
            successChance += successChance / 3;
        }
        return successChance;
    }

    // take money from store
    public void robStore() {

        Crime crime = new Crime(this);
        crime.setMeleeEffect(1);
        crime.setUniqueEffect(1);
        crime.setTakeDamage(20);
        crime.setDangerFactor(1);
        crime.setMoneyIncrease(50);
        crime.setRespectIncrease(20);
        crime.setKarmaDecrease(20);
        crime.setInitialMessage("You entered a liquor store and demanded money.");
        crime.setResponseOne("The store owner fought back and you got hurt.");
        crime.setGetAway("You got away with some cash.");
        crime.setFailed("Being such an amateur, you did not get any money.");
        crime.setDied("You did not make it.");
        output += crime.execute();
        /*
        checkAvailable();
        int successChance = calcChance(1, 1);
        int taskChance = randomNumber(1, 100);

        if (available) {
            output += "You entered a liquor store and demanded money.\n";

            // some skills lessen damage
            if (melee.getType().equals("JiuJitsu")
                    || melee.getType().equals("Ninja")) {
                takeDamage(10);
                output += "You used " + melee.getType()
                        + " to lessen the damage.\n";
            } else {
                takeDamage(20);
            }
            output += "The store owner fought back and you got hurt.\n";
            randomEvent(1);
            checkLive();
            if (alive && successChance >= taskChance) {
                rewardCrime(50, 20, 20);
                output += "You got away with some cash.\n";
            } else if (successChance < taskChance) {
                rewardCrime(0, 20, 20);
                output += "Being such an amateur, you did not get any money.\n";
            } else if (!alive) {
                output += "You did not make it. \n";
            } else {
                save();
            }
            setWorkTime(1);
        } else {
            notAvailable("rob store");
        }
        save();

        */
    }

    public void heist() {
        checkAvailable();
        int successChance = calcChance(2, 2);
        int taskChance = randomNumber(1, 100);
        int bulletNeeded = 6;

        if (available && bullet >= 6) {
            output += "You found a buddy and try to get some unfortunate"
                    + " soul on the road to give you some money.\n";
            takeDamage(50);
            spendBullet(bulletNeeded);
            output += "That guy fought back.\n";
            randomEvent(2);
            checkLive();
            if (alive && successChance >= taskChance) {
                rewardCrime(120, 50, 50);
                output += "You took some money from the pour soul.\n";
            } else if (successChance < taskChance) {
                rewardCrime(0, 50, 50);
                output += "In the mist of the haste. Money was not grabed.\n";
            } else if (!alive) {
                output += "You were left by your partner to die.\n";
            } else
                save();
            setWorkTime(3);
        } else if (bullet < bulletNeeded) {
            output = "You need at least " + bulletNeeded + " bullets.\n";
        } else {
            notAvailable("heist");
        }
        save();
    }

    public void robBank() {
        checkAvailable();
        int successChance = calcChance(1, 2);
        int taskChance = randomNumber(1, 100);
        int bulletNeeded = 36;
        int carSeatNeeded = 4;
        int followerNeeded = 4;

        if (available && bullet >= bulletNeeded && myGun.getType().equals("tommy")
                && myCar.getSeat() >= carSeatNeeded && follower >= followerNeeded) {
            output += "You found " + (followerNeeded - 1) + " more guys to help you rob a bank.\n";
            takeDamage(99);
            spendBullet(36);
            output += "Security guards called for police.\n";
            randomEvent(3); // increase dangerFactor for robbing a bank.
            checkLive();
            if (alive && successChance >= taskChance) {
                rewardCrime(1000, 200, 200);
                output += "You got some money from the local bank.\n";
            } else if (successChance < taskChance) {
                output += "The bank have top notch security.\n";
                rewardCrime(0, 200, 200);
            } else if (!alive) {
                output += "Cop shoot through your heart.\n";
            } else
                save();
            setWorkTime(3);
        } else if (bullet < bulletNeeded) {
            output += "You don't have enough bullets.\n";
        } else if (!myGun.getType().equals("tommy")) {
            output += "You need a tommy gun to rob a bank.\n";
        } else if (myCar.getSeat() < carSeatNeeded) {
            output += "You need a car that seats at least " + carSeatNeeded + " people.\n";
            output += "Your car seats " + myCar.getSeat() + " people.\n";
        } else if (follower < followerNeeded) {
            output += "You need at least " + followerNeeded + " followers to rob a bank.\n";
        } else {
            notAvailable("rob bank");
        }
        save();
    }

    public void assassination() {
        checkAvailable();
        int successChance = calcChance(1, 2);
        int taskChance = randomNumber(1, 100);
        int bulletNeeded = 10;

        // Snipe skill increase the chance of assassination
        successChance = addChance("Snipe", successChance);

        if (available && bullet >= bulletNeeded && myGun.getType().equals("sniper")) {
            output += "You accepted a task to assassinate. \n";

            spendBullet(10);
            output += "You made an attempt on your target. \n";
            randomEvent(2); // increase dangerFactor for assassination
            checkLive();
            if (alive && successChance >= taskChance) {
                rewardCrime(1000, 100, 100);
                output += "You got some money for the hit. \n";
            } else if (successChance < taskChance) {
                output += "Your target survived. \n";
                rewardCrime(0, 100, 100);
            } else if (!alive) {
                output += "Your target retaliated. \n";
            } else
                save();
            setWorkTime(3);

        } else if (bullet < bulletNeeded) {
            output += "You don't have enough bullets.\n";
        } else if (!myGun.getType().equals("sniper")) {
            output += "You need a sniper gun to assassinate. \n";
        } else {
            notAvailable("assassination");
        }
        save();
    }

    public void smuggling() {
        checkAvailable();
        int successChance = calcChance(1, 2);
        int taskChance = randomNumber(1, 100);
        int carSeatNeeded = 5;

        // gladiator skill increase the chance of smuggling
        successChance = addChance("Gladiator", successChance);

        if (available && myCar.getSeat() >= carSeatNeeded) {
            output += "You drove around and trying to find people to smuggle. \n";

            randomEvent(2); // increase dangerFactor for smuggling
            checkLive();
            if (alive && successChance >= taskChance) {
                rewardCrime(1000, 100, 100);
                output += "There is enough people willing to get across the boarder. \n";
            } else if (successChance < taskChance) {
                rewardCrime(0, 100, 100);
                output += "Police checked your vehicle and people ran away. \n";
            } else if (!alive) {
                output += "In the fire exchange, the rookie cop had a head shot. \n";
            } else
                save();
            setWorkTime(3);

        } else if (myCar.getSeat() < carSeatNeeded) {
            output += "You need a car that fits at least" + carSeatNeeded + " people. \n";
        } else {
            notAvailable("smuggling");
        }
        save();
    }

    public void extortion() {
        checkAvailable();
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
            setWorkTime(3);

        } else if (!myGun.getType().equals("tommy")) {
            output += "You need a Tommy gun to extort people. \n";
        } else if (bullet < 100) {
            output += "You need more bullets. \n";
        } else {
            notAvailable("extortion");
        }
        save();
    }

    public void prostitution() {
        checkAvailable();
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
            setWorkTime(3);

        } else if (follower < 8) {
            output += "There is not enough people running your business. \n";
        } else if (respect < 40000) {
            output += "People do not respect you.\n";
        } else {
            notAvailable("prostitution");
        }
        save();

    }

    public void kidnapping() {
        checkAvailable();
        int successChance = 0;
        int taskChance = randomNumber(1, 100);

        // Human shield skill increase the chance of extortion
        if (unique.getType().equals("HumanShield")) {
            // System.out.println("Your special ability " + unique.getType() +
            // " was useful for kidnapping.\n");
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
            setWorkTime(3);

        } else if (follower < 8) {
            output += "Not enough people to run the show. \n";
        } else if (respect < 40000) {
            output += "Nobody gave you any tip for possible target. \n";
        } else {
            notAvailable("kidnapping");
        }
        save();

    }

    public void humanTrafficking() {
        checkAvailable();
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
            setWorkTime(3);

        } else if (follower < 8) {
            output += "Not enough connections. \n";
        } else if (respect < 4000) {
            output += "Your guys are just idiots. \n";
        } else {
            notAvailable("huamn trafficking");
        }
        save();
    }

    public void drugDealing() {
        checkAvailable();
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
            setWorkTime(3);

        } else if (follower < 8) {
            output += "Cannot sell without people on streets. \n";
        } else if (respect < 4000) {
            output += "The next family is taking your territories. \n";
        } else {
            notAvailable("drug dealing");
        }
        save();
    }

    public void playDice(int bet, int amount) {
        String type = "";
        if (bet > 3) {
            type = "big";
        } else {
            type = "small";
        }
        if (money > amount) {
            int roll = randomNumber(1, 6);
            if (roll > 3 && type.equals("big")) {
                money = money + amount;
                output += "You won! \n";
            } else if (roll > 3 && type.equals("small")) {
                money = money - amount;
                output += "You lost! \n";
            } else if (roll < 4 && type.equals("big")) {
                money = money - amount;
                output += "You lost! \n";
            } else if (roll < 4 && type.equals("small")) {
                money = money + amount;
                output += "You won! \n";
            }

            if (roll == bet) {
                money = money + amount * 5;
                output += "You hit the exact number!!!! \n";
            }
        } else {
            output += "You don't have that much money. \n";
        }
    }

    public void horseRacing(int pick, int wager) {
        int payout = randomNumber(2, 13);
        if (money > wager) {
            int result = randomNumber(1, 9);
            if (pick == result) {
                money = money + wager * payout;
                output += "You won $ " + wager * payout + "! \n";
            } else {
                money = money - wager;
                output += "You lost! \n";
            }
        } else {
            output += "You don't have that much money. \n";
        }
    }

    public void Donate(int will) {
        checkAvailable();
        if (alive && !busy && !inJail) {
            money = money - will;
            karma = karma + will;
        } else {
            notAvailable("donation");
        }
        save();
    }

    public void buyBullet(int number) {
        checkAvailable();

        if (available && money > (number * 10)
                && (carryWeight + number / 63) < weight) {
            money = money - 10 * number;
            bullet = bullet + number;
            carryWeight = carryWeight + (double) number / 63;
            output = "You bought " + number + " bullets.\n";
        } else if (money < (number * 10)) {
            output = "You don't have enough money.\n";
        } else if ((carryWeight + number) > weight) {
            output = "You can't carry that many bullets.\n";
        } else {
            notAvailable("buying bullet");
        }
        save();
    }

    public void buyBlood() {
        checkAvailable();
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
            notAvailable("buy blood");
        }
        save();
    }

    public void buyGun(String gunName) {
        checkAvailable();

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
            output += "You bought a " + gunName + ".\n";
        } else if (myGun.getType().equals(gunName)) {
            output += "You have a " + gunName + " already.\n";
        } else if (money < newGun.getCost()) {
            output += "You don't have enough money.\n";
            output += "The gun cost $" + newGun.getCost() + "\n";
        } else if ((carryWeight + newGun.getWeight()) > weight) {
            output = "You are carrying too much weight.\n";
        } else {
            notAvailable("buying gun");
        }
        save();
    }

    public void buyCar(String carType) {
        checkAvailable();

        Car oldCar = new Car();
        oldCar.loadProperties(myCar.getType());
        Car newCar = new Car();
        newCar.loadProperties(carType);
        if (!myCar.getType().equals(carType) && money > newCar.getCost()
                && available) {
            myCar.loadProperties(carType);
            money = money - myCar.getCost();
            output += "You bought a " + carType + ".\n";
        } else if (myCar.getType().equals(carType)) {
            output = "You have a " + carType + " already.\n";
        } else if (money < newCar.getCost()) {
            output = "You don't have enough money.\n";
            output += "The vehicle cost $" + newCar.getCost() + "\n";
        } else {
            notAvailable("buying car");
        }
        save();
    }

    public void addMoney() {
        money += 500;
        output = "$500 magically appeared in your bank account.\n";
    }

    public void addRespect() {
        respect += 5000;
        output = "You somehow earned respect out of thin air.\n";
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
        checkAvailable();

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
            notAvailable("storing");
        }
        save();

    }

    public void listStorage() {
        checkAvailable();

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
        checkAvailable();

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
            notAvailable("retrieving");
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
                setJailTime(2);
            } else {
                output += "You did not run fast enough. Police got you in jail.\n";
                setJailTime(1);
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
        checkInJail();
        checkBusy();
        checkBlood();
        checkEnergy();
        checkAvailable();
        save();
    }

    public void status() {
        checkStatus();

        output = "Name: " + capWord(name) + " | Age: " + age + " | Class: "
                + charClass + " | Height: " + height + " cm" + "\n"
                + "Weight: " + weight + " lb" + " | HP: " + HP + " | Energy: "
                + energy + " | Karma: " + karma + "\nFollower: " + follower
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

    public void notAvailable(String functionName) {
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
            status();
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
            output += "You carried too much stuff and had to drop some bullets.\n";
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

    public void setWorkTime(int minutes) {
        busy = true;
        workTime = convertToMS(minutes);
        workStartTime = new Date().getTime();

        output += "Your need " + workTime / 1000 / 60
                + " minutes until you can do you next task.\n";
    }

    public void setJailTime(int minutes) {
        inJail = true;
        inJailTime = convertToMS(minutes);
        jailTime = new Date().getTime();

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
        // output = name + " is loaded.\n";

        checkStatus();
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

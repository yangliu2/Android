package com.panzoto.android;

import java.util.Date;

/**
 * Created by Yang on 12/21/2014.
 */
public class Crime {

    private Person person = new Person();
    private Skill melee = new Skill();
    private Skill unique = new Skill();
    private Gun gun = new Gun();

    private int meleeEffect = 0;
    private int uniqueEffect = 0;
    private int bulletsNeeded = 0;
    private String gunNeeded = "none";
    private int seatNeeded = 0;
    private int followersNeeded = 0;
    private int moneyIncrease = 0;
    private int workTime = 0;
    private int respectIncrease = 0;
    private int karmaDecrease = 0;

    private int successChance = 0;
    private int taskChance = Tools.randomNumber(1, 100);
    private int takeDamage = 0;
    private int dangerFactor = 0;

    private String initialMessage = "";
    private String responseOne = "";
    private String getAway = "";
    private String failed = "";
    private String died = "";
    private String functionName = "";

    public Crime(Person newPerson) {
        person = newPerson;
        melee = newPerson.getMelee();
        unique = newPerson.getUnique();
        gun = newPerson.getGun();
    }

    public void setMeleeEffect(int effect) {
        meleeEffect = effect;
    }

    public void setUniqueEffect(int effect) {
        uniqueEffect = effect;
    }

    public void setBulletsNeeded(int bulletsNeeded) {
        this.bulletsNeeded = bulletsNeeded;
    }

    public void setGunNeeded(String gunNeeded) {
        this.gunNeeded = gunNeeded;
    }

    public void setSeatNeeded(int seatNeeded) {
        this.seatNeeded = seatNeeded;
    }

    public void setFollowersNeeded(int followersNeeded) {
        this.followersNeeded = followersNeeded;
    }

    public void setTakeDamage(int damage) {
        takeDamage = damage;
    }

    public void setDangerFactor(int factor) {
        dangerFactor = factor;
    }

    public void setMoneyIncrease(int increase) {
        moneyIncrease = increase;
    }

    public void setRespectIncrease(int increase) {
        respectIncrease = increase;
    }

    public void setKarmaDecrease(int decrease) {
        karmaDecrease = decrease;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public void setInitialMessage(String message) {
        initialMessage = message;
    }

    public void setResponseOne(String message) {
        responseOne = message;
    }

    public void setGetAway(String message) {
        getAway = message;
    }

    public void setFailed(String message) {
        failed = message;
    }

    public void setDied(String message) {
        died = message;
    }

    public void setFunctionName(String message) {
        functionName = message;
    }


    public int calcChance(int meleeEffect, int uniqueEffect) {
        int successChance = Tools.randomNumber(1 + melee.getLevel() / meleeEffect
                + unique.getLevel() / uniqueEffect, 100);

        return successChance;
    }

    public int addChance(String uniqueSkill, int successChance) {
        if (unique.getType().equals(uniqueSkill)) {
            successChance += successChance / 3;
        }
        return successChance;
    }

    public String execute() {
        String output = "";

        person.checkAvailable();
        calcChance(meleeEffect, uniqueEffect);

        // if no particular gun is required then any gun would be fine
        if (gunNeeded.equals("none")){
            gunNeeded = person.getGun().getType();
        }

        if (person.getAvailable() && person.getBullet() >= bulletsNeeded
                && person.getGun().getType().equals(gunNeeded)
                && person.getCar().getSeat() >= seatNeeded
                && person.getFollower() >= followersNeeded) {
            output += initialMessage + "\n";
            output += responseOne + "\n";
            person.takeDamage(takeDamage);
            RandomEvent event = new RandomEvent(person);
            output += event.displayEvent(dangerFactor);

            person.checkLive();
            if (person.getAlive() && successChance >= taskChance) {
                person.rewardCrime(moneyIncrease, respectIncrease, karmaDecrease);
                output += getAway + "\n";
            } else if (successChance < taskChance) {
                person.rewardCrime(0, respectIncrease, karmaDecrease);
                output += failed + "\n";
            } else if (!person.getAlive()) {
                output += died + "\n";
            } else {
                person.save();
            }
            person.setWorkTime(workTime);
        } else if (person.getBullet() < bulletsNeeded) {
            output += "You does not have enough bullets.\n";
        } else if (!person.getGun().getType().equals(gunNeeded)) {
            output += "You need a " + gunNeeded + " gun for this job.\n";
        } else if (person.getCar().getSeat() < seatNeeded) {
            output += "You need a car that seats at least " + seatNeeded + " people.\n";
        } else if (person.getFollower() < followersNeeded) {
            output += "You need at least " + followersNeeded + " followers to rob a bank.\n";
        } else {
            person.notAvailable(functionName);
        }
        output += person.getOutput();
        return output;
    }
}


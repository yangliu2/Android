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

    private int successChance = 0;
    private int taskChance = Tools.randomNumber(1, 100);

    private int takeDamage = 0;
    private int dangerFactor = 0;
    private int moneyIncrease = 0;
    private int respectIncrease = 0;
    private int karmaDecrease = 0;

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

    public String execute() {
        String output = "";

        person.checkAvailable();
        calcChance(meleeEffect, uniqueEffect);

        if (person.getAvailable()) {
            output += initialMessage + "\n";
            output += responseOne + "\n";
            person.takeDamage(takeDamage);
            RandomEvent event = new RandomEvent(person);
            output += event.displayEvent(dangerFactor);

            person.checkLive();
            if (person.getAlive() && successChance >= taskChance) {
                person.rewardCrime(moneyIncrease, 20, 20);
                output += getAway + "\n";
            } else if (successChance < taskChance) {
                person.rewardCrime(0, 20, 20);
                output += failed + "\n";
            } else if (!person.getAlive()) {
                output += died + "\n";
            } else {
                person.save();
            }
            output += person.setWorkTime(1);
        } else {
            person.notAvailable(functionName);
            output += person.getOutput();
        }

        return output;
    }
}


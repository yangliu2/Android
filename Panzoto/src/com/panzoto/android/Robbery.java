package com.panzoto.android;

import java.util.Date;

/**
 * Created by Yang on 12/21/2014.
 */
public class Robbery {

    Person person = new Person();
    Skill melee = new Skill();
    Skill unique = new Skill();

    int successChance = 0;
    int taskChance = 0;

    public Robbery(Person newPerson) {
        person = newPerson;
        melee = newPerson.getMelee();
        unique = newPerson.getUnique();
    }

    public void calcChance() {

        successChance = Tools.randomNumber(1 + melee.getLevel() + unique.getLevel(), 100);
        taskChance = Tools.randomNumber(1, 100);

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
    }

    public void checkLive(Person person) {

        if (person.getHP() <= 0) {
            person.setAlive(false);
            // output += "You are dead.\n";
        }
    }

    public String execute (){
        String output ="";
        long currentTime = new Date().getTime();
        RandomEvent event = new RandomEvent(person);
        calcChance();

        output += "You entered a liquor store and demanded money.\n";

        // some skills lessen damage
        if (melee.getType().equals("JiuJitsu")
                || melee.getType().equals("Ninja")) {
            person.setHP(person.getHP() - Tools.randomNumber(0, 10) + person.getFollower());
            output += "You used " + melee.getType()
                    + " to lessen the damage.\n";
        } else {
            person.setHP(person.getHP() - Tools.randomNumber(0, 20) + person.getFollower());
        }
        output += "The store owner fought back and you got hurt.\n";

        person = event.displayEvent(1);
        checkLive(person);

        System.out.println(person.getAlive());
        if (person.getAlive() && successChance >= taskChance) {
            person.setMoney(person.getMoney() + Tools.randomNumber(0, 50));
            output += "You got away with some cash.\n";
            person.setRespect(person.getRespect() + 20);
            person.setKarma(person.getKarma() - 20);
        } else if (successChance < taskChance) {
            output += "Being such an amateur, you did not get any money.\n";
            person.setRespect(person.getRespect() + 20);
            person.setKarma(person.getKarma() - 20);
        } else if (!person.getAlive()) {
            output += "You did not make it. \n";
        } else {
            person.save();
        }
        output += person.setWorkTime(Tools.convertToMS(1), currentTime);
        return output;
    }

}

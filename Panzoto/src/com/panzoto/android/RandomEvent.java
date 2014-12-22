package com.panzoto.android;

import java.util.Date;

/**
 * Created by Yang on 12/21/2014.
 */
public class RandomEvent {

    Person person = new Person();
    Gun myGun = new Gun();
    Skill unique = new Skill();
    Skill melee = new Skill();


    public RandomEvent(Person newPerson) {
        person = newPerson;
        melee = newPerson.getMelee();
        unique = newPerson.getUnique();
        myGun = newPerson.getGun();
    }

    public Person displayEvent(int dangerFactor) {

        String output = "";
        long currentTime = new Date().getTime();

        if (Tools.randomNumber(0, 4) == 1) {
            if (!myGun.getType().equals("none") && person.getBullet() > 6) {
                int damage = Tools.randomNumber(0, 20);
                damage = damage / myGun.getPower();
                person.setHP(person.getHP() - damage);
                person.setBullet(person.getBullet() - Tools.randomNumber(1, 6));
                output += "Police arrived on the scene. \n"
                        + "You fired back at the policeman and got away.\n";
            } else {
                person.setHP(person.getHP() - Person.randomNumber(0, 50));
                output += "A policeman fired at you. You are hurt bad.\n";
            }
        }

        // positive karma prevent superhero catches, but negative karma increase
        // chance to get free stuff
        int karmaMod = Math.abs(person.getKarma());

        if (Tools.randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
                && person.getKarma() < 0) {
            if (unique.getType().equals("HumanShield")
                    || unique.getType().equals("WildCall")
                    || unique.getType().equals("MassRush")
                    || unique.getType().equals("Disguise")) {
                person.setHP(person.getHP() * unique.getLevel() / 100);
                output += "You used " + unique.getType() + " to get away.\n";
            } else {
                person.setHP(1);
                output += "Bad luck. You just met Batman.\n";
            }
        }

        // put player in jail
        if (Tools.randomNumber(0, 10 / dangerFactor) == 1) {
            if (unique.getType().equals("Disguise")) {
                output += "You managed to reduce jail time by using "
                        + unique.getType() + " \n";
                person.setJailTime(Tools.convertToMS(1 - unique.getLevel() / 100),
                        currentTime);
            } else {
                output += "You did not run fast enough. Police got you in jail.\n";
                person.setJailTime(Tools.convertToMS(1), currentTime);
            }
        }

        if (Tools.randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
                && person.getKarma() < 0) {
            if (unique.getType().equals("HumanShield")
                    || unique.getType().equals("WildCall")
                    || unique.getType().equals("MassRush")
                    || unique.getType().equals("Disguise")) {
                person.setHP(person.getHP() * unique.getLevel() / 100);
                output += "You used " + unique.getType() + " to get away.\n";
            } else {
                person.setHP(1);
                output += "Bad luck. You just met Superman.\n";
            }
        }

        if (Tools.randomNumber(0, 1000 / dangerFactor) == 1) {
            person.setHP(1);
            output += "Lighting hit you.\n";
        }

        if (Tools.randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
                && person.getKarma() < 0) {
            person.setMoney(person.getMoney() + 500);
            output += "Joker gave your some money.\n";
        }

        if (Tools.randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
                && person.getKarma() < 0) {
            if (myGun.getType().equals("none")) {
                myGun.setType("hand");
                output += "Golden Finger gave you a hand gun.\n";
            } else {
                output += "Golden Finger wants to give you a gun, but you "
                        + "already have one.\n";
            }
        }

        if (Tools.randomNumber(0, 1000 / (karmaMod / 10000 + dangerFactor)) == 1
                && person.getKarma() < 0) {
            person.setBullet(person.getBullet() + 100);
            person.setCarryWeight(person.getCarryWeight() + (double) 100 / 63);
            output += "Al Capone give you some bullets.\n";
        }

        if (Person.randomNumber(0, 1000 / dangerFactor) == 1 && person.getRespect() > 500) {
            person.setRespect(person.getRespect() - 500);
            output += "There was a coup in your gang and you lost some respect.\n";
        }

        if (Person.randomNumber(0, 1000 / dangerFactor) == 1 && person.getKarma() < 0) {
            person.setRespect(person.getRespect() - 500);
            output += "Some one high up must really like you.\n";
        }

        person.setOutput(person.getOutput() + output);
        return person;
    }


}

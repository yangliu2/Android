package com.panzoto.android;

import java.util.Random;

/**
 * Created by Yang on 12/21/2014.
 */
public class Tools {

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

    // convert minutes into milliseconds for date calculation
    public static long convertToMS(double minute) {
        return (long) minute * 60 * 1000;
    }
}

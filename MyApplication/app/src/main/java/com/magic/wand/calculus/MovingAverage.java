package com.magic.wand.calculus;

public class MovingAverage {

    public static double[] movingAvergageOfBase(int base, double[] input){
        double[] retVal = new double[input.length - base + 1];
        double[] a = new double[base];
        double sum = 0.0;

        for (int i=0; i < input.length ; i++){
            sum -= a[i % base];
            a[i % base] = input[i];
            sum += a[i % base];
            if (i >= (base - 1)) {
                retVal[i - base + 1]= sum/base;
            }
        }

        return retVal;
    }

}

package com.example.myapplication;

import com.example.myapplication.calculus.Levenstein;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LevensteinUnitTest {

    @Test
    public void testLevenstein() {
        int value = Levenstein.characterDistance("balazs","kakas");
        Assert.assertTrue(value == 3);
    }

    @Test
    public void testLevensteinWithIdenticalNumbers() {
        int value = Levenstein.numbericDistance2(
                new double[]{1.0,1.1,2.0},
                new double[]{1.0,1.1,2.0},
                0.2);
        Assert.assertTrue(value == 0);
    }

    @Test
    public void testLevensteinWithIdenticalLargeNumbers() {
        double epsilon = 0.2;
        double[] originalArr = new double[10000];
        for (int i = 0; i < 10000 ; i++ ) {
            originalArr[i] = Math.random()*20;
        }
        double[] newArray = originalArr.clone();
        newArray[5] = originalArr[5] + epsilon * 2 ;
        newArray[10] = originalArr[10] + epsilon * 2 ;
        newArray[15] = originalArr[15] + epsilon * 2 ;

        int value = Levenstein.numbericDistance2(
                originalArr,
                newArray,
                0.2);
        Assert.assertTrue(value == 3);
    }

    @Test
    public void testLevensteinWithIdenticalLargeNumbers2() {
        double epsilon = 0.2;
        double[] originalArr = new double[10000];
        for (int i = 0; i < 10000 ; i++ ) {
            originalArr[i] = Math.random()*20;
        }
        double[] newArray = originalArr.clone();
        newArray[5] = originalArr[5] + epsilon * 2 ;

        int value = Levenstein.numbericDistance2(
                originalArr,
                newArray,
                0.2);
        Assert.assertTrue(value == 1);
    }

    @Test
    public void testLevensteinWithIdenticalLargeNumbers3() {
        double epsilon = 0.2;
        double[] originalArr = new double[10000];
        for (int i = 0; i < 10000 ; i++ ) {
            originalArr[i] = Math.random()*20;
        }

        double[] newArray = Arrays.copyOfRange(originalArr,200,9900);

        int value = Levenstein.numbericDistance2(
                originalArr,
                newArray,
                0.2);
        Assert.assertTrue(value == 300);
    }

    @Test
    public void testLevensteinWithNumbersWithinEpsilon() {
        int value = Levenstein.numbericDistance2(
                new double[]{1.0,1.1,2.0},
                new double[]{1.0,1.2,1.9},
                0.2);
        Assert.assertTrue(value == 0);
    }

    @Test
    public void testLevensteinWithNumbersOutsideEpsilon() {
        int value = Levenstein.numbericDistance2(
                new double[]{1.0,1.1,2.0},
                new double[]{1.0,1.2,1.7},
                0.2);
        Assert.assertTrue(value == 1);
    }

    @Test
    public void testLevensteinWithNumericDistance() {
        int value = Levenstein.numbericDistance3(
                new double[][]{
                        {0.0,   0.0,    9.8},
                        {0.0,   0.0,    9.8}
                },
                new double[][]{
                        {0.0,   0.0,    9.8}},
                0.2);

        Assert.assertTrue(value == 1);
    }

    @Test
    public void testLevensteinWithNumericDistance2() {
        int value = Levenstein.numbericDistance3(
                new double[][]{
                        {0.0,   0.0,    9.8},
                        {0.0,   0.0,    9.8}
                },
                new double[][]{
                        {0.0,   0.0,    9.8},
                        {0.0,   0.0,    9.8}
                },
                0.2);

        Assert.assertTrue(value == 0);
    }

    @Test
    public void testLevensteinWithNumericDistance3() {
        int value = Levenstein.numbericDistance3(
                new double[][]{
                        {0.0,   0.0,    9.7},
                        {0.0,   0.0,    9.6}
                },
                new double[][]{
                        {0.0,   0.0,    9.6},
                        {0.0,   0.2,    9.8}
                },
                0.2);

        Assert.assertTrue(value == 0);
    }


}

package com.example.myapplication;

import com.example.myapplication.calculus.KMPStringCalc;
import com.example.myapplication.calculus.Levenstein;

import org.junit.Assert;
import org.junit.Test;

public class KPMUnitTest {

    @Test
    public void testKMPSearchWithNumbers() {
        int value = KMPStringCalc.KMPSearchWithNumbers(
                new double[]{0.0,   0.0,    9.7},
                new double[]{0.0,   0.0,    9.6},
                0.2);

        Assert.assertTrue(value == 0);
    }

    @Test
    public void testKMPSearchWithNumbers2() {
        int value = KMPStringCalc.KMPSearchWithNumbers(
                new double[]{0.0,   0.0,    9.7},
                new double[]{0.0,   0.0,    0.0,    9.6,    6.5},
                0.2);

        Assert.assertTrue(value == 1);
    }
}

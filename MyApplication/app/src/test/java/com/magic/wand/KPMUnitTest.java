package com.magic.wand;

import com.magic.wand.calculus.KMPStringCalc;

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

package com.magic.wand;

import com.magic.wand.calculus.Normalizer;

import org.junit.Assert;
import org.junit.Test;

public class NormalizerTests {

    @Test
    public void testMaxFinding1() {
        double result = Normalizer.calc_max(new double[]{1.0,1.0});
        Assert.assertTrue(result==1.0);
    }

    @Test
    public void testMaxFInding2() {
        double result = Normalizer.calc_max(new double[]{2.0,1.0,3.0});
        Assert.assertTrue(result==3.0);
    }

    @Test
    public void testMultipliing() {
        double[] inputNumbers = new double[]{1.0,2.0,3.0};
        Normalizer.multiplyElements(2.0,inputNumbers);
        Assert.assertTrue(inputNumbers[0]==2.0);
    }
}



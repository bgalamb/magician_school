package com.magic.wand;

import com.magic.wand.calculus.MovingAverage;

import org.junit.Assert;
import org.junit.Test;

public class MovingAverageTest {

    @Test
    public void testMovingAverageSimple() {
        double[] result = MovingAverage.movingAvergageOfBase(2,new double[]{1.0,1.0,1.0});
        Assert.assertTrue(result.length == 2);
        Assert.assertEquals(result,new double[]{1.0,1.0});
    }

    @Test
    public void testMovingAverageSimple2() {
        double[] result = MovingAverage.movingAvergageOfBase(2,new double[]{1.0,2.0,1.0});
        Assert.assertTrue(result.length == 2);
        Assert.assertTrue(result[0]== 1.5);
    }

    @Test
    public void testMovingAverageSimple3() {
        double[] result = MovingAverage.movingAvergageOfBase(3,new double[]{1.0,2.0,1.0});
        Assert.assertTrue(result.length == 1);
    }


}

package com.example.myapplication;

import com.example.myapplication.calculus.Levenstein;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class QueueUnitTest {

    @Test
    public void testLinkedListPerformance() {

        ArrayList a = new ArrayList(200);
        for (int i = 0; i< 200; i++) {
         a.add(i);
        }

        LinkedList<Float> sensorDataX = new LinkedList(a);

        for (int j = 0; j < 100000; j++) {
            sensorDataX.offerFirst(new Float((int)a.get(j % 200)));
            sensorDataX.removeLast();
        }
        Assert.assertEquals(sensorDataX.size(), 200);
    }
}

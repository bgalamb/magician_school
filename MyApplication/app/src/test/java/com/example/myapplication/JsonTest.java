package com.example.myapplication;

import com.example.myapplication.calculus.KMPStringCalc;
import com.example.myapplication.loader.LocalJsonLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    public static final String ASSET_BASE_PATH = "../app/src/main/assets/";

    public String readJsonFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ASSET_BASE_PATH + filename)));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();
    }

    @Test
    public void testJsonReadingValidWord() throws Exception {
        String magicWord = "abrakadabra";
        String axis = "x";

        LocalJsonLoader.initStickAccelerationDataJSON(readJsonFile(LocalJsonLoader.LOCAL_JSON_FILE_NAME));
        List<double[]> retVal = LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord,axis);

        Assert.assertEquals(retVal.size(),4);
    }

    @Test
    public void testJsonReadingInvalidWord() throws Exception {
        String magicWord = "abrakadablo";
        String axis = "x";

        LocalJsonLoader.initStickAccelerationDataJSON(readJsonFile(LocalJsonLoader.LOCAL_JSON_FILE_NAME));
        List<double[]> retVal = LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord,axis);

        Assert.assertEquals(retVal.size(),0);
    }

    @Test
    public void testJsonReadingSimilarWord() throws Exception {
        String magicWord = "abrakadabro";
        String axis = "x";

        LocalJsonLoader.initStickAccelerationDataJSON(readJsonFile(LocalJsonLoader.LOCAL_JSON_FILE_NAME));
        String retVal = LocalJsonLoader.getSimilarWord(magicWord,1);

        Assert.assertEquals(retVal,"abrakadabra");
    }
}

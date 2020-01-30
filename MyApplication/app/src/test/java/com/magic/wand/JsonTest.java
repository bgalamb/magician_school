package com.magic.wand;

import com.magic.wand.datatype.MagicWordWrapper;
import com.magic.wand.loader.LocalJsonLoader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @Before
    public void setUp() throws Exception{
        LocalJsonLoader.initStickAccelerationDataJSON(readJsonFile(LocalJsonLoader.LOCAL_JSON_FILE_NAME));
    }

    @Test
    public void testJsonReadingValidWord()  {
        String magicWord = "abrakadabra";
        String axis = "x";
        List<double[]> retVal = LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord,axis);
        Assert.assertEquals(retVal.size(),4);
    }

    @Test
    public void testJsonReadingInvalidWord() {
        String magicWord = "abrakadablo";
        String axis = "x";
        List<double[]> retVal = LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord,axis);
        Assert.assertEquals(retVal.size(),0);
    }

    @Test
    public void testJsonReadingValidWordSize()  {
        Assert.assertEquals(LocalJsonLoader.getMagicNum(),2);
    }

    @Test
    public void testJsonReadingAllWords()  {
        Assert.assertEquals(LocalJsonLoader.getMagicWords(),2);
    }

    @Test
    public void testJsonReadingDetailsForWord()  {
        List<MagicWordWrapper> results = LocalJsonLoader.getMagicWords();
        Assert.assertEquals(results.get(0).getMagicWordKey(),"lumus");
        Assert.assertEquals(results.get(0).getMagicWordTitle(),"Lumus");
    }


}

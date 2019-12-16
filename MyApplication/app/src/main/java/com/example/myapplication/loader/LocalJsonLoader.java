package com.example.myapplication.loader;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.calculus.Levenstein;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LocalJsonLoader {

    public static final String LOCAL_JSON_FILE_NAME = "file_name.json";
    protected static String JSON_AS_STRING = null;
    private static final String TAG = "MyActivity";

    private static JSONObject STICK_ACCELERTION_SAMPLE_DATA_JSON = null;

    public static void initStickAccelerationDataJSON(Context context) {
        if (JSON_AS_STRING == null) {
            try {
                InputStream is = context.getAssets().open(LOCAL_JSON_FILE_NAME);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                JSON_AS_STRING = new String(buffer, "UTF-8");
                STICK_ACCELERTION_SAMPLE_DATA_JSON = new JSONObject(JSON_AS_STRING);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Log.i(TAG,"JSON initialized.");
    }

    public static void initStickAccelerationDataJSON(String jsonString) {
        if (JSON_AS_STRING == null) {
            try {
                JSON_AS_STRING = jsonString;
                STICK_ACCELERTION_SAMPLE_DATA_JSON = new JSONObject(JSON_AS_STRING);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static List<double[]> getStickAccelerationSamplesByAxis(String magicWord, String axis){
        List<double[]> retVal = new ArrayList<>();
        try {
            if (!STICK_ACCELERTION_SAMPLE_DATA_JSON.has(magicWord) ){

                //TODO go on with a valid word which is very similar to the expected one
                //getSimilarWord();
                Log.i(TAG,"word not found in JSON returning");

                return retVal;
            }
            JSONArray stickAccelerationsByWordAndAxis = STICK_ACCELERTION_SAMPLE_DATA_JSON.getJSONObject(magicWord).getJSONArray(axis);
            Log.i(TAG,"stickAccelerationsByWordAndAxis size="+stickAccelerationsByWordAndAxis.length());
            for (int i = 0; i < stickAccelerationsByWordAndAxis.length(); i++) {
               Log.i(TAG,"i is "+i);
                JSONArray accelerationDataJson = stickAccelerationsByWordAndAxis.getJSONArray(i);
                double[] sampleAccelerationData = new double[accelerationDataJson.length()];
                for (int j = 0; j < accelerationDataJson.length(); j++) {
                    sampleAccelerationData[j] = Double.parseDouble(accelerationDataJson.get(j).toString());
                }
                retVal.add(sampleAccelerationData);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return retVal;
    }

    //TODO handle difference
    public static String getSimilarWord(String wordCandidate,int difference){

        TreeMap<Integer,String> distancesTree = new TreeMap<>();
        Iterator<String> it = STICK_ACCELERTION_SAMPLE_DATA_JSON.keys();
        while (it.hasNext()) {
            String word = it.next();
            distancesTree.put(Levenstein.characterDistance(wordCandidate,word),word);
        }

        return distancesTree.firstEntry().getValue();
    }
}

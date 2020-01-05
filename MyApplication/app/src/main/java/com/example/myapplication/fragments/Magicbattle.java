package com.example.myapplication.fragments;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.calculus.Levenstein;
import com.example.myapplication.loader.LocalJsonLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class Magicbattle extends Fragment implements SensorEventListener {
    private static final String TAG = "MyMagicBattle";
    private Magicbattle.OnBattleFragmentInteractionListener mListener;

    //QUEUE FOR SENSOR DATA COLLECTING
    //TODO do other dimensions too not only X
    private LinkedList<Float> sensorAccelerationDataByXAxis = new LinkedList();
    private LinkedList<Float> sensorAccelerationDataByYAxis = new LinkedList();
    private LinkedList<Float> sensorAccelerationDataByZAxis = new LinkedList();


    private static final int QUEUE_SIZE = 150;
    private static int ELEMENTS_IN_QUEUE = 0;
    private static final int MAX_DIFFERENCE = 20;
    private static final int EPSILON = 2;

    private static boolean BUTTON_PRESSED = false;

    private SensorManager mSensorManager;
    private Sensor mAccelerationSensor;
    private SpeechRecognizer mSpeechRecognizer;

    private String magicWord = "";
    private ImageButton bigButton;
    private TextView hearedword;
    private TextView successful;
    private TextView earnedPoints;
    Intent mSpeechRecognizerIntent;


    public Magicbattle() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Magicbattle.OnBattleFragmentInteractionListener) {
            mListener = (Magicbattle.OnBattleFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we need to retrieve the system service on the parent activity
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_magicbattle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bigButton = view.findViewById(R.id.recordButton);
        hearedword = view.findViewById(R.id.hearedword);
        successful = view.findViewById(R.id.successful);
        earnedPoints = view.findViewById(R.id.textView8);

        initSpeechRecognizer();
        initSensors();
        initBigButton();

    }


    //INITIALIZERS
    protected void initSensors() {
        if (mAccelerationSensor == null) {
            mAccelerationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    protected void initBigButton() {
        //Control speech recognition start and stop by button
        bigButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //TODO uncomment this when testing on real device
                        mSpeechRecognizer.stopListening();
                        BUTTON_PRESSED = false;
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "started listening");
                        //TODO uncomment this when testing on real device
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        successful.setText("Was the magic successful?");
                        hearedword.setText("Listening to magic word...");
                        BUTTON_PRESSED = true;
                        break;
                }
                return false;
            }
        });
    }

    protected void initSpeechRecognizer() {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                hearedword.setHint("Ready for recognition...");
                //Log.i(TAG,"ready for speech recognition");
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float v) {
            }

            @Override
            public void onBufferReceived(byte[] bytes) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int i) {
                Log.i(TAG, "error received during voice recognition");
                //cleanup
                cleanUp();
            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                Log.i(TAG, "got result from recognition");
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null && matches.size() > 0) {
                    magicWord = matches.get(0);
                    hearedword.setText(magicWord);
                    Log.i(TAG, "word recognition is " + magicWord);

                    double[][] measurments = retrieveAndTransformSensorData();

                    boolean successX = isMagicSuccessful(magicWord, measurments[0],"x");
                    boolean successY = isMagicSuccessful(magicWord, measurments[1],"y");
                    boolean successZ = isMagicSuccessful(magicWord, measurments[2],"z");

                    boolean result = (successX && successY) || (successX && successZ) || (successY && successZ);
                    if (result){
                        successful.setText("Yeah, you did it!!");
                    }else{
                        successful.setText("Keep practicing...");
                    }


                    //TODO handle scores properly
                    if (result) {
                        earnedPoints.setText("+10p");
                        mListener.onMagicInteraction(10);
                    }else{
                        earnedPoints.setText("");
                    }


                }
                //cleanup
                Log.i(TAG, "cleaning Up");
                cleanUp();
            }

            @Override
            public void onPartialResults(Bundle bundle) {
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
            }

        });

        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.ITALIAN);
    }

    //MAGIC RELATED
    protected static boolean isMagicSuccessful(String magicWord, double[] measuredMovement, String axis) {
        Log.i(TAG, "isMagicSuccessful? with input word " + magicWord);
        Log.i(TAG, "The actual movement for axis "+ axis +" data is ");
        logArray(measuredMovement);

        if (LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord, axis).size() == 0) {
            Log.i(TAG, "no data for word, returning false");
            return false;
        }

        int numberOfTries = LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord, axis).size();
        for (int i = 0; i < numberOfTries; i++) {
            double[] expectedAcceleration = LocalJsonLoader.getStickAccelerationSamplesByAxis(magicWord, axis).get(i);
            Log.i(TAG, "The expected movement for axis "+ axis +" data contains " + expectedAcceleration.length);
            Log.i(TAG,"==============");

            int value = Levenstein.numbericDistance2(
                    measuredMovement,
                    expectedAcceleration,
                    EPSILON);

            Log.i(TAG, "difference of magic word compared to expected is " + value);
            Log.i(TAG, "===========");

            if (value < MAX_DIFFERENCE) {
                return true;
            }
        }
        return false;
    }

    //SENSOR RELATED
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!BUTTON_PRESSED) return;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            sensorAccelerationDataByXAxis.offerFirst(event.values[0]);
            //Log.i(TAG,"data added to X "+event.values[0]);
            sensorAccelerationDataByYAxis.offerFirst(event.values[1]);
            //Log.i(TAG,"data added to Y "+event.values[1]);
            sensorAccelerationDataByZAxis.offerFirst(event.values[2]);
            //Log.i(TAG,"data added to Z "+event.values[2]);

            if (++ELEMENTS_IN_QUEUE > QUEUE_SIZE) {
                sensorAccelerationDataByXAxis.removeLast();
                sensorAccelerationDataByYAxis.removeLast();
                sensorAccelerationDataByZAxis.removeLast();
            }
            Log.d(TAG, "data added to queue");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerationSensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public  void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    protected double[][] retrieveAndTransformSensorData() {
        //X
        double[] sensorAccelerationDataByXAxisArr = new double[sensorAccelerationDataByXAxis.size()];
        int sensorDataSizeX = sensorAccelerationDataByXAxis.size();
        for (int i = 0; i < sensorDataSizeX; i++) {
            sensorAccelerationDataByXAxisArr[i] = sensorAccelerationDataByXAxis.pollFirst();
        }
        logArray(sensorAccelerationDataByXAxisArr);
        //Y
        double[] sensorAccelerationDataByYAxisArr = new double[sensorAccelerationDataByYAxis.size()];
        int sensorDataSizeY = sensorAccelerationDataByYAxis.size();
        for (int i = 0; i < sensorDataSizeY; i++) {
            sensorAccelerationDataByYAxisArr[i] = sensorAccelerationDataByYAxis.pollFirst();
        }
        logArray(sensorAccelerationDataByYAxisArr);
        //Z
        double[] sensorAccelerationDataByZAxisArr = new double[sensorAccelerationDataByZAxis.size()];
        int sensorDataSizeZ = sensorAccelerationDataByZAxis.size();
        for (int i = 0; i < sensorDataSizeZ; i++) {
            sensorAccelerationDataByZAxisArr[i] = sensorAccelerationDataByZAxis.pollFirst();
        }
        logArray(sensorAccelerationDataByZAxisArr);

        return new double[][]{sensorAccelerationDataByXAxisArr,sensorAccelerationDataByYAxisArr,sensorAccelerationDataByZAxisArr};
    }

    //OTHER
    protected void cleanUp() {
        Log.i(TAG, "cleaning Up");

        sensorAccelerationDataByXAxis.clear();
        sensorAccelerationDataByYAxis.clear();
        sensorAccelerationDataByZAxis.clear();

        ELEMENTS_IN_QUEUE = 0;
    }
    private static void logArray(double[] arr){
        Log.i(TAG, Arrays.toString(arr));
    }


    //interface to communicate with activity
    public interface OnBattleFragmentInteractionListener {
        void onMagicInteraction(int score);
    }

}

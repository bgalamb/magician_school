package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.fragments.Magicbattle;
import com.example.myapplication.fragments.MagicwandsFragmentAdapter;
import com.example.myapplication.loader.LocalJsonLoader;

public class MainActivity extends AppCompatActivity implements MagicwandsFragmentAdapter.OnWandFragmentInteractionListener, Magicbattle.OnBattleFragmentInteractionListener {
    private static final String TAG = "MyActivity";

    public static int selectedMagicWand = 0;
    public static int totalScore = 0;
    public static Integer images[] = {R.drawable.wand1, R.drawable.wand2, R.drawable.wand3,R.drawable.wand4,R.drawable.wand5, R.drawable.wand6,R.drawable.wand7};

    private TextView points;
    private TextView wandID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalJsonLoader.initStickAccelerationDataJSON(getBaseContext());

        loadPreferences();

        //init top
        points = findViewById(R.id.points);
        points.setText(totalScore+"p");
        wandID = findViewById(R.id.wandID);
        wandID.setText(selectedMagicWand+"");

    }

    @Override
    public void onAttachFragment(Fragment fragment) {}

    @Override
    public void onSelectWandInteraction(int num) {
        selectedMagicWand=num;
        wandID.setText(selectedMagicWand+"");
        savePreferences();
    }

    @Override
    public void onMagicInteraction(int score) {
        totalScore += score;
        points.setText(totalScore+"p");
        savePreferences();
    }

    private void savePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("WAND", selectedMagicWand+"");
        editor.putString("SCORE", totalScore+"");
        editor.apply();
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        totalScore = Integer.parseInt(sharedPreferences.getString("SCORE", "0"));
        selectedMagicWand = Integer.parseInt(sharedPreferences.getString("WAND", "0"));

    }


}

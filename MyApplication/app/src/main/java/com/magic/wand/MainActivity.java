package com.magic.wand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import com.magic.wand.fragments.MagicBattleFragment;
import com.magic.wand.fragments.MagicWandsFragmentAdapter;
import com.magic.wand.loader.LocalJsonLoader;

public class MainActivity extends AppCompatActivity implements MagicWandsFragmentAdapter.OnWandFragmentInteractionListener, MagicBattleFragment.OnBattleFragmentInteractionListener {
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
        checkPermission();
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

//    private void getBitmapdata() {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(getResources(), R.id.myimage, options);
//        int imageHeight = options.outHeight;
//        int imageWidth = options.outWidth;
//        String imageType = options.outMimeType;
//    }

    protected void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }


}

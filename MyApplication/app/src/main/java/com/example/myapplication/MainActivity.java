package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.myapplication.fragments.Magicwands;

public class MainActivity extends AppCompatActivity implements Magicwands.OnFragmentInteractionListener{
    private static final String TAG = "MyActivity";

    String selectedMagicWand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {}

    @Override
    public void onFragmentInteraction(String wandID) {
        selectedMagicWand=wandID;
    }

    public String getWandId(){
        return selectedMagicWand;
    }
}

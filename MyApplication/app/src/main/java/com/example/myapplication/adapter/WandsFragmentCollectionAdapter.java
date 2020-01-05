package com.example.myapplication.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.MainActivity;
import com.example.myapplication.fragments.MagicWandsFragment;

public class WandsFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    private int currImage = 0;

    public WandsFragmentCollectionAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        MagicWandsFragment magicWandsFragment = new MagicWandsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message",position+"");
        bundle.putString("default",MainActivity.selectedMagicWand+"");
        magicWandsFragment.setArguments(bundle);

        return magicWandsFragment;
    }

    @Override
    public int getCount() {
        return MainActivity.images.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position);
    }
}

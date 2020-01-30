package com.magic.wand.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.magic.wand.fragments.MagicWordsFragment;
import com.magic.wand.loader.LocalJsonLoader;

public class WordsFragmentCollectionAdapter extends FragmentStatePagerAdapter {


    public WordsFragmentCollectionAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        MagicWordsFragment demoFragment = new MagicWordsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",LocalJsonLoader.getMagicWords().get(position).getMagicWordTitle()+"("+LocalJsonLoader.getMagicWords().get(position).getMagicWordPower()+")");
        bundle.putString("description",LocalJsonLoader.getMagicWords().get(position).getMagicWordDescription());
        demoFragment.setArguments(bundle);

        return demoFragment;
    }

    @Override
    public int getCount() {
        return LocalJsonLoader.getMagicWords().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position);
    }
}

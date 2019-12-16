package com.example.myapplication.fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WandsFragmentCollectionAdapter;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;


/**
 * A simple {@link Fragment} subclass.
 */
public class Magicwands extends Fragment implements View.OnClickListener {


    private ViewPager viewPager;
    private WandsFragmentCollectionAdapter adapter;
    private FragmentActivity myContext;
    private Button button_useit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_magicwands, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new WandsFragmentCollectionAdapter(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager = view.findViewById(R.id.pager_wands);
        viewPager.setAdapter(adapter);

        button_useit = view.findViewById(R.id.button_usewand);
        button_useit.setOnClickListener(this);
    }


    public Magicwands() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_usewand:
                Navigation.findNavController(v).navigate(MagicwandsDirections.actionMagicwandsToMagicbattle());
                break;
            default:
                break;
        }
    }
}

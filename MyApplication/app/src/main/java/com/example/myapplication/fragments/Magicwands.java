package com.example.myapplication.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
    private Magicwands.OnWandFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Magicwands.OnWandFragmentInteractionListener) {
            mListener = (Magicwands.OnWandFragmentInteractionListener) context;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_magicwands_pager, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new WandsFragmentCollectionAdapter(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager = view.findViewById(R.id.pager_wands);
        viewPager.setAdapter(adapter);

        button_useit = view.findViewById(R.id.button_usewand);
        button_useit.setOnClickListener(this);

        view.setBackgroundColor(Color.parseColor("#f2f2f2"));

    }

    public Magicwands() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_usewand:
                if (mListener != null) {
                    mListener.onSelectWandInteraction (viewPager.getCurrentItem());
                }
                break;
            default:
                break;
        }
    }

    //interface to communicate with activity
    public interface OnWandFragmentInteractionListener {
        void onSelectWandInteraction(int wandID);
    }
}

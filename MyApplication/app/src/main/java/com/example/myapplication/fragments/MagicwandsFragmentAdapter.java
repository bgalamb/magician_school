package com.example.myapplication.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.WandsFragmentCollectionAdapter;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;


/**
 * A simple {@link Fragment} subclass.
 */
public class MagicWandsFragmentAdapter extends Fragment implements View.OnClickListener , ViewPager.OnPageChangeListener {

    private static final String TAG = "MyActivityPager";
    private ViewPager viewPager;
    private WandsFragmentCollectionAdapter adapter;
    private Button button_useit;
    private TextView wandHint;
    private MagicWandsFragmentAdapter.OnWandFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MagicWandsFragmentAdapter.OnWandFragmentInteractionListener) {
            mListener = (MagicWandsFragmentAdapter.OnWandFragmentInteractionListener) context;
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
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(adapter);

        wandHint = view.findViewById(R.id.wandHint);

        button_useit = view.findViewById(R.id.button_usewand);
        button_useit.setOnClickListener(this);

        if ( viewPager.getCurrentItem() == MainActivity.selectedMagicWand){
            button_useit.setVisibility(View.INVISIBLE);
        }else{
            button_useit.setVisibility(View.VISIBLE);
        }

    }

    public MagicWandsFragmentAdapter() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_usewand:
                if (mListener != null) {
                    button_useit.setVisibility(View.INVISIBLE);
                    mListener.onSelectWandInteraction (viewPager.getCurrentItem());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
       Log.i(TAG,"current position is " + position);
       Log.i(TAG, "total score " + MainActivity.totalScore);
        wandHint.setText("");

        if (position == MainActivity.selectedMagicWand) {
                button_useit.setVisibility(View.INVISIBLE);
       } else {
          if (MainActivity.totalScore >= (position * 20)) {
              button_useit.setVisibility(View.VISIBLE);
          }else{
              if(position > 0) {
                  wandHint.setText("You need " + position * 20 + "p to unlock this wand");
              }
              button_useit.setVisibility(View.INVISIBLE);
          }
       }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //interface to communicate with activity
    public interface OnWandFragmentInteractionListener {
        void onSelectWandInteraction(int wandID);
    }


}

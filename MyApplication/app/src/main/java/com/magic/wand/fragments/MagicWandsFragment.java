package com.magic.wand.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.magic.wand.MainActivity;
import com.magic.wand.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagicWandsFragment extends Fragment {

    public MagicWandsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_magicwands, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        String message = getArguments().getString("message");
        String selectedID = getArguments().getString("default");

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageDisplay);
        imageView.setImageResource(MainActivity.images[Integer.parseInt(message)]);

    }

}

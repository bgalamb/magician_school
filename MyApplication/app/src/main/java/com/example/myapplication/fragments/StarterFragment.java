package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StarterFragment extends Fragment implements View.OnClickListener {

    private Button button_wand;
    private Button button_word;
    private Button button_battle;

    public StarterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starter, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button_word = view.findViewById(R.id.button_word);
        button_word.setOnClickListener(this);

        button_wand = view.findViewById(R.id.button_wand);
        button_wand.setOnClickListener(this);

        button_battle = view.findViewById(R.id.button_battle);
        button_battle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_word:
                Navigation.findNavController(v).navigate(StarterFragmentDirections.actionStarterFragmentToMagicwords());
                break;
            case R.id.button_wand:
                Navigation.findNavController(v).navigate(StarterFragmentDirections.actionStarterFragmentToMagicwands());
                break;
            case R.id.button_battle:
                Navigation.findNavController(v).navigate(StarterFragmentDirections.actionStarterFragmentToMagicbattle());
                break;
            default:
                break;
        }
    }
}

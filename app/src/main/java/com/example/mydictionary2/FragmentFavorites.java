package com.example.mydictionary2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class FragmentFavorites extends Fragment {

    RecyclerView rvFavorites;

    public static FragmentFavorites newInstance(String param1, String param2) {
        FragmentFavorites fragment = new FragmentFavorites();
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites_layout, container, false);

        rvFavorites = rootView.findViewById(R.id.rvFavorites);
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        AdapterFavorites adapter = new AdapterFavorites(getActivity(),helper.getWords());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        rvFavorites.setAdapter(adapter);
        rvFavorites.setLayoutManager(layoutManager);

        return rootView;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        DatabaseHelper helper = new DatabaseHelper(getActivity());
//        AdapterFavorites adapter = new AdapterFavorites(getActivity(),helper.getWords());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        rvFavorites.setAdapter(adapter);
//        rvFavorites.setLayoutManager(layoutManager);
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        AdapterFavorites adapter = new AdapterFavorites(getActivity(),helper.getWords());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFavorites.setAdapter(adapter);
        rvFavorites.setLayoutManager(layoutManager);
        } else {

        }
    }
}

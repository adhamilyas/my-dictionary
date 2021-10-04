package com.example.mydictionary2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentExplore extends Fragment {

    RecyclerView rvExplore;
    InterfaceAPI interfaceAPI;
    private EditText edtSearch;
    private ImageButton btnSearch;

    public FragmentExplore() {
    }
    public static FragmentExplore newInstance(String param1, String param2) {
        FragmentExplore fragment = new FragmentExplore();
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore_layout, container, false);

        edtSearch = rootView.findViewById(R.id.edtSearch);
        btnSearch = rootView.findViewById(R.id.btnSearch);
        rvExplore = rootView.findViewById(R.id.rvExplore);
        interfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String keyword = edtSearch.getText().toString();
                    Call<List<DataWord>> wordCall = interfaceAPI.getWord(keyword);
                    wordCall.enqueue(new Callback<List<DataWord>>() {
                        @Override
                        public void onResponse(Call<List<DataWord>> call, Response<List<DataWord>> response) {
                            if(response.isSuccessful()){
                                List<DataWord> words = response.body();
                                AdapterExplore adapter = new AdapterExplore(getActivity(),words);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                rvExplore.setAdapter(adapter);
                                rvExplore.setLayoutManager(layoutManager);
                            }
                            else{
                                Toast.makeText(getActivity(), "Something wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<DataWord>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Something wrong!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }
}

package com.example.cs310newsapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SportsFragment extends Fragment {



    private RecyclerView recyclerViewSports;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            ArrayList<News> data = (ArrayList<News>)msg.obj;
            NewsAdapter adp = new NewsAdapter((MainActivity)getContext(),data);
            recyclerViewSports.setAdapter(adp);
            recyclerViewSports.setVisibility(View.VISIBLE);

            return true;
        }
    });





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sports_fragment,null);
        recyclerViewSports = v.findViewById(R.id.recyclerViewofSports);

        recyclerViewSports.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerViewSports.setVisibility(View.INVISIBLE);

        NewsRepository repo = new NewsRepository();
        repo.getNewsByCategory(((CS310NewsApp) getActivity().getApplication()).srv, dataHandler,2);


        return v;
    }
}

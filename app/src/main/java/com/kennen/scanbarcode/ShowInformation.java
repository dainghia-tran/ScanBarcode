package com.kennen.scanbarcode;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.kennen.scanbarcode.MainActivity.myDB;
import static com.kennen.scanbarcode.MainActivity.viewPager;

public class ShowInformation extends Fragment
{
    View rootView;
    RecyclerView recyclerView;
    RVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.show_infor, container, false);

        View temp = inflater.inflate(R.layout.product_infor, container, false);

        adapter = new RVAdapter(getContext(), myDB.getAllProduct());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv_show);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }
}

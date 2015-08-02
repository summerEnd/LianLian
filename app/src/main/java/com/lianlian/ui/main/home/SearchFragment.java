package com.lianlian.ui.main.home;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lianlian.R;
import com.lianlian.adapter.SearchAdapter;
import com.lianlian.adapter.decor.ListDivider;
import com.lianlian.entity.Search;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private EditText edkeyword;
    private View search;
    private RecyclerView recycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

        edkeyword = (EditText) getActivity().findViewById(R.id.ed_keyword);
        search = getActivity().findViewById(R.id.btn_search);
        recycleView = (RecyclerView) getActivity().findViewById(R.id.recycleView);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layout);
        ColorDrawable divider = new ColorDrawable(Color.parseColor("#434850"));
        recycleView.addItemDecoration(new ListDivider(divider));
        recycleView.setAdapter(new SearchAdapter(getActivity(), new ArrayList<Search>()));
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

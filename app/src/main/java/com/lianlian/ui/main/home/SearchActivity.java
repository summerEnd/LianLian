package com.lianlian.ui.main.home;

import android.os.Bundle;

import com.lianlian.ui.BaseActivity;

/**
 * Created by Lincoln on 15/8/2.
 */
public class SearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new SearchFragment()).commit();
    }
}

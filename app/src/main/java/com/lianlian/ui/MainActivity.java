package com.lianlian.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabWidget;

import com.lianlian.R;
import com.lianlian.ui.home.HomeFragment;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout fragmentContainer;
    private RadioButton rbhome;
    private RadioButton rbcm;
    private RadioButton rbll;
    private RadioButton rbme;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);
        rbhome = (RadioButton) findViewById(R.id.rb_home);
        rbcm = (RadioButton) findViewById(R.id.rb_cm);
        rbll = (RadioButton) findViewById(R.id.rb_ll);
        rbme = (RadioButton) findViewById(R.id.rb_me);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new HomeFragment()).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

        }
    }


}

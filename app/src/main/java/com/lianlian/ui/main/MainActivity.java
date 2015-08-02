package com.lianlian.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.lianlian.ui.main.chumo.ChumoFragment;
import com.lianlian.ui.main.home.HomeFragment;
import com.lianlian.ui.main.home.ReceiveFriends;
import com.lianlian.ui.main.plus.PlusActionChooser;
import com.lianlian.ui.main.lian.LianFragment;
import com.lianlian.ui.main.me.MeFragment;
import com.lianlian.utils.FragmentSwitcher;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    FragmentSwitcher switcher;
    SparseArray<Class<? extends Fragment>> fragments = new SparseArray<>();

    {
        fragments.append(R.id.rb_home, HomeFragment.class);
        fragments.append(R.id.rb_cm, ChumoFragment.class);
        fragments.append(R.id.rb_ll, LianFragment.class);
        fragments.append(R.id.rb_me, MeFragment.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        switcher = new FragmentSwitcher(this,R.id.fragmentContainer);
        switcher.put(R.id.rb_home, HomeFragment.class);
        switcher.put(R.id.rb_cm, ChumoFragment.class);
        switcher.put(R.id.rb_ll, LianFragment.class);
        switcher.put(R.id.rb_me, MeFragment.class);
        findViewById(R.id.plusAction).setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.rb_home);
        new ReceiveFriends().show(getSupportFragmentManager(), "bf");

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switcher.showFragmentWith(getSupportFragmentManager(), checkedId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plusAction: {
                new PlusActionChooser(this).showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                break;
            }
        }
    }
}

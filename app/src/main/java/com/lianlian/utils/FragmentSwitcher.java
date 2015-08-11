package com.lianlian.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import com.lianlian.R;

/**
 * Created by Lincoln on 15/7/21.
 */
public class FragmentSwitcher {

    SparseArray<Class<? extends Fragment>> fragments = new SparseArray<>();
    Fragment curFragment;
    private Activity context;
    private int containerId;

    public FragmentSwitcher(Activity context) {
        this(context, R.id.fragmentContainer);
    }

    public FragmentSwitcher(Activity context, int containerId) {
        this.context = context;
        this.containerId = containerId;
    }

    public void put(int id, Class<? extends Fragment> f) {
        fragments.append(id, f);
    }


    public void showFragmentWith(FragmentManager fm, int checkedId) {
        String tag = String.valueOf(checkedId);
        Fragment fragment = fm.findFragmentByTag(tag);

        if (fragment == null) {
            Class<? extends Fragment> fragmentClass = fragments.get(checkedId);

            fragment = Fragment.instantiate(context, fragmentClass.getName());
        }
        FragmentTransaction ft = fm.beginTransaction();

        if (!fragment.isAdded()) {
            ft.add(containerId, fragment, tag);
        } else {
            ft.show(fragment);
        }
        if (curFragment != null && curFragment != fragment) {
            ft.hide(curFragment);
        }
        ft.commit();
        fm.executePendingTransactions();
        curFragment = fragment;
    }
}

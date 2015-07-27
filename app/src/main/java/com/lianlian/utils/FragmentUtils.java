package com.lianlian.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by Lincoln on 15/7/21.
 */
public class FragmentUtils {
    public static void switchFragment(FragmentManager manager, Fragment show, Fragment hide, int containerViewId) {
        FragmentTransaction ft = manager.beginTransaction();
        if (!show.isAdded()) {
            ft.add(containerViewId, show);
        } else if (show.isHidden()) {
            ft.show(show);
        }

        if (hide.isAdded() && !hide.isHidden()) {
            ft.hide(hide);
        }


        ft.commit();
    }
}

package com.lianlian.ui.test;

import android.app.Activity;

import com.lianlian.ui.login.AddInfoActivity1;
import com.lianlian.ui.login.AddInfoActivity2;
import com.lianlian.ui.login.RegisterActivity;
import com.sp.lib.activity.DEBUGActivity;
import com.sp.lib.activity.STestActivity;

import java.util.List;

public class ActivityList extends STestActivity {

    @Override
    protected void addTest(List<Class<? extends Activity>> activities) {
        activities.add(AddInfoActivity1.class);
        activities.add(AddInfoActivity2.class);
        activities.add(RegisterActivity.class);
        activities.add(DEBUGActivity.class);
    }
}

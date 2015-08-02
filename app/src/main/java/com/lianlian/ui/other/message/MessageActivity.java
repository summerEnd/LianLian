package com.lianlian.ui.other.message;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;
import com.lianlian.utils.FragmentSwitcher;
import com.sp.lib.widget.nav.title.ITab;
import com.sp.lib.widget.nav.title.PageStrip;

public class MessageActivity extends BaseActivity {


    private PageStrip pagerTitle;
    FragmentSwitcher switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_layout);
        switcher = new FragmentSwitcher(this,R.id.container);
        switcher.put(R.id.tab_msg_chumo, MsgChuMoFragment.class);
        switcher.put(R.id.tab_msg_ll, MsgLianFragment.class);
        switcher.put(R.id.tab_msg_liaotian, MsgChatFragment.class);
        switcher.put(R.id.tab_msg_notice, MsgNoticeFragment.class);
        initialize();
    }

    private void initialize() {

        pagerTitle = (PageStrip) findViewById(R.id.pagerTitle);
        pagerTitle.setTabListener(new PageStrip.onTabChangeListener() {
            @Override
            public void onSelected(int position, ITab tab) {
                switcher.showFragmentWith(getSupportFragmentManager(), tab.getView().getId());
            }
        });
        switcher.showFragmentWith(getSupportFragmentManager(), pagerTitle.getTab(0).getView().getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                OptionsDialog dialog = new OptionsDialog();
                dialog.show(getSupportFragmentManager(), "");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_msg, menu);
        return true;
    }
}

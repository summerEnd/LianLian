package com.lianlian.ui.main.home;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;

import java.util.ArrayList;

public class SendMsgActivity extends BaseActivity {

    private ListView listView;
    private EditText editMsg;
    private ImageView send;
    ArrayList<String> msgs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
        initialize();
    }


    private void initialize() {

        for (int i = 0; i < 5; i++) {
            msgs.add("来一发"+i);
        }
        listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(new MsgAdapter());
        editMsg = (EditText) findViewById(R.id.editMsg);
        send = (ImageView) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgs.add(editMsg.getText().toString());
                editMsg.setText("");
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        });

    }

    private class MsgAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return msgs.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView==null){
                convertView= LayoutInflater.from(SendMsgActivity.this).inflate(R.layout.msg_item,parent,false);
            }
            ((AppCompatCheckedTextView) convertView).setText(msgs.get(position));
            return convertView;
        }
    }
}

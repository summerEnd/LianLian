package com.lianlian.ui.login;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.adapter.LabelAdapter;
import com.lianlian.entity.Label;
import com.lianlian.ui.BaseActivity;
import com.sp.lib.common.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class AddInfoActivity2 extends BaseActivity {

    private RecyclerView gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_activity2);
        initialize();
    }

    private void initialize() {

        gridLayout = (RecyclerView) findViewById(R.id.gridLayout);
        GridLayoutManager layout = new GridLayoutManager(this, 4);
        gridLayout.setLayoutManager(layout);
        ArrayList<Label> labels=new ArrayList<>();
        RandomUtil randomUtil=new RandomUtil();
        for (int i = 0; i < 50; i++) {
            Label label=new Label();
            label.name = randomUtil.nextString(1, 20);
            labels.add(label);
        }
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddInfoActivity2.this, AddInfoActivity3.class));

            }
        });


        gridLayout.setAdapter(new LabelAdapter(this,labels));
        gridLayout.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(5,5,5,5);
            }
        });
    }

    void add(List<String> label){
        int start=0;

        for (String str:label){
            Button button=new Button(this);
            button.setText(str);
            GridLayout.LayoutParams lp=new GridLayout.LayoutParams();
            lp.setGravity(Gravity.FILL);
            if (str.length()>5){

                lp.columnSpec=GridLayout.spec(start,2);
                start+=2;
            }else{
                lp.columnSpec=GridLayout.spec(start,1);
                start+=1;
            }
            gridLayout.addView(button,lp);
        }
    }

}

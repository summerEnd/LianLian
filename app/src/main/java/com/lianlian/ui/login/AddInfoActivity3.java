package com.lianlian.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lianlian.R;

public class AddInfoActivity3 extends AddInfoActivity2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView labelName = (TextView) findViewById(R.id.labelName);
        labelName.setText(R.string.sure);
        Button button= (Button) findViewById(R.id.btn_sure);
        button.setText(R.string.sure);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

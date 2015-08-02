package com.lianlian.ui.main.plus;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.lianlian.R;

/**
 * Created by Lincoln on 15/8/2.
 *
 */
public class PlusActionChooser extends PopupWindow implements View.OnClickListener{


    public PlusActionChooser(Context context) {
        super(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.plus_window, null);

        layout.findViewById(R.id.goMeet).setOnClickListener(this);
        layout.findViewById(R.id.sendLianLian).setOnClickListener(this);
        layout.findViewById(R.id.invite).setOnClickListener(this);
        layout.findViewById(R.id.dismiss).setOnClickListener(this);

        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
        setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(layout);
    }



    @Override
    public void onClick(View v) {
        Context context=v.getContext();
        switch (v.getId()){
            case R.id.goMeet:{
                context.startActivity(new Intent(context,MeetActivity.class));

                break;
            }
            case R.id.sendLianLian:{
                context.startActivity(new Intent(context,SendLianLian.class));

                break;
            }
            case R.id.invite:{
                context.startActivity(new Intent(context,InviteFriends.class));
                break;
            }
            case R.id.dismiss:{
                dismiss();
                break;
            }
        }
    }
}

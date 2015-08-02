package com.lianlian.ui.main.home;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lianlian.R;

/**
 * Created by Lincoln on 15/7/23.
 */
public class ReceiveFriends extends DialogFragment implements View.OnClickListener{
    private TextView leaveTime;
    private RelativeLayout infoLayout;
    private TextView location;
    private ImageView close;
    private TextView userName;
    private Button button;
    private ImageView dismiss;
    private ImageView avatar;
    private TextView paoyounum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.receive_friends, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        assert view != null;
        leaveTime = (TextView) view.findViewById(R.id.leaveTime);
        infoLayout = (RelativeLayout) view.findViewById(R.id.infoLayout);
        location = (TextView) view.findViewById(R.id.location);
        close = (ImageView) view.findViewById(R.id.close);
        userName = (TextView) view.findViewById(R.id.userName);
        button = (Button) view.findViewById(R.id.button);
        dismiss = (ImageView) view.findViewById(R.id.chatTa);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        paoyounum = (TextView) view.findViewById(R.id.paoyou_num);

        close.setOnClickListener(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:{
                dismiss();
                break;
            }
            case R.id.button:{
                break;
            }
        }
    }
}

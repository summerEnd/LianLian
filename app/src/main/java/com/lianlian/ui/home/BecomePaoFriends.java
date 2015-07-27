package com.lianlian.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lianlian.R;
import com.lianlian.utils.ImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Lincoln on 15/7/25.
 */
public class BecomePaoFriends extends DialogFragment implements View.OnClickListener{
    private ImageView bgimage;
    private ImageView close;
    private ImageView imageView2;
    private ImageView avatar1;
    private ImageView avatar2;
    private RelativeLayout avatarLayout;
    private TextView userName;
    private TextView location;
    private ImageView chatTa;
    private TextView share;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.become_paoyou, container, false);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();
        assert v != null;

        bgimage = (ImageView) v.findViewById(R.id.bg_image);
        close = (ImageView) v.findViewById(R.id.close);
        imageView2 = (ImageView) v.findViewById(R.id.imageView2);
        avatar1 = (ImageView) v.findViewById(R.id.avatar1);
        avatar2 = (ImageView) v.findViewById(R.id.avatar2);
        avatarLayout = (RelativeLayout) v.findViewById(R.id.avatarLayout);
        userName = (TextView) v.findViewById(R.id.userName);
        location = (TextView) v.findViewById(R.id.location);
        chatTa = (ImageView) v.findViewById(R.id.chatTa);
        share = (TextView) v.findViewById(R.id.share);

        close.setOnClickListener(this);
        chatTa.setOnClickListener(this);

        String url = "http://f.hiphotos.baidu.com/image/pic/item/fc1f4134970a304e006c1b7ed2c8a786c8175ce3.jpg";
        int size=getResources().getDimensionPixelSize(R.dimen.avatarSize);
        ImageLoader.getInstance().displayImage("",avatar1, ImageOptions.getRound(size));
        ImageLoader.getInstance().displayImage(url,avatar2, ImageOptions.getRound(size));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:{
                dismiss();
                break;
            }
            case R.id.chatTa:{
                startActivity(new Intent(getActivity(),SendMsgActivity.class));
                break;
            }
        }
    }
}

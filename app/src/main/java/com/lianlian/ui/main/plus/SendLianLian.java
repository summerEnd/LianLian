package com.lianlian.ui.main.plus;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lianlian.R;
import com.lianlian.ui.BaseActivity;

public class SendLianLian extends BaseActivity {
    //倒计时
    private TextView m_obj_countDown = null;

    //播放
    private FrameLayout m_obj_out = null;
    private ImageView m_obj_replay = null;
    private ImageView m_obj_play = null;

    //点击上传头像
    private RelativeLayout m_obj_upLoadPic = null;

    //需要显示的头像
    private ImageView m_obj_showPic = null;

    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_lian_lian);
        init();
    }

    //显示播放控件
    private void showPlay(boolean state) {
        if (!state) {
            m_obj_out.setVisibility(View.GONE);
            m_obj_replay.setVisibility(View.GONE);
            m_obj_play.setVisibility(View.GONE);
        } else {
            m_obj_out.setVisibility(View.VISIBLE);
            m_obj_replay.setVisibility(View.VISIBLE);
            m_obj_play.setVisibility(View.VISIBLE);
        }
    }

    //显示倒计时控件
    private void showCountDown(boolean state) {
        if (!state) {
            m_obj_countDown.setVisibility(View.GONE);
        } else {
            m_obj_countDown.setVisibility(View.VISIBLE);
        }
    }

    private void init() {

        m_obj_countDown = (TextView) findViewById(R.id.id_lianlian_line3_tv3);

        m_obj_out = (FrameLayout) findViewById(R.id.id_lianlian_framelayout1);
        m_obj_replay = (ImageView) findViewById(R.id.id_linelian4_iv1);
        m_obj_play = (ImageView) findViewById(R.id.id_linelian4_iv3);

        m_obj_upLoadPic = (RelativeLayout) findViewById(R.id.id_lianlian_line1_relative2_relative1);

        m_obj_showPic = (ImageView) findViewById(R.id.id_lianlian_line1_relative2_relative1_iv1);
        //点击上传头像
        m_obj_upLoadPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //m_obj_upLoadPic.setVisibility(View.GONE);
                //m_obj_showPic.setVisibility(View.VISIBLE);
                BabyPopWindow wnd = new BabyPopWindow(SendLianLian.this, R.layout.adapter_popwindow, Gravity.BOTTOM);
                wnd.showAsDropDown(v);
                wnd.registerPic(new BabyPopWindow.PicChoice() {

                    @Override
                    public void xiangce() {
                        m_obj_upLoadPic.setVisibility(View.GONE);
                        m_obj_showPic.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void paizhao() {
                        m_obj_upLoadPic.setVisibility(View.GONE);
                        m_obj_showPic.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        m_obj_showPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BabyPopWindow wnd = new BabyPopWindow(SendLianLian.this, R.layout.adapter_popwindow, Gravity.BOTTOM);
                wnd.showAsDropDown(v);
                wnd.registerPic(new BabyPopWindow.PicChoice() {

                    @Override
                    public void xiangce() {
                        m_obj_upLoadPic.setVisibility(View.GONE);
                        m_obj_showPic.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void paizhao() {
                        m_obj_upLoadPic.setVisibility(View.GONE);
                        m_obj_showPic.setVisibility(View.VISIBLE);
                    }
                });

            }
        });

        m_obj_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "开始发布", Toast.LENGTH_SHORT).show();
            }
        });

        m_obj_replay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "重播", Toast.LENGTH_SHORT).show();
            }
        });

        m_obj_play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "播放", Toast.LENGTH_SHORT).show();

            }
        });
        //切换
        findViewById(R.id.id_lianlian_test_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (0 == num) {
                    showCountDown(false);
                    showPlay(false);
                } else if (1 == num) {
                    showCountDown(true);
                    showPlay(false);
                } else if (2 == num) {
                    showCountDown(false);
                    showPlay(true);
                } else if (3 == num) {

                }
                num++;
                if (num >= 4) {
                    num = 0;
                }
            }
        });

    }
}

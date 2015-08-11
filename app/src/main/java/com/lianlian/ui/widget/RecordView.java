package com.lianlian.ui.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianlian.R;

import java.io.IOException;

/**
 * Created by Lincoln on 15/8/9.
 */
public class RecordView extends FrameLayout implements View.OnClickListener {

    private static final String LOG_TAG = RecordView.class.getSimpleName();

    private class OnRecordTouchListener implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    startRecord();
                    break;
                }

                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP: {
                    finishRecord();
                    break;
                }
            }
            return true;
        }
    }


    private ImageView replay;
    private ImageView record;
    private ImageView tryPlay;
    private ImageView publish;
    private TextView tv_seconds;

    int maxSeconds = 10;
    int curSecond;
    private boolean isRecording;
    MediaRecorder mRecorder;
    private MediaPlayer mPlayer = null;

    //语音文件保存路径
    private String file = null;
    private Runnable timeTicker = new Runnable() {
        @Override
        public void run() {

            if (!isRecording) {
                //播放已经停止
                removeCallbacks(this);
                return;
            }
            if (curSecond > 0) {
                showSecond(curSecond);
                curSecond--;
                postDelayed(this, 1000);
            } else {
                finishRecord();
            }
        }
    };

    public RecordView(Context context) {
        super(context);
        init(context);
    }

    public RecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public RecordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.record_layout, this, true);
        replay = (ImageView) findViewById(R.id.replay);
        record = (ImageView) findViewById(R.id.record);
        tryPlay = (ImageView) findViewById(R.id.tryPlay);
        publish = (ImageView) findViewById(R.id.publish);
        tv_seconds = (TextView) findViewById(R.id.tv_seconds);

        replay.setOnClickListener(this);
        tryPlay.setOnClickListener(this);
        publish.setOnClickListener(this);
        record.setOnTouchListener(new OnRecordTouchListener());

        //设置sdcard的路径
        file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp";
        prepare();
    }

    private void prepare() {
        replay.setVisibility(INVISIBLE);
        tryPlay.setVisibility(INVISIBLE);
        publish.setVisibility(INVISIBLE);
        tv_seconds.setVisibility(VISIBLE);
        record.setImageResource(R.drawable.record_wait_start);
        record.setOnTouchListener(new OnRecordTouchListener());
        showSecond(maxSeconds);
    }

    private void onRecordStart() {
        replay.setVisibility(INVISIBLE);
        tryPlay.setVisibility(INVISIBLE);
        publish.setVisibility(INVISIBLE);
        tv_seconds.setVisibility(VISIBLE);
        record.setImageResource(R.drawable.record_doing);
        showSecond(maxSeconds);
        isRecording = true;
    }

    private void onRecordDone() {
        replay.setVisibility(VISIBLE);
        tryPlay.setVisibility(VISIBLE);
        publish.setVisibility(VISIBLE);
        record.setImageResource(R.drawable.record_done);
        record.setOnTouchListener(null);
        tv_seconds.setVisibility(INVISIBLE);
        isRecording = false;
    }


    private void showSecond(int secondLeft) {
        tv_seconds.setText(secondLeft + "\"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.replay: {
                prepare();
                break;
            }
            case R.id.record: {
                if (!isRecording) {
                    startRecord();
                }
                break;
            }
            case R.id.tryPlay: {
                startPlay();
                break;
            }
            case R.id.publish: {
                finishPlay();
                break;
            }
        }
    }

    private void finishPlay() {
        try {
            mPlayer.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPlay() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        try {
            mPlayer.setDataSource(file);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "播放失败");
        }
    }


    /**
     * 开始录音
     */
    public final void startRecord() {
        onRecordStart();
        curSecond = maxSeconds;
        post(timeTicker);

        if (mRecorder == null) {
            mRecorder = new MediaRecorder();

        }
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(file);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        mRecorder.start();
    }

    /**
     * 结束录音
     */
    public final void finishRecord() {
        onRecordDone();
        try {
            mRecorder.stop();
            mRecorder.release();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}

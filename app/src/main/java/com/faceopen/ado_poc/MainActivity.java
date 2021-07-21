package com.faceopen.ado_poc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;


import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mikhaellopez.circleview.CircleView;

public class MainActivity extends AppCompatActivity {
    BottomSheetBehavior sheetBehavior;
    RelativeLayout layoutBottomSheet;
    private Context mContext;
    public CircleView circleView1;
    public CircleView circleView2;
    public CircleView circleView3;
    public ImageView ado1, ado2;
    public FrameLayout frameLayout;

    private ConstraintLayout cl_highlightA;
    private ConstraintLayout cl_highlightB;
    private TextView thanksText;
    private ConstraintLayout cl_video ;
    private VideoView videoView1 ;
    private ConstraintLayout cl_connect;
    private ImageView startAdo;
    private TextView firstText;
    private TextView secondText;
    private ConstraintLayout cl_scan;
    private RipplePulseLayout scanAnimation;
    private TextView btnNext;
    private ConstraintLayout cl_connectDevice;
    private TextView fistText;
    private Guideline guideline;
    private ImageView adoImage;
    private TextView secondText12;
    private ImageView batteryIcon;
    private Button continueButton;
    private TextView retryText;
    private ConstraintLayout cl_connecting;
    private Guideline guideline1;
    private ImageView deviceImage;
    private DottedProgressBar progress;
    private ImageView adoImage1;
    private TextView text_pair_warning;
    private TextView textConnect;
    private ConstraintLayout cl_installation;
    private TextView tvInstall;
    private ImageView adoImage2;
    private TextView tvDetail;
    private TextView tvDetail_a;
    private ImageView ivDetail_b;
    private TextView tvDetail_b;
    private Button bt_continue_install;
    private TextView tvDetail_c;
    private ConstraintLayout cl_clamping;
    private TextView tvClamp;
    private TextView tvClamp_Detail;
    private ImageView ivDoor;
    private ImageView iv_clamp_detail_b;
    private TextView tvclamp_Detail;
    private Button bt_continue_clamp;
    private ConstraintLayout cl_autoFasten;
    private TextView tvAuto;
    private TextView tv_auto_detail;
    private Guideline gl_auto;
    private ImageView ivAutoDoor;
    private TextView tv_auto_steps;
    private TextView tv_auto_steps_1;
    private TextView tv_auto_steps_2;
    private CircleProgressBar pb_vertical;
    private CircleProgressBar pb_horizontal;
    private TextView tv_auto_detail_a;
    private TextView tv_auto_manual_clamp;
    private ConstraintLayout cl_manualFasten;
    private TextView tvManual;
    private Guideline gl_manual;
    private ImageView ivManualDoor;
    private TextView tv_up;
    private ImageView iv_up;
    private ImageView iv_down;
    private TextView tv_down;
    private TextView tv_manual_continue;
    private Button bt_continue_manual_clamp;
    private ConstraintLayout cl_calibration;
    private TextView tv_cal_head;
    private TextView tv_cal_detail;
    private TextView tv_cal_serial_a;
    private TextView tv_cal_detail_a;
    private TextView tv_cal_serial_b;
    private TextView tv_cal_detail_b;
    private TextView tv_cal_serial_c;
    private TextView tv_cal_detail_c;
    private ConstraintLayout cl_closeCal;
    private TextView tv_cal;
    private TextView tv_cal_sub;
    private ImageView iv_close_door_preview;
    private Guideline gl_close;
    private ImageView iv_close_door;
    private TextView tv_close_forward;
    private ImageView iv_close_forward;
    private ImageView iv_close_backward;
    private TextView tv_close_backward;
    private TextView tv_close_icon_a;
    private TextView tv_close_icon_b;
    private TextView tv_close_continue;
    private Button bt_continue_close_calibration;
    private ConstraintLayout cl_openCal;
    private TextView tv_open_cal;
    private TextView tv_open_cal_sub;
    private ImageView iv_open_door_preview;
    private Guideline gl_open;
    private ImageView iv_open_door;
    private TextView tv_open_forward;
    private ImageView iv_open_forward;
    private ImageView iv_open_backward;
    private TextView tv_open_backward;
    private TextView tv_open_icon_a;
    private TextView tv_open_icon_b;
    private TextView tv_open_continue;
    private Button bt_continue_open_calibration;
    private ConstraintLayout cl_test;
    private TextView tv_test_detail;
    private Button bt_continue_testing;
    private TextView tv_test_skip;
    private ConstraintLayout cl_operation_test;
    private TextView tv_test;
    private TextView tv_test_sub;
    private Guideline gl_test;
    private ImageView iv_test_door;
    private Switch labeledSwitch;
    private TextView swClose;
    private TextView swOpen;
    private TextView tv_reCalibrate;
    private Button bt_continue_test;
    private ConstraintLayout cl_done;
    private TextView tv_done;
    private ImageView iv_close_bot;

    DottedProgressBar bar;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_bottom_sheet = findViewById(R.id.btn_bottom_sheet);
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        circleView1 = findViewById(R.id.circleView1);
        circleView2 = findViewById(R.id.circleView2);
        circleView3 = findViewById(R.id.circleView3);
        frameLayout = findViewById(R.id.frameLayout);
        bar = findViewById(R.id.progress);
        ado1 = findViewById(R.id.ado1);
        ado2 = findViewById(R.id.ado2);
        iniViews();
        screenChange();
        btn_bottom_sheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
//                        BottomSheetDialog bottomSheet = new BottomSheetDialog();
//                        bottomSheet.show(getSupportFragmentManager(),
//                                "ModalBottomSheet");

                        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            //btnBottomSheet.setText("Close sheet");
                        } else {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            //btnBottomSheet.setText("Expand sheet");
                        }
                    }
                });


        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        final Handler handler=new Handler();
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        PulseAnimation.newInstance(MainActivity.this).startRippleAnimation(circleView1, circleView2, circleView3);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //PulseAnimation.newInstance(MainActivity.this).foundDevice(ado1);
                                PulseAnimation.newInstance(MainActivity.this).createDevices(MainActivity.this,frameLayout);
                            }
                        },1000);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        Runnable run = new Runnable(){
            @Override
            public void run() {
                bar.startProgress();
            }
        };
        Handler han = new Handler();
        han.postAtTime(run, 100);
    }

    private void iniViews() {
        cl_highlightA = (ConstraintLayout) findViewById(R.id.cl_highlightA);
        cl_highlightB = (ConstraintLayout) findViewById(R.id.cl_highlightB);
        thanksText = (TextView) findViewById(R.id.thanksText);
        cl_video = (ConstraintLayout) findViewById(R.id.cl_video );
        videoView1 = (VideoView) findViewById(R.id.videoView1) ;
        cl_connect = (ConstraintLayout) findViewById(R.id.cl_connect);
        startAdo = (ImageView) findViewById(R.id.startAdo);
        firstText = (TextView) findViewById(R.id.firstText);
        secondText = (TextView) findViewById(R.id.secondText);
        cl_scan = (ConstraintLayout) findViewById(R.id.cl_scan);
        scanAnimation = (RipplePulseLayout) findViewById(R.id.scanAnimation);
        btnNext = (TextView) findViewById(R.id.btnNext);
        cl_connectDevice = (ConstraintLayout) findViewById(R.id.cl_connectDevice);
        fistText = (TextView) findViewById(R.id.fistText);
        guideline = (Guideline) findViewById(R.id.guideline);
        adoImage = (ImageView) findViewById(R.id.adoImage);
        secondText12 = (TextView) findViewById(R.id.secondText12);
        batteryIcon = (ImageView) findViewById(R.id.batteryIcon);
        continueButton = (Button) findViewById(R.id.continueButton);
        retryText = (TextView) findViewById(R.id.retryText);
        cl_connecting = (ConstraintLayout) findViewById(R.id.cl_connecting);
        guideline1 = (Guideline) findViewById(R.id.guideline1);
        deviceImage = (ImageView) findViewById(R.id.deviceImage);
        progress = (DottedProgressBar) findViewById(R.id.progress);
        adoImage1 = (ImageView) findViewById(R.id.adoImage1);
        text_pair_warning = (TextView) findViewById(R.id.text_pair_warning);
        textConnect = (TextView) findViewById(R.id.textConnect);
        cl_installation = (ConstraintLayout) findViewById(R.id.cl_installation);
        tvInstall = (TextView) findViewById(R.id.tvInstall);
        adoImage2 = (ImageView) findViewById(R.id.adoImage2);
        tvDetail = (TextView) findViewById(R.id.tvDetail);
        tvDetail_a = (TextView) findViewById(R.id.tvDetail_a);
        ivDetail_b = (ImageView) findViewById(R.id.ivDetail_b);
        tvDetail_b = (TextView) findViewById(R.id.tvDetail_b);
        bt_continue_install = (Button) findViewById(R.id.bt_continue_install);
        tvDetail_c = (TextView) findViewById(R.id.tvDetail_c);
        cl_clamping = (ConstraintLayout) findViewById(R.id.cl_clamping);
        tvClamp = (TextView) findViewById(R.id.tvClamp);
        tvClamp_Detail = (TextView) findViewById(R.id.tvClamp_Detail);
        ivDoor = (ImageView) findViewById(R.id.ivDoor);
        iv_clamp_detail_b = (ImageView) findViewById(R.id.iv_clamp_detail_b);
        tvclamp_Detail = (TextView) findViewById(R.id.tvclamp_Detail);
        bt_continue_clamp = (Button) findViewById(R.id.bt_continue_clamp);
        cl_autoFasten = (ConstraintLayout) findViewById(R.id.cl_autoFasten);
        tvAuto = (TextView) findViewById(R.id.tvAuto);
        tv_auto_detail = (TextView) findViewById(R.id.tv_auto_detail);
        gl_auto = (Guideline) findViewById(R.id.gl_auto);
        ivAutoDoor = (ImageView) findViewById(R.id.ivAutoDoor);
        tv_auto_steps = (TextView) findViewById(R.id.tv_auto_steps);
        tv_auto_steps_1 = (TextView) findViewById(R.id.tv_auto_steps_1);
        tv_auto_steps_2 = (TextView) findViewById(R.id.tv_auto_steps_2);
        pb_vertical = (CircleProgressBar) findViewById(R.id.pb_vertical);
        pb_horizontal = (CircleProgressBar) findViewById(R.id.pb_horizontal);
        tv_auto_detail_a = (TextView) findViewById(R.id.tv_auto_detail_a);
        tv_auto_manual_clamp = (TextView) findViewById(R.id.tv_auto_manual_clamp);
        cl_manualFasten = (ConstraintLayout) findViewById(R.id.cl_manualFasten);
        tvManual = (TextView) findViewById(R.id.tvManual);
        gl_manual = (Guideline) findViewById(R.id.gl_manual);
        ivManualDoor = (ImageView) findViewById(R.id.ivManualDoor);
        tv_up = (TextView) findViewById(R.id.tv_up);
        iv_up = (ImageView) findViewById(R.id.iv_up);
        iv_down = (ImageView) findViewById(R.id.iv_down);
        tv_down = (TextView) findViewById(R.id.tv_down);
        tv_manual_continue = (TextView) findViewById(R.id.tv_manual_continue);
        bt_continue_manual_clamp = (Button) findViewById(R.id.bt_continue_manual_clamp);
        cl_calibration = (ConstraintLayout) findViewById(R.id.cl_calibration);
        tv_cal_head = (TextView) findViewById(R.id.tv_cal_head);
        tv_cal_detail = (TextView) findViewById(R.id.tv_cal_detail);
        tv_cal_serial_a = (TextView) findViewById(R.id.tv_cal_serial_a);
        tv_cal_detail_a = (TextView) findViewById(R.id.tv_cal_detail_a);
        tv_cal_serial_b = (TextView) findViewById(R.id.tv_cal_serial_b);
        tv_cal_detail_b = (TextView) findViewById(R.id.tv_cal_detail_b);
        tv_cal_serial_c = (TextView) findViewById(R.id.tv_cal_serial_c);
        tv_cal_detail_c = (TextView) findViewById(R.id.tv_cal_detail_c);
        cl_closeCal = (ConstraintLayout) findViewById(R.id.cl_closeCal);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        tv_cal_sub = (TextView) findViewById(R.id.tv_cal_sub);
        iv_close_door_preview = (ImageView) findViewById(R.id.iv_close_door_preview);
        gl_close = (Guideline) findViewById(R.id.gl_close);
        iv_close_door = (ImageView) findViewById(R.id.iv_close_door);
        tv_close_forward = (TextView) findViewById(R.id.tv_close_forward);
        iv_close_forward = (ImageView) findViewById(R.id.iv_close_forward);
        iv_close_backward = (ImageView) findViewById(R.id.iv_close_backward);
        tv_close_backward = (TextView) findViewById(R.id.tv_close_backward);
        tv_close_icon_a = (TextView) findViewById(R.id.tv_close_icon_a);
        tv_close_icon_b = (TextView) findViewById(R.id.tv_close_icon_b);
        tv_close_continue = (TextView) findViewById(R.id.tv_close_continue);
        bt_continue_close_calibration = (Button) findViewById(R.id.bt_continue_close_calibration);
        cl_openCal = (ConstraintLayout) findViewById(R.id.cl_openCal);
        tv_open_cal = (TextView) findViewById(R.id.tv_open_cal);
        tv_open_cal_sub = (TextView) findViewById(R.id.tv_open_cal_sub);
        iv_open_door_preview = (ImageView) findViewById(R.id.iv_open_door_preview);
        gl_open = (Guideline) findViewById(R.id.gl_open);
        iv_open_door = (ImageView) findViewById(R.id.iv_open_door);
        tv_open_forward = (TextView) findViewById(R.id.tv_open_forward);
        iv_open_forward = (ImageView) findViewById(R.id.iv_open_forward);
        iv_open_backward = (ImageView) findViewById(R.id.iv_open_backward);
        tv_open_backward = (TextView) findViewById(R.id.tv_open_backward);
        tv_open_icon_a = (TextView) findViewById(R.id.tv_open_icon_a);
        tv_open_icon_b = (TextView) findViewById(R.id.tv_open_icon_b);
        tv_open_continue = (TextView) findViewById(R.id.tv_open_continue);
        bt_continue_open_calibration = (Button) findViewById(R.id.bt_continue_open_calibration);
        cl_test = (ConstraintLayout) findViewById(R.id.cl_test);
        tv_test_detail = (TextView) findViewById(R.id.tv_test_detail);
        bt_continue_testing = (Button) findViewById(R.id.bt_continue_testing);
        tv_test_skip = (TextView) findViewById(R.id.tv_test_skip);
        cl_operation_test = (ConstraintLayout) findViewById(R.id.cl_operation_test);
        tv_test = (TextView) findViewById(R.id.tv_test);
        tv_test_sub = (TextView) findViewById(R.id.tv_test_sub);
        gl_test = (Guideline) findViewById(R.id.gl_test);
        iv_test_door = (ImageView) findViewById(R.id.iv_test_door);
        labeledSwitch = (Switch) findViewById(R.id.labeledSwitch);
        swClose = (TextView) findViewById(R.id.swClose);
        swOpen = (TextView) findViewById(R.id.swOpen);
        tv_reCalibrate = (TextView) findViewById(R.id.tv_reCalibrate);
        bt_continue_test = (Button) findViewById(R.id.bt_continue_test);
        cl_done = (ConstraintLayout) findViewById(R.id.cl_done);
        tv_done = (TextView) findViewById(R.id.tv_done);
        iv_close_bot = (ImageView) findViewById(R.id.iv_close_bot);
    }


    private void screenChange(){
        new CountDownTimer(35000, 1000) {
            public void onTick(long millisUntilFinished) {
                count++;
                Log.d("XXX", "COUNT XXX" + count);
                if(count == 1){
                    cl_highlightA.setVisibility(View.VISIBLE);
                }
                if(count == 3){
                    cl_highlightA.setVisibility(View.GONE);
                    cl_highlightB.setVisibility(View.VISIBLE);
                }
                if(count == 4){
                    cl_highlightB.setVisibility(View.GONE);
                    cl_video.setVisibility(View.VISIBLE);
                    videoView1.setBackgroundColor(Color.WHITE);
                    String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.testvideo;
                    Uri uri = Uri.parse(uriPath);
                    videoView1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            videoView1.setVideoURI(uri);
                        }
                    }, 100);

                    videoView1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            videoView1.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }, 1000);
                    videoView1.requestFocus();
                    videoView1.start();


                }
                if(count == 10){
                    cl_video.setVisibility(View.GONE);
                    cl_connect.setVisibility(View.VISIBLE);
                }

                if(count == 11){
                    cl_connect.setVisibility(View.GONE);
                    cl_scan.setVisibility(View.VISIBLE);
                }
                if(count == 13){
                    cl_scan.setVisibility(View.GONE);
                    cl_connectDevice.setVisibility(View.VISIBLE);
                }
                if(count == 15){
                    cl_connectDevice.setVisibility(View.GONE);
                    cl_connecting.setVisibility(View.VISIBLE);
                }
                if(count == 17){
                    cl_connecting.setVisibility(View.GONE);
                    cl_installation.setVisibility(View.VISIBLE);
                }
                if(count == 19){
                    cl_installation.setVisibility(View.GONE);
                    cl_clamping.setVisibility(View.VISIBLE);
                }
                if(count == 21){
                    cl_clamping.setVisibility(View.GONE);
                    cl_autoFasten.setVisibility(View.VISIBLE);
                }
                if(count == 23){
                    cl_autoFasten.setVisibility(View.GONE);
                    cl_manualFasten.setVisibility(View.VISIBLE);
                }
                if(count == 25){
                    cl_manualFasten.setVisibility(View.GONE);
                    cl_calibration.setVisibility(View.VISIBLE);
                }
                if(count == 27){
                    cl_calibration.setVisibility(View.GONE);
                    cl_closeCal.setVisibility(View.VISIBLE);
                }
                if(count == 29){
                    cl_closeCal.setVisibility(View.GONE);
                    cl_openCal.setVisibility(View.VISIBLE);
                }
                if(count == 31){
                    cl_closeCal.setVisibility(View.GONE);
                    cl_openCal.setVisibility(View.VISIBLE);
                }
                if(count == 33){
                    cl_openCal.setVisibility(View.GONE);
                    cl_test.setVisibility(View.VISIBLE);
                }
                if(count == 35){
                    cl_test.setVisibility(View.GONE);
                    cl_operation_test.setVisibility(View.VISIBLE);
                }
                if(count == 37){
                    cl_operation_test.setVisibility(View.GONE);
                    cl_done.setVisibility(View.VISIBLE);
                }
            }
            public void onFinish() {
                cl_done.setVisibility(View.GONE);
                //screenChange();
            }

        }.start();
    }


}
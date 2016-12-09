package com.zcl.hxqh.zhongchuliang.view.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.model.Asset;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;
import com.zcl.hxqh.zhongchuliang.zxing.camera.CameraManager;
import com.zcl.hxqh.zhongchuliang.zxing.decoding.CaptureActivityHandler;
import com.zcl.hxqh.zhongchuliang.zxing.decoding.InactivityTimer;
import com.zcl.hxqh.zhongchuliang.zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Initial the camera
 * @author Ryan.Tang
 */
public class MipcaActivityCapture extends BaseActivity implements Callback {

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    private int mark;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        //ViewUtil.addTopView(getApplicationContext(), this, R.string.scan_card);
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);

        ImageView mButtonBack = (ImageView) findViewById(R.id.img_back);
        TextView textView=(TextView)findViewById(R.id.txt_title);
        textView.setText(getString(R.string.scanning_text));
        mButtonBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MipcaActivityCapture.this.finish();

            }
        });
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);

        mark = getIntent().getIntExtra("mark",0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * 处理扫描结果
     * @param result
     */
    public void handleDecode(Result result) {
        inactivityTimer.onActivity();
//        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if (resultString.equals("")) {
            Toast.makeText(MipcaActivityCapture.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        }else {
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putString("result", resultString);
//            bundle.putInt("mark",mark);
//            Log.i("MipcaActivityCapture", "resultString=" + resultString);
//            resultIntent.putExtras(bundle);
//            resultIntent.setClass(MipcaActivityCapture.this, Results_Activity.class);
//            this.setResult(RESULT_OK, resultIntent);
//            startActivityForResult(resultIntent,RESULT_OK);
            if (getIntent().getIntExtra("mark",0)!=100&&getIntent().getIntExtra("mark",0)!=-1&&getIntent().getIntExtra("mark",0)!=-2) {
                Intent intent = getIntent();
                intent.putExtra("result", resultString);
                MipcaActivityCapture.this.setResult(mark, intent);
//                finish();
            }else if (getIntent().getIntExtra("mark",0)==-1){//库存盘点
                getN_Invverline(getIntent().getStringExtra("invvernum"),resultString);
            }else if (getIntent().getIntExtra("mark",0)==-2){//出库
                getInvreserve(getIntent().getStringExtra("wonum"), resultString);
            }else {
                getAsset(resultString);
            }
        }
        MipcaActivityCapture.this.finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    /**
     * 获取设备信息*
     */

    private void getAsset(String assetnum) {
        ImManager.getData(MipcaActivityCapture.this, ImManager.setAssetUrl(assetnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
//                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Asset> items = new ArrayList<Asset>();
                try {
                    if (results!=null&&results.getResultlist()!=null) {
                        items = Ig_Json_Model.parseAssetFromString(results.getResultlist());
                        if (items!=null&&items.size()==1){
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("asset",items.get(0));
                            resultIntent.setClass(MipcaActivityCapture.this, AssetDetailActivity.class);
                            startActivityForResult(resultIntent, RESULT_OK);
                            finish();
                        }else {
                            MessageUtils.showMiddleToast(MipcaActivityCapture.this, "未查询到设备信息");
                        }
                    }else {
                        MessageUtils.showMiddleToast(MipcaActivityCapture.this, "数据获取失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                MessageUtils.showMiddleToast(MipcaActivityCapture.this, "数据获取失败");
            }
        });
    }

    /**
     * 获取库存盘点行信息*
     */

    private void getN_Invverline(String invvernum,String itemnum) {
        ImManager.getData(MipcaActivityCapture.this, ImManager.setN_InvverlineUrl(invvernum, itemnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
//                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<N_Invverline> items = new ArrayList<N_Invverline>();
                try {
                    if (results!=null&&results.getResultlist()!=null) {
                        items = Ig_Json_Model.parseN_InvverlineFromString(results.getResultlist());
                        if (items!=null&&items.size()==1){
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("N_Invverline",items.get(0));
                            resultIntent.setClass(MipcaActivityCapture.this, N_InvverlineDetailActivity.class);
                            startActivityForResult(resultIntent, RESULT_OK);
                            finish();
                        }else {
                            MessageUtils.showMiddleToast(MipcaActivityCapture.this, "未查询到设备信息");
                        }
                    }else {
                        MessageUtils.showMiddleToast(MipcaActivityCapture.this, "数据获取失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                MessageUtils.showMiddleToast(MipcaActivityCapture.this, "数据获取失败");
            }
        });
    }

    /**
     * 获取预留项目信息*
     */

    private void getInvreserve(final String wonum, final String itemnum) {
        ImManager.getData(MipcaActivityCapture.this, ImManager.setInvreserveUrl(wonum, itemnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
//                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Invreserve> items = new ArrayList<Invreserve>();
                try {
                    if (results!=null&&results.getResultlist()!=null) {
                        items = Ig_Json_Model.parseInvreserveFromString(results.getResultlist());
                        if (items!=null&&items.size()==1){
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("invreserve",items.get(0));
                            resultIntent.putExtra("wonum",wonum);
                            resultIntent.setClass(MipcaActivityCapture.this, InvreserveDetailActivity.class);
                            startActivityForResult(resultIntent, RESULT_OK);
                            finish();
                        }else {
                            MessageUtils.showMiddleToast(MipcaActivityCapture.this, "未查询到预留项目信息");
                            finish();
//                            addInvreserve(wonum,itemnum);
                        }
                    }else {
                        MessageUtils.showMiddleToast(MipcaActivityCapture.this, "数据获取失败");
                        finish();
//                        addInvreserve(wonum,itemnum);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                MessageUtils.showMiddleToast(MipcaActivityCapture.this, "数据获取失败");
                finish();
            }
        });
    }

    //新增入库预留项目
    private void addInvreserve(String wonum,String itemnum){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("itemnum",itemnum);
        resultIntent.putExtra("wonum",wonum);
        resultIntent.setClass(MipcaActivityCapture.this, InvreserveAddNewActivity.class);
        startActivityForResult(resultIntent, RESULT_OK);
        finish();
    }

}
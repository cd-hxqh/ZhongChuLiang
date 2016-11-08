package com.zcl.hxqh.zhongchuliang.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.constants.Constants;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.until.AccountUtils;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 出库
 */
public class InvreserveDetailActivity extends BaseActivity {

    private static final String TAG = "InvreserveDetailActivity";
    private TextView titleTextView; // 标题

    private ImageView backImage; //返回

    private Invreserve invreserve;
    private String wonum;

    /**
     * 界面说明*
     */

    private TextView requestnum; //请求
    private TextView itemnum;//项目
    private TextView location;//库房
    private TextView description; //描述
    private TextView reservedqty;//已预留数量
    private TextView restype; //预留类型
    private EditText binnum; //货柜

    private Button input;//提交


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invreserve_detail);

        initData();
        findViewById();
        initView();
    }

    private void initData() {
        invreserve = (Invreserve) getIntent().getSerializableExtra("invreserve");
        wonum = getIntent().getStringExtra("wonum");
    }


    /**
     * 初始化界面控件*
     */
    private void findViewById() {
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);

        requestnum = (TextView) findViewById(R.id.invreserve_requestnum);
        itemnum = (TextView) findViewById(R.id.invreserve_itemnum);
        location = (TextView) findViewById(R.id.invreserve_location);
        description = (TextView) findViewById(R.id.invreserve_description);
        reservedqty = (TextView) findViewById(R.id.invreserve_reservedqty);
        restype = (TextView) findViewById(R.id.invreserve_restype);
        binnum = (EditText) findViewById(R.id.invreserve_binnum);

        input = (Button) findViewById(R.id.input_button_id);
    }


    /**
     * 设置事件监听*
     */
    private void initView() {
        titleTextView.setText(getString(R.string.title_activity_invbalance_detail));
        backImage.setOnClickListener(backOnClickListener);
        backImage.setVisibility(View.VISIBLE);
        requestnum.setText(invreserve.requestnum);
        itemnum.setText(invreserve.itemnum);
        location.setText(invreserve.location);
        description.setText(invreserve.description);
        reservedqty.setText(invreserve.reservedqty);
        restype.setText(invreserve.restype);

        input.setOnClickListener(inputOnClickListener);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener inputOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isOK()) {
                showProgressBar(R.string.submit_process_ing);
                final String num = binnum.getText().toString();
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {


                        String data = getBaseApplication().getWsService().INV03Issue(AccountUtils.getUserName(InvreserveDetailActivity.this), wonum,
                                invreserve.itemnum, invreserve.reservedqty, invreserve.location, "", AccountUtils.getIpAddress(InvreserveDetailActivity.this));
                        Log.i(TAG, "data=" + data);
                        if (data == null) {
                            return "";
                        }
                        return data;
                    }

                    @Override
                    protected void onPostExecute(String o) {
                        super.onPostExecute(o);
                        colseProgressBar();

                        if (o==null||o.equals("")) {
                            MessageUtils.showMiddleToast(InvreserveDetailActivity.this, "操作失败");
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(o);
                            String result = jsonObject.getString("msg");
                            MessageUtils.showMiddleToast(InvreserveDetailActivity.this, result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            MessageUtils.showMiddleToast(InvreserveDetailActivity.this, "操作失败");
                            InvreserveDetailActivity.this.finish();
                        }
                        InvreserveDetailActivity.this.finish();
                    }
                }.execute();
            }
        }
    };

    private boolean isOK() {
        if (binnum == null || binnum.getText().equals("")) {
            Toast.makeText(InvreserveDetailActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}

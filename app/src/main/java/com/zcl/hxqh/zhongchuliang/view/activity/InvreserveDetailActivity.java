package com.zcl.hxqh.zhongchuliang.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.model.Person;
import com.zcl.hxqh.zhongchuliang.until.AccountUtils;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
    private EditText issueto;//发放到
    private TextView cardnum;//卡号
    private TextView usrby;//使用人

    private Button input;//提交


    /**使用人**/
    private String enterby;


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
        issueto = (EditText) findViewById(R.id.invreserve_issueto);
        cardnum = (TextView) findViewById(R.id.invreserve_cardnum);
        usrby = (TextView) findViewById(R.id.invreserve_usrby);

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
        issueto.setText(invreserve.issueto);

        cardnum.setOnClickListener(cardnumOnClickListener);

        input.setOnClickListener(inputOnClickListener);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener cardnumOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InvreserveDetailActivity.this, NFC_Activity.class);
            startActivityForResult(intent, 1);
        }
    };

    private View.OnClickListener inputOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isOK()) {
                showProgressBar(R.string.submit_process_ing);
                final String num = binnum.getText().toString();
                final String issueto2 = issueto.getText().toString();
                final String n_cardnum = cardnum.getText().toString();

                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = getBaseApplication().getWsService().INV03Issue(AccountUtils.getUserName(InvreserveDetailActivity.this), wonum,
                                invreserve.itemnum, invreserve.reservedqty, invreserve.location, num, issueto2, AccountUtils.getIpAddress(InvreserveDetailActivity.this), n_cardnum, enterby);
//                        Log.i(TAG, "data=" + data);
                        if (data == null) {
                            return "";
                        }
                        return data;
                    }

                    @Override
                    protected void onPostExecute(String o) {
                        super.onPostExecute(o);
                        colseProgressBar();

                        if (o == null || o.equals("")) {
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
        if (binnum == null || binnum.getText().toString().equals("")) {
            MessageUtils.showMiddleToast(InvreserveDetailActivity.this, "请填写货柜");
            return false;
        } else if (issueto == null || issueto.getText().toString().equals("")) {
            MessageUtils.showMiddleToast(InvreserveDetailActivity.this, "请填写发放到");
            return false;
        }


        else {
            return true;
        }
    }

    /**
     * 获取库存项目信息*
     */

    private void getPerson(String cardnum) {
        ImManager.getData(this, ImManager.setPersonUrl(cardnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
//                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Person> items = new ArrayList<Person>();
                try {
                    items = Ig_Json_Model.parsePersonFromString(results.getResultlist());
                    if (items.size() == 0) {
                        Toast.makeText(InvreserveDetailActivity.this, "未查询到此人", Toast.LENGTH_SHORT).show();
                    } else if (items.size() == 1) {
                        usrby.setText(items.get(0).displayname);
                        enterby=items.get(0).personid;
                    } else {
                        Toast.makeText(InvreserveDetailActivity.this, "查询错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(InvreserveDetailActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case 1:
                    cardnum.setText(data.getStringExtra("cardnum"));
                    getPerson(data.getStringExtra("cardnum"));
                    break;
            }
        }
    }
}

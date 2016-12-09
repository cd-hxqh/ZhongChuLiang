package com.zcl.hxqh.zhongchuliang.view.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.until.AccountUtils;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 新建预留项目
 */
public class InvreserveAddNewActivity extends BaseActivity {

    private static final String TAG = "InvreserveAddNewActivity";
    private TextView titleTextView; // 标题

    private ImageView backImage; //返回

    private String itemNum;
    private String wonum;
    private Invreserve invreserve = new Invreserve();

    /**
     * 界面说明*
     */

//    private EditText requestnum; //请求
    private TextView itemnum;//项目
    private EditText location;//库房
//    private EditText description; //描述
    private EditText reservedqty;//已预留数量
//    private EditText restype; //预留类型
    private EditText binnum; //货柜

    private Button input;//提交


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invreserve_addnew);

        initData();
        findViewById();
        initView();
    }

    private void initData() {
        itemNum = getIntent().getStringExtra("itemnum");
        wonum = getIntent().getStringExtra("wonum");
    }


    /**
     * 初始化界面控件*
     */
    private void findViewById() {
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);

//        requestnum = (EditText) findViewById(R.id.invreserve_requestnum);
        itemnum = (TextView) findViewById(R.id.invreserve_itemnum);
        location = (EditText) findViewById(R.id.invreserve_location);
//        description = (EditText) findViewById(R.id.invreserve_description);
        reservedqty = (EditText) findViewById(R.id.invreserve_reservedqty);
//        restype = (EditText) findViewById(R.id.invreserve_restype);
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
        itemnum.setText(itemNum);

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




                        String data = getBaseApplication().getWsService().INV03Issue(AccountUtils.getUserName(InvreserveAddNewActivity.this), wonum,
                                itemNum, invreserve.reservedqty, invreserve.location, num,"", AccountUtils.getIpAddress(InvreserveAddNewActivity.this),"","");
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

                        if (o==null||o.equals("")) {
                            MessageUtils.showMiddleToast(InvreserveAddNewActivity.this, "操作失败");
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(o);
                            String result = jsonObject.getString("msg");
                            MessageUtils.showMiddleToast(InvreserveAddNewActivity.this, result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            MessageUtils.showMiddleToast(InvreserveAddNewActivity.this, "操作失败");
                            InvreserveAddNewActivity.this.finish();
                        }
                        InvreserveAddNewActivity.this.finish();
                    }
                }.execute();
            }
        }
    };

    private boolean isOK() {
        if (binnum == null || binnum.getText().toString().equals("")) {
            Toast.makeText(InvreserveAddNewActivity.this, "请填写货柜", Toast.LENGTH_SHORT).show();
            return false;
        }else if (location == null || location.getText().toString().equals("")) {
            Toast.makeText(InvreserveAddNewActivity.this, "请填写库房", Toast.LENGTH_SHORT).show();
            return false;
        }else if (reservedqty == null || reservedqty.getText().toString().equals("")) {
            Toast.makeText(InvreserveAddNewActivity.this, "请填写预留数量", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            invreserve.location = location.getText().toString();
            invreserve.reservedqty = reservedqty.getText().toString();
            return true;
        }
    }
}

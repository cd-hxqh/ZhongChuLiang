package com.zcl.hxqh.zhongchuliang.view.activity;

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
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;
import com.zcl.hxqh.zhongchuliang.model.Poline;
import com.zcl.hxqh.zhongchuliang.until.AccountUtils;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 入库管理物料详情*
 */
public class N_InvverlineDetailActivity extends BaseActivity {

    private static final String TAG = "N_InvverlineDetailActivity";
    private TextView titleTextView; // 标题

    private ImageView backImage; //返回

    private N_Invverline nInvverline;
    private String ponum;

    /**
     * 界面说明*
     */

    private TextView sn; //项目
    private TextView itemnum;//项目
    private TextView itemdesc;//项目描述
//    private TextView physcntdate; //计划盘点日期
    private TextView binnum;//货柜
    private TextView lotnum; //批次
    private EditText physcnt; //实际库存
    private TextView itemin19; //规格型号
    private TextView avblbalance; //可用量

    private Button input;//提交


    private String location; //库房


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_invverline_detail);

        initData();
        findViewById();
        initView();
    }

    private void initData() {
        nInvverline = (N_Invverline) getIntent().getSerializableExtra("N_Invverline");
        location = getIntent().getStringExtra("location");
    }


    /**
     * 初始化界面控件*
     */
    private void findViewById() {
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);

        sn = (TextView) findViewById(R.id.n_invverline_sn);
        itemnum = (TextView) findViewById(R.id.n_invverline_itemnum);
        itemdesc = (TextView) findViewById(R.id.n_invverline_itemdesc);
//        physcntdate = (TextView) findViewById(R.id.n_invverline_physcntdate);
        binnum = (TextView) findViewById(R.id.n_invverline_binnum);
        lotnum = (TextView) findViewById(R.id.n_invverline_lotnum);
        physcnt = (EditText) findViewById(R.id.n_invverline_physcnt);
        itemin19 = (TextView) findViewById(R.id.n_invverline_itemin19);
        avblbalance = (TextView) findViewById(R.id.n_invverline_avblbalance);

        input = (Button) findViewById(R.id.input_button_id);
    }


    /**
     * 设置事件监听*
     */
    private void initView() {
        titleTextView.setText(getString(R.string.title_activity_invbalance_detail));
        backImage.setOnClickListener(backOnClickListener);
        backImage.setVisibility(View.VISIBLE);
//        if (mark == 1000) {
//            qty_title.setText(R.string.poline_recorde_num);
//            type = Constants.RECEIPT;
//        } else if (mark == 1001) {
//            qty_title.setText(R.string.poline_return_num);
//            type = Constants.RETURN;
//        }
        sn.setText(nInvverline.sn);
        itemnum.setText(nInvverline.itemnum);
        itemdesc.setText(nInvverline.itemdesc);
//        physcntdate.setText(nInvverline.physcntdate);
        binnum.setText(nInvverline.binnum);
        lotnum.setText(nInvverline.lotnum);
        physcnt.setText(nInvverline.physcnt);
        itemin19.setText(nInvverline.itemin19);
        avblbalance.setText(nInvverline.avblbalance);

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
                final String number = physcnt.getText().toString();
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = getBaseApplication().getWsService().INV08UpdateN(nInvverline.invvernum,
                                nInvverline.sn, nInvverline.itemnum, number,AccountUtils.getIpAddress(N_InvverlineDetailActivity.this));
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
                            MessageUtils.showMiddleToast(N_InvverlineDetailActivity.this, "操作失败");
                        }else {
                            MessageUtils.showMiddleToast(N_InvverlineDetailActivity.this, o);
                        }
                        N_InvverlineDetailActivity.this.finish();
                    }
                }.execute();
            }
        }
    };

    private boolean isOK() {
        if (physcnt == null || physcnt.getText().equals("")) {
            Toast.makeText(N_InvverlineDetailActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if (Integer.parseInt(physcnt.getText().toString()) > Integer.parseInt(physcnt.orderqty)) {
//            Toast.makeText(N_InvverlineDetailActivity.this, "请输入正确数量", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        else {
            return true;
        }
    }
}

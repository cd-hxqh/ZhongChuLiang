package com.zcl.hxqh.zhongchuliang.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.Matrectrans;

/**
 * Created by Administrator on 2016/10/21.
 */
public class PoMatrectransActivity extends BaseActivity {
    private static final String TAG = "PoMatrectransActivity";

    private TextView titleTextView; // 标题

    private ImageView backImage; //返回

    public TextView polinenum;//po行
    public TextView itemnum;//项目
    public TextView description;//描述
    public TextView quantity;//数量
    public TextView receivedunit;//单位
    public TextView issuetype;//类型
    public TextView status;//状态
    public TextView actualdate;//到货时间
    public TextView statusdate;//入库时间

    private Matrectrans matrectrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_matrectrans_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     *获取界面数据
     */
    private void geiIntentData() {
        matrectrans = (Matrectrans) getIntent().getSerializableExtra("matrectrans");
    }

    /**
     * 初始化界面控件
     */
    private void findViewById(){
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);

        polinenum = (TextView) findViewById(R.id.matrectrans_polinenum);
        itemnum = (TextView) findViewById(R.id.matrectrans_itemnum);
        description = (TextView) findViewById(R.id.matrectrans_description);
        quantity = (TextView) findViewById(R.id.matrectrans_receiptquantity);
        receivedunit = (TextView) findViewById(R.id.matrectrans_receivedunit);
        issuetype = (TextView) findViewById(R.id.matrectrans_issuetype);
        status = (TextView) findViewById(R.id.matrectrans_status);
        actualdate = (TextView) findViewById(R.id.matrectrans_actualdate);
        statusdate = (TextView) findViewById(R.id.matrectrans_statusdate);
    }

    private void initView() {
        titleTextView.setText(R.string.matrectrans_po_details);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        backImage.setVisibility(View.VISIBLE);

        polinenum.setText(matrectrans.polinenum);
        itemnum.setText(matrectrans.itemnum);
        description.setText(matrectrans.description);
        quantity.setText(matrectrans.quantity);
        receivedunit.setText(matrectrans.receivedunit);
        issuetype.setText(matrectrans.issuetype);
        status.setText(matrectrans.status);
        actualdate.setText(matrectrans.actualdate);
        statusdate.setText(matrectrans.statusdate);
    }
}

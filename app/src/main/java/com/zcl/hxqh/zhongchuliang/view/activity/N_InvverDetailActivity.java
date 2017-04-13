package com.zcl.hxqh.zhongchuliang.view.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.adapter.N_InvverlineAdapter;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.model.N_Invver;
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;
import com.zcl.hxqh.zhongchuliang.view.widght.ListViewForScrollView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 库存盘点详情
 */

public class N_InvverDetailActivity extends BaseActivity {
    @Bind(R.id.txt_title)
    TextView titleText; //标题
    @Bind(R.id.img_back)
    ImageView backImageView; //返回按钮

    @Bind(R.id.n_invver_invvernum)
    TextView invvernum; //编号
    @Bind(R.id.n_invver_description)
    TextView description; //描述
    @Bind(R.id.n_invver_location)
    TextView location;//库房
    @Bind(R.id.n_invver_locdesc)
    TextView locdesc;//库房描述
    @Bind(R.id.n_invver_status)
    TextView status;//状态
    @Bind(R.id.n_invver_statusdate)
    TextView statusdate;//状态时间
    @Bind(R.id.n_invver_reportname)
    TextView reportname;//创建人
    @Bind(R.id.n_invver_reportdate)
    TextView reportdate;//创建时间
    @Bind(R.id.qr_right)
    ImageView qrView;//扫描二维码

    @Bind(R.id.load_more_layout)
    RelativeLayout loadmoreLayout;//点击加载更多
    @Bind(R.id.load_more_button)
    Button loadmoreButton;

    @Bind(R.id.load_layout)
    LinearLayout loadLayout;//加载布局
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.listview)
    ListViewForScrollView listView;
    ListViewForScrollView.LayoutManager mLayoutManager;

    N_InvverlineAdapter n_invverlineAdapter;
    ArrayList<N_Invverline> items;


    private N_Invver n_invver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_invver);
        ButterKnife.bind(this);
        getIntentData();
        getMatrectransList();
        setEvent();
    }

    private void getIntentData() {
        n_invver = (N_Invver) getIntent().getSerializableExtra("n_invver");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        n_invverlineAdapter = new N_InvverlineAdapter(this, n_invver.location);
        listView.setAdapter(n_invverlineAdapter);
        getMatrectransList();
    }


    /**
     * 设置事件监听
     **/
    private void setEvent() {
        titleText.setText(getString(R.string.n_invverline_text));
        backImageView.setVisibility(View.VISIBLE);
        qrView.setVisibility(View.VISIBLE);
        scrollView.smoothScrollTo(0, 0);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        n_invverlineAdapter = new N_InvverlineAdapter(this, n_invver.location);
        listView.setAdapter(n_invverlineAdapter);
        if (n_invver != null) {
            invvernum.setText(n_invver.invvernum);
            description.setText(n_invver.description);
            location.setText(n_invver.location);
            locdesc.setText(n_invver.locdesc);
            status.setText(n_invver.status);
            statusdate.setText(n_invver.statusdate);
            reportname.setText(n_invver.reportname);
            reportdate.setText(n_invver.reportdate);
        }
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);
        loadmoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadLayout.getVisibility() != View.VISIBLE) {
                    loadLayout.setVisibility(View.VISIBLE);
                    loadmoreButton.setBackgroundResource(R.drawable.up);
                } else {
                    loadLayout.setVisibility(View.GONE);
                    loadmoreButton.setBackgroundResource(R.drawable.down);
                }
            }
        });

    }

    //返回事件
    @OnClick(R.id.img_back)
    void setBackImageView() {
        finish();
    }

    //二维码扫描
    @OnClick(R.id.qr_right)
    void setQrOnClickListener() {
        Intent intent = new Intent(N_InvverDetailActivity.this, MipcaActivityCapture.class);
        intent.putExtra("mark", -1);
        intent.putExtra("invvernum", n_invver.invvernum);
        startActivityForResult(intent, 0);
    }

    /**
     * 获取入库管理*
     */

    private void getMatrectransList() {
        ImManager.getData(N_InvverDetailActivity.this, ImManager.setN_InvverlineUrl(n_invver.invvernum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                items = new ArrayList<N_Invverline>();
                try {
                    items = Ig_Json_Model.parseN_InvverlineFromString(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {
                        n_invverlineAdapter.adddate(items);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                MessageUtils.showMiddleToast(N_InvverDetailActivity.this, "数据获取失败");
            }
        });
    }


}

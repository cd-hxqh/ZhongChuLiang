package com.zcl.hxqh.zhongchuliang.view.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.adapter.MatrectransAdapter2;
import com.zcl.hxqh.zhongchuliang.adapter.N_InvverlineAdapter;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.model.Matrectrans;
import com.zcl.hxqh.zhongchuliang.model.N_Invver;
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;
import com.zcl.hxqh.zhongchuliang.model.Po;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;
import com.zcl.hxqh.zhongchuliang.view.widght.ListViewForScrollView;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 库存盘点详情
 */

public class N_InvverDetailActivity extends BaseActivity {
    private static final String TAG = "N_InvverDetailActivity";
    /**
     * 标题
     **/
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;


    private TextView invvernum; //编号

    private TextView description; //描述

    private TextView location;//库房

    private TextView locdesc;//库房描述

    private TextView status;//状态

    private TextView statusdate;//状态时间

    private TextView reportname;//创建人

    private TextView reportdate;//创建时间

//    private Button recordeBtn;//接收按钮
//
//    private Button returnBtn;//返回按钮

    private RelativeLayout loadmoreLayout;//点击加载更多
    private Button loadmoreButton;

    private LinearLayout loadLayout;//加载布局

    private ScrollView scrollView;

    private ListViewForScrollView listView;
    ListViewForScrollView.LayoutManager mLayoutManager;

    N_InvverlineAdapter n_invverlineAdapter;
    ArrayList<N_Invverline> items;

//    private final int RECORDE_MARK = 1000;//接收标记
//    private final int RETURN_MARK = 1001;//退货标记

    private N_Invver n_invver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_invver);
        getIntentData();
        initView();
        getMatrectransList();
        setEvent();
    }

    private void getIntentData() {
        n_invver = (N_Invver) getIntent().getSerializableExtra("n_invver");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        n_invverlineAdapter = new N_InvverlineAdapter(this,n_invver.location);
        listView.setAdapter(n_invverlineAdapter);
        getMatrectransList();
    }

    /**
     * 初始化界面组件
     **/
    private void initView() {
        titleText = (TextView) findViewById(R.id.txt_title);
        backImageView = (ImageView) findViewById(R.id.img_back);
        invvernum = (TextView) findViewById(R.id.n_invver_invvernum);
        description = (TextView) findViewById(R.id.n_invver_description);
        location = (TextView) findViewById(R.id.n_invver_location);
        locdesc = (TextView) findViewById(R.id.n_invver_locdesc);
        status = (TextView) findViewById(R.id.n_invver_status);
        statusdate = (TextView) findViewById(R.id.n_invver_statusdate);
        reportname = (TextView) findViewById(R.id.n_invver_reportname);
        reportdate = (TextView) findViewById(R.id.n_invver_reportdate);

        loadmoreLayout = (RelativeLayout) findViewById(R.id.load_more_layout);
        loadmoreButton = (Button) findViewById(R.id.load_more_button);
        loadLayout = (LinearLayout) findViewById(R.id.load_layout);

//        recordeBtn = (Button) findViewById(R.id.po_recorde_text);
//        returnBtn = (Button) findViewById(R.id.po_return_text);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        listView = (ListViewForScrollView) findViewById(R.id.listview);
    }

    /**
     * 设置事件监听
     **/
    private void setEvent() {
        titleText.setText(getString(R.string.material_receiving));
        backImageView.setVisibility(View.VISIBLE);
        backImageView.setOnClickListener(backImageViewOnClickListener);
        scrollView.smoothScrollTo(0, 0);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        n_invverlineAdapter = new N_InvverlineAdapter(this,n_invver.location);
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
                if (loadLayout.getVisibility()!=View.VISIBLE) {
                    loadLayout.setVisibility(View.VISIBLE);
                    loadmoreButton.setBackgroundResource(R.drawable.up);
                }else {
                    loadLayout.setVisibility(View.GONE);
                    loadmoreButton.setBackgroundResource(R.drawable.down);
                }
            }
        });

//        recordeBtn.setOnClickListener(intentOnClicListener);
//        returnBtn.setOnClickListener(intentOnClicListener);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

//    private View.OnClickListener intentOnClicListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(N_InvverDetailActivity.this, PoLineActivity.class);
//            intent.putExtra("n_invver",n_invver.invvernum);
//            if(v.getId()==recordeBtn.getId()){
//                intent.putExtra("mark",RECORDE_MARK);
//            }else if(v.getId()==returnBtn.getId()){
//                intent.putExtra("mark",RETURN_MARK);
//            }
//            startActivity(intent);
//        }
//    };

    /**
     * 获取入库管理*
     */

    private void getMatrectransList() {
        ImManager.getData(N_InvverDetailActivity.this, ImManager.setN_InvverlineUrl(n_invver.invvernum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                items = new ArrayList<N_Invverline>();
                try {
                    items = Ig_Json_Model.parseN_InvverlineFromString(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {
                        n_invverlineAdapter.adddate(items);
//                        if (page == 1) {
//                            poAdapter = new PoAdapter(getActivity());
//                            mRecyclerView.setAdapter(poAdapter);
//                        }
//                        if (page == totalPages) {
//                            poAdapter.adddate(items);
//                        }
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

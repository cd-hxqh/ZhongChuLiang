package com.zcl.hxqh.zhongchuliang.view.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zcl.hxqh.zhongchuliang.adapter.InvreserveAdapter;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.model.WorkOrder;
import com.zcl.hxqh.zhongchuliang.view.widght.ListViewForScrollView;
import com.zcl.hxqh.zhongchuliang.view.widght.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 出库管理详情
 */
public class WorkOrderDetailsActivity extends BaseActivity {

    private static final String TAG = "WorkOrderDetailsActivity";

    private TextView titleTextView; // 标题

    private ImageView backImage; //返回按钮


    /**
     * --界面显示的textView--**
     */

    private TextView wonum; //工单编号

    private TextView description; //描述

    private TextView n_location; //库房

    private TextView locdesc; //库房描述

    private TextView n_purpose; //领用用途

    private TextView department; //部门

    private TextView status; //状态

    private TextView statusdate; //状态日期

    private TextView reportname; //申请人

    private TextView reportdate; //申请日期

    /**
     * WorkOrder*
     */
    private WorkOrder workOrder;


    private RelativeLayout loadmoreLayout;//点击加载更多
    private Button loadmoreButton;

    private LinearLayout loadLayout;//加载布局

    private Button chooseItem;//选择预留项目

//    private ScrollView scrollView;
//
//    private ListViewForScrollView listView;
//    ListViewForScrollView.LayoutManager mLayoutManager;


//    private InvreserveAdapter invreserveAdapter;
//    private ArrayList<Invreserve> items = new ArrayList<>();


//    private String wonum="965361";

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workorder_details_items);
        geiIntentData();
        initView();
        setEvent();
    }

    /**
     * 获取上个界面的数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");

    }


    /**
     * 初始化界面组件
     */
    private void initView() {
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);

        wonum = (TextView) findViewById(R.id.workorder_wonum);
        description = (TextView) findViewById(R.id.workorder_description);
        n_location = (TextView) findViewById(R.id.workorder_n_location);
        locdesc = (TextView) findViewById(R.id.workorder_locdesc);
        n_purpose = (TextView) findViewById(R.id.workorder_n_purpose);
        department = (TextView) findViewById(R.id.workorder_department);
        status = (TextView) findViewById(R.id.workorder_status);
        statusdate = (TextView) findViewById(R.id.workorder_statusdate);
        reportname = (TextView) findViewById(R.id.workorder_reportname);
        reportdate = (TextView) findViewById(R.id.workorder_reportdate);

        loadmoreLayout = (RelativeLayout) findViewById(R.id.load_more_layout);
        loadmoreButton = (Button) findViewById(R.id.load_more_button);
        loadLayout = (LinearLayout) findViewById(R.id.load_layout);

        chooseItem = (Button) findViewById(R.id.workorder_choose_item);

//        scrollView = (ScrollView) findViewById(R.id.scrollView);
//        listView = (ListViewForScrollView) findViewById(R.id.listview);


//        getInvreserveList(workOrder.wonum);
//        getInvreserveList(wonum);
    }


    /**
     * 设置事件监听
     */
    private void setEvent() {
        titleTextView.setText(getString(R.string.workorder_detail_title));
        backImage.setOnClickListener(backOnClickListener);
        backImage.setVisibility(View.VISIBLE);
//        scrollView.smoothScrollTo(0, 0);
//        mLayoutManager = new LinearLayoutManager(this);
//        listView.setLayoutManager(mLayoutManager);
//        invreserveAdapter = new InvreserveAdapter(WorkOrderDetailsActivity.this,workOrder.wonum);
//        listView.setAdapter(invreserveAdapter);

        if (workOrder != null) {
            wonum.setText(workOrder.wonum);
            description.setText(workOrder.description);
            n_location.setText(workOrder.n_location);
            locdesc.setText(workOrder.locdesc);
            n_purpose.setText(workOrder.n_purpose);
            department.setText(workOrder.department);
            status.setText(workOrder.status);
            statusdate.setText(workOrder.statusdate);
            reportname.setText(workOrder.reportname);
            reportdate.setText(workOrder.reportdate);
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

        chooseItem.setOnClickListener(chooseItemOnClickListener);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener chooseItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(WorkOrderDetailsActivity.this, InvreserveListActivity.class);
            intent.putExtra("wonum",workOrder.wonum);
            startActivity(intent);
        }
    };

//    /**
//     * 获取gInvreserve信息*
//     */
//
//    private void getInvreserveList(final String wonum) {
//        ImManager.getDataPagingInfo(this, ImManager.serInvreserveUrl(wonum, "", page, 20), new HttpRequestHandler<Results>() {
//            @Override
//            public void onSuccess(Results results) {
////                Log.i(TAG, "data=" + results);
//            }
//
//            @Override
//            public void onSuccess(Results results, int totalPages, int currentPage) {
//                items = new ArrayList<Invreserve>();
//                try {
//                    items = Ig_Json_Model.parseInvreserveFromString(results.getResultlist());
//                    if (items == null || items.isEmpty()) {
//                    } else {
//                        invreserveAdapter.adddate(items);
////                        if (page == 1) {
////                            poAdapter = new PoAdapter(getActivity());
////                            mRecyclerView.setAdapter(poAdapter);
////                        }
////                        if (page == totalPages) {
////                            poAdapter.adddate(items);
////                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(String error) {
////                mSwipeLayout.setRefreshing(false);
////                notLinearLayout.setVisibility(View.VISIBLE);
//            }
//        });
//    }

}

package com.zcl.hxqh.zhongchuliang.view.activity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.WorkOrder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 出库管理详情
 */
public class WorkOrderDetailsActivity extends BaseActivity {

    private static final String TAG = "WorkOrderDetailsActivity";
    @Bind(R.id.txt_title)
    TextView titleTextView; // 标题
    @Bind(R.id.img_back)
    ImageView backImage; //返回按钮


    /**
     * --界面显示的textView--**
     */
    @Bind(R.id.workorder_wonum)
    TextView wonum; //工单编号
    @Bind(R.id.workorder_description)
    TextView description; //描述
    @Bind(R.id.workorder_n_location)
    TextView n_location; //库房
    @Bind(R.id.workorder_locdesc)
    TextView locdesc; //库房描述
    @Bind(R.id.workorder_n_purpose)
    TextView n_purpose; //领用用途
    @Bind(R.id.workorder_department)
    TextView department; //部门
    @Bind(R.id.workorder_status)
    TextView status; //状态
    @Bind(R.id.workorder_statusdate)
    TextView statusdate; //状态日期
    @Bind(R.id.workorder_reportname)
    TextView reportname; //申请人
    @Bind(R.id.workorder_reportdate)
    TextView reportdate; //申请日期

    /**
     * WorkOrder*
     */
    private WorkOrder workOrder;

    @Bind(R.id.load_more_layout)
    RelativeLayout loadmoreLayout;//点击加载更多

    @Bind(R.id.load_more_button)
    Button loadmoreButton;

    @Bind(R.id.load_layout)
    LinearLayout loadLayout;//加载布局

    @Bind(R.id.workorder_choose_item)
    Button chooseItem;//选择预留项目


    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workorder_details_items);
        ButterKnife.bind(this);
        geiIntentData();
        setEvent();
    }

    /**
     * 获取上个界面的数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");

    }




    /**
     * 设置事件监听
     */
    private void setEvent() {
        titleTextView.setText(getString(R.string.workorder_detail_title));
        backImage.setVisibility(View.VISIBLE);

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

    }

    //返回事件
    @OnClick(R.id.img_back)void setBackOnClickListener(){
        finish();
    }
    //选择事件
    @OnClick(R.id.workorder_choose_item)void setChooseItemOnClickListener(){
        Intent intent = new Intent(WorkOrderDetailsActivity.this, InvreserveListActivity.class);
        intent.putExtra("wonum", workOrder.wonum);
        startActivity(intent);
    }


}

package com.zcl.hxqh.zhongchuliang.view.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.adapter.InvreserveAdapter;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.constants.Constants;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.until.AccountUtils;
import com.zcl.hxqh.zhongchuliang.until.MessageUtils;
import com.zcl.hxqh.zhongchuliang.view.widght.SwipeRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by think on 2015/12/16.
 * 出库预留项目列表页面
 */
public class InvreserveListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener{
    private static final String TAG = "InvreserveListActivity";

    private TextView titleTextView; // 标题

    private ImageView backImage; //返回

    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    SwipeRefreshLayout mSwipeLayout;

    InvreserveAdapter invreserveAdapter;

    private Button inputall;//全部提交
    /**
     * 暂无数据*
     */
    LinearLayout notLinearLayout;

    public String wonum;

    private int page = 1;

    private ArrayList<Invreserve> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poline_list);

        geiIntentData();
        findViewById();
        initView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        invreserveAdapter = new InvreserveAdapter(this);
        mRecyclerView.setAdapter(invreserveAdapter);
        page = 1;
        getPoLineList();
    }

    /**
     * 获取界面数据
     */
    private void geiIntentData() {
        wonum = getIntent().getStringExtra("wonum");
    }

    /**
     * 初始化界面控件
     */
    private void findViewById() {
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_topics);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        inputall = (Button) findViewById(R.id.input_all);

        notLinearLayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    private void initView() {
        backImage.setVisibility(View.VISIBLE);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        inputall.setOnClickListener(inputOnClickListener);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        invreserveAdapter = new InvreserveAdapter(this);
        mRecyclerView.setAdapter(invreserveAdapter);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        mSwipeLayout.setRefreshing(true);
        mSwipeLayout.setLoading(false);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);

        getPoLineList();
    }

    /**
     * 获取库存项目信息*
     */

    private void getPoLineList() {
        ImManager.getDataPagingInfo(this, ImManager.serInvreserveUrl(wonum, "", page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
//                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                items = new ArrayList<Invreserve>();
                try {
                    items = Ig_Json_Model.parseInvreserveFromString(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {
                        invreserveAdapter.adddate(items);
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
                mSwipeLayout.setRefreshing(false);
                mSwipeLayout.setLoading(false);
            }

            @Override
            public void onFailure(String error) {
                mSwipeLayout.setRefreshing(false);
                mSwipeLayout.setLoading(false);
                if (invreserveAdapter.getItemCount() != 0) {
                    MessageUtils.showMiddleToast(InvreserveListActivity.this, getString(R.string.loading_data_fail));
                } else {
                    notLinearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private View.OnClickListener inputOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showProgressBar(R.string.submit_process_ing);
            final ArrayList<Invreserve> items = invreserveAdapter.getChecked();
            if (items != null && items.size() != 0) {
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = null;
                        for (Invreserve invreserve : items) {
                            data = getBaseApplication().getWsService().INV03Issue(AccountUtils.getUserName(InvreserveListActivity.this), wonum,
                                    invreserve.itemnum, invreserve.reservedqty, invreserve.location,"", AccountUtils.getIpAddress(InvreserveListActivity.this));
                            if (data == null) {
                                return "";
                            }
                        }
                        return data;
                    }

                    @Override
                    protected void onPostExecute(String o) {
                        super.onPostExecute(o);
                        if (o == null || o.equals("")) {
                            MessageUtils.showMiddleToast(InvreserveListActivity.this, "操作失败");
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(o);
                            String result = jsonObject.getString("msg");
                            MessageUtils.showMiddleToast(InvreserveListActivity.this, result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            MessageUtils.showMiddleToast(InvreserveListActivity.this, "操作失败");
                            InvreserveListActivity.this.finish();
                        }
//                        InvreserveListActivity.this.finish();
                    }
                }.execute();
            }
        }
    };

    @Override
    public void onLoad() {
        page++;
        getPoLineList();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getPoLineList();
        mSwipeLayout.setRefreshing(false);
    }
}
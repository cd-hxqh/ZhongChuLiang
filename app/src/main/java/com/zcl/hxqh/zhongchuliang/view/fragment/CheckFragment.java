package com.zcl.hxqh.zhongchuliang.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.adapter.N_InvverAdapter;
import com.zcl.hxqh.zhongchuliang.api.HttpRequestHandler;
import com.zcl.hxqh.zhongchuliang.api.ImManager;
import com.zcl.hxqh.zhongchuliang.api.ig_json.Ig_Json_Model;
import com.zcl.hxqh.zhongchuliang.bean.Results;
import com.zcl.hxqh.zhongchuliang.model.N_Invver;
import com.zcl.hxqh.zhongchuliang.view.widght.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 库存盘点
 */
public class CheckFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "CheckFragment";
    private static final int RESULT_ADD_TOPIC = 100;

    @Bind(R.id.list_topics)
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeLayout;

    private int page = 1;


    @Bind(R.id.have_not_data_id)
    LinearLayout notLinearLayout; //暂无数据

    N_InvverAdapter invverAdapter;

    private static final int mark = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container,
                false);
        ButterKnife.bind(this, view);//绑定framgent

        findByIdView(view);


        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        invverAdapter = new N_InvverAdapter(getActivity(), mark);
        mRecyclerView.setAdapter(invverAdapter);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setColor(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
        mSwipeLayout.setRefreshing(true);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        mSwipeLayout.setRefreshing(true);
        getItemList();
    }


    /**
     * 获取库存项目信息*
     */

    private void getItemList() {
        ImManager.getDataPagingInfo(getActivity(), ImManager.sercN_InvverUrl("", page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<N_Invver> items = null;
                try {
                    items = Ig_Json_Model.parseN_InvverFromString(results.getResultlist());
                    mSwipeLayout.setRefreshing(false);
                    mSwipeLayout.setLoading(false);
                    if (items == null || items.isEmpty()) {
                        notLinearLayout.setVisibility(View.VISIBLE);
                    } else {
                        if (page == 1) {
                            invverAdapter = new N_InvverAdapter(getActivity(), mark);
                            mRecyclerView.setAdapter(invverAdapter);
                        }
                        if (totalPages == page) {
                            invverAdapter.adddate(items);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
                mSwipeLayout.setRefreshing(false);
                notLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getItemList();
    }

    //上拉加载
    @Override
    public void onLoad() {
        page++;
        getItemList();
    }
}

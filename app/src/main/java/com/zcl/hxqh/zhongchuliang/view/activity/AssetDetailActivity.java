package com.zcl.hxqh.zhongchuliang.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.Asset;
import com.zcl.hxqh.zhongchuliang.model.Locations;


public class AssetDetailActivity extends BaseActivity {

    private TextView titleTextView; // 标题

    private ImageView backImage; //返回


    /**界面值ʾ**/
    private TextView assetnum; //设备编号

    private TextView description; //描述

    private TextView n_locaname;  //位置

    private TextView n_modelnum;  //设备全称

    private TextView assettype;  //类型

    private TextView parent;  //父级

    private TextView status;  //状态
    /**Locations**/
    private Asset asset;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);

        initData();

        findViewById();

        initView();
    }


    /**获取上个界面**/
    private void initData() {
        asset= (Asset) getIntent().getSerializableExtra("asset");
    }


    /**初始化界面控件**/
    private void findViewById() {
        titleTextView = (TextView) findViewById(R.id.txt_title);
        backImage = (ImageView) findViewById(R.id.img_back);


        assetnum=(TextView)findViewById(R.id.asset_assetnum);
        description=(TextView)findViewById(R.id.asset_description);
        n_locaname=(TextView)findViewById(R.id.asset_n_locaname);
        n_modelnum=(TextView)findViewById(R.id.asset_n_modelnum);
        assettype=(TextView)findViewById(R.id.asset_assettype);
        parent=(TextView)findViewById(R.id.asset_parent);
        status=(TextView)findViewById(R.id.asset_status);
    }

    /**设置事件监听**/
    private void initView() {
        titleTextView.setText(getString(R.string.asset_title));
        backImage.setOnClickListener(backOnClickListener);
        backImage.setVisibility(View.VISIBLE);
        if(asset!=null){
            assetnum.setText(asset.getAssetnum()==null?"":asset.getAssetnum());
            description.setText(asset.getDescription()==null?"":asset.getDescription());
            n_locaname.setText(asset.getN_locaname()==null?"":asset.getN_locaname());
            n_modelnum.setText(asset.getN_modelnum()==null?"":asset.getN_modelnum());
            assettype.setText(asset.getAssettype()==null?"":asset.getAssettype());
            parent.setText(asset.getParent()==null?"":asset.getParent());
            status.setText(asset.getStatus()==null?"":asset.getStatus());
        }


    }



    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}

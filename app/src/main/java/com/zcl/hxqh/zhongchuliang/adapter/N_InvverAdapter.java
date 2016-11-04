package com.zcl.hxqh.zhongchuliang.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.N_Invver;
import com.zcl.hxqh.zhongchuliang.view.activity.CInvbalancesActivity;
import com.zcl.hxqh.zhongchuliang.view.activity.N_InvverDetailActivity;

import java.util.ArrayList;

/**
 * Created by apple on 15/6/4.
 * 库存盘点
 */
public class N_InvverAdapter extends RecyclerView.Adapter<N_InvverAdapter.ViewHolder> {

    private static final String TAG = "InvAdapter";
    Context mContext;
    ArrayList<N_Invver> mInventorys = new ArrayList<N_Invver>();

    private int mark; //库存情况/库存盘点

    public N_InvverAdapter(Context context, int mark) {
        mContext = context;
        this.mark = mark;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final N_Invver n_invver = mInventorys.get(i);

        viewHolder.itemNum.setText(n_invver.invvernum);
        viewHolder.itemDesc.setText(n_invver.description);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, N_InvverDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("n_invver", n_invver);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mInventorys.size();
    }

    public void update(ArrayList<N_Invver> data, boolean merge) {
        if (merge && mInventorys.size() > 0) {
            for (int i = 0; i < mInventorys.size(); i++) {
                Log.i(TAG, "mItems=" + mInventorys.get(i).invvernum);
                N_Invver obj = mInventorys.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).invvernum == obj.invvernum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(obj);
            }
        }
        mInventorys = data;

        notifyDataSetChanged();
    }

    public void adddate(ArrayList<N_Invver> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!mInventorys.contains(data.get(i))) {
                    mInventorys.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * CardView*
         */
        public RelativeLayout cardView;

        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 描述*
         */
        public TextView itemDesc;

        public ViewHolder(View view) {
            super(view);
            cardView = (RelativeLayout) view.findViewById(R.id.card_container);
            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }
}

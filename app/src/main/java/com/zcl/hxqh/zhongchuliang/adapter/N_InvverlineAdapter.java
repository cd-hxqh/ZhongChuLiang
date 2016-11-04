package com.zcl.hxqh.zhongchuliang.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;
import com.zcl.hxqh.zhongchuliang.view.activity.N_InvverDetailActivity;
import com.zcl.hxqh.zhongchuliang.view.activity.N_InvverlineDetailActivity;
import com.zcl.hxqh.zhongchuliang.view.activity.PoMatrectransActivity;

import java.util.ArrayList;

/**
 * Created by apple on 15/12/12.
 */
public class N_InvverlineAdapter extends RecyclerView.Adapter<N_InvverlineAdapter.ViewHolder> {

    private static final String TAG = "N_InvverlineAdapter";
    N_InvverDetailActivity activity;
    String location;
    public ArrayList<N_Invverline> mItems = new ArrayList<N_Invverline>();

    public N_InvverlineAdapter(N_InvverDetailActivity activity,String location) {
        this.activity = activity;
        this.location = location;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item2, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final N_Invverline item = mItems.get(i);


        viewHolder.itemNum_title.setText(R.string.item_num_text);
        viewHolder.itemNumber_title.setText("实际库存:");
        viewHolder.itemNum.setText(item.itemnum == null ? "" : item.itemnum);
        viewHolder.itemDesc.setText(item.itemdesc);
        viewHolder.itemNumber.setText(item.physcnt);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, N_InvverlineDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("N_Invverline", item);
                bundle.putString("location", location);
//                bundle.putInt("mark", activity.mark);
                intent.putExtras(bundle);
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(ArrayList<N_Invverline> data, boolean merge) {
        if (merge && mItems.size() > 0) {
            for (int i = 0; i < mItems.size(); i++) {
                Log.i(TAG, "mItems=" + mItems.get(i).itemnum);
                N_Invverline obj = mItems.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).itemnum == obj.itemnum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(obj);
            }
        }
        mItems = data;

        notifyDataSetChanged();
    }

    public void update(N_Invverline matrectrans) {
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).itemnum.equals(matrectrans.itemnum)) {
                mItems.set(i, matrectrans);
            }
        }
        notifyDataSetChanged();
    }

    public void adddate(ArrayList<N_Invverline> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (mItems.size() > 0) {
                    for (int j = 0; j < mItems.size(); j++) {
                        if (mItems.get(j).itemnum.equals(data.get(i).itemnum)) {
                            mItems.remove(j);
                        }
                    }
                }
            }
            mItems.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void remove(String itemnum) {
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).itemnum.equals(itemnum)) {
                mItems.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    public void removeAllData() {
        if (mItems.size() > 0) {
            mItems.removeAll(mItems);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * CardView*
         */
        public CardView cardView;

        /**
         * 编号*
         */
        public TextView itemNum_title;

        /**
         * 项目*
         */
        public TextView itemNum;
        /**
         * 描述*
         */
        public TextView itemDesc;
        /**
         * 数量*
         */
        public TextView itemNumber_title;
        public TextView itemNumber;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);
            itemNum_title = (TextView) view.findViewById(R.id.item_num_title);
            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
            itemNumber_title = (TextView) view.findViewById(R.id.item_binnum_title);
            itemNumber = (TextView) view.findViewById(R.id.item_binnum_text);
        }
    }
}

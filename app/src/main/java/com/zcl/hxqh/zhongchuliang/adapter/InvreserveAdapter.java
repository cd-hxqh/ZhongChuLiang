package com.zcl.hxqh.zhongchuliang.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcl.hxqh.zhongchuliang.R;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.model.Poline;
import com.zcl.hxqh.zhongchuliang.view.activity.InvreserveDetailActivity;
import com.zcl.hxqh.zhongchuliang.view.activity.InvreserveListActivity;
import com.zcl.hxqh.zhongchuliang.view.activity.PoLineActivity;
import com.zcl.hxqh.zhongchuliang.view.activity.PolineDetailActivity;

import java.util.ArrayList;

/**
 * Created by think on 15/12/16.
 */
public class InvreserveAdapter extends RecyclerView.Adapter<InvreserveAdapter.ViewHolder> {

    private static final String TAG = "InvreserveAdapter";
    InvreserveListActivity activity;
    ArrayList<Invreserve> invreserves = new ArrayList<Invreserve>();

    public InvreserveAdapter(InvreserveListActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_check, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Invreserve invreserve = invreserves.get(i);


        viewHolder.itemNum.setText(invreserve.itemnum);
        viewHolder.itemDesc.setText(invreserve.description);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, InvreserveDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("invreserve", invreserve);
                bundle.putString("wonum", activity.wonum);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                invreserve.ischeck = isChecked;
            }
        });

    }

    @Override
    public int getItemCount() {
        return invreserves.size();
    }

    public void update(ArrayList<Invreserve> data, boolean merge) {
        if (merge && invreserves.size() > 0) {
            for (int i = 0; i < invreserves.size(); i++) {
                Invreserve obj = invreserves.get(i);
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
        invreserves = data;

        notifyDataSetChanged();
    }

    public void adddate(ArrayList<Invreserve> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!invreserves.contains(data.get(i))) {
                    invreserves.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public void update(Invreserve invreserve) {
        for (int i = 0; i < invreserves.size(); i++) {
            if (invreserves.get(i).itemnum.equals(invreserve.itemnum)) {
                invreserves.set(i, invreserve);
            }
        }
        notifyDataSetChanged();
    }

    public ArrayList<Invreserve> getChecked(){
        ArrayList<Invreserve> list = new ArrayList<>();
        for (Invreserve invreserve : invreserves){
            if (invreserve.ischeck){
                list.add(invreserve);
            }
        }
        return list;
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

        /**
         * 勾选框
         */
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            cardView = (RelativeLayout) view.findViewById(R.id.card_container);
            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }
}

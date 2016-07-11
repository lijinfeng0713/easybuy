package com.gentlemen.easybuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gentlemen.easybuy.R;
import com.gentlemen.easybuy.model.Good;

import java.util.List;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class GoodAdapter extends BaseAdapter {

    private List<Good> list;
    private Context context;

    public GoodAdapter (Context context, List<Good> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.goods,null);
            holder = new ViewHolder();

            holder.nameTv = (TextView) convertView.findViewById(R.id.name);
            holder.priceTv = (TextView) convertView.findViewById(R.id.price);
            holder.description = (TextView) convertView.findViewById(R.id.description);

            holder.nameTv.setText(list.get(position).getName());
            holder.description.setText(list.get(position).getDescription());
            holder.priceTv.setText(String.valueOf(list.get(position).getPrice())+"元/"+list.get(position).getUnit());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTv;
        TextView priceTv;
        TextView description;
    }
}

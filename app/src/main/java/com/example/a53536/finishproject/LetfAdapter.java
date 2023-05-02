package com.example.a53536.finishproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LetfAdapter extends BaseAdapter{
    private List<String> goosList;
    private Context context;
    private String typeW="";
    public LetfAdapter(List<String> goosList, Context context){
        this.goosList=goosList;
        this.context=context;
    }
    public void select(String typeW){
        this.typeW=typeW;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return goosList.size();
    }

    @Override
    public Object getItem(int position) {
        return goosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null) {
            viewHolder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_left, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(goosList.get(position)+ "");

        return convertView;
    }
    //  适配器中的GridView缓存类
    class ViewHolder {
        TextView tvName;
    }
}
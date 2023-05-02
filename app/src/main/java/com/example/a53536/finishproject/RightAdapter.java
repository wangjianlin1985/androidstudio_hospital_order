package com.example.a53536.finishproject;

/**
 * Created by 53536 on 2021/5/24.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class RightAdapter extends BaseAdapter{
    private List<MyContent> goosList;
    private Context context;
    public RightAdapter(List<MyContent> goosList, Context context){
        this.goosList=goosList;
        this.context=context;

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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_right, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvType = (TextView) convertView.findViewById(R.id.tvType);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(goosList.get(position).name+ "");
        viewHolder.tvType.setText(goosList.get(position).type+"");

        String  type=goosList.get(position).type;

        if (position == 0) {
            viewHolder.tvType.setVisibility(View.VISIBLE);
        } else {
            String nextType = goosList.get(position - 1).type;
            if (type.equals(nextType)) {
                viewHolder.tvType.setVisibility(View.GONE);
            } else {
                viewHolder.tvType.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }
    //  适配器中的GridView缓存类
    class ViewHolder {
        TextView tvName;
        TextView tvType;

    }
}
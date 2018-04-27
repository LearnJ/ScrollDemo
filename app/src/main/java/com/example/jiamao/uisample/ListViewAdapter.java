package com.example.jiamao.uisample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jiamao on 2018/4/26.
 */

public class ListViewAdapter extends BaseAdapter {

    private List<ItemInfo>itemInfos;
    private Context mcontext;
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ListViewAdapter(List<ItemInfo> itemInfos, Context mcontext) {
        this.itemInfos = itemInfos;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return itemInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return itemInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder=null;
        ItemInfo info=itemInfos.get(position);

        if (convertView==null) {
            viewHolder=new ViewHolder();
            convertView=LayoutInflater.from(mcontext).inflate(R.layout.layout_item, parent, false);

            viewHolder.leftView=convertView.findViewById(R.id.left);
            viewHolder.sureView=convertView.findViewById(R.id.right_sure);
            viewHolder.cancelView=convertView.findViewById(R.id.right_cancel);
            viewHolder.scrollItem=convertView.findViewById(R.id.scrollItem);
            convertView.setTag(viewHolder);

        }else{

            viewHolder= (ViewHolder) convertView.getTag();

        }
        CustomScrollView.scrollViews.add(viewHolder.scrollItem);

        viewHolder.leftView.setText(info.getLeft());
//        viewHolder.sureView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (clickListener!=null){
//                    clickListener.onSureClick(v);
//                }
//            }
//        });
//
//        viewHolder.cancelView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clickListener!=null){
//                    clickListener.onCancelClick(v);
//                }
//            }
//        });
//        viewHolder.scrollItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clickListener!=null){
//                    clickListener.onItemClick(v);
//                }
//            }
//        });

        return convertView;
    }

    public static class ViewHolder{
        public TextView leftView;
        public TextView sureView;
        public TextView cancelView;
        public CustomScrollView scrollItem;

    }


    public interface ClickListener{

        void onSureClick(View v);

        void onCancelClick(View v);

        void onItemClick(View v);

    }


}

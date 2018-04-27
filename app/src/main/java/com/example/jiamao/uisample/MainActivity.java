package com.example.jiamao.uisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomTextView customTextView;

    private List<ItemInfo>itemInfos;
    private CustomListView mListView;
    private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customTextView=findViewById(R.id.numTV);
        mListView=findViewById(R.id.listView);
        customTextView.setNumText(100,5000);
        initTestDatas();
        adapter=new ListViewAdapter(itemInfos,this);
        mListView.setAdapter(adapter);
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"点击le",Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setClickListener(new ListViewAdapter.ClickListener() {
            @Override
            public void onSureClick(View v) {
                Toast.makeText(MainActivity.this,"点击le确定",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelClick(View v) {
                Toast.makeText(MainActivity.this,"点击le取消",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View v) {
                Toast.makeText(MainActivity.this,"点击leItem",Toast.LENGTH_SHORT).show();
            }
        });

    }

    void initTestDatas(){
        itemInfos=new ArrayList<ItemInfo>();

        for (int i=0;i<30;i++){
            ItemInfo info=new ItemInfo();

            info.setLeft("测试条目"+i);
            itemInfos.add(info);
        }
    }
}

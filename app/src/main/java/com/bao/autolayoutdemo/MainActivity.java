package com.bao.autolayoutdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.id_RecycleView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> mDatas = new ArrayList<>();
        mDatas.add("王大炮");
        mDatas.add("张小山");
        mDatas.add("Jack Li");
        LinearLayoutManager ll = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ll);
        recyclerView.setAdapter(new MyAdapter(this,mDatas));
    }

    private class MyAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private List<String> mDatas;
        public MyAdapter(Context context, List<String> mDatas) {
            this.mContext = context;
            this.mDatas = mDatas;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            RecyclerView.ViewHolder viewHolder = new MyViewHolder(View.inflate(mContext,R.layout.layout_item,null));
//            return viewHolder;
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item,viewGroup,false);
            return new MyViewHolder(convertView);//使用三个参数的方法,否则AutoLayout不起作用
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((MyViewHolder)viewHolder).tvName.setText(mDatas.get(i));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvName;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.id_tv_name);
            }
        }
    }
}

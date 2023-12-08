package com.example.jingnews.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jingnews.NewsText;
import com.example.jingnews.databinding.RecyclerViewItemBinding;
import com.example.jingnews.enetity.News;
import com.example.jingnews.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyRecycleViewHolder> {
    private List<News> news;
    private LayoutInflater inflater;
    public MyRecycleAdapter(List<News> news, LayoutInflater inflater){
        this.news=news;
        this.inflater=inflater; }
    @NonNull
    @Override
    public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewItemBinding recyclerViewItemBinding=RecyclerViewItemBinding.inflate(inflater,parent,false);
        MyRecycleViewHolder viewHolder=new MyRecycleViewHolder(recyclerViewItemBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewHolder holder, int position) {
        holder.bindingData(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class MyRecycleViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewItemBinding recyclerViewItemBinding;
        public MyRecycleViewHolder(@NonNull RecyclerViewItemBinding binding) {
            super(binding.getRoot());
            recyclerViewItemBinding=binding;

        }
        public void bindingData(News news){
            recyclerViewItemBinding.newsTitle.setText(news.getNewstitle());
            recyclerViewItemBinding.newsContent.setText(news.getNewsdetail());
            recyclerViewItemBinding.newsDate.setText(news.getNewsdate());
            recyclerViewItemBinding.imageView2.setBackgroundResource(news.getNewsphoto());
            recyclerViewItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //startActivity(new Intent(getApplicationContext(), PersonalChange.class));
                    String id = news.getNewsid();
                    Log.d("tt", "你点击的是" + news.getNewsid());
                    new Thread() {
                        @Override
                        public void run() {
                            Connection connection= JDBCUtils.getConn();
                            try {
                                String sql="update news set look=1 where id=?";
                                if(connection!=null){
                                    PreparedStatement ps=connection.prepareStatement(sql);
                                    if(ps!=null){
                                        ps.setString(1,id);
                                        //ResultSet rs=ps.executeQuery();
                                        int count = ps.executeUpdate();
                                        Log.d("tt","look修改成功");
                                        connection.close();
                                        ps.close();
                                    }else{
                                        Log.d("tt","ps为空");
                                    }
                                }else{
                                    Log.d("tt","connection为空");
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                Log.d("tt","数据库异常");
                            }
                        }
                    }.start();
                    Intent intent=new Intent(view.getContext(), NewsText.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

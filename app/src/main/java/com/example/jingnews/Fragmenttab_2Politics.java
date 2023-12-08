package com.example.jingnews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jingnews.adapters.MyRecycleAdapter;
import com.example.jingnews.databinding.Fragmenttab2politicsBinding;
import com.example.jingnews.enetity.News;
import com.example.jingnews.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmenttab_2Politics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmenttab_2Politics extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<News> newslist0;
    private Fragmenttab2politicsBinding binding;
    public Fragmenttab_2Politics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment5.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmenttab_2Politics newInstance(String param1, String param2) {
        Fragmenttab_2Politics fragment = new Fragmenttab_2Politics();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= Fragmenttab2politicsBinding.inflate(inflater,container,false);
//        return inflater.inflate(R.layout.fragmenttab_1recommend, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newslist0=new ArrayList<>();

        new Thread(){
            @Override
            public void run() {
               /* LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.item, null);*/

                Connection connection = JDBCUtils.getConn();
                try {
                    String sql = "select * from news where typ=?";
                    if (connection != null) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (ps != null) {

                            ps.setString(1, "1");
                            ResultSet rs = ps.executeQuery();
                            //int count=rs.getMetaData().getColumnCount();
                            while (rs.next()) {
                                String newsid = rs.getString(1);
                                String newstitle = rs.getString(2);
                                String newsdetail = rs.getString(3);
                                String newstype = rs.getString(4);
                                String newsdate=rs.getString(5);
                                String newstext=rs.getString(6);
                                int newsphoto=rs.getInt(7);
                                News news = new News(newsid, newstitle, newsdetail, newstype,newsdate,newstext,newsphoto);
                                newslist0.add(news);
                            }

                            getActivity().runOnUiThread(() -> {
                                binding.myRecycleView4.setAdapter(new MyRecycleAdapter(newslist0,getLayoutInflater()));
                                Log.d("123", "onViewCreated: "+newslist0.size());
                                binding.myRecycleView4.setLayoutManager(new LinearLayoutManager(getContext()));
                            });

                        } else {
                            Log.d("读取", "ps为空");
                        }
                    } else {
                        Log.d("读取", "未连接");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("读取", "数据库异常");
                }
            }
        }.start();
        Log.d("123", "run: 线程结束"+newslist0.size());
    }
}
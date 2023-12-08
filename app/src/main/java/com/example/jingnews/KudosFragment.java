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
import com.example.jingnews.databinding.FragmentKudosBinding;
import com.example.jingnews.enetity.News;
import com.example.jingnews.utils.JDBCUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KudosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KudosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<News> newslist;
    private FragmentKudosBinding binding;
    private boolean isHandleBack = true;
    private String userid;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KudosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KudosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KudosFragment newInstance(String param1, String param2) {
        KudosFragment fragment = new KudosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public boolean onBackPressed() {

        if (isHandleBack) {
            //Toast.makeText(getActivity(), "点击了返回键", Toast.LENGTH_SHORT).show();
            return isHandleBack;
        }

        return isHandleBack;
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
        binding= FragmentKudosBinding.inflate(inflater,container,false);
//        return inflater.inflate(R.layout.fragmenttab_1recommend, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        com.google.android.material.bottomnavigation.BottomNavigationView com = getActivity().findViewById(R.id.bottomNavigationView);
        com.setVisibility(BottomNavigationView.GONE);
        newslist = new ArrayList<>();

        new Thread() {
            @Override
            public void run() {
               /* LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.item, null);*/
                selectid();
                    Connection connection = JDBCUtils.getConn();
                    try {
                        String sql = "select * from kudos where userid=?";
                        if (connection != null) {
                            PreparedStatement ps = connection.prepareStatement(sql);
                            if (ps != null) {
                                ps.setString(1, userid);
                                ResultSet rs = ps.executeQuery();
                                //int count=rs.getMetaData().getColumnCount();
                                while (rs.next()) {
                                    String newsid = rs.getString(1);
                                    String userid = rs.getString(2);
                                    String newstitle = rs.getString(3);
                                    String newsdetail = rs.getString(4);
                                    String newsdate = rs.getString(5);
                                    int newsphoto = rs.getInt(6);
                                    String newsautor = rs.getString(7);
                                    News news = new News(newsid, newstitle, newsdetail, newsdate, newsphoto, newsautor);
                                    newslist.add(news);
                                }

                                getActivity().runOnUiThread(() -> {
                                    binding.myRecycleView0.setAdapter(new MyRecycleAdapter(newslist, getLayoutInflater()));
                                    Log.d("123", "onViewCreated: " + newslist.size());
                                    binding.myRecycleView0.setLayoutManager(new LinearLayoutManager(getContext()));
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
        Log.d("123", "run: 线程结束" + newslist.size());
    }

    public void selectid(){

        /*new Thread(){
            @Override
            public void run() {*/
        Connection connection=JDBCUtils.getConn();
        try {
            String sql="select id from user where login=?";
            if(connection!=null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1, "1");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        userid=rs.getString(1);
                        //Log.d("a",userid+"+"+nwid);
                    }
                }else{
                    Log.d("a","ps为空");
                }
            }else{
                Log.d("a","connection为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("a","数据库异常");
        }
           /* }
        }.start();*/
    }
}
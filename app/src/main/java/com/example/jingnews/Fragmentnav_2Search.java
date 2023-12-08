package com.example.jingnews;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jingnews.adapters.MyRecycleAdapter;
import com.example.jingnews.databinding.Fragmentnav2searchBinding;
import com.example.jingnews.enetity.News;
import com.example.jingnews.utils.JDBCUtils;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentnav_2Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentnav_2Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<News> newslist;
    private List<News> newslist0;
    private Fragmentnav2searchBinding binding;

    public Fragmentnav_2Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentnav_2Search newInstance(String param1, String param2) {
        Fragmentnav_2Search fragment = new Fragmentnav_2Search();
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
        binding= Fragmentnav2searchBinding.inflate(inflater,container,false);
//        return inflater.inflate(R.layout.fragmenttab_1recommend, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(){
            @Override
            public void run(){
                selecthot();
            }
        }.start();
        newslist = new ArrayList<>();
        newslist0 = new ArrayList<>();
        SearchView msearch=getActivity().findViewById(R.id.sech);
        msearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (TextUtils.isEmpty(query)) {
                    Snackbar.make(view,"请输入查找内容111",Snackbar.LENGTH_SHORT).show();

                } else {

                    LinearLayout hot=getActivity().findViewById(R.id.hotsearch);
                    hot.setVisibility(LinearLayout.GONE);
                    Snackbar.make(view,query,Snackbar.LENGTH_SHORT).show();
                    new Thread(){
                        @Override
                        public void run(){
                            HashMap<String, Object> map = new HashMap<>();
                            Connection connection= JDBCUtils.getConn();
                            try{
                                String sql="select * from news where keywords= ?";
                                if(connection!=null){
                                    PreparedStatement ps = connection.prepareStatement(sql);
                                    if(ps!=null){
                                        Log.d("dd",query);
                                        ps.setString(1, query);
                                        ResultSet rs = ps.executeQuery();
                                        while (rs.next()) {
                                            String newsid = rs.getString(1);
                                            String newstitle=rs.getString(2);
                                            String newsdetail = rs.getString(3);
                                            String newstype=rs.getString(4);
                                            String newsdate = rs.getString(5);
                                            String newsmaintext=rs.getString(6);
                                            int newsphoto = rs.getInt(7);
                                            String newslook=rs.getString(8);
                                            String newsendtext=rs.getString(9);
                                            String newsmiddletext=rs.getString(10);
                                            String newsfinallytext=rs.getString(11);
                                            String newsautor=rs.getString(12);
                                            String newskeywords=rs.getString(13);
                                            News news = new News(newsid,newstitle, newsdetail, newsdate, newsphoto,newsautor);
                                            newslist.add(news);
                                        }
                                        getActivity().runOnUiThread(() -> {
                                            /*LinearLayout hot=getActivity().findViewById(R.id.hotsearch);
                                            hot.setVisibility(LinearLayout.GONE);*/
                                            //binding.hotsearch.setVisibility(LinearLayout.GONE);
                                            binding.myRecycleView2.setAdapter(new MyRecycleAdapter(newslist, getLayoutInflater()));
                                            Log.d("123", "onViewCreated: " + newslist.size());
                                            binding.myRecycleView2.setLayoutManager(new LinearLayoutManager(getContext()));
                                            newslist = new ArrayList<>();
                                        });
                                        connection.close();
                                        ps.close();
                                    }else{
                                        Log.d("dd","搜索ps为空");
                                    }
                                }else {
                                    Log.d("dd","搜索connection为空");
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                                Log.d("dd","搜索数据库异常");
                            }

                      }
                    }.start();
                }
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    //Snackbar.make(view,"请输入查找内容222",Snackbar.LENGTH_SHORT).show();
                }else{
                    //Snackbar.make(view,newText,Snackbar.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public void selecthot(){
               /* LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.item, null);*/

            Connection connection = JDBCUtils.getConn();
            try {
                String sql = "select * from news where typ=?";
                if (connection != null) {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    if (ps != null) {

                        ps.setString(1, "0");
                        ResultSet rs = ps.executeQuery();
                        //int count=rs.getMetaData().getColumnCount();
                        while (rs.next()) {
                            String newsid = rs.getString(1);
                            String newstitle = rs.getString(2);
                            String newsdetail = rs.getString(3);
                            String newstype = rs.getString(4);
                            String newsdate = rs.getString(5);
                            String newstext = rs.getString(6);
                            int newsphoto = rs.getInt(7);
                            News news = new News(newsid, newstitle, newsdetail, newstype, newsdate, newstext, newsphoto);
                            newslist0.add(news);
                        }

                        getActivity().runOnUiThread(() -> {
                            binding.myRecycleView3.setAdapter(new MyRecycleAdapter(newslist0, getLayoutInflater()));
                            Log.d("123", "onViewCreated: " + newslist0.size());
                            binding.myRecycleView3.setLayoutManager(new LinearLayoutManager(getContext()));
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
}
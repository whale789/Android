package com.example.jingnews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jingnews.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NewsText extends AppCompatActivity implements View.OnClickListener {
    private String userid;
    private String nwid;
    private String nwtitle;
    private String nwdetail;
    private String nwtype;
    private String nwdate;
    private String nwtext;
    private int nwphoto;
    private String nwlook;
    private String nwetext;
    private String nwmtext;
    private String nwftext;
    private String nwautor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_text);
        LinearLayout kudo=findViewById(R.id.kudos);
        kudo.setOnClickListener(this);
        LinearLayout conn=findViewById(R.id.conn);
        conn.setOnClickListener(this);
        ImageView kudopic=findViewById(R.id.kudospic);
        new Thread(){
            @Override
            public void run(){
                Connection connection= JDBCUtils.getConn();
                try{
                    String sql="select * from news where look=?";
                    if(connection!=null){
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if(ps!=null){
                            ps.setString(1, "1");
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()){
                                nwid=rs.getString(1);
                                nwtitle=rs.getString(2);
                                nwdetail=rs.getString(3);
                                nwtype=rs.getString(4);
                                nwdate=rs.getString(5);
                                nwtext=rs.getString(6);
                                nwphoto=rs.getInt(7);
                                nwlook=rs.getString(8);
                                nwetext=rs.getString(9);
                                nwmtext=rs.getString(10);
                                nwftext=rs.getString(11);
                                nwautor=rs.getString(12);
                                selectid();
                                add();
                                selectkudos();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView newstitle=findViewById(R.id.ntitle);
                                        newstitle.setText(nwtitle);
                                        TextView newsdetail=findViewById(R.id.ndetail);
                                        newsdetail.setText(nwdetail);
                                        TextView newsdate=findViewById(R.id.ndate);
                                        newsdate.setText(nwdate);
                                        TextView newstext=findViewById(R.id.ntext);
                                        newstext.setText(nwtext);
                                        ImageView newsphoto=findViewById(R.id.nphoto);
                                        newsphoto.setBackgroundResource(nwphoto);
                                        TextView newsendtext=findViewById(R.id.netext);
                                        newsendtext.setText(nwetext);
                                        TextView newsmiddletext=findViewById(R.id.nmtext);
                                        newsmiddletext.setText(nwmtext);
                                        TextView newsfinallytext=findViewById(R.id.nftext);
                                        newsfinallytext.setText(nwftext);
                                        TextView newsautor=findViewById(R.id.nautor);
                                        newsautor.setText(nwautor);
                                    }
                                });
                            }
                        }else{
                            Log.d("t","ps为空");
                        }
                    }else{
                        Log.d("t","connection为空");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("t","数据库异常");
                }

            }
        }.start();




    }

    public void selectkudos(){
        Connection connection=JDBCUtils.getConn();
        try{
            String sql="select * from kudos where userid=? and newsid=?";
            if(connection!=null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,userid);
                    ps.setString(2,nwid);
                    ResultSet rs=ps.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    if(rs.next())
                    {
                        ImageView kudopic=findViewById(R.id.kudospic);
                        kudopic.setBackgroundResource(R.drawable.kudosd);
                    }
                    else{

                        ImageView kudopic=findViewById(R.id.kudospic);
                        kudopic.setBackgroundResource(R.drawable.kudosw);
                    }
                    connection.close();
                    ps.close();
                }else{
                    Log.d("t","挑选点赞图片ps为空");
                }
            }else{
                Log.d("t","挑选点赞图片connection为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("t","挑选点赞图片数据库异常");
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new Thread() {
            @Override
            public void run() {
                Connection connection= JDBCUtils.getConn();
                try {
                    String sql="update news set look=0 where look=?";
                    if(connection!=null){
                        PreparedStatement ps=connection.prepareStatement(sql);
                        if(ps!=null){
                            ps.setString(1,"1");
                            //ResultSet rs=ps.executeQuery();
                            int count = ps.executeUpdate();
                            Log.d("ttt","look修改成功");
                            connection.close();
                            ps.close();
                        }else{
                            Log.d("ttt","ps为空");
                        }
                    }else{
                        Log.d("ttt","connection为空");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("ttt","数据库异常");
                }
            }
        }.start();
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
                                Log.d("a",userid+"+"+nwid);
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

    public void add(){
        /*new Thread(){
            @Override
            public void run() {*/
                Connection connection=JDBCUtils.getConn();
                try {
                    String sql="insert into history(newsid,userid,newstitle,newsdatail,newsdate,newsphoto,newsautor) value(?,?,?,?,?,?,?)";
                    if(connection!=null){
                        PreparedStatement ps=connection.prepareStatement(sql);
                        if(ps!=null){
                            ps.setString(1,nwid);
                            ps.setString(2,userid);
                            ps.setString(3,nwtitle);
                            ps.setString(4,nwdetail);
                            ps.setString(5,nwdate);
                            ps.setInt(6,nwphoto);
                            ps.setString(7,nwautor);
                            Log.d("aa",nwid+userid+nwtitle);
                            int count = ps.executeUpdate();
                            if(count>0){
                                Log.d("aa","不重复增加成功");
                            }else{
                                Log.d("aa","重复，增加失败");
                            }
                            Log.d("aa","增加成功");
                            connection.close();
                            ps.close();
                        }else{
                            Log.d("aa","ps为空");
                        }
                    }else{
                        Log.d("aa","connection为空");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("aa","数据库异常");
                }
           /* }
        }.start();*/
    }

    public void delkudos(){
        Connection connection=JDBCUtils.getConn();
        try{
            String sql="delete from kudos where userid=? and newsid=?";
            if(connection!=null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,userid);
                    ps.setString(2,nwid);
                    int count = ps.executeUpdate();
                    Log.d("t","删除点赞成功");
                    connection.close();
                    ps.close();
                }else{
                    Log.d("t","删除点赞ps为空");
                }
            }else{
                Log.d("t","删除点赞connection为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("bb","数据库异常");
        }
    }


    public void addkudos(){
        Connection connection=JDBCUtils.getConn();
        try {
            String sql="insert into kudos(newsid,userid,newstitle,newsdatail,newsdate,newsphoto,newsautor) value(?,?,?,?,?,?,?)";
            if(connection!=null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,nwid);
                    ps.setString(2,userid);
                    ps.setString(3,nwtitle);
                    ps.setString(4,nwdetail);
                    ps.setString(5,nwdate);
                    ps.setInt(6,nwphoto);
                    ps.setString(7,nwautor);
                    int count = ps.executeUpdate();
                    Log.d("aaa",nwid+userid+nwtitle);
                    if(count>0){
                        Log.d("aaa","不重复增加成功");
                    }else{
                        Log.d("aaa","重复，增加失败");
                    }
                    Log.d("aaa","增加成功");
                    connection.close();
                    ps.close();
                }else{
                    Log.d("aa","ps为空");
                }
            }else{
                Log.d("aa","connection为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("aa","数据库异常");
        }
    }

    public void delconn(){
        Connection connection=JDBCUtils.getConn();
        try{
            String sql="delete from connection where userid=? and newsid=?";
            if(connection!=null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,userid);
                    ps.setString(2,nwid);
                    int count = ps.executeUpdate();
                    Log.d("t","删除点赞成功");
                    connection.close();
                    ps.close();
                }else{
                    Log.d("t","删除点赞ps为空");
                }
            }else{
                Log.d("t","删除点赞connection为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("bb","数据库异常");
        }
    }

    public void addconn(){
        Connection connection=JDBCUtils.getConn();
        try {
            String sql="insert into connection value(?,?,?,?,?,?,?)";
            if(connection!=null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,nwid);
                    ps.setString(2,userid);
                    ps.setString(3,nwtitle);
                    ps.setString(4,nwdetail);
                    ps.setString(5,nwdate);
                    ps.setInt(6,nwphoto);
                    ps.setString(7,nwautor);
                    int count = ps.executeUpdate();
                    Log.d("aaa",nwid+userid+nwtitle);
                    if(count>0){
                        Log.d("aaa","不重复增加成功");
                    }else{
                        Log.d("aaa","重复，增加失败");
                    }
                    Log.d("aaa","增加成功");
                    connection.close();
                    ps.close();
                }else{
                    Log.d("aa","ps为空");
                }
            }else{
                Log.d("aa","connection为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("aa","数据库异常");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kudos:
                if(userid==null)
                {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }else {
                    ImageView kudos = findViewById(R.id.kudospic);
                    new Thread() {
                        public void run() {
                            Connection connection = JDBCUtils.getConn();
                            try {
                                String sql = "select * from kudos where userid=? and newsid=?";
                                if (connection != null) {
                                    PreparedStatement ps = connection.prepareStatement(sql);
                                    if (ps != null) {
                                        ps.setString(1, userid);
                                        ps.setString(2, nwid);
                                        ResultSet rs = ps.executeQuery();
                                        int count = rs.getMetaData().getColumnCount();
                                        if (rs.next()) {
                                            ImageView kudopic = findViewById(R.id.kudospic);
                                            kudopic.setBackgroundResource(R.drawable.kudosw);
                                            delkudos();
                                        } else {
                                            ImageView kudopic = findViewById(R.id.kudospic);
                                            kudopic.setBackgroundResource(R.drawable.kudosd);
                                            addkudos();
                                        }
                                        connection.close();
                                        ps.close();
                                    } else {
                                        Log.d("t", "挑选点赞图片ps为空");
                                    }
                                } else {
                                    Log.d("t", "挑选点赞图片connection为空");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("t", "挑选点赞图片数据库异常");
                            }
                        }
                    }.start();
                }
                break;
            case R.id.conn:
                if(userid==null){
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }else {
                    new Thread() {
                        public void run() {
                            Connection connection = JDBCUtils.getConn();
                            try {
                                String sql = "select * from connection where userid=? and newsid=?";
                                if (connection != null) {
                                    PreparedStatement ps = connection.prepareStatement(sql);
                                    if (ps != null) {
                                        ps.setString(1, userid);
                                        ps.setString(2, nwid);
                                        ResultSet rs = ps.executeQuery();
                                        int count = rs.getMetaData().getColumnCount();
                                        if (rs.next()) {
                                            ImageView kudopic = findViewById(R.id.connpic);
                                            kudopic.setBackgroundResource(R.drawable.connd);
                                            delconn();
                                        } else {
                                            ImageView kudopic = findViewById(R.id.connpic);
                                            kudopic.setBackgroundResource(R.drawable.connw);
                                            addconn();
                                        }
                                        connection.close();
                                        ps.close();
                                    } else {
                                        Log.d("t", "挑选点赞图片ps为空");
                                    }
                                } else {
                                    Log.d("t", "挑选点赞图片connection为空");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("t", "挑选点赞图片数据库异常");
                            }
                        }
                    }.start();
                }
                break;
        }
    }
}
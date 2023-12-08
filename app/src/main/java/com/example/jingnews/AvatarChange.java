package com.example.jingnews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jingnews.circle.CircleImageView;
import com.example.jingnews.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AvatarChange extends AppCompatActivity implements View.OnClickListener {

    Button avatarsave;
    ImageView a1,a2,a3,a4,a7,a8,a9,a10,a11,a12,a13,a14;
    CircleImageView a0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_change);
        /*avatarsave=findViewById(R.id.avatarsave);
        avatarsave.setOnClickListener(this);*/
        a0=findViewById(R.id.a0);
        a0.setOnClickListener(this);
        a1=findViewById(R.id.a1);
        a1.setOnClickListener(this);
        a2=findViewById(R.id.a2);
        a2.setOnClickListener(this);
        a3=findViewById(R.id.a3);
        a3.setOnClickListener(this);
        a4=findViewById(R.id.a4);
        a4.setOnClickListener(this);
        a7=findViewById(R.id.a7);
        a7.setOnClickListener(this);
        a8=findViewById(R.id.a8);
        a8.setOnClickListener(this);
        a9=findViewById(R.id.a9);
        a9.setOnClickListener(this);
        a10=findViewById(R.id.a10);
        a10.setOnClickListener(this);
        a11=findViewById(R.id.a11);
        a11.setOnClickListener(this);
        a12=findViewById(R.id.a12);
        a12.setOnClickListener(this);
        a13=findViewById(R.id.a13);
        a13.setOnClickListener(this);
        a14=findViewById(R.id.a14);
        a14.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.a1:
                a0.setBackgroundResource(R.drawable.avatar1);
                changeavatar("1");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a2:
                a0.setBackgroundResource(R.drawable.avatar2);
                changeavatar("2");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a3:
                a0.setBackgroundResource(R.drawable.avatar3);
                changeavatar("3");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a4:
                a0.setBackgroundResource(R.drawable.avatar4);
                changeavatar("4");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a7:
                a0.setBackgroundResource(R.drawable.avatar7);
                changeavatar("7");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a8:
                a0.setBackgroundResource(R.drawable.avatar8);
                changeavatar("8");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a9:
                a0.setBackgroundResource(R.drawable.avatar9);
                changeavatar("9");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a10:
                a0.setBackgroundResource(R.drawable.avatar10);
                changeavatar("10");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a11:
                a0.setBackgroundResource(R.drawable.avatar11);
                changeavatar("11");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a12:
                a0.setBackgroundResource(R.drawable.avatar12);
                changeavatar("12");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a13:
                a0.setBackgroundResource(R.drawable.avatar13);
                changeavatar("13");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.a14:
                a0.setBackgroundResource(R.drawable.avatar14);
                changeavatar("14");
                Toast.makeText(this, "成功修改头像", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void changeavatar(String s) {
        new Thread() {
            @Override
            public void run() {
                Connection connection = JDBCUtils.getConn();
                try {
                    String sql = "update user set avatar=? where login=1";
                    if (connection != null) {// connection不为null表示与数据库建立了连接
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (ps != null) {
                            ps.setString(1, s);
                            int rs = ps.executeUpdate();
                            if (rs > 0) {
                                Log.d("头像：", "修改成功");
                            }
                            connection.close();
                            ps.close();
                        }
                    }
                } catch (Exception e) {   
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
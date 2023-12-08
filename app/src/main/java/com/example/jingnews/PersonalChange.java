package com.example.jingnews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jingnews.circle.CircleImageView;
import com.example.jingnews.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonalChange extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;
    Button btn;
    private boolean isHandleBack = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_change);
        PersonalChange();
        CircleImageView ava=findViewById(R.id.avatary);
        ava.setOnClickListener(this);
        Button btn=findViewById(R.id.save);
        btn.setOnClickListener(this);
        /*e1=et1.getText().toString();
        Toast.makeText(this, e1, Toast.LENGTH_SHORT).show();
        e2=et2.getText().toString();
        Toast.makeText(this, e2, Toast.LENGTH_SHORT).show();*/
    }
    public void PersonalChange(){
        String id,userAccount1,userPassword,userName,avatar,email;
        new Thread(){
            @Override
            public void run() {
                Connection connection = JDBCUtils.getConn();
                try {
                    String sql = "select * from user where login = ?";
                    if (connection != null) {// connection不为null表示与数据库建立了连接
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (ps != null) {
                            ps.setString(1, "1");
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                //注意：下标是从1开始
                                String id = rs.getString(1);
                                String userAccount1 = rs.getString(2);
                                String userPassword = rs.getString(3);
                                String userName = rs.getString(4);
                                String avatar=rs.getString(5);
                                String email=rs.getString(6);
                                //User user = new User(id, userAccount1, userPassword, userName,avatar,email);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView tv1=findViewById(R.id.userid);
                                        tv1.setText(id);
                                        TextView tv2=findViewById(R.id.useracc);
                                        tv2.setText(userAccount1);
                                        EditText ev1=findViewById(R.id.username);
                                        ev1.setText(userName);
                                        EditText ev2=findViewById(R.id.useremail);
                                        ev2.setText(email);
                                        String a=avatar;
                                        CircleImageView av=findViewById(R.id.avatary);
                                        if(a.equals("0")) {
                                            av.setBackgroundResource(R.drawable.defau);
                                        }else {
                                            if(a.equals("1")) {
                                                av.setBackgroundResource(R.drawable.avatar1);
                                            }else{
                                                if(a.equals("2"))
                                                {
                                                    av.setBackgroundResource(R.drawable.avatar2);
                                                }else{
                                                    if(a.equals("3")){
                                                        av.setBackgroundResource(R.drawable.avatar3);
                                                    }else{
                                                        if(a.equals("4")){
                                                            av.setBackgroundResource(R.drawable.avatar4);
                                                        }else{
                                                            if(a.equals("7")){
                                                                av.setBackgroundResource(R.drawable.avatar7);
                                                            }else{
                                                                if(a.equals("8")){
                                                                    av.setBackgroundResource(R.drawable.avatar8);
                                                                }else{
                                                                    if(a.equals("9")){
                                                                        av.setBackgroundResource(R.drawable.avatar9);
                                                                    }else{
                                                                        if(a.equals("10")) {
                                                                            av.setBackgroundResource(R.drawable.avatar10);
                                                                        }else {
                                                                            if(a.equals("11")){
                                                                                av.setBackgroundResource(R.drawable.avatar11);
                                                                            }else{
                                                                                if(a.equals("12")) {
                                                                                    av.setBackgroundResource(R.drawable.avatar12);
                                                                                }else{
                                                                                    if(a.equals("13")){
                                                                                        av.setBackgroundResource(R.drawable.avatar13);
                                                                                    }else{
                                                                                        if(a.equals("14")){
                                                                                            av.setBackgroundResource(R.drawable.avatar14);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    //Toast.makeText(PersonalChange.this, "查询失败", Toast.LENGTH_SHORT).show();
                    Log.d("jing","查询失败");
                }

            }
        }.start();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatary:
                startActivity(new Intent(getApplicationContext(), AvatarChange.class));
                break;
            case R.id.save:
                EditText et1=(EditText) findViewById(R.id.username);
                EditText et2=(EditText) findViewById(R.id.useremail);
                String e1=et1.getText().toString();
                //Toast.makeText(this, e1, Toast.LENGTH_SHORT).show();
                String e2=et2.getText().toString();
                //Toast.makeText(this, e2, Toast.LENGTH_SHORT).show();
                //break;
                new Thread() {
                    @Override
                    public void run() {
                        Connection connection = JDBCUtils.getConn();
                        try {
                            String sql = "update user set userName=?,email=? where login=1";
                            if (connection != null) {
                                PreparedStatement ps = connection.prepareStatement(sql);
                                if (ps != null) {
                                    ps.setString(1, e1);
                                    ps.setString(2, e2);
                                    int rs = ps.executeUpdate();
                                    if (rs > 0) {
                                        Log.d("修改个人信息", "成功");

                                    } else {
                                        Log.d("修改个人信息", "失败");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
                Toast.makeText(PersonalChange.this, "修改成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            default:
                break;
        }
    }

}
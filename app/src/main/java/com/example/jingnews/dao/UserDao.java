package com.example.jingnews.dao;

import android.util.Log;

import com.example.jingnews.enetity.User;
import com.example.jingnews.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * author: zq
 * date: 2022.03.13
 * **/
public class UserDao {

    private static final String TAG = "mysql-party-UserDao";


    /**
     * 查找登录人员
     */

    public String selectname(){
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        String loginname=null;

        try{
            String sql="select * from user where login=?";
            if (connection != null){
                loginname="连接成功";
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,"1");
                    ResultSet rs=ps.executeQuery();
                    int count=rs.getMetaData().getColumnCount();
                    while (rs.next()){
                        for (int i = 1;i <= count;i++){
                            String field = rs.getMetaData().getColumnName(i);
                            map.put(field, rs.getString(field));
                        }
                    }
                    connection.close();
                    ps.close();
                    if(map.size()!=0){
                        StringBuilder s = new StringBuilder();
                        for (String key : map.keySet()){
                            if(key.equals("userName")) {
                                loginname=(String)map.get(key);
                                Log.d("结果",loginname);
                                break;
                            }else {
                                loginname="找不到";
                            }
                        }
                    }
                    else{
                       loginname="未登录";
                    }
                }
                else {
                    loginname="ps为null";
                }
            }else {
                loginname="未连接";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.d(TAG, "查找失败" + e.getMessage());
            loginname="wu";
        }
        return loginname;
    }

    /**
     * function: 登录
     * */
    public int login(String userAccount, String userPassword){

        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        int msg = 0;
        try {
            // mysql简单的查询语句。这里是根据user表的userAccount字段来查询某条记录
            String sql = "select * from user where userAccount = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    Log.e(TAG,"账号：" + userAccount);
                    //根据账号进行查询
                    ps.setString(1, userAccount);
                    // 执行sql查询语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                        int count = rs.getMetaData().getColumnCount();
                        //将查到的内容储存在map里
                        while (rs.next()){
                            // 注意：下标是从1开始的
                            for (int i = 1;i <= count;i++){
                                String field = rs.getMetaData().getColumnName(i);
                                map.put(field, rs.getString(field));
                            }
                        }
                        connection.close();
                        ps.close();

                    if (map.size()!=0){
                        StringBuilder s = new StringBuilder();
                        //寻找密码是否匹配
                        for (String key : map.keySet()){
                            if(key.equals("userPassword")){
                                if(userPassword.equals(map.get(key))){
                                    msg = 1;            //密码正确
                                }
                                else
                                    msg = 2;            //密码错误
                            }
                            break;
                        }
                    }else {
                        Log.e(TAG, "查询结果为空");
                        msg = 3;
                    }
                }else {
                    msg = 0;
                }
            }else {
                msg = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常login：" + e.getMessage());
            msg = 0;
        }

        if(msg==1)
        {

            HashMap<String, Object> map1 = new HashMap<>();
            // 根据数据库名称，建立连接
            Connection connection1 = JDBCUtils.getConn();
            try {
                String sql1 = "update user set login=1 where userAccount = ?";
                if (connection1 != null){// connection不为null表示与数据库建立了连接
                    Log.e(TAG,"账号：" + userAccount);
                    PreparedStatement ps1 = connection1.prepareStatement(sql1);
                    if (ps1 != null) {
                        Log.d("账号：","不为空");
                        ps1.setString(1,userAccount);
                        int rs1 = ps1.executeUpdate();
                        if(rs1>0){
                            Log.d("账号：","修改成功");
                        }
                        connection1.close();
                        ps1.close();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                Log.d(TAG, "修改登录信息失败");
                msg=0;
            }
        }

        return msg;
    }

    /**
     * 退出登录
     */
    public void loginout(){
        Connection connection=JDBCUtils.getConn();
        int msg=0;
        try {
            String sql="update user set login=0 where login=?";
            if(connection!=null)
            {
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null)
                {
                    ps.setString(1,"1");
                    int rs=ps.executeUpdate();
                    if(rs>0){
                       Log.d("loginout:","退出登录成功");
                    }
                    connection.close();
                    ps.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "退出登录失败：" + e.getMessage());
        }
    }

    /**
     * 确定头像
     */
    public String loadavatar(){
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        String av=null;
        try{
            String sql="select * from user where login=?";
            if (connection != null){
                PreparedStatement ps=connection.prepareStatement(sql);
                if(ps!=null){
                    ps.setString(1,"1");
                    ResultSet rs=ps.executeQuery();
                    int count=rs.getMetaData().getColumnCount();
                    while (rs.next()){
                        for (int i = 1;i <= count;i++){
                            String field = rs.getMetaData().getColumnName(i);
                            map.put(field, rs.getString(field));
                        }
                    }
                    connection.close();
                    ps.close();
                    if(map.size()!=0){
                        StringBuilder s = new StringBuilder();
                        for (String key : map.keySet()){
                            if(key.equals("avatar")) {
                                av=(String)map.get(key);
                                break;
                            }else {
                                av="0";
                            }
                        }
                    }
                    else{
                        av="0";
                    }
                }
                else {
                    av="0";
                }
            }else {
                av="0";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            av="0";
        }
        return av;

    }


    /**
     * function: 注册
     * */
    public boolean register(User user){
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();

        try {
            String sql = "insert into user(userAccount,userPassword,userName,email) values (?,?,?,?)";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库
                    ps.setString(1,user.getUserAccount());
                    ps.setString(2,user.getUserPassword());
                    ps.setString(3,user.getUserName());
                    ps.setString(4,user.getEmail());
                    Log.e("jing",user.getUserAccount());
                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0)
                        return true;
                    else
                        return false;
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常register：" + e.getMessage());
            return false;
        }

    }




    /**
     * function: 根据账号进行查找该用户是否存在
     * */
    public User findUser(String userAccount) {

        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        User user = null;
        try {
            String sql = "select * from user where userAccount = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setString(1, userAccount);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        //注意：下标是从1开始
                        String id = rs.getString(1);
                        String userAccount1 = rs.getString(2);
                        String userPassword = rs.getString(3);
                        String userName = rs.getString(4);
                        String avatar=rs.getString(5);
                        String email=rs.getString(6);
                        user = new User(id, userAccount1, userPassword, userName,avatar,email);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return user;
    }




}
package com.example.jingnews;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class imageDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_demo);
        ///readDB2Image();
        ImageView im=findViewById(R.id.img);
       /* Drawable drawable = getResources().getDrawable(R.drawable.avatar1);
        String str=drawable.toString();
        Log.d("tt",str);*/
        //Drawable d= new Drawable(str);
        //im.setImageResource(getResources().getIdentifier("avatar1" + ".png", "drawable", "com.example.jingnews"));
        ApplicationInfo appInfo = getApplicationInfo();
        int resID = getResources().getIdentifier("news3", "drawable", "com.example.jingnews");
        Log.d("tt",""+resID);
         //im.setBackgroundResource(resID);
        //im.setBackground(Drawable.createFromPath("D:/1.png"));
    }
   /* public static void readImage2DB() {

        /*new Thread() {
            @Override
            public void run() {
                String path = "D:/1.png";
                Connection connection = JDBCUtils.getConn();
                FileInputStream in = null;
                try {
                    in = ImageUtil.readImage(path);
                    String sql = "update news set photo=? where id=1";
                    if (connection != null) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (ps != null) {
                            ps.setBinaryStream(1, in, in.available());
                            int count = ps.executeUpdate();
                            if (count > 0) {
                                Log.d("ttt", "插入成功");
                            } else {
                                Log.d("ttt", "插入失败");
                            }
                            connection.close();
                            ps.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("ttt","数据库异常");
                }
            }
        }.start();
    }*/



   /* public static void readDB2Image() {
        String targetPath = "D:/1.png";
        new Thread() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    conn = JDBCUtils.getConn();
                    String sql = "select * from news where id =?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, 1);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        InputStream in = rs.getBinaryStream("photo");
                        ImageUtil.readBin2Image(in, targetPath);
                    }
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }.start();
    }*/

}
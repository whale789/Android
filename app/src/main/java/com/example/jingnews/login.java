package com.example.jingnews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jingnews.dao.UserDao;

/**
 * function：连接页面加载首页
 */
public class login extends AppCompatActivity {
    private static final String TAG = "jing";
    private String account=null;
    int msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    public void reg(View view){
        /*Intent intent=new Intent(login.this,register.class);
        startActivity(intent);*/
        startActivity(new Intent(getApplicationContext(),register.class));
    }


    /**
     * function: 登录
     * */
    public void login(View view){

        EditText EditTextAccount = findViewById(R.id.uesrAccount);
        EditText EditTextPassword = findViewById(R.id.userPassword);
        account=EditTextAccount.getText().toString();

        new Thread(){
            @Override
            public void run() {
                UserDao userDao = new UserDao();
                msg = userDao.login(EditTextAccount.getText().toString(),EditTextPassword.getText().toString());
                hand1.sendEmptyMessage(msg);
            }
        }.start();

    }

    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler((Looper.getMainLooper())) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();

            } else if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(login.this,MainActivity.class);
                intent.putExtra("data",account);
                startActivity(intent);
            } else if (msg.what == 2){
                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3){
                Toast.makeText(getApplicationContext(), "账号不存在", Toast.LENGTH_LONG).show();
            }
        }
    };

}
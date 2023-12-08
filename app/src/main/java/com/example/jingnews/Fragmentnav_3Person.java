package com.example.jingnews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jingnews.circle.CircleImageView;
import com.example.jingnews.dao.UserDao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentnav_3Person#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentnav_3Person extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String s=null;
    private String a;
    private String loginame;
    private FragmentManager fmanager;
    private FragmentTransaction ftransaction;
    public Fragmentnav_3Person() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentnav_3Person newInstance(String param1, String param2) {
        Fragmentnav_3Person fragment = new Fragmentnav_3Person();
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

        new Thread() {
            @Override
            public void run() {
                UserDao userDao = new UserDao();
                loginame = userDao.selectname();
                // Toast.makeText(getActivity(), loginame, Toast.LENGTH_SHORT).show();
                Log.d("传递结果：",loginame);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = getActivity().findViewById(R.id.personname);
                        text.setText(loginame);
                        LinearLayout layout=getActivity().findViewById(R.id.dynamic);
                        LinearLayout layout1=getActivity().findViewById(R.id.function);
                        LinearLayout layout2=getActivity().findViewById(R.id.dynamicno);
                        String name=text.getText().toString();
                        if(name.equals("未登录")) {
                            layout.setVisibility(LinearLayout.GONE);
                        }else{
                            layout2.setVisibility(LinearLayout.GONE);
                        }
                    }
                });
            }
        }.start();



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentnav_3person,container,false);

        return view;
    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }



    @Override
    public void onClick(View view) {
        LinearLayout layout=getActivity().findViewById(R.id.dynamic);
        LinearLayout layout2=getActivity().findViewById(R.id.dynamicno);
        TextView text=getActivity().findViewById(R.id.personname);
        String name=text.getText().toString();
        if(name.equals("未登录")) {
            switch (view.getId())
            {
                case R.id.personal:
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.history:
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.login:
                    Intent intent = new Intent(getActivity(), login.class);
                    startActivity(intent);
                    /*text.setText("jing");
                    layout2.setVisibility(LinearLayout.GONE);
                    layout.setVisibility(LinearLayout.VISIBLE);*/
                    break;
                case R.id.update:
                    Intent intent1 = new Intent(getActivity(), Update.class);
                    startActivity(intent1);
                    /*text.setText("jing");
                    layout2.setVisibility(LinearLayout.GONE);
                    layout.setVisibility(LinearLayout.VISIBLE);*/

                    break;
                case R.id.aboutmy:
                    Intent intent2 = new Intent(getActivity(), AboutMy.class);
                    startActivity(intent2);
                    break;
                case R.id.loginout:
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    break;
            }
            //Snackbar.make(view,"请先登录！",Snackbar.LENGTH_SHORT).show();
        }
        else{
            switch (view.getId())
            {
                case R.id.connectbutton:
                    MainActivity mainActivity1=(MainActivity) getActivity();
                    mainActivity1.gotoConnectionFragment();
                    break;
                case R.id.kudosbutton:
                    MainActivity  mainActivity0 = (MainActivity) getActivity();
                    mainActivity0. gotoKudosFragment();
                    break;
                case R.id.personal:
                    Intent intent = new Intent(getActivity(), PersonalChange.class);
                    startActivity(intent);
                    break;
                case R.id.history:
                    MainActivity  mainActivity = (MainActivity) getActivity();
                    mainActivity. gotoDownloadFragment();
                    break;
                case R.id.update:
                    //Snackbar.make(view,"版本更新",Snackbar.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getActivity(), Update.class);
                    startActivity(intent1);
                    break;
                case R.id.aboutmy:
                    Intent intent2 = new Intent(getActivity(), AboutMy.class);
                    startActivity(intent2);
                    break;
                case R.id.loginout:
                    Toast.makeText(getActivity(),"成功退出登录",Toast.LENGTH_SHORT).show();
                    new Thread() {
                        @Override
                        public void run() {
                            UserDao userDao = new UserDao();
                            userDao.loginout();
                            // Toast.makeText(getActivity(), loginame, Toast.LENGTH_SHORT).show();
                            Log.d("传递结果：",loginame);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView text = getActivity().findViewById(R.id.personname);
                                    text.setText("未登录");
                                    CircleImageView av=getActivity().findViewById(R.id.avatar);
                                    av.setBackgroundResource(R.drawable.defau);
                                    LinearLayout layout=getActivity().findViewById(R.id.dynamic);
                                    LinearLayout layout2=getActivity().findViewById(R.id.dynamicno);
                                    String name=text.getText().toString();
                                    if(name.equals("未登录")) {
                                        layout.setVisibility(LinearLayout.GONE);
                                        layout2.setVisibility(LinearLayout.VISIBLE);
                                    }else{
                                        layout2.setVisibility(LinearLayout.GONE);
                                        layout.setVisibility(LinearLayout.VISIBLE);
                                    }
                                }
                            });
                        }
                    }.start();
                    //onResume();
                    /*Fragmentnav_1Home fragment=new Fragmentnav_1Home();
                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragmelayout,fragment);
                    transaction.commit();*/
                    break;
            }

        }


        //Snackbar.make(view,"jing",Snackbar.LENGTH_SHORT).show();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new Thread() {
            @Override
            public void run() {
                UserDao userDao = new UserDao();
                a=userDao.loadavatar();
                // Toast.makeText(getActivity(), loginame, Toast.LENGTH_SHORT).show();
                Log.d("头像：","是"+a);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CircleImageView av=getActivity().findViewById(R.id.avatar);
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
        }.start();
        Button btn=getActivity().findViewById(R.id.personal);
        btn.setOnClickListener(this);
        Button btn1=getActivity().findViewById(R.id.history);
        btn1.setOnClickListener(this);
        Button btn2=getActivity().findViewById(R.id.update);
        btn2.setOnClickListener(this);
        Button btn3=getActivity().findViewById(R.id.aboutmy);
        btn3.setOnClickListener(this);
        Button btn4=getActivity().findViewById(R.id.login);
        btn4.setOnClickListener(this);
        Button btn5=getActivity().findViewById(R.id.loginout);
        btn5.setOnClickListener(this);
        LinearLayout btn6=getActivity().findViewById(R.id.kudosbutton);
        btn6.setOnClickListener(this);
        LinearLayout btn7=getActivity().findViewById(R.id.connectbutton);
        btn7.setOnClickListener(this);
    }
}
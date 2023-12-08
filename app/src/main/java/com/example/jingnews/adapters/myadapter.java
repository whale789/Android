package com.example.jingnews.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jingnews.Fragmenttab_1Recommend;
import com.example.jingnews.Fragmenttab_2Politics;
import com.example.jingnews.Fragmenttab_3Kang;
import com.example.jingnews.Fragmenttab_4Fun;
import com.example.jingnews.Fragmenttab_5Technology;
import com.example.jingnews.Fragmenttab_6Finance;
import com.example.jingnews.Fragmenttab_7Military;

import java.util.ArrayList;

public class myadapter extends FragmentStateAdapter {


    public static ArrayList<String> arrayList=new ArrayList<String>(){{
        add("推荐");
        add("政治");
        add("抗疫");
        add("企业");
        add("科技");
        add("财经");
        add("军事");
    }};
    public static ArrayList<Fragment> arrayFragment=new ArrayList<Fragment>(){{
        add(new Fragmenttab_1Recommend());
        add(new Fragmenttab_2Politics());
        add(new Fragmenttab_3Kang());
        add(new Fragmenttab_4Fun());
        add(new Fragmenttab_5Technology());
        add(new Fragmenttab_6Finance());
        add(new Fragmenttab_7Military());
    }};
    public myadapter(@NonNull Fragment fragment) {
        super(fragment);


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

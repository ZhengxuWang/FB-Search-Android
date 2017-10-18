package com.example.finalhwnine.Result;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.finalhwnine.MainActivity;
import com.example.finalhwnine.Result.Fragment.MyFragmentPagerAdapter;
import com.example.finalhwnine.R;
import com.example.finalhwnine.Result.Fragment.UsersFragment;

public class ResultActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    String keyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();//隐藏掉整个ActionBar
        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");
        setContentView(R.layout.activity_result);
        //初始化视图

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Result");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Log.d("userfragment", "press btn");
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        initViews();
        //setSupportActionBar(toolbar);
        //Log.d("debug111","success");
    }

    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), keyword);
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        five = mTabLayout.getTabAt(4);
        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(R.drawable.ic_users);
        two.setIcon(R.drawable.ic_pages);
        three.setIcon(R.drawable.ic_events);
        four.setIcon(R.drawable.ic_places);
        five.setIcon(R.drawable.ic_groups);
    }
}

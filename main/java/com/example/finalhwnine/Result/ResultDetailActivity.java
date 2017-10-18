package com.example.finalhwnine.Result;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.finalhwnine.MainActivity;
import com.example.finalhwnine.R;
import com.example.finalhwnine.Result.Fragment.MyDetailFragmentAdapter;
import com.example.finalhwnine.Result.Fragment.MyFragmentPagerAdapter;

public class ResultDetailActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyDetailFragmentAdapter myDetailFragmentAdapter;
    SharedPreferences favorite;
    SharedPreferences.Editor editor;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();//隐藏掉整个ActionBar
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        setContentView(R.layout.activity_details_result);
        //初始化视图

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("More Details");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Log.d("userfragment", "press btn");
                Intent intent = new Intent(ResultDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        favorite = getSharedPreferences("favorite",MODE_PRIVATE);
        editor = favorite.edit();
        initViews();
        //setSupportActionBar(toolbar);
        //Log.d("debug111","success");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int ids = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (ids == R.id.favorite) {
            editor.putString(id,"ok");
            editor.commit();
        }else if(ids==R.id.share){

        }

        return super.onOptionsItemSelected(item);
    }
    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.detail_viewPager);
        myDetailFragmentAdapter = new MyDetailFragmentAdapter(getSupportFragmentManager(),id);
        mViewPager.setAdapter(myDetailFragmentAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.detail_tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(R.drawable.ic_albums);
        two.setIcon(R.drawable.ic_posts);
    }
}

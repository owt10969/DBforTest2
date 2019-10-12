package com.example.dbfortest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mainView extends AppCompatActivity implements OnBannerListener {
    public static final String GOOGLE_ACCOUNT ="";
    public Banner banner;
    private RecyclerView mRecyclerView,mRecyclerView2;
    private GalleryAdapter mAdapter,mAdapter2;
    private List<Integer> mDatas,mDatas2;
    private Button teampage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        initView();
        setListener();
        bannerGo();
        initDatas();
        scrollView();
    }
    private void initView(){
        teampage=findViewById(R.id.teamPage);
    }
    private void setListener(){
        teampage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mainView.this,team.class);
                startActivity(intent);
            }
        });
    }
    private void scrollView(){
        //得到控件
        mRecyclerView =findViewById(R.id.id_recyclerview_horizontal);
        mRecyclerView2=findViewById(R.id.id_recyclerview_horizontal2);
        //設置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView2.setLayoutManager(linearLayoutManager2);
        //設置適配器
        mAdapter = new GalleryAdapter(mainView.this, mDatas);
        mAdapter2 =new GalleryAdapter(mainView.this,mDatas2);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView2.setAdapter(mAdapter2);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            Toast.makeText(mainView.this,"choose",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void initDatas()
    {
        mDatas = new ArrayList<>(Arrays.asList(
                R.drawable.aa,
                R.drawable.bb,
                R.drawable.cc,
                R.drawable.dd,
                R.drawable.ee,
                R.drawable.a));

        mDatas2 = new ArrayList<>(Arrays.asList(
                R.drawable.aa,
                R.drawable.bb,
                R.drawable.cc,
                R.drawable.dd,
                R.drawable.ee,
                R.drawable.a));
    }
    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>
    {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;
        public GalleryAdapter(Context context, List<Integer> datats)
        {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public ViewHolder(View arg0)
            {
                super(arg0);
            }
            ImageView mImg;
            TextView mTxt;
        }
        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }
        /**
         * 創建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = mInflater.inflate(R.layout.tt,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mImg = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            return viewHolder;
        }
        /**
         * 設置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i)
        {
            viewHolder.mImg.setImageResource(mDatas.get(i));
        }
    }

    /**Banner Start*****************************************************/

    private  void bannerGo(){
        List<String> images = new ArrayList<>();
        images.add("https://www.iwaishin.com/wp-content/uploads/2019/08/ASUS-zenfine-6-%E9%96%8B%E7%AE%B1%E8%A9%95%E6%B8%AC%E7%B8%AE%E5%9C%96.jpg");
        images.add("https://d2lfcsub12kx0l.cloudfront.net/tw/article/img/201902/2019022707312749763_ExtraLargeSize-640x640.jpg");
        images.add("https://i.ytimg.com/vi/wmAaYAbzT8Q/maxresdefault.jpg");
        images.add("http://i2.hdslb.com/bfs/archive/43d2ddd1140ec07f1c25be3550e8ff87d1ae6c6e.jpg");
        images.add("https://static.newmobilelife.com/wp-content/uploads/2019/05/vs.jpeg");
        banner = findViewById(R.id.banner);
        System.out.println("==========================");
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .setDelayTime(3000)
                .setOnBannerListener(this)
                .start();
        System.out.println("==========================AAAAA");
    }
    public void OnBannerClick (int position){
        Toast.makeText(getApplicationContext(),"Touch :"+position,Toast.LENGTH_SHORT).show();
    }

    public class GlideImageLoader extends ImageLoader {
        private  String TAG="GlideImageLoader";
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    /***Banner end******************************************************/
}

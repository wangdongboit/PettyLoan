package com.yixun.pettyloan.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.Product;
import com.yixun.pettyloan.ui.ProductsListFactory;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class EntityFragment extends BaseSupportFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner_entity)
    Banner mBanner;
    @BindView(R.id.tab_entity_title)
    TabLayout mTab;
    @BindView(R.id.vp_entity_content)
    ViewPager mViewPager;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static EntityFragment getInstance(String title) {
        EntityFragment sf = new EntityFragment();
        return sf;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToorBar();
    }

    private void initToorBar() {
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_entity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.blue_medium);
        mBanner.setImageLoader(new GlideImageLoader());
        List<String> urlList = new ArrayList<>();
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=c973f24fda4b0e74d9d233efd0a34dd3&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201608%2F10%2F20160810204650_8sQuF.jpeg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364364886&di=48c237b56ad5126d60aac44b6d35c6ac&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D1038163141%2C2794573076%26fm%3D214%26gp%3D0.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=a137348f727c523a65936c60d6a6b6cb&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201605%2F26%2F20160526175959_xRWTH.thumb.700_0.jpeg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=f101d8d3cc8abfed11a2ad6584a30df4&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160907%2FImg467862638.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345400&di=9812520307a97a517568e690257da493&imgtype=0&src=http%3A%2F%2Fimg.ladywu.net%2F2017%2F0704%2F20170704021437678.jpg");
        mBanner.setImages(urlList).start();

        List<Product> goodList = new ArrayList<>();
        Product mode1 = new Product();
        mode1.setProductName("产品1");
        mode1.setBigRate("10");
        mode1.setLittleRate("% + 0.32%*");
        Product mode2 = new Product();
        mode2.setProductName("产品2");
        mode2.setBigRate("5");
        mode2.setLittleRate("% + 0.32%*");
        Product mode3 = new Product();
        mode3.setProductName("产品3");
        mode3.setBigRate("10");
        mode3.setLittleRate("%");
        goodList.add(mode1);
        goodList.add(mode2);
        goodList.add(mode3);
        goodList.add(mode1);
        goodList.add(mode2);
        goodList.add(mode3);

        ProductsListFactory goodsListFactory = new ProductsListFactory(getActivity());
        View view1 = goodsListFactory.newInstance(goodList);
        View view2 = goodsListFactory.newInstance(goodList);
        List<View> viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        EntityPageAdapter adapter = new EntityPageAdapter(viewList);
        mViewPager.setAdapter(adapter);
        mTab.addTab(mTab.newTab().setText("黄金产品"));
        mTab.addTab(mTab.newTab().setText("钻石产品"));
//        mTab.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTab.setScrollPosition(position, 0, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        configRefresh();
    }

    private class GlideImageLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .crossFade()
                    .into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return new ImageView(context);
        }
    }

    private class EntityPageAdapter extends PagerAdapter {
        private List<View> mViewList;

        EntityPageAdapter(List<View> viewList) {
            mViewList = viewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }
    }

    private void configRefresh(){
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus(){
        Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                SystemClock.sleep(1000);
                subscriber.onNext("refresh");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mRefresh.setRefreshing(false);
                    }
                });
    }

}
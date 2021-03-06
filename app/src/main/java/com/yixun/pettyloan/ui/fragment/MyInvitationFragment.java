package com.yixun.pettyloan.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.InviteHistory;
import com.yixun.pettyloan.entity.InviteHistoryItemViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyInvitationFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.recycler)
    RecyclerView mFeedsRecycler;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static MyInvitationFragment getInstance(String title) {
        MyInvitationFragment sf = new MyInvitationFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_invitation;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        bindContent();
    }

    @Override
    protected void initData() {
        configRefresh();
    }

    private void initToolbar() {
        mToolbar.setTitle(mTitle);
        mToolbar.inflateMenu(R.menu.tool_bar_menu_my_invitation);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sign:
                        Toast.makeText(getContext(), "功能开发中...", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void bindContent() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(InviteHistory.class, new InviteHistoryItemViewBinder(context));
        mFeedsRecycler.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new InviteHistory("2017年01月", "1.00", 0, 10));
        items.add(new InviteHistory("2017年02月", "2.00", 1, 11));
        items.add(new InviteHistory("2017年03月", "3.00", 2, 12));
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    private void configRefresh() {
        mRefresh.setColorSchemeColors(ContextCompat.getColor(context, R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus() {
        mRefresh.setRefreshing(false);
    }

//    @OnClick({R.id.iv_back})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_back:
//                pop();
//                break;
//            default:
//                break;
//        }
//    }
}
package com.xsusenet.ip4.UI.Bids;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Adapters.BidsListAdapter;
import com.xsusenet.ip4.Models.Bids.Bid;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.Util;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class BidListActivity extends DaggerAppCompatActivity implements BidsListContract.view {

    String listId;
    String Title;
    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.bids_list_title)
    TextView bidsListTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bids_rec)
    RecyclerView bidsRec;

    LinearLayoutManager linearLayoutManager;
    BidsListAdapter bidsListAdapter;
    List<Bid> bidList = new ArrayList<>();

    @Inject
    Util util;

    @Inject
    BidsListPresenterImpl presenter;
    @BindView(R.id.progress)
    LinearLayout progress;

    boolean isLastPage = false;
    boolean isLoading = false;
    int PAGE_SIZE = 10;
    int page_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_list);
        ButterKnife.bind(this);
        initUi();

        if (getIntent().getExtras() != null) {
            Title = getIntent().getStringExtra("title");
            title.setText(Title);
            listId = getIntent().getStringExtra("list_id");
        }

        if (util.isNetworkAvailable()) {
            presenter.getBidList(listId,page_no);
        } else {
            util.displayToast(BidListActivity.this, getString(R.string.network_unavailable), 2);
        }

    }

    @OnClick(R.id.back)
    void onBack() {
        onBackPressed();
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(BidListActivity.this, title);
        Util.getUtils().overrideFontsBold(BidListActivity.this, bidsListTitle);

        RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.getId() == R.id.bids_rec) {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            page_no = page_no + 1;
                            presenter.getBidList(listId,page_no);
                        }
                    }
                }
            }
        };


        linearLayoutManager = new LinearLayoutManager(this);
        bidsListAdapter = new BidsListAdapter(this, bidList);
        bidsRec.setHasFixedSize(true);
        bidsRec.setLayoutManager(linearLayoutManager);
        bidsRec.setAdapter(bidsListAdapter);
    }

    @Override
    public void ShowProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void StopProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void setList(List<Bid> result) {
        if (result != null) {
            bidList.addAll(result);
            bidsListAdapter.notifyDataSetChanged();
        }
    }
}

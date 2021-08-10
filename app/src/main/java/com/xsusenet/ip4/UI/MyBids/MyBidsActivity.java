package com.xsusenet.ip4.UI.MyBids;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Adapters.MyBidsAdapter;
import com.xsusenet.ip4.Adapters.WatchListAdapter;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.MyBids.Bid;
import com.xsusenet.ip4.Models.MyBids.Bids;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class MyBidsActivity extends DaggerAppCompatActivity implements MyBidsContract.view {

    @Inject
    MyBidsPresenter presenter;
    @Inject
    Util util;
    @BindView(R.id.go_back)
    RelativeLayout goBack;
    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.bids_rec)
    RecyclerView bidsRec;
    @BindView(R.id.progress)
    LinearLayout progress;
    MyBidsAdapter adapter;

    List<Bid> bidList = new ArrayList<>();
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bids);
        ButterKnife.bind(this);
        initUi();
        setAdapter();
        if (util.isNetworkAvailable()) {
            presenter.getServerCDT();
            presenter.getMyBids();

        } else
            util.displayToast(MyBidsActivity.this, getString(R.string.network_unavailable), 2);


    }

    @Override
    protected void onResume() {
        presenter.getServerCDT();
        super.onResume();
    }

    @OnClick(R.id.go_back)
    void onBack() {
        onBackPressed();
    }

    private void setAdapter() {
        adapter = new MyBidsAdapter(MyBidsActivity.this, bidList);
        layoutManager = new LinearLayoutManager(this);
        bidsRec.setLayoutManager(layoutManager);
        bidsRec.setAdapter(adapter);
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(MyBidsActivity.this, Title);
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
    public void showResult(boolean b, List<Bid> all) {
        if (b) {
            if (all != null) {
                if (all.size() > 0) {
                    this.bidList.addAll(all);
                    adapter.notifyDataSetChanged();
                } else {
                    this.bidList.clear();
                    adapter.notifyDataSetChanged();
                    Util.getUtils().displayToast(this, "List is empty", 3);
                }
            } else {
                this.bidList.clear();
                adapter.notifyDataSetChanged();
                Util.getUtils().displayToast(this, "List is empty", 3);
            }
        } else {
            this.bidList.clear();
            adapter.notifyDataSetChanged();
            Util.getUtils().displayToast(this, "List is empty", 3);

        }
    }

    @Override
    public void SetTime(String time) {
        if (time != null) {
            if (!time.isEmpty()) {
                MyPreferenceManager.getInstance().savePref("STIME", time);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

}

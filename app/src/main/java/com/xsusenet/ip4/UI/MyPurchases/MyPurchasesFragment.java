package com.xsusenet.ip4.UI.MyPurchases;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xsusenet.ip4.Adapters.PurchasesAdapter;
import com.xsusenet.ip4.Adapters.WatchListAdapter;
import com.xsusenet.ip4.Models.Purchases.All;
import com.xsusenet.ip4.Models.WatchList.Result;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;


public class MyPurchasesFragment extends DaggerFragment implements MyPurchasesContract.view {

    @BindView(R.id.rec)
    RecyclerView rec;

    Unbinder unbinder;
    @Inject
    MyPurchasesPresenterImpl presenter;

    List<All> purchasesList = new ArrayList<>();
    @Inject
    Util util;

    PurchasesAdapter adapter;
    LinearLayoutManager layoutManager;
    @BindView(R.id.progress)
    LinearLayout progress;

    boolean isLastPage = false;
    boolean isLoading = false;
    int PAGE_SIZE = 10;
    int page_no = 0;

    public MyPurchasesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_purchases, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUi(view);
        setAdapter();
        if (util.isNetworkAvailable()) {
            presenter.getMyPurchasesList(page_no);

        } else
            util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);

        RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.getId() == R.id.rec) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            page_no = page_no + 1;
                            presenter.getMyPurchasesList(page_no);
                        }
                    }
                } else {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            page_no = page_no + 1;
                            presenter.getMyPurchasesList(page_no);
                        }
                    }
                }
            }
        };
        rec.addOnScrollListener(recyclerViewOnScrollListener);

        return view;
    }

    private void setAdapter() {
        if (getActivity() != null) {
            layoutManager = new LinearLayoutManager(getActivity());
            rec.setLayoutManager(layoutManager);
        }
        adapter = new PurchasesAdapter(getActivity(), purchasesList, new PurchasesAdapter.OnCancelItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                cancelAlert(purchasesList.get(position).getTransactionId());
            }
        });
        rec.setAdapter(adapter);
    }

    private void initUi(View v) {

        Util.getUtils().overrideFontsBold(getActivity(), v.findViewById(R.id.Title));
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
    public void showResult(boolean b, List<All> allList) {
        if (allList != null) {
            isLoading = false;
            this.isLastPage = b;
            if (allList.size() > 0) {
                this.purchasesList.addAll(allList);
                adapter.notifyDataSetChanged();
            }
        } else {
            purchasesList.clear();
            adapter.notifyDataSetChanged();
        }
    }

    private void cancelAlert(String subscriptionId) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                //set message, title, and icon
                .setTitle("Cancel")
                .setMessage("Do you want to cancel this Subscription?")
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (util.isNetworkAvailable()) {
                            presenter.cancelSubscription(subscriptionId);
                        } else {
                            util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
    @Override
    public void CancelResult(boolean b, String message) {
        if (b) {
            if (util != null && getActivity()!=null)
                util.displayToast(getActivity(), message, 1);
            purchasesList.clear();
            page_no = 0;
            adapter.notifyDataSetChanged();
            if (util.isNetworkAvailable()) {
                presenter.getMyPurchasesList(page_no);

            } else{
                if (util != null && getActivity()!=null)
                    util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);}
        } else {
            if (util != null && getActivity()!=null)
            util.displayToast(getActivity(), message, 2);
        }
    }


}
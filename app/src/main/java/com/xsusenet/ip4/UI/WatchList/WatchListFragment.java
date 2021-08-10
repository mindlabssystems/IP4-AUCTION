package com.xsusenet.ip4.UI.WatchList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.otto.Subscribe;
import com.xsusenet.ip4.Adapters.BlockSizeAdapter;
import com.xsusenet.ip4.Adapters.RegionsAdapter;
import com.xsusenet.ip4.Adapters.SalesListAdapter;
import com.xsusenet.ip4.Adapters.SortByAdapter;
import com.xsusenet.ip4.Adapters.WatchListAdapter;
import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.Models.WatchList.ModelWatchList;
import com.xsusenet.ip4.Models.WatchList.Result;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchListFragment extends DaggerFragment implements WatchListContract.view {

    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.purchase)
    TextView purchase;
    @BindView(R.id.rent)
    TextView rent;
    @BindView(R.id.sort_by_text)
    TextView sortByText;
    @BindView(R.id.sort_by_lay)
    RelativeLayout sortByLay;
    @BindView(R.id.region_text)
    TextView regionText;
    @BindView(R.id.region_lay)
    RelativeLayout regionLay;
    @BindView(R.id.block_size)
    TextView blockSize;
    @BindView(R.id.block_size_lay)
    RelativeLayout blockSizeLay;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.sort_by_text_1)
    TextView sortByText1;
    @BindView(R.id.region_text_1)
    TextView regionText1;
    @BindView(R.id.block_size_1)
    TextView blockSize1;
    @BindView(R.id.linear_filter)
    LinearLayout linearFilter;
    @BindView(R.id.listView_sortby)
    RecyclerView listViewSortby;
    @BindView(R.id.listView_regions)
    RecyclerView listViewRegions;
    @BindView(R.id.listView_block_size)
    RecyclerView listViewBlockSize;
    @BindView(R.id.close)
    RelativeLayout close;
    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.apply)
    TextView apply;
    @BindView(R.id.filter_card)
    CardView filterCard;
    @BindView(R.id.progress)
    LinearLayout progress;

    WatchListAdapter watchListAdapter;
    LinearLayoutManager layoutManager;

    boolean isLastPage = false;
    boolean isLoading = false;
    int PAGE_SIZE = 10;

    List<Size> blockSizeList = new ArrayList<>();
    List<RegionResult> regionResultList = new ArrayList<>();
    List<ModelSortBy> sortByList = new ArrayList<>();
    ModelSortBy selectedSortBy;

    BlockSizeAdapter blockSizeAdapter;
    SortByAdapter sortByAdapter;
    RegionsAdapter regionsAdapter;

    static String OPEN_FILTER;

    String PURCHASE_OR_RENT;
    String sortBy;
    ArrayList<String> sizeIntList = new ArrayList<>();
    ArrayList<String> regionIntList = new ArrayList<>();
    ArrayList<String> salesTypeIntList = new ArrayList<>();

    List<Result> watchList = new ArrayList<>();

    Unbinder unbinder;

    @Inject
    Util util;

    @Inject
    WatchListPresenterImpl presenter;


    public WatchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_watch_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUi(view);
//        callAPis();
        setAdapter();

        PURCHASE_OR_RENT = Constants.PURCHASE;
        if (util.isNetworkAvailable()) {
            presenter.getWatchList();

        } else
            util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);


        return view;
    }

    private void initUi(View v) {
        Util.getUtils().overrideFontsBold(getActivity(), Title);
    }

    private void callAPis() {
        if (util.isNetworkAvailable()) {
            presenter.getSortBy();
            presenter.getBlockSize();
            presenter.getRegions();
        }
    }

    private void setAdapter() {
        if (getActivity() != null) {
            layoutManager = new LinearLayoutManager(getActivity());
            rec.setLayoutManager(layoutManager);
        }
        watchListAdapter = new WatchListAdapter(getActivity(), watchList);
        rec.setAdapter(watchListAdapter);
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
    public void SortByResult(List<ModelSortBy> modelSortBy) {

    }

    @Override
    public void BlockSizeResult(List<Size> size) {

    }

    @Override
    public void RegionResult(List<RegionResult> regions) {

    }

    @Override
    public void showResult(boolean b, List<Result> all) {

        if (b) {
            if (all != null) {
                if (all.size() > 0) {
                    this.watchList.addAll(all);
                    watchListAdapter.notifyDataSetChanged();
                } else {

                    this.watchList.clear();
                    watchListAdapter.notifyDataSetChanged();
                    Util.getUtils().displayToast(getActivity(), "Wish list is empty", 3);
                }
            } else {
                this.watchList.clear();
                watchListAdapter.notifyDataSetChanged();
                Util.getUtils().displayToast(getActivity(), "Wish list is empty", 3);

            }
        } else {
            this.watchList.clear();
            watchListAdapter.notifyDataSetChanged();
            Util.getUtils().displayToast(getActivity(), "Wish list is empty", 3);

        }
    }

    @Subscribe
    public void onEvent(MessageData messageData) {
        String[] seperatedString = messageData.getMsgData().split(",");

        if (seperatedString[0].equals(Constants.REMOVED_FROM_WATCH_LIST)) {
            watchList.clear();
            watchListAdapter.notifyDataSetChanged();
            presenter.getWatchList();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}

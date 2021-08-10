package com.xsusenet.ip4.UI.MySales;

import android.os.Bundle;
import android.util.Log;
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
import com.xsusenet.ip4.Adapters.MySalesListAdapter;
import com.xsusenet.ip4.Adapters.RegionsAdapter;
import com.xsusenet.ip4.Adapters.SortByAdapter;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.View.getDefaultSize;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySalesFragment extends DaggerFragment implements MySalesContract.view {
    Unbinder unbinder;
    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.sort_by_text)
    TextView sortByText;
    @BindView(R.id.region_text)
    TextView regionText;
    @BindView(R.id.block_size)
    TextView blockSize;

    @Inject
    Util util;

    @Inject
    MySalesFragPresenterImpl presenter;


    List<Size> blockSizeList = new ArrayList<>();
    List<RegionResult> regionResultList = new ArrayList<>();
    List<ModelSortBy> sortByList = new ArrayList<>();
    ModelSortBy selectedSortBy;

    List<SalesResult> salesList = new ArrayList<>();

    BlockSizeAdapter blockSizeAdapter;
    SortByAdapter sortByAdapter;
    RegionsAdapter regionsAdapter;
    @BindView(R.id.listView_sortby)
    RecyclerView listViewSortby;
    @BindView(R.id.listView_regions)
    RecyclerView listViewRegions;
    @BindView(R.id.listView_block_size)
    RecyclerView listViewBlockSize;
    @BindView(R.id.sort_by_lay)
    RelativeLayout sortByLay;
    @BindView(R.id.region_lay)
    RelativeLayout regionLay;
    @BindView(R.id.block_size_lay)
    RelativeLayout blockSizeLay;
    @BindView(R.id.filter_card)
    CardView filterCard;
    @BindView(R.id.close)
    RelativeLayout close;
    @BindView(R.id.sort_by_text_1)
    TextView sortByText1;
    @BindView(R.id.region_text_1)
    TextView regionText1;
    @BindView(R.id.block_size_1)
    TextView blockSize1;

    static String OPEN_FILTER;


    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.apply)
    TextView apply;
    @BindView(R.id.sale)
    TextView sale;
    @BindView(R.id.lease)
    TextView lease;

    LinearLayoutManager layoutManager;

    MySalesListAdapter adapter;

    boolean isLastPage = false;
    boolean isLoading = false;
    int PAGE_SIZE = 10;
    int page_no;

    String category_id,
            sales_type_id,
            size_id,
            region_id,
            sale_method,
            list_id;

    ArrayList<String> sizeIntList = new ArrayList<>();
    ArrayList<String> regionIntList = new ArrayList<>();
    ArrayList<String> salesTypeIntList = new ArrayList<>();

    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.progress)
    LinearLayout progress;
    String sortBy;
    String SALE_OR_LEASE;


    public MySalesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_sales, container, false);
        unbinder = ButterKnife.bind(this, v);
        presenter.getServerCDT();
        BusProvider.getInstance().register(this);
        initUi(v);
        callAPis();
        SALE_OR_LEASE = Constants.SALE;
        if (util.isNetworkAvailable()) {
            presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, page_no);

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
                            presenter.getServerCDT();
                            isLoading = true;
                            page_no = page_no + 1;
                            presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, page_no);

                        }
                    }
                }
/*
                else {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            page_no = page_no + 1;
                            presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, page_no);

*/
/*
                            presenter.getLists(category_id,
                                    sales_type_id,
                                    size_id,
                                    region_id,
                                    sale_method,
                                    list_id,
                                    page_no);
*//*

                        }
                    }
                }
*/
            }
        };
        rec.addOnScrollListener(recyclerViewOnScrollListener);
        return v;
    }

    @Override
    public void onResume() {
        presenter.getServerCDT();
        super.onResume();
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

    private void callAPis() {
        if (util.isNetworkAvailable()) {
            presenter.getSortBy();
            presenter.getBlockSize();
            presenter.getRegions();
        } else {
            Util.getUtils().displayToast(getActivity(), String.valueOf(R.string.network_unavailable), 2);
        }
    }

    private void initUi(View v) {
        setAdapter();
        Util.getUtils().overrideFontsBold(getActivity(), Title);
        Util.getUtils().overrideFontsBold(getActivity(), sale);
        Util.getUtils().overrideFontsBold(getActivity(), lease);
        Util.getUtils().overrideFontsSemiBold(getActivity(), sortByText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), regionText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), blockSize);
        Util.getUtils().overrideFontsSemiBold(getActivity(), apply);
        Util.getUtils().overrideFontsSemiBold(getActivity(), clear);
        filterCard.setVisibility(View.GONE);



    }

    private void setAdapter() {
        if(getActivity() != null){
            layoutManager = new LinearLayoutManager(getActivity());
            rec.setLayoutManager(layoutManager);
        }
        adapter = new MySalesListAdapter(getActivity(), salesList);
        rec.setAdapter(adapter);
    }


    @OnClick(R.id.sale)
    void onSale() {
        presenter.getServerCDT();
        sale.setBackgroundResource(R.drawable.status_bar_bg_green);
        lease.setBackgroundResource(R.drawable.status_bar_bg);
        SALE_OR_LEASE = Constants.SALE;
        salesList.clear();
        presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);

    }

    @OnClick(R.id.lease)
    void onLease() {
        presenter.getServerCDT();

        lease.setBackgroundResource(R.drawable.status_bar_bg_green);
        sale.setBackgroundResource(R.drawable.status_bar_bg);
        SALE_OR_LEASE = Constants.LEASE;
        salesList.clear();
        presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
    }

    @OnClick(R.id.sort_by_lay)
    void onSortBy() {
        OPEN_FILTER = Constants.SORT_BY;

        sortByText1.setVisibility(View.VISIBLE);
        listViewSortby.setVisibility(View.VISIBLE);

        regionText1.setVisibility(View.INVISIBLE);
        listViewRegions.setVisibility(View.GONE);
        blockSize1.setVisibility(View.INVISIBLE);
        listViewBlockSize.setVisibility(View.GONE);

        filterCard.setVisibility(View.VISIBLE);
        clear.setVisibility(View.GONE);
    }

    @OnClick(R.id.region_lay)
    void onRegion() {
        OPEN_FILTER = Constants.REGION;

        regionText1.setVisibility(View.VISIBLE);
        listViewRegions.setVisibility(View.VISIBLE);

        sortByText1.setVisibility(View.INVISIBLE);
        listViewSortby.setVisibility(View.GONE);
        blockSize1.setVisibility(View.INVISIBLE);
        listViewBlockSize.setVisibility(View.GONE);

        filterCard.setVisibility(View.VISIBLE);
        clear.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.block_size_lay)
    void onBlockSize() {
        OPEN_FILTER = Constants.BLOCK_SIZE;

        blockSize1.setVisibility(View.VISIBLE);
        listViewBlockSize.setVisibility(View.VISIBLE);

        regionText1.setVisibility(View.INVISIBLE);
        listViewRegions.setVisibility(View.GONE);
        sortByText1.setVisibility(View.INVISIBLE);
        listViewSortby.setVisibility(View.GONE);

        filterCard.setVisibility(View.VISIBLE);
        clear.setVisibility(View.VISIBLE);


    }

    @OnClick(R.id.close)
    void onClose() {
        OPEN_FILTER = "";
        filterCard.setVisibility(View.GONE);
        sortByLay.setVisibility(View.VISIBLE);
        blockSizeLay.setVisibility(View.VISIBLE);
        regionLay.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.apply)
    void onApply() {
        if (OPEN_FILTER.equals(Constants.SORT_BY)) {
            selectedSortBy = sortByAdapter.getSelectedItem();
            if (selectedSortBy.getKey() != null)
                sortBy = selectedSortBy.getKey();
            Log.d("TAG", "" + selectedSortBy);
        } else if (OPEN_FILTER.equals(Constants.BLOCK_SIZE)) {
            blockSizeList = BlockSizeAdapter.sizeList;
            sizeIntList.clear();
            for (Size size : blockSizeList) {
                if (size.isChecked())
                    sizeIntList.add(size.getId().toString());
            }


        } else if (OPEN_FILTER.equals(Constants.REGION)) {
            regionResultList = RegionsAdapter.regionResults;
            regionIntList.clear();
            for (RegionResult result : regionResultList) {
                if (result.isChecked())
                    regionIntList.add(result.getRegionId());
            }


        }

        salesList.clear();
        presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
        onClose();
    }

    @OnClick(R.id.clear)
    void onClear() {
        if (OPEN_FILTER.equals(Constants.BLOCK_SIZE)) {
            for (Size size : blockSizeList) {
                size.setChecked(false);
            }
            blockSizeAdapter = new BlockSizeAdapter((ArrayList<Size>) blockSizeList, getActivity(), new BlockSizeAdapter.OnItemClickListener() {
                @Override
                public void onItemClicked(int position) {
                    blockSizeList.get(position).setChecked(!blockSizeList.get(position).isChecked());
                    blockSizeAdapter.notifyDataSetChanged();
                }
            });
            listViewBlockSize.setAdapter(blockSizeAdapter);
            sizeIntList.clear();
        } else if (OPEN_FILTER.equals(Constants.REGION)) {
            for (RegionResult result : regionResultList) {
                result.setChecked(false);
            }
            regionsAdapter = new RegionsAdapter((ArrayList<RegionResult>) regionResultList, getActivity(), new RegionsAdapter.OnItemClickListener() {
                @Override
                public void onItemClicked(int position) {
                    regionResultList.get(position).setChecked(!regionResultList.get(position).isChecked());
                    regionsAdapter.notifyDataSetChanged();
                }
            });
            listViewRegions.setAdapter(regionsAdapter);
            regionIntList.clear();
        }
        salesList.clear();
        presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
        onClose();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
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
        if (modelSortBy != null)
            sortByList = modelSortBy;
        sortByAdapter = new SortByAdapter((ArrayList<ModelSortBy>) modelSortBy, getActivity());
        listViewSortby.setAdapter(sortByAdapter);
    }

    @Override
    public void BlockSizeResult(List<Size> size) {
        if (size != null)
            blockSizeList = size;
        blockSizeAdapter = new BlockSizeAdapter((ArrayList<Size>) blockSizeList, getActivity(),
                new BlockSizeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        blockSizeList.get(position).setChecked(!blockSizeList.get(position).isChecked());
                        blockSizeAdapter.notifyDataSetChanged();
                    }
                });

        listViewBlockSize.setHasFixedSize(true);
        listViewBlockSize.setAdapter(blockSizeAdapter);
    }

    @Override
    public void RegionResult(List<RegionResult> regions) {
        if (regions != null)
            regionResultList = regions;
        regionsAdapter = new RegionsAdapter((ArrayList<RegionResult>) regionResultList, getActivity(), new RegionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                regionResultList.get(position).setChecked(!regionResultList.get(position).isChecked());
                regionsAdapter.notifyDataSetChanged();
            }
        });
        listViewRegions.setHasFixedSize(true);
        listViewRegions.setAdapter(regionsAdapter);
    }

    @Override
    public void showResult(boolean b, List<SalesResult> salesList1) {
        if (salesList1 != null) {
            isLoading = false;
            this.isLastPage = b;
            if (salesList1.size() > 0) {
                this.salesList.addAll(salesList1);
                adapter.notifyDataSetChanged();
            }
            else{
                salesList.clear();
                adapter.notifyDataSetChanged();
            }
        } else {
            salesList.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onEvent(MessageData messageData) {
        String[] seperatedString = messageData.getMsgData().split(",");

        Fragment fragment;

        if (seperatedString[0].equals(Constants.NEW_PRODUCT)) {
            salesList.clear();
            presenter.getLists1(SALE_OR_LEASE, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




}

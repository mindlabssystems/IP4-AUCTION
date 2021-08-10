package com.xsusenet.ip4.UI.AllSales;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.otto.Subscribe;
import com.xsusenet.ip4.Adapters.BlockSizeAdapter;
import com.xsusenet.ip4.Adapters.RegionsAdapter;
import com.xsusenet.ip4.Adapters.SalesListAdapter;
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
import com.xsusenet.ip4.Util.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesListFragment extends DaggerFragment implements SalesListFragmentContract.view {

    @BindView(R.id.Title)
    TextView Title;

    Unbinder unbinder;

    @BindView(R.id.rec)
    RecyclerView rec;

    @Inject
    Util util;

    @Inject
    SalesListPresenterImpl presenter;

    String category_id,
            sales_type_id,
            size_id,
            region_id,
            sale_method,
            list_id;

    int page_no = 0;
    @BindView(R.id.progress)
    LinearLayout progress;
    @BindView(R.id.listView_sortby)
    RecyclerView listViewSortby;
    @BindView(R.id.listView_regions)
    RecyclerView listViewRegions;
    @BindView(R.id.listView_block_size)
    RecyclerView listViewBlockSize;

    @BindView(R.id.sort_by_text_1)
    TextView sortByText1;
    @BindView(R.id.region_text_1)
    TextView regionText1;
    @BindView(R.id.block_size_1)
    TextView blockSize1;

    @BindView(R.id.filter_card)
    CardView filterCard;
    @BindView(R.id.close)
    RelativeLayout close;
    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.apply)
    TextView apply;
    @BindView(R.id.sort_by_lay)
    RelativeLayout sortByLay;
    @BindView(R.id.region_lay)
    RelativeLayout regionLay;
    @BindView(R.id.block_size_lay)
    RelativeLayout blockSizeLay;

    SalesListAdapter adapter;

    List<SalesResult> salesList = new ArrayList<>();


    LinearLayoutManager layoutManager;

    boolean isLastPage = false;
    boolean isLoading = false;
    int PAGE_SIZE = 10;
    @BindView(R.id.purchase)
    TextView purchase;
    @BindView(R.id.rent)
    TextView rent;


    List<Size> blockSizeList = new ArrayList<>();
    List<RegionResult> regionResultList = new ArrayList<>();
    List<ModelSortBy> sortByList = new ArrayList<>();
    ModelSortBy selectedSortBy;

    BlockSizeAdapter blockSizeAdapter;
    SortByAdapter sortByAdapter;
    RegionsAdapter regionsAdapter;

    static String OPEN_FILTER, _sCDT;

    String PURCHASE_OR_RENT;
    String sortBy;
    ArrayList<String> sizeIntList = new ArrayList<>();
    ArrayList<String> regionIntList = new ArrayList<>();
    ArrayList<String> salesTypeIntList = new ArrayList<>();
    @BindView(R.id.sort_by_text)
    TextView sortByText;
    @BindView(R.id.region_text)
    TextView regionText;
    @BindView(R.id.block_size)
    TextView blockSize;

    public SalesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        //get current server time(API) 
        presenter.getServerCDT();
        BusProvider.getInstance().register(this);
        
        initUi(view);
        callAPis();
        setAdapter();

        PURCHASE_OR_RENT = Constants.PURCHASE;
        if (util.isNetworkAvailable()) {
            presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, page_no);

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
                            presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, page_no);
                        }
                    }
                }

            }
        };
        rec.addOnScrollListener(recyclerViewOnScrollListener);

        return view;
    }

    private void callAPis() {
        if (util.isNetworkAvailable()) {
            presenter.getSortBy();
            presenter.getBlockSize();
            presenter.getRegions();
        } else
            util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);
    }


    private void setAdapter() {
        if (getActivity() != null) {
            layoutManager = new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rec.setLayoutManager(layoutManager);
        }
        adapter = new SalesListAdapter(getActivity(), salesList);
        rec.setAdapter(adapter);
    }

    private void initUi(View v) {
        Util.getUtils().overrideFontsBold(getActivity(), Title);
        Util.getUtils().overrideFontsBold(getActivity(), purchase);
        Util.getUtils().overrideFontsBold(getActivity(), rent);
        Util.getUtils().overrideFontsSemiBold(getActivity(), sortByText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), regionText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), blockSize);
        Util.getUtils().overrideFontsSemiBold(getActivity(), apply);
        Util.getUtils().overrideFontsSemiBold(getActivity(), clear);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void ShowProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void StopProgress() {
        if(progress!= null)
        progress.setVisibility(View.GONE);
    }

    @OnClick(R.id.purchase)
    void onSale() {
        presenter.getServerCDT();
        purchase.setBackgroundResource(R.drawable.status_bar_bg_green);
        rent.setBackgroundResource(R.drawable.status_bar_bg);

        PURCHASE_OR_RENT = Constants.PURCHASE;
        salesList.clear();
        presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
    }

    @OnClick(R.id.rent)
    void onLease() {
        presenter.getServerCDT();
        rent.setBackgroundResource(R.drawable.status_bar_bg_green);
        purchase.setBackgroundResource(R.drawable.status_bar_bg);

        PURCHASE_OR_RENT = Constants.RENT;
        salesList.clear();
        presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
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
        presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
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

        onClose();
        salesList.clear();
        presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, 0);
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
        } else {
            salesList.clear();
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void SortByResult(List<ModelSortBy> modelSortBy) {
        if (modelSortBy != null)
            sortByList = modelSortBy;
        if(getActivity() != null)
        sortByAdapter = new SortByAdapter((ArrayList<ModelSortBy>) modelSortBy, getActivity());
        if(listViewSortby!=null && sortByAdapter != null)
            listViewSortby.setAdapter(sortByAdapter);
    }

    @Override
    public void BlockSizeResult(List<Size> size) {
        if (size != null)
            blockSizeList = size;
        if(getActivity() != null)
        blockSizeAdapter = new BlockSizeAdapter((ArrayList<Size>) blockSizeList, getActivity(), new BlockSizeAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                blockSizeList.get(position).setChecked(!blockSizeList.get(position).isChecked());
                blockSizeAdapter.notifyDataSetChanged();
            }
        });

        if(blockSizeAdapter != null)
        listViewBlockSize.setAdapter(blockSizeAdapter);
    }

    @Override
    public void RegionResult(List<RegionResult> regions) {
        if (regions != null)
            regionResultList = regions;
        if(getActivity()!= null)
        regionsAdapter = new RegionsAdapter((ArrayList<RegionResult>) regionResultList, getActivity(), new RegionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                regionResultList.get(position).setChecked(!regionResultList.get(position).isChecked());
                regionsAdapter.notifyDataSetChanged();
            }
        });
        if (getActivity() != null) {
            listViewRegions.setLayoutManager(new LinearLayoutManager(getActivity()));
            listViewRegions.setAdapter(regionsAdapter);
        }
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

    @Subscribe
    public void onEvent(MessageData messageData) {
        String[] seperatedString = messageData.getMsgData().split(",");
        if (seperatedString[0].equalsIgnoreCase(Constants.BID_SUBMITTED)) {
            page_no = 0;
            salesList.clear();
            presenter.getLists(PURCHASE_OR_RENT, sortBy, sizeIntList, regionIntList, salesTypeIntList, page_no);

        }
    }
}
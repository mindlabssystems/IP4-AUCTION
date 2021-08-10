package com.xsusenet.ip4.UI.SellProduct;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Adapters.AddRegionAdapter;
import com.xsusenet.ip4.Adapters.BlockListAdapter;
import com.xsusenet.ip4.Adapters.RegionsAdapter;
import com.xsusenet.ip4.Adapters.RegionsListAdapter;
import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class AddProductActivity extends DaggerAppCompatActivity implements AddProductContract.view, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.go_back)
    RelativeLayout goBack;
    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.category_tx)
    TextView categoryTx;
    @BindView(R.id.rb_ipv4)
    AppCompatRadioButton rbIpv4;
    @BindView(R.id.rb_asn)
    AppCompatRadioButton rbAsn;
    @BindView(R.id.rgp_category)
    RadioGroup rgpCategory;
    @BindView(R.id.block_size)
    TextView blockSize;
    @BindView(R.id.no_of_address)
    TextView noOfAddress;
    @BindView(R.id.region)
    TextView region;
    @BindView(R.id.sale_method_tx)
    TextView saleMethodTx;
    @BindView(R.id.rb_auction)
    AppCompatRadioButton rbAuction;
    @BindView(R.id.rb_buy_now)
    AppCompatRadioButton rbBuyNow;
    @BindView(R.id.rgp_sale_method)
    RadioGroup rgpSaleMethod;
    @BindView(R.id.sale_type_tx)
    TextView saleTypeTx;
    @BindView(R.id.rb_sale)
    AppCompatRadioButton rbSale;
    @BindView(R.id.rb_rent)
    AppCompatRadioButton rbRent;
    @BindView(R.id.rgp_sale_type)
    RadioGroup rgpSaleType;
    @BindView(R.id.sale_price)
    EditText salePrice;
    @BindView(R.id.minimum_term)
    EditText minimumTerm;
//    @BindView(R.id.end_date)
//    TextView endDate;
    @BindView(R.id.starting_ip)
    EditText startingIp;
//    @BindView(R.id.ending_ip)
//    EditText endingIp;
    @BindView(R.id.detail_info)
    EditText detailInfo;
    @BindView(R.id.notes)
    EditText notes;
    @BindView(R.id.submit_but)
    TextView submitBut;
    @BindView(R.id.transferable_tx)
    TextView transferableTX;
    @BindView(R.id.progress)
    LinearLayout progress;

    String SALE_OR_RENT = "1";
    String AUCTION_OR_SELL = "1";
    String region_id, size_id, MONTH, YEAR, DAY, END_DATE,
            sBlock, sAddress, sRegion, sPrice, sMinterm, sDate, sStartIP, sEndIP, sInfo, sNote="";

    List<RegionResult> regionList = new ArrayList<>();
    List<RegionResult> regionList_ = new ArrayList<>();
    List<Size> sizeList = new ArrayList<>();
    List<String> regionIntList = new ArrayList<>();
    RegionsListAdapter regionsAdapter;
    BlockListAdapter blockListAdapter;
    AddRegionAdapter adapter;
    @Inject
    Util util;

    @Inject
    AddProductPresenterImpl presenter;

    @BindView(R.id.regions_rec)
    RecyclerView regionsRec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        initUI();
        setTrasferable();

        callAPI();

    }

    private void setTrasferable() {

        adapter = new AddRegionAdapter((ArrayList<RegionResult>) regionList_, AddProductActivity.this, new RegionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                regionList_.get(position).setChecked(!regionList_.get(position).isChecked());
                adapter.notifyDataSetChanged();
            }
        });
        regionsRec.hasFixedSize();
        regionsRec.setLayoutManager(new LinearLayoutManager(AddProductActivity.this));
        regionsRec.setAdapter(adapter);
    }

    private void callAPI() {
        if (util.isNetworkAvailable()) {
            presenter.getBlockSize();
            presenter.getRegions();
        } else {
            Util.getUtils().displayToast(AddProductActivity.this, String.valueOf(R.string.network_unavailable), 2);
        }
    }

    private void initUI() {
        Util.getUtils().overrideFontsBold(AddProductActivity.this, Title);
        Util.getUtils().overrideFontsBold(AddProductActivity.this, title);
        Util.getUtils().overrideFontsBold(AddProductActivity.this, submitBut);
        Util.getUtils().overrideFontsSemiBold(AddProductActivity.this, findViewById(R.id.category_tx));
        Util.getUtils().overrideFontsSemiBold(AddProductActivity.this, findViewById(R.id.sale_type_tx));
        Util.getUtils().overrideFontsSemiBold(AddProductActivity.this, findViewById(R.id.sale_method_tx));
        Util.getUtils().overrideFontsSemiBold(AddProductActivity.this, findViewById(R.id.transferable_tx));

        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.block_size));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.no_of_address));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.region));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.sale_price));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.minimum_term));
//        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.end_date));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.starting_ip));
//        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.ending_ip));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.detail_info));
//        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.notes));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.rb_auction));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.rb_buy_now));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.rb_ipv4));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.rb_asn));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.rb_rent));
        Util.getUtils().overrideFontsRegular(AddProductActivity.this, findViewById(R.id.rb_sale));

        rbIpv4.setChecked(true);
        rbAuction.setChecked(true);
        rbSale.setChecked(true);
        rgpCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                if (checkedId == R.id.rb_ipv4) {

                } else if (checkedId == R.id.rb_asn) {

                }
            }
        });

        rgpSaleType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                if (checkedId == R.id.rb_sale) {
                    minimumTerm.setVisibility(View.GONE);
                    SALE_OR_RENT = "1";

                    regionIntList.clear();
                    regionList_.clear();
                    presenter.getRegions();

                    transferableTX.setVisibility(View.VISIBLE);
                    regionsRec.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rb_rent) {
                    minimumTerm.setVisibility(View.VISIBLE);
                    SALE_OR_RENT = "2";
                    regionIntList.clear();
                    regionList_.clear();
                    transferableTX.setVisibility(View.GONE);
                    regionsRec.setVisibility(View.GONE);
                }
            }
        });

        rgpSaleMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.rb_auction) {
                    AUCTION_OR_SELL = "1";
                } else if (checkedId == R.id.rb_buy_now) {
                    AUCTION_OR_SELL = "2";
                }
            }
        });
    }

    @OnClick(R.id.submit_but)
    void onSubmit() {
        if (Util.getUtils().isNetworkAvailable()) {
            if (regionList_ != null && regionList_.size() > 0)
                for (RegionResult result : regionList_) {
                    if (result.isChecked())
                        regionIntList.add(result.getRegionId());
                }

            if (checkBlockSize()  && checkRegion() && checkSalePrice()) {
                if (SALE_OR_RENT.equals("2")) {
                    if (checkMinTerm()  && checkStartingIP()   ) {
                        presenter.Submit(size_id, sAddress, region_id, AUCTION_OR_SELL, SALE_OR_RENT, sPrice, sMinterm, sDate, sStartIP, "", "", sNote, regionIntList);
                    }
                } else {
                    if (checkTransferable()&& checkStartingIP()  ) {
                        presenter.Submit(size_id, sAddress, region_id, AUCTION_OR_SELL, SALE_OR_RENT, sPrice, "", sDate, sStartIP, "", "", sNote, regionIntList);
                    }
                }
            }
        } else {
            util.displayToast(AddProductActivity.this, getString(R.string.network_unavailable), 2);
        }

    }

    private boolean checkTransferable() {
       if(regionIntList != null){
           if(regionIntList.size() >0){
               return true;
           }else {
               util.displayToast(this, "Transferable to is required for sale type \"Sale\"", 2);
               return false;

           }
       }else{
           util.displayToast(this, "Transferable to is required for sale type \"Sale\"", 2);
           return false;
       }

    }
 private boolean checkNotes() {
        sNote = notes.getText().toString().trim();
        return true;
    }

    private boolean checkdetailInfo() {
        sInfo = detailInfo.getText().toString().trim();
        if (!sInfo.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Detail Info Missing", 2);
            return false;
        }
    }

/*
    private boolean checkendingIP() {

        sEndIP = endingIp.getText().toString().trim();
        if (!sEndIP.isEmpty()) {
            if (util.isValidIP(sEndIP))
                return true;
            else {
                util.displayToast(this, "Invalid Ending IP", 2);
                return false;
            }

        } else {
            util.displayToast(this, "Ending IP Missing", 2);
            return false;
        }
    }
*/

    private boolean checkStartingIP() {
        sStartIP = startingIp.getText().toString().trim();
        if (!sStartIP.isEmpty()) {
            if (util.isValidIP(sStartIP))
                return true;
            else {
                util.displayToast(this, "Invalid Starting IP", 2);
                return false;
            }

        } else {
            util.displayToast(this, "Starting IP Missing", 2);
            return false;
        }
    }

/*
    private boolean checkEndDate() {
        sDate = endDate.getText().toString().trim();
        if (!sDate.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Date Missing", 2);
            return false;
        }
    }
*/

    private boolean checkMinTerm() {
        sMinterm = minimumTerm.getText().toString().trim();
        if (!sMinterm.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Minimum term Missing", 2);
            return false;
        }
    }

    private boolean checkSalePrice() {
        sPrice = salePrice.getText().toString().trim();
        if (!sPrice.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Sale Price Missing", 2);
            return false;
        }
    }

    private boolean checkRegion() {

        sRegion = region.getText().toString().trim();
        if (!sRegion.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "RIP Missing", 2);
            return false;
        }
    }

/*
    private boolean checkAddress() {
        sAddress = noOfAddress.getText().toString().trim();
        if (!sAddress.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "No of Address Missing", 2);
            return false;
        }
    }
*/

    private boolean checkBlockSize() {
        sBlock = blockSize.getText().toString().trim();
        if (!sBlock.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Block size Missing", 2);
            return false;
        }
    }

    @OnClick(R.id.back)
    void onBack() {
        onBackPressed();
    }


/*
    @OnClick(R.id.end_date)
    void onEndDate() {
        showDatePickerDialog();
    }
*/


    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddProductActivity.this,
                AddProductActivity.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @OnClick(R.id.region)
    void setRegion() {
        final Dialog dialog = new Dialog(AddProductActivity.this);
        dialog.setContentView(R.layout.custom_spinner_items);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView listView = dialog.findViewById(R.id.list_view);
        regionsAdapter = new RegionsListAdapter((ArrayList<RegionResult>) regionList, R.layout.spinner_item, AddProductActivity.this);
        listView.setAdapter(regionsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                // TODO Auto-generated method stub
                region.setText(regionList.get(position).getRegionName());
                region_id = regionList.get(position).getRegionId();
//                regionList_.clear();
//                for (RegionResult regionResult : regionList) {
//                    if (!region_id.equals(regionResult.getRegionId())) {
//                        regionResult.setChecked(false);
//                        regionList_.add(regionResult);
//                    }
//                }
                adapter.notifyDataSetChanged();
                dialog.cancel();

            }

        });
        dialog.show();
    }


    @OnClick(R.id.block_size)
    void setBlockSize() {
        final Dialog dialog = new Dialog(AddProductActivity.this);
        dialog.setContentView(R.layout.custom_spinner_items);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView listView = dialog.findViewById(R.id.list_view);
        blockListAdapter = new BlockListAdapter((ArrayList<Size>) sizeList, R.layout.spinner_item, AddProductActivity.this);
        listView.setAdapter(blockListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> arg0, View arg1,

                                    int position, long arg3) {

                // TODO Auto-generated method stub
                blockSize.setText(sizeList.get(position).getBlockSize());
                size_id = sizeList.get(position).getId().toString();
                sAddress = sizeList.get(position).getNoOfSubnets().toString();
                noOfAddress.setText(sAddress);
                dialog.cancel();

            }

        });
        dialog.show();
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
    public void BlockSizeResult(List<Size> size) {
        if (size != null) {
            sizeList = size;
        }

    }

    @Override
    public void RegionResult(List<RegionResult> regions) {
        if (regions != null) {
            regionList = regions;
            this.regionList_.addAll(regions);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowResult(boolean b, String message) {
        if (b) {
            util.displayToast(AddProductActivity.this, message, 1);
            BusProvider.getInstance().post(new MessageData(Constants.NEW_PRODUCT + ","));
            finish();
        } else {
            util.displayToast(AddProductActivity.this, message, 2);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month + 1;
        if (dayOfMonth < 10)
            DAY = "0" + dayOfMonth;
        else
            DAY = "" + dayOfMonth;
        if (Month < 10)
            MONTH = "0" + Month;
        else
            MONTH = "" + Month;
//        YEAR = String.valueOf(year);
//        String date = YEAR + "-" + MONTH + "-" + DAY;
//        END_DATE = date;
//        endDate.setText(date);
    }

    @Override
    protected void onResume() {
        BusProvider.getInstance().register(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }
}

package com.xsusenet.ip4.UI.Buy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.xsusenet.ip4.Models.Detail.DetailList;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.TermsActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.PurchaseActivity;
import com.xsusenet.ip4.UI.Register.RegisterActivity;
import com.xsusenet.ip4.UI.WatchList.WatchListFragment;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.StringUtil;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class BuyActivity extends DaggerAppCompatActivity implements BuyActivityContract.view {

    @BindView(R.id.back)
    AppCompatImageView back;

    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.block_size_tx)
    TextView blockSizeTx;
    @BindView(R.id.block_size)
    TextView blockSize;
    @BindView(R.id.registered_in_tx)
    TextView registeredInTx;
    @BindView(R.id.registered_in)
    TextView registeredIn;
    @BindView(R.id.block_tx)
    TextView blockTx;
    @BindView(R.id.block)
    TextView block;
    @BindView(R.id.number_of_address_tx)
    TextView numberOfAddressTx;
    @BindView(R.id.number_of_address)
    TextView numberOfAddress;
    @BindView(R.id.further_details_tx)
    TextView furtherDetailsTx;
    @BindView(R.id.further_details)
    TextView furtherDetails;
    @BindView(R.id.transferable_to_tx)
    TextView transferableToTx;
    @BindView(R.id.transferable_to)
    TextView transferableTo;
    @BindView(R.id.range_tx)
    TextView rangeTx;
    @BindView(R.id.range)
    TextView range;
    @BindView(R.id.use_full_links_tx)
    TextView useFullLinksTx;
    @BindView(R.id.use_full_links)
    TextView useFullLinks;
    @BindView(R.id.img)
    AppCompatImageView img;
    @BindView(R.id.make_offer_bu_tx)
    TextView makeOfferBuTx;
    @BindView(R.id.make_offer_But)
    RelativeLayout makeOfferBut;
    @BindView(R.id.watch_list)
    AppCompatImageView watchList;
    @BindView(R.id.min_term_title)
    TextView minTermTitle;
    @BindView(R.id.mini_term)
    TextView miniTerm;
    @BindView(R.id.mini_term_lay)
    RelativeLayout miniTermLay;
    @BindView(R.id.buy_tx)
    TextView buyTx;

    @BindView(R.id.view_transferable)
    View viewTransferable;

    String listId;

    @Inject
    Util util;

    @Inject
    BuyActivityPresenterImpl presenter;
    @BindView(R.id.progress)
    LinearLayout progress;

    boolean WATCHED;
    boolean from_watchlist;
    boolean RENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            if (getIntent().getStringExtra("SALE_TYPE").equals(Constants.PURCHASE)) {
                miniTermLay.setVisibility(View.GONE);
                RENT = false;
                useFullLinks.setText("Closing Conditions");
            } else if (getIntent().getStringExtra("SALE_TYPE").equals(Constants.RENT)) {
                miniTermLay.setVisibility(View.VISIBLE);
                RENT = true;
                useFullLinks.setText("Leasing Conditions");
            }
            listId = getIntent().getStringExtra("list_id");
            from_watchlist = getIntent().hasExtra("from_watchlist");
        }
        initUi();

        if (util.isNetworkAvailable()) {
            presenter.getDetail(listId);
        } else {
            util.displayToast(BuyActivity.this, getString(R.string.network_unavailable), 2);
        }
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(BuyActivity.this, title);

        Util.getUtils().overrideFontsBold(BuyActivity.this, blockSize);
        Util.getUtils().overrideFontsBold(BuyActivity.this, registeredIn);
        Util.getUtils().overrideFontsBold(BuyActivity.this, block);
        Util.getUtils().overrideFontsBold(BuyActivity.this, numberOfAddress);
        Util.getUtils().overrideFontsBold(BuyActivity.this, furtherDetails);
        Util.getUtils().overrideFontsBold(BuyActivity.this, transferableTo);
        Util.getUtils().overrideFontsBold(BuyActivity.this, range);
        Util.getUtils().overrideFontsBold(BuyActivity.this, makeOfferBuTx);
        Util.getUtils().overrideFontsBold(BuyActivity.this, miniTerm);


        Util.getUtils().overrideFontsBold(BuyActivity.this, buyTx);


        Util.getUtils().overrideFontsSemiBold(BuyActivity.this, useFullLinks);

        Util.getUtils().overrideFontsRegular(BuyActivity.this, blockSizeTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, registeredInTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, blockTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, numberOfAddressTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, furtherDetailsTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, transferableToTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, rangeTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, useFullLinksTx);
        Util.getUtils().overrideFontsRegular(BuyActivity.this, minTermTitle);

    }

    @OnClick(R.id.use_full_links)
    void onUsefulLinks() {
        if (RENT) {
            Intent intent = new Intent(BuyActivity.this, TermsActivity.class);
            intent.putExtra("from", "leasing");
            intent.putExtra("url", Constants.URL_LEASING);
            startActivity(intent);
        } else {
            Intent intent1 = new Intent(BuyActivity.this, TermsActivity.class);
            intent1.putExtra("from", "closing");
            intent1.putExtra("url",Constants.URL_CLOSING );
            startActivity(intent1);
        }
    }

    @OnClick(R.id.make_offer_But)
    void onBuy() {
        if (Util.getUtils().isNetworkAvailable()) {
            Intent intent = new Intent(BuyActivity.this, PurchaseActivity.class);
            intent.putExtra("list_id", listId);
            if (RENT) {
                intent.putExtra("type", "leasing");
            } else {
                intent.putExtra("type", "closing");
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else {
            util.displayToast(this, getString(R.string.no_internet), 2);
        }
    }

    @OnClick(R.id.watch_list)
    void onWatchList() {
        if (WATCHED) {
            presenter.removeFromWatchList(listId);
        } else {
            presenter.addToWatchList(listId);
        }
    }

    @OnClick(R.id.back)
    void onBack() {
        onBackPressed();
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
    public void setList(DetailList lists) {
        if (lists != null) {
            if (lists.getSaleStatus().equals("2")) {
                makeOfferBut.setVisibility(View.GONE);
            }
            if (lists.getAddedToWatchlist() == 1) {
                WATCHED = true;
                watchList.setImageResource(R.drawable.ic_heart_filled);
            } else {
                WATCHED = false;
                watchList.setImageResource(R.drawable.ic_heart);
            }

            StringBuilder titleB = new StringBuilder();
            titleB.append("/");
            titleB.append(lists.getBlockSize());
            titleB.append(" Block registered in ");
            titleB.append(lists.getRegionName());
            title.setText(titleB);

            String block_size = "/" + lists.getBlockSize();
            blockSize.setText(block_size);
            registeredIn.setText(lists.getRegionName());
            block.setText("");
            numberOfAddress.setText(String.valueOf(lists.getNoOfAddress()));



            if (lists.getSaleOrRent().equals("1")) {
                if (lists.getTransferrableTo() != null) {
                    if (lists.getTransferrableTo().size() > 0) {
                        int size = lists.getTransferrableTo().size();
                        StringBuilder builder = new StringBuilder();

                        for (int i = 0; i < size; i++) {
                            builder.append(lists.getTransferrableTo().get(i).getRegionName());
                            if (i < size - 1)
                                builder.append(", ");
                        }

                        transferableTo.setText(builder);
                    }
                }

            } else {
                transferableTo.setVisibility(View.GONE);
                transferableToTx.setVisibility(View.GONE);
                viewTransferable.setVisibility(View.GONE);

            }

            miniTerm.setText(lists.getRentMinTerm().toString());

            StringBuilder title = new StringBuilder();
            String[] strArray = lists.getStartingIp().split("\\.");
            for (int j = 0; j < strArray.length; j++) {
                if (j == 0) {
                    for (int i = 0; i < strArray[j].length(); i++) {
                        title.append(strArray[j].charAt(i));
                    }
                } else {
                    for (int i = 0; i < strArray[j].length(); i++) {
                        title.append("X");
                    }
                }
                if (j != strArray.length - 1)
                    title.append(".");
            }

            String blockS = "/" + lists.getBlockSize();
            title.append(blockS);
            block.setText(title);
        } else {
            Util.getUtils().displayToast(BuyActivity.this, "Details not found", 2);
        }
    }

    @Override
    public void WatchResult(boolean b, String message) {
        if (b) {
            WATCHED = true;
            watchList.setImageResource(R.drawable.ic_heart_filled);
            util.displayToast(BuyActivity.this, message, 1);
        } else {
            util.displayToast(BuyActivity.this, message, 2);
        }
    }

    @Override
    public void WatchRemoveResult(boolean b, String message) {
        if (b) {
            WATCHED = false;
            watchList.setImageResource(R.drawable.ic_heart);
            util.displayToast(BuyActivity.this, message, 1);
            BusProvider.getInstance().post(new MessageData(Constants.REMOVED_FROM_WATCH_LIST + ","));
            if (from_watchlist)
                finish();
        } else {
            util.displayToast(BuyActivity.this, message, 2);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

}

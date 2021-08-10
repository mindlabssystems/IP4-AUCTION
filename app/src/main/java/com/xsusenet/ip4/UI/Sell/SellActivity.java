package com.xsusenet.ip4.UI.Sell;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.DetailList;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.TermsActivity;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.StringUtil;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class SellActivity extends DaggerAppCompatActivity implements SellActivityContract.view {

    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.sell_tx)
    TextView sellTx;
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
    @BindView(R.id.view_transferable)
    View viewTransferable;

    @BindView(R.id.purchase_info_lay)
    LinearLayout purchaseinfoLay;
    @BindView(R.id.purchase_info)
    TextView purchaseInfo;
    @BindView(R.id.purchaser_tx)
    TextView purchaserTx;
    @BindView(R.id.amount_tx)
    TextView totalAmountTx;
    @BindView(R.id.transaction_date_tx)
    TextView transactionDateTx;

    @BindView(R.id.purchaser)
    TextView purchaser;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.transaction_date)
    TextView transactionDate;
    @Inject
    Util util;

    @Inject
    SellActivityPresenterImpl presenter;
    String listId;
    @BindView(R.id.progress)
    LinearLayout progress;

    boolean RENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ButterKnife.bind(this);
        initUi();

        if (getIntent().getExtras() != null) {
            listId = getIntent().getStringExtra("list_id");
        }
        if (util.isNetworkAvailable()) {
            presenter.getDetail(listId);
        } else {
            util.displayToast(SellActivity.this, getString(R.string.network_unavailable), 2);
        }
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(SellActivity.this, title);

        Util.getUtils().overrideFontsBold(SellActivity.this, blockSize);
        Util.getUtils().overrideFontsBold(SellActivity.this, registeredIn);
        Util.getUtils().overrideFontsBold(SellActivity.this, block);
        Util.getUtils().overrideFontsBold(SellActivity.this, numberOfAddress);
        Util.getUtils().overrideFontsBold(SellActivity.this, furtherDetails);
        Util.getUtils().overrideFontsBold(SellActivity.this, transferableTo);
        Util.getUtils().overrideFontsBold(SellActivity.this, range);
        Util.getUtils().overrideFontsBold(SellActivity.this, purchaserTx);
        Util.getUtils().overrideFontsBold(SellActivity.this, totalAmountTx);
        Util.getUtils().overrideFontsBold(SellActivity.this, transactionDateTx);


        Util.getUtils().overrideFontsBold(SellActivity.this, sellTx);
        Util.getUtils().overrideFontsBold(SellActivity.this, purchaseInfo);


        Util.getUtils().overrideFontsSemiBold(SellActivity.this, useFullLinks);

        Util.getUtils().overrideFontsRegular(SellActivity.this, blockSizeTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, registeredInTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, blockTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, numberOfAddressTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, furtherDetailsTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, transferableToTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, rangeTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, useFullLinksTx);
        Util.getUtils().overrideFontsRegular(SellActivity.this, purchaser);
        Util.getUtils().overrideFontsRegular(SellActivity.this, amount);
        Util.getUtils().overrideFontsRegular(SellActivity.this, transactionDate);

    }

    @OnClick(R.id.use_full_links)
    void onUsefulLinks() {
        if (RENT) {
            Intent intent = new Intent(SellActivity.this, TermsActivity.class);
            intent.putExtra("from", "leasing");
            intent.putExtra("url", Constants.URL_LEASING);
            startActivity(intent);
        } else {
            Intent intent1 = new Intent(SellActivity.this, TermsActivity.class);
            intent1.putExtra("from", "closing");
            intent1.putExtra("url", Constants.URL_CLOSING);
            startActivity(intent1);
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


            if (lists.getUserId().toString().equals(MyPreferenceManager.getInstance().getPref(Constants.USER_ID))) {
                useFullLinks.setVisibility(View.GONE);

                if (!lists.getPurchaseResult().isEmpty()) {
                    purchaseinfoLay.setVisibility(View.VISIBLE);
                    purchaserTx.setText(lists.getPurchaseResult().get(0).getName());
                    totalAmountTx.setText(lists.getPurchaseResult().get(0).getTotalAmount().toString());
                    transactionDateTx.setText(lists.getPurchaseResult().get(0).getTransactionDate().toString());
                }
            } else {
                purchaseinfoLay.setVisibility(View.GONE);
                useFullLinks.setVisibility(View.VISIBLE);
            }
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
                RENT = false;
                useFullLinks.setText("Closing Conditions");

            } else {
                transferableTo.setVisibility(View.GONE);
                transferableToTx.setVisibility(View.GONE);
                viewTransferable.setVisibility(View.GONE);
                RENT = true;
                useFullLinks.setText("Leasing Conditions");
            }

            StringBuilder rangeS = new StringBuilder();
            rangeS.append(lists.getStartingIp());
            rangeS.append(" - ");
            rangeS.append(lists.getEndingIp());
            range.setText(rangeS);


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
            Util.getUtils().displayToast(SellActivity.this, "Details not found", 2);
        }
    }
}

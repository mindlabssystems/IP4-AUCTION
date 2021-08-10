package com.xsusenet.ip4.UI.SellAuction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.Guideline;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.DetailList;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.TermsActivity;
import com.xsusenet.ip4.UI.Bids.BidListActivity;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.StringUtil;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import dagger.android.support.DaggerAppCompatActivity;

public class SellAuctionActivity extends DaggerAppCompatActivity implements SellAuctionActivityContract.view {

    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.buy_tx)
    TextView buyTx;
    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.auction_detail)
    TextView auctionDetail;
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
    @BindView(R.id.make_offer_title)
    TextView makeOfferTitle;
    @BindView(R.id.offer_range_title)
    TextView offerRangeTitle;
    //    @BindView(R.id.hr_text)
//    TextView hrText;
    @BindView(R.id.hr)
    TextView hr;
    //    @BindView(R.id.minit_text)
//    TextView minitText;
    @BindView(R.id.minit)
    TextView minit;
    //    @BindView(R.id.second_text)
//    TextView secondText;
    @BindView(R.id.second)
    TextView second;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.guide_3)
    Guideline guide3;
    @BindView(R.id.opening_bid_title)
    TextView openingBidTitle;
    @BindView(R.id.opening_bid)
    TextView openingBid;
    @BindView(R.id.price_per_address_tx)
    TextView pricePerAddressTx;
    @BindView(R.id.price_per_address)
    TextView pricePerAddress;
    @BindView(R.id.no_bids)
    TextView noBids;
    @BindView(R.id.bids_count)
    TextView bidsCount;

    @BindView(R.id.go_to_bids)
    RelativeLayout goToBids;
    @BindView(R.id.min_term_title)
    TextView minTermTitle;
    @BindView(R.id.mini_term)
    TextView miniTerm;
    @BindView(R.id.mini_term_lay)
    RelativeLayout miniTermLay;
    @BindView(R.id.progress)
    LinearLayout progress;
    @BindView(R.id.view_transferable)
    View viewTransferable;


    @Inject
    Util util;

    @Inject
    SellAuctionActivityPresenterImpl presenter;

    String listId;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.view_bids)
    AppCompatImageView viewBids;
    @BindView(R.id.timer)
    CountdownView timer;
    int total_amount;

    boolean RENT;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_auction);
        ButterKnife.bind(this);
        initUi();

        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra("SALE_TYPE")) {
                if (getIntent().getStringExtra("SALE_TYPE").equals(Constants.SALE)) {
                    miniTermLay.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                } else if (getIntent().getStringExtra("SALE_TYPE").equals(Constants.LEASE)) {
                    miniTermLay.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);
                }
            }
            listId = getIntent().getStringExtra("list_id");
        }


        if (util.isNetworkAvailable()) {
            presenter.getDetail(listId);
        } else {
            util.displayToast(SellAuctionActivity.this, getString(R.string.network_unavailable), 2);
        }

    }

    @OnClick(R.id.use_full_links)
    void onUsefulLinks() {
        if (RENT) {
            Intent intent = new Intent(SellAuctionActivity.this, TermsActivity.class);
            intent.putExtra("from", "leasing");
            intent.putExtra("url", Constants.URL_LEASING);
            startActivity(intent);
        } else {
            Intent intent1 = new Intent(SellAuctionActivity.this, TermsActivity.class);
            intent1.putExtra("from", "closing");
            intent1.putExtra("url", Constants.URL_CLOSING);
            startActivity(intent1);
        }
    }

    @OnClick(R.id.go_to_bids)
    void onViewBids() {
        if (!bidsCount.getText().toString().isEmpty()) {
            int count = Integer.parseInt(bidsCount.getText().toString());
            if (count > 0) {
                Intent intent = new Intent(SellAuctionActivity.this, BidListActivity.class);
                intent.putExtra("list_id", listId);
                intent.putExtra("title", title.getText().toString());
                startActivity(intent);
            } else {
                util.displayToast(SellAuctionActivity.this, "No More Bid", 1);
            }
        }

    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, title);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, auctionDetail);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, blockSize);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, registeredIn);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, block);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, numberOfAddress);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, furtherDetails);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, transferableTo);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, range);
//        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, hrText);
//        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, minitText);
//        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, secondText);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, timer);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, openingBid);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, pricePerAddress);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, makeOfferTitle);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, buyTx);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, bidsCount);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, miniTerm);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, purchaserTx);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, totalAmountTx);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, transactionDateTx);
        Util.getUtils().overrideFontsBold(SellAuctionActivity.this, purchaseInfo);

        Util.getUtils().overrideFontsSemiBold(SellAuctionActivity.this, hr);
        Util.getUtils().overrideFontsSemiBold(SellAuctionActivity.this, minit);
        Util.getUtils().overrideFontsSemiBold(SellAuctionActivity.this, second);
        Util.getUtils().overrideFontsSemiBold(SellAuctionActivity.this, useFullLinks);

        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, blockSizeTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, registeredInTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, blockTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, numberOfAddressTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, furtherDetailsTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, transferableToTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, rangeTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, useFullLinksTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, time);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, openingBidTitle);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, pricePerAddressTx);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, offerRangeTitle);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, noBids);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, minTermTitle);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, purchaser);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, amount);
        Util.getUtils().overrideFontsRegular(SellAuctionActivity.this, transactionDate);
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


            if (lists.getSaleMethod().equals("1")) {
                if (lists.getNoOfBids() > 0) {
                    total_amount = lists.getCurrentBid();
                } else {
                    total_amount = lists.getOpeningBid();
                }
            } else {
                total_amount = lists.getSalePrice();
            }


            float no_of_address = Float.parseFloat(lists.getNoOfAddress().toString());
            double price_per_add = total_amount / no_of_address;
            String s = "$" + round(price_per_add, 2);
            pricePerAddress.setText(s);


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

            if (lists.getRentMinTerm() != null)
                if (lists.getRentMinTerm().toString().isEmpty() || lists.getRentMinTerm() == 0) {
                    if (lists.getSaleOrRent().equals("1")) {
                        miniTermLay.setVisibility(View.GONE);
                        view1.setVisibility(View.GONE);
                    }
                } else {
                    if (lists.getRentMinTerm() >= 1) {
                        miniTerm.setText(lists.getRentMinTerm().toString());
                        miniTermLay.setVisibility(View.VISIBLE);
                    }
                }

            if (lists.getNoOfBids() > 0) {
                openingBidTitle.setText("CURRENT BID:");
                String bid = "$" + lists.getCurrentBid();
                openingBid.setText(bid);

            } else {
                openingBidTitle.setText("OPENING BID:");
                String bid = "$" + lists.getOpeningBid();
                openingBid.setText(bid);
            }

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

            bidsCount.setText(String.valueOf(lists.getNoOfBids()));

            String cdt = Util.getUtils().getCDT();
            String[] ends_d_t = lists.getEndDate().split(" ");
            String[] ends = ends_d_t[0].split("-");
            String endsdt = ends[1] + "/" + ends[2] + "/" + ends[0] + " 23:59:59";
            String timeS = ends[2] + "/" + ends[1] + "/" + ends[0] + " at  23:59:59";
            time.setText(timeS);
            long minites = Util.getUtils().differenceInMinit(cdt, endsdt);

            if (minites > 0) {
                timer.start(minites);
            } else {
                timer.stop();
                timer.allShowZero();
            }
        } else {
            Util.getUtils().displayToast(SellAuctionActivity.this, "Details not found", 2);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
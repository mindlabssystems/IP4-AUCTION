package com.xsusenet.ip4.UI.BuyAuction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.Guideline;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.DetailList;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.TermsActivity;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.PurchaseActivity;
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
import cn.iwgang.countdownview.CountdownView;
import dagger.android.support.DaggerAppCompatActivity;

public class BuyAuctionActivity extends DaggerAppCompatActivity implements BuyAuctionContract.view {

    @BindView(R.id.back)
    AppCompatImageView back;
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
    @BindView(R.id.hr)
    TextView hr;
    @BindView(R.id.minit)
    TextView minit;
    @BindView(R.id.second)
    TextView second;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.opening_bid_title)
    TextView openingBidTitle;
    @BindView(R.id.opening_bid)
    TextView openingBid;
    @BindView(R.id.price_per_address_tx)
    TextView pricePerAddressTx;
    @BindView(R.id.price_per_address)
    TextView pricePerAddress;

    @BindView(R.id.make_offer_bu_tx)
    TextView makeOfferBuTx;

    @BindView(R.id.make_offer_But)
    RelativeLayout makeOfferBut;
    @BindView(R.id.buy_tx)
    TextView buyTx;
    @BindView(R.id.guide_3)
    Guideline guide3;
    @BindView(R.id.min_term_title)
    TextView minTermTitle;
    @BindView(R.id.mini_term)
    TextView miniTerm;
    @BindView(R.id.mini_term_lay)
    RelativeLayout miniTermLay;
    @BindView(R.id.watch_list)
    AppCompatImageView watchList;
    @BindView(R.id.view_transferable)
    View viewTransferable;
    @BindView(R.id.last_view)
    View _lastView;

    String listId;
    int openingbidAmount, percentbidAmount;


    @Inject
    Util util;

    @Inject
    BuyAuctionPresenterImpl presenter;
    @BindView(R.id.progress)
    LinearLayout progress;
    @BindView(R.id.bid_amount_ed)
    EditText bidAmountEd;

    @BindView(R.id.img)
    AppCompatImageView butIcon;
    boolean WATCHED;
    @BindView(R.id.timer)
    CountdownView timer;
    int total_amount;
    boolean from_watchlist;
    boolean bid_purchase = false;

    boolean RENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_auction);
        ButterKnife.bind(this);
        initUi();

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


        if (util.isNetworkAvailable()) {
            presenter.getServerCDT();
            presenter.getDetail(listId);
        } else {
            util.displayToast(BuyAuctionActivity.this, getString(R.string.network_unavailable), 2);
        }
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, title);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, auctionDetail);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, blockSize);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, registeredIn);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, block);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, numberOfAddress);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, furtherDetails);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, transferableTo);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, range);

        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, openingBid);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, pricePerAddress);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, makeOfferBuTx);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, makeOfferTitle);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, buyTx);
        Util.getUtils().overrideFontsBold(BuyAuctionActivity.this, miniTerm);


        Util.getUtils().overrideFontsSemiBold(BuyAuctionActivity.this, hr);
        Util.getUtils().overrideFontsSemiBold(BuyAuctionActivity.this, minit);
        Util.getUtils().overrideFontsSemiBold(BuyAuctionActivity.this, second);
        Util.getUtils().overrideFontsSemiBold(BuyAuctionActivity.this, useFullLinks);

        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, blockSizeTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, registeredInTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, blockTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, numberOfAddressTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, furtherDetailsTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, transferableToTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, rangeTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, useFullLinksTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, time);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, openingBidTitle);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, pricePerAddressTx);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, offerRangeTitle);
        Util.getUtils().overrideFontsRegular(BuyAuctionActivity.this, minTermTitle);
    }

    @OnClick(R.id.use_full_links)
    void onUsefulLinks() {
        if (RENT) {
            Intent intent = new Intent(BuyAuctionActivity.this, TermsActivity.class);
            intent.putExtra("from", "leasing");
            intent.putExtra("url", Constants.URL_LEASING);
            startActivity(intent);
        } else {
            Intent intent1 = new Intent(BuyAuctionActivity.this, TermsActivity.class);
            intent1.putExtra("from", "closing");
            intent1.putExtra("url", Constants.URL_CLOSING);
            startActivity(intent1);
        }
    }


    @OnClick(R.id.back)
    void onBack() {
        onBackPressed();
    }


    @OnClick(R.id.make_offer_But)
    void onMakeOffer() {
        if (util.isNetworkAvailable()) {
            if (bid_purchase) {
                if (!bidAmountEd.getText().toString().isEmpty()) {
                    if (Integer.parseInt(bidAmountEd.getText().toString().trim()) >= percentbidAmount) {
                        presenter.makeAnOffer(bidAmountEd.getText().toString(), listId);
                    } else {
                        util.displayToast(BuyAuctionActivity.this, "Enter $" + percentbidAmount + " or more", 2);
                    }

                } else {
                    util.displayToast(BuyAuctionActivity.this, "Enter bid amount", 2);
                }
            } else {
                if (makeOfferBuTx.getText().toString().equals("Purchase")) {
                    Intent intent = new Intent(BuyAuctionActivity.this, PurchaseActivity.class);
                    intent.putExtra("list_id", listId);
                    if (RENT) {
                        intent.putExtra("type", "leasing");
                    } else {
                        intent.putExtra("type", "closing");
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }

            }
        } else
            util.displayToast(BuyAuctionActivity.this, getString(R.string.network_unavailable), 2);

    }

    @OnClick(R.id.watch_list)
    void onWatchList() {
        if (WATCHED) {
            if (util.isNetworkAvailable())
                presenter.removeFromWatchList(listId);
            else
                util.displayToast(BuyAuctionActivity.this, getString(R.string.network_unavailable), 2);
        } else {
            if (util.isNetworkAvailable())
                presenter.addToWatchList(listId);
            else
                util.displayToast(BuyAuctionActivity.this, getString(R.string.network_unavailable), 2);
        }
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
                bidAmountEd.setVisibility(View.GONE);
                bid_purchase = false;
                if (lists.getWinner() == Integer.parseInt(MyPreferenceManager.getInstance().getPref(Constants.USER_ID))) {
                    if (lists.getTransactionStatus().equals("0")) {
                        makeOfferBuTx.setText("Purchase");
                    } else if (lists.getTransactionStatus().equals("1")) {
                        butIcon.setVisibility(View.GONE);
                        makeOfferBuTx.setText("You purchased this Auction");
                    }
                } else {
                    butIcon.setVisibility(View.GONE);
                    makeOfferBuTx.setText("You Lose\n(Your Bid $" + lists.getMyBidAmount() + ")");
                }
            } else if (lists.getSaleStatus().equals("1")) {
                if (lists.getBidsPlaced() == 1) {
                    butIcon.setVisibility(View.GONE);
                    bidAmountEd.setVisibility(View.GONE);
                    makeOfferBuTx.setText("Bid Ongoing\n(your bid $" + lists.getMyBidAmount() + ")");
                } else {
                    bid_purchase = true;
                    butIcon.setVisibility(View.VISIBLE);
                    bidAmountEd.setVisibility(View.VISIBLE);
                    makeOfferBuTx.setText("Make an offer");
                }
            } else {
                bid_purchase = true;
                butIcon.setVisibility(View.VISIBLE);
                bidAmountEd.setVisibility(View.VISIBLE);
                makeOfferBuTx.setText("Make an offer");
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
            } else {
                transferableTo.setVisibility(View.GONE);
                transferableToTx.setVisibility(View.GONE);
                viewTransferable.setVisibility(View.GONE);
            }


            if(lists.getSaleOrRent().equals("1")) {
                miniTermLay.setVisibility(View.GONE);
            }
            if (lists.getRentMinTerm() != null)
                miniTerm.setText(lists.getRentMinTerm().toString());
            if (lists.getNoOfBids() > 0) {
                openingBidTitle.setText("CURRENT BID:");
                String bid = "$" + lists.getCurrentBid();
                openingBid.setText(bid);
                openingbidAmount = lists.getCurrentBid();
                percentbidAmount = (int) (openingbidAmount * (5.0f / 100.0f)) + openingbidAmount;
                bidAmountEd.setHint("Enter $" + percentbidAmount + " or more");
            } else {
                openingBidTitle.setText("OPENING BID:");
                String bid = "$" + lists.getOpeningBid();
                openingBid.setText(bid);
                openingbidAmount = lists.getOpeningBid();
                percentbidAmount = (int) (openingbidAmount * (5.0f / 100.0f)) + openingbidAmount;
                bidAmountEd.setHint("Enter $" + percentbidAmount + " or more");
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


            String cdt = " ";
            String sCDT = MyPreferenceManager.getInstance().getPref("STIME", " ");
            cdt = sCDT.split(" ")[0].split("-")[1] + "/" + sCDT.split(" ")[0].split("-")[2] + "/" + sCDT.split(" ")[0].split("-")[0] + "  " + sCDT.split(" ")[1];

            if (cdt.isEmpty()) {
                cdt = Util.getUtils().getCDT();
            }

            String[] ends_d_t = lists.getEndDate().split(" ");
            String[] ends = ends_d_t[0].split("-");
            String endsdt = ends[1] + "/" + ends[2] + "/" + ends[0] + " 23:59:59";
            Log.d("TAG", endsdt);
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
            Util.getUtils().displayToast(BuyAuctionActivity.this, "Details not found", 2);
        }
    }

    @Override
    public void showResult(boolean b, String message) {
        if (b) {
            util.displayToast(BuyAuctionActivity.this, message, 1);
            BusProvider.getInstance().post(new MessageData(Constants.BID_SUBMITTED + ","));
            onBackPressed();
        } else {
            util.displayToast(BuyAuctionActivity.this, message, 2);
        }
    }

    @Override
    public void WatchResult(boolean b, String message) {
        if (b) {
            WATCHED = true;
            watchList.setImageResource(R.drawable.ic_heart_filled);
            util.displayToast(BuyAuctionActivity.this, message, 1);
        } else {
            util.displayToast(BuyAuctionActivity.this, message, 2);
        }
    }

    @Override
    public void WatchRemoveResult(boolean b, String message) {
        if (b) {
            WATCHED = false;
            watchList.setImageResource(R.drawable.ic_heart);
            util.displayToast(BuyAuctionActivity.this, message, 1);
            BusProvider.getInstance().post(new MessageData(Constants.REMOVED_FROM_WATCH_LIST + ","));
            if (from_watchlist)
                finish();
        } else {
            util.displayToast(BuyAuctionActivity.this, message, 2);
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void SetTime(String time) {
        if (time != null) {
            if (!time.isEmpty()) {
                MyPreferenceManager.getInstance().savePref("STIME", time);
            }
        }
    }

}
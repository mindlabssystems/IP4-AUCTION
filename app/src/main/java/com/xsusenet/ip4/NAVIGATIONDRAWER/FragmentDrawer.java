package com.xsusenet.ip4.NAVIGATIONDRAWER;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.squareup.otto.Subscribe;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.VISIBLE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDrawer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDrawer extends Fragment implements View.OnClickListener {

    Context context;
    PendingIntent pi;
    Unbinder unbinder;
    @BindView(R.id.nav_icon)
    AppCompatImageView navIcon;
    @BindView(R.id.relative_my_account)
    RelativeLayout relativeMyAccount;
    @BindView(R.id.relative_my_list)
    RelativeLayout relativeMyList;
    @BindView(R.id.relative_list)
    RelativeLayout relativeList;
    @BindView(R.id.notification_text)
    TextView notificationText;
    @BindView(R.id.relative_notification)
    RelativeLayout relativeNotification;
    @BindView(R.id.logout)
    TextView logout;
    @BindView(R.id.relative_watch_list)
    RelativeLayout relativeWatchList;
    @BindView(R.id.relative_my_purchases)
    RelativeLayout relativeMyPurchases;
    @BindView(R.id.my_sales_text)
    TextView mySalesText;
    @BindView(R.id.watch_list_text)
    TextView watchListText;
    @BindView(R.id.my_account_text)
    TextView myAccountText;
    @BindView(R.id.listSalestext)
    TextView listSalestext;
    @BindView(R.id.my_purchases_text)
    TextView mypurchasesText;
    @BindView(R.id.notification_badge)
    AppCompatImageView notificationBadge;

    private NavigationDrawerCallbacks mCallbacks;

    public FragmentDrawer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDrawer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDrawer newInstance(String param1, String param2) {
        FragmentDrawer fragment = new FragmentDrawer();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BusProvider.getInstance().register(this);

        try {
            mCallbacks = (NavigationDrawerCallbacks) context;
            this.context = context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drawer, null);
        unbinder = ButterKnife.bind(this, v);
        initUi(v);
        setClicks();
        return v;
    }

    @Subscribe
    public void onEvent(MessageData msgData) {
        String[] seperatedString = msgData.getMsgData().split(",");

        if (seperatedString[0].equalsIgnoreCase(Constants.MESSAGERECEIEVED)) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // Stuff that updates the UI
                    if (notificationBadge != null) {
                        notificationBadge.setVisibility(VISIBLE);
                    }
                }
            });


        }
        if (seperatedString[0].equalsIgnoreCase(Constants.MESSAGEOPENED)) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // Stuff that updates the UI
                    if (notificationBadge != null) {
                        notificationBadge.setVisibility(VISIBLE);
                    }
                }
            });

        }

        if (seperatedString[0].equalsIgnoreCase("HIDE")) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // Stuff that updates the UI
                    if (notificationBadge != null) {
                        notificationBadge.setVisibility(View.GONE);
                    }
                }
            });

        }
    }

    private void setClicks() {
        navIcon.setOnClickListener(this::onClick);
        relativeMyAccount.setOnClickListener(this::onClick);
        relativeMyList.setOnClickListener(this::onClick);
        relativeList.setOnClickListener(this::onClick);
        relativeWatchList.setOnClickListener(this::onClick);
        relativeNotification.setOnClickListener(this::onClick);
        relativeMyPurchases.setOnClickListener(this::onClick);
        logout.setOnClickListener(this::onClick);
    }

    @Override
    public void onResume() {
//        BusProvider.getInstance().register(this);

        super.onResume();
    }

    @Override
    public void onPause() {
//        if (BusProvider.getInstance() != null)
//            BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    private void initUi(View v) {
        Util.getUtils().overrideFontsSemiBold(getActivity(), listSalestext);
        Util.getUtils().overrideFontsSemiBold(getActivity(), mySalesText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), myAccountText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), watchListText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), notificationText);
        Util.getUtils().overrideFontsSemiBold(getActivity(), mypurchasesText);
        Util.getUtils().overrideFontsBold(getActivity(), v.findViewById(R.id.logout));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onClick(View v) {
        mCallbacks.onNavigationDrawerItemSelected(v.getId());
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

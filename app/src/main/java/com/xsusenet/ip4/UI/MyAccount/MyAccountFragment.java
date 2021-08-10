package com.xsusenet.ip4.UI.MyAccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.xsusenet.ip4.Models.User.User;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.ChangePassword.ChangePasswordActivity;
import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.UI.MyBids.MyBidsActivity;
import com.xsusenet.ip4.Util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends DaggerFragment implements View.OnClickListener, MyAccountContract.view {


    @BindView(R.id.edit_profile_lay)
    RelativeLayout editProfileLay;
    Unbinder unbinder;
    @BindView(R.id.change_pwd_lay)
    RelativeLayout changePwdLay;
    @BindView(R.id.my_bids_lay)
    RelativeLayout myBidsLay;

    @BindView(R.id.push_status_switch)
    Switch push_status_switch;

    @BindView(R.id.email_status_switch)
    Switch email_nots_status_switch;

    @BindView(R.id.progress)
    LinearLayout progress;


    boolean PUSH_NOTS, EMAIL_NOTS;

    @Inject
    MyAccountPresenterImpl presenter;
    @Inject
    Util util;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_my_account, container, false);
        unbinder = ButterKnife.bind(this, v);
        initUi(v);
        setClicks();
        if (util.isNetworkAvailable()) {
            presenter.getProfile();
        } else {
            util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);
        }
        return v;
    }

    private void setClicks() {
        editProfileLay.setOnClickListener(this::onClick);
        changePwdLay.setOnClickListener(this::onClick);
        myBidsLay.setOnClickListener(this::onClick);
        push_status_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!PUSH_NOTS)
                        callChangePushStatus("1");
                } else {
                    if (PUSH_NOTS)
                        callChangePushStatus("0");
                }
            }
        });

        email_nots_status_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (!EMAIL_NOTS)
                        callChangeEmailStatus("1");
                } else {
                    if (EMAIL_NOTS)
                        callChangeEmailStatus("0");
                }
            }
        });

    }

    private void callChangePushStatus(String status) {
        if (Util.getUtils().isNetworkAvailable()) {
            presenter.changeStatusofPush(status);
        } else {
            Util.getUtils().displayToast(getActivity(), getString(R.string.no_internet), 2);
        }
    }

    private void callChangeEmailStatus(String status) {
        if (Util.getUtils().isNetworkAvailable()) {
            presenter.changeStatusofEmailNotifications(status);
        } else {
            Util.getUtils().displayToast(getActivity(), getString(R.string.no_internet), 2);
        }
    }

    private void initUi(View v) {
        Util.getUtils().overrideFontsSemiBold(getActivity(), v.findViewById(R.id.edit_profile_text));
        Util.getUtils().overrideFontsSemiBold(getActivity(), v.findViewById(R.id.change_pwd_text));
        Util.getUtils().overrideFontsSemiBold(getActivity(), v.findViewById(R.id.my_bids_text));
        Util.getUtils().overrideFontsSemiBold(getActivity(), v.findViewById(R.id.push_status));
        Util.getUtils().overrideFontsSemiBold(getActivity(), v.findViewById(R.id.email_status));
        Util.getUtils().overrideFontsBold(getActivity(), v.findViewById(R.id.Title));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_profile_lay) {
            Intent intent = new Intent(getActivity(),
                    EditProfileActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.change_pwd_lay) {
            Intent intent = new Intent(getActivity(),
                    ChangePasswordActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.my_bids_lay) {
            Intent intent = new Intent(getActivity(),
                    MyBidsActivity.class);
            startActivity(intent);
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
    public void PushNotificationsResult(boolean status, String s) {
        if (status) {
            if (!PUSH_NOTS) {
                push_status_switch.setChecked(true);
                PUSH_NOTS = true;
                Util.getUtils().displayToast(getActivity(), s, 1);
            } else {
                PUSH_NOTS = false;
                push_status_switch.setChecked(false);
                Util.getUtils().displayToast(getActivity(), s, 1);
            }
        }
    }

    @Override
    public void setValues(User user) {
        if (user != null) {
            if (user.getPushNotificationStatus().equals("1")) {
                PUSH_NOTS = true;
                push_status_switch.setChecked(true);
            } else {
                PUSH_NOTS = false;
                push_status_switch.setChecked(false);
            }

            if (user.getEmailNotificationStatus().equals("1")) {
                EMAIL_NOTS = true;
                email_nots_status_switch.setChecked(true);
            } else {
                EMAIL_NOTS = false;
                email_nots_status_switch.setChecked(false);
            }
        } else {
//            Util.getUtils().displayToast(getActivity(), "Profile details not found", 2);
        }
    }

    @Override
    public void EmailNotificationsResult(boolean b, String message) {
        if (b) {
            if (!EMAIL_NOTS) {
                push_status_switch.setChecked(true);
                EMAIL_NOTS = true;
                Util.getUtils().displayToast(getActivity(), message, 1);
            } else {
                EMAIL_NOTS = false;
                push_status_switch.setChecked(false);
                Util.getUtils().displayToast(getActivity(), message, 1);
            }
        }

    }

}

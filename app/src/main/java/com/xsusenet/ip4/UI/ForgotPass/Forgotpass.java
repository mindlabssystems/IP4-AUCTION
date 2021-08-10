package com.xsusenet.ip4.UI.ForgotPass;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Login.LoginActivity;
import com.xsusenet.ip4.Util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;



public class Forgotpass extends DialogFragment {

    String Semail;
    @Inject
    Util util;
    ForgotPassInterface forgotPassInterface;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.SubmitBut)
    RelativeLayout SubmitBut;
    @BindView(R.id.progress)
    LinearLayout progress;
    Unbinder unbinder;
    @BindView(R.id.close)
    AppCompatImageView close;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;

    @BindView(R.id.login_text)
    TextView loginText;

    public static Forgotpass newInstance() {
        Forgotpass searchfragment = new Forgotpass();
        return searchfragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    @Override
    public void onAttach(@NonNull Activity context) {
        super.onAttach(context);
        if (getActivity() != null) {
            forgotPassInterface = (ForgotPassInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forgotpasslayout, container, false);
        unbinder = ButterKnife.bind(this, v);
        Util.getUtils().overrideFontsBold(getActivity(), text1);
        Util.getUtils().overrideFontsRegular(getActivity(), text2);
        Util.getUtils().overrideFontsRegular(getActivity(), email);
        Util.getUtils().overrideFontsBold(getActivity(), loginText);
        return v;
    }


    @OnClick(R.id.SubmitBut)
    void setSubmitBut() {

        if (Util.getUtils().isNetworkAvailable()) {
            if (checkemail()) {
                forgotPassInterface.onPasswordChange(Semail);
                dismiss();
                
            }
        }else {
            Util.getUtils().displayToast(getActivity(), getString(R.string.no_internet),2);
        }

    }


    private boolean checkemail() {
        Semail = email.getText().toString().trim();
        if (!Semail.isEmpty() && Util.getUtils().isValidEmail(Semail)) {
//            mLinearLayoutemail.setBackground(getResources().getDrawable(R.drawable.));
            return true;
        } else {
//            mLinearLayoutemail.setBackground(getResources().getDrawable(R.drawable.));
            Util.getUtils().displayToast(getActivity(), getResources().getString(R.string.invalid_email), 2);
            return false;
        }
    }

    @OnClick(R.id.close)
    void setClose() {
        dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

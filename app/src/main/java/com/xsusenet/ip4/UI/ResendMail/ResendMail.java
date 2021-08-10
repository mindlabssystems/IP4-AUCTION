package com.xsusenet.ip4.UI.ResendMail;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.ForgotPass.ForgotPassInterface;
import com.xsusenet.ip4.UI.ForgotPass.Forgotpass;
import com.xsusenet.ip4.Util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ResendMail extends DialogFragment {

    String Semail;
    @Inject
    Util util;
    ResendMailInterface resendMailInterface;
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

    public static ResendMail newInstance() {
        ResendMail resendMail = new ResendMail();
        return resendMail;
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
            resendMailInterface = (ResendMailInterface) context;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_resend_mail, container, false);
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
                resendMailInterface.onResend(Semail);
                dismiss();

               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 2500);*/

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
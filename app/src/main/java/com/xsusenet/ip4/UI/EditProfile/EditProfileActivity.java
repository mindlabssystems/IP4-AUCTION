package com.xsusenet.ip4.UI.EditProfile;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.xsusenet.ip4.Adapters.CountryAdapter;
import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.User.User;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class EditProfileActivity extends DaggerAppCompatActivity implements EditProfileContract.view {

    @BindView(R.id.Title)
    TextView Title;


    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.country)
    TextView country;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.state)
    EditText state;
    @BindView(R.id.pincode)
    EditText pincode;
    @BindView(R.id.updateBut)
    TextView updateBut;
    @BindView(R.id.go_back)
    RelativeLayout goBack;


    @Inject
    EditProfilePresenterImpl presenter;

    @Inject
    Util util;
    CountryAdapter countryAdapter;

    List<Country> countryList = new ArrayList<>();
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.email)
    EditText email;


    String sfirstName, slastName, semail, sjobTitle, sMobile, sTimezone, sAddress, sCountry, sCity, sPostcode, sCompanyName,sState;
    @BindView(R.id.progress)
    LinearLayout progress;
    @BindView(R.id.company_name)
    EditText companyName;

    String country_id;
    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.job_tittle)
    EditText jobTittle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);
        initUi();
        if (util.isNetworkAvailable()) {
            getCountryAPI();
            presenter.getProfile();
        } else {
            util.displayToast(this, getString(R.string.network_unavailable), 2);
        }

    }

    private void getCountryAPI() {
        presenter.getCountry();
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(EditProfileActivity.this, Title);
        Util.getUtils().overrideFontsBold(EditProfileActivity.this, updateBut);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, firstName);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, lastName);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, email);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, mobile);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, companyName);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, address);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, country);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, city);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, state);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, pincode);
        Util.getUtils().overrideFontsRegular(EditProfileActivity.this, jobTittle);
    }

    @OnClick(R.id.go_back)
    void goBack() {
        onBackPressed();
    }


    @OnClick(R.id.updateBut)
    void setUpdateBut() {
        if (Util.getUtils().isNetworkAvailable()) {
            if (checkFirstName()  && checkCompanyName() && checkEmail() && checkJobTitle() && checkMobile() && checkAddress()
                    && checkCountry() && checkState() && checkCity()  && checkPostcode()) {

                presenter.Update(sfirstName, slastName, semail, sCompanyName, sMobile, sAddress, country_id, sCity, sPostcode,sjobTitle,sState);

            }
        }
        else {
            util.displayToast(EditProfileActivity.this, getString(R.string.network_unavailable), 2);
        }
    }

    private boolean checkCompanyName() {
        sCompanyName = companyName.getText().toString().trim();
        if (!sCompanyName.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Company Name Missing", 2);
            return false;
        }
    }

    private boolean checkAddress() {
        sAddress = address.getText().toString().trim();
        if (!sAddress.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Address Missing", 2);
            return false;
        }
    }


    private boolean checkPostcode() {
        sPostcode = pincode.getText().toString().trim();
        if (!sPostcode.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Post Code Missing", 2);
            return false;
        }
    }

    private boolean checkCity() {
        sCity = city.getText().toString().trim();
        if (!sCity.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "City Missing", 2);
            return false;
        }
    }
    private boolean checkState() {
        sState = state.getText().toString().trim();
        if (!sState.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "State Missing", 2);
            return false;
        }
    }

    private boolean checkCountry() {
        sCountry = country.getText().toString().trim();
        if (!sCountry.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Country Missing", 2);
            return false;
        }
    }

    private boolean checkMobile() {
        sMobile = mobile.getText().toString().trim();
        if (!sMobile.isEmpty()) {
            if (sMobile.length() != 10) {
                util.displayToast(this, "Mobile Number Invalid", 2);
                return false;
            } else {
                return true;
            }
        } else {
            util.displayToast(this, "Mobile Number Missing", 2);
            return false;
        }
    }

    private boolean checkJobTitle() {
        sjobTitle = jobTittle.getText().toString().trim();
        if (!sjobTitle.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, "Job Title Missing", 2);
            return false;
        }
    }

    private boolean checkEmail() {
        semail = email.getText().toString().trim();
        if (!semail.isEmpty() && util.isValidEmail(semail)) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.invalid_email), 2);
            return false;
        }
    }

    private boolean checkLastName() {
        slastName = lastName.getText().toString().trim();
        if (!slastName.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.last_name_must_not_empty), 2);
            return false;
        }
    }

    private boolean checkFirstName() {
        sfirstName = firstName.getText().toString().trim();
        if (!sfirstName.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.first_name_must_not_empty), 2);
            return false;
        }
    }

    @OnClick(R.id.country)
    void setCountry() {
        final Dialog dialog = new Dialog(EditProfileActivity.this);
        dialog.setContentView(R.layout.custom_spinner_items);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView listView = dialog.findViewById(R.id.list_view);
        countryAdapter = new CountryAdapter((ArrayList<Country>) countryList, R.layout.spinner_item, EditProfileActivity.this);
        listView.setAdapter(countryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> arg0, View arg1,

                                    int position, long arg3) {

                country.setText(countryList.get(position).getCountriesName());
                country_id = countryList.get(position).getCountriesId();
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
    public void showResult(boolean b, String message) {
        if (b) {
            util.displayToast(this, message, 1);
            onBackPressed();
        } else
            util.displayToast(this, message, 2);

    }

    @Override
    public void ListCountries(List<Country> countries) {
        countryList = new ArrayList<>();
        this.countryList = countries;
    }

    @Override
    public void setValues(User user) {
        if (user != null ) {

            firstName.setText(user.getName());
            companyName.setText(user.getBusinessName());
            email.setText(user.getEmail());
            address.setText(user.getAddress1());
            country.setText(user.getCountryName());
            city.setText(user.getCity());
            state.setText(user.getState());
            pincode.setText(user.getZipcode());
            mobile.setText(user.getMobileNumber());
            country_id = String.valueOf(user.getCountryId());
            jobTittle.setText(user.getJobTitle());

        }
        else {
            Util.getUtils().displayToast(EditProfileActivity.this, "Profile details not found", 2);

        }

    }
}
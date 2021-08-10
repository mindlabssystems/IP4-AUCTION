package com.xsusenet.ip4.UI.EditProfile;

import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.User.User;

import java.util.List;

public interface EditProfileContract {
    interface presenter {
        void getCountry();

        void Update(String sfirstName, String slastName, String semail, String sjobTitle, String sMobile, String sAddress, String countryId, String sCity, String sPostcode, String title,String state);

        void getProfile();
    }

    interface view {
        void ShowProgress();

        void StopProgress();

        void showResult(boolean b, String message);


        void ListCountries(List<Country> countries);

        void setValues(User user);
    }
}

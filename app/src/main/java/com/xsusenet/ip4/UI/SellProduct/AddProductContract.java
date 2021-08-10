package com.xsusenet.ip4.UI.SellProduct;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.User.User;

import java.util.List;

public interface AddProductContract {
    interface presenter {
    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void BlockSizeResult(List<Size> size);

        void RegionResult(List<RegionResult> regions);

        void ShowResult(boolean b, String message);
    }
}

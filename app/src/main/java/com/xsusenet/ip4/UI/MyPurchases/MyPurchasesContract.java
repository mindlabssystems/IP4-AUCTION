package com.xsusenet.ip4.UI.MyPurchases;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Purchases.All;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.Models.WatchList.Result;

import java.util.List;

public interface MyPurchasesContract {
    interface presenter {

    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void showResult(boolean b, List<All> allList);
        void CancelResult(boolean b, String msg);
    }
}

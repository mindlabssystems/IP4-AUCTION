package com.xsusenet.ip4.UI.AllSales;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;

import java.util.List;

public interface SalesListFragmentContract {
    interface presenter {
    }

    interface view {


        void ShowProgress();

        void StopProgress();

        void showResult(boolean b, List<SalesResult> message);

        void SortByResult(List<ModelSortBy> sortByList);

        void BlockSizeResult(List<Size> size);

        void RegionResult(List<RegionResult> regions);

        void SetTime(String time);
    }
}

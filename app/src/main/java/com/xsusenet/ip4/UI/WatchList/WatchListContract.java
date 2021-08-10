package com.xsusenet.ip4.UI.WatchList;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.Models.WatchList.Result;

import java.util.ArrayList;
import java.util.List;

public interface WatchListContract {
    interface presenter {
        void getSortBy();

        void getBlockSize();

        void getRegions();


        void getWatchList();
    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void SortByResult(List<ModelSortBy> modelSortBy);

        void BlockSizeResult(List<Size> size);

        void RegionResult(List<RegionResult> regions);

        void showResult(boolean b, List<Result> all);
    }
}

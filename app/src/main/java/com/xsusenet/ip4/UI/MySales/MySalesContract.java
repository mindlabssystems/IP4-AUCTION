package com.xsusenet.ip4.UI.MySales;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;

import java.util.ArrayList;
import java.util.List;

public interface MySalesContract {
    interface presenter {
        void getSortBy();

        void getBlockSize();

        void getRegions();

        void getLists1(String SALE_OR_LEASE, String sortBy, ArrayList<String> sizeIntList, ArrayList<String> regionIntList, ArrayList<String> salesTypeIntList, int page_no);
    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void SortByResult(List<ModelSortBy> modelSortBy);

        void BlockSizeResult(List<Size> size);

        void RegionResult(List<RegionResult> regions);

        void showResult(boolean b, List<SalesResult> all);
        void SetTime(String time);
    }
}

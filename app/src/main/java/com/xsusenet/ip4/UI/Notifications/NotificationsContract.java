package com.xsusenet.ip4.UI.Notifications;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Notifications.Notification;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;

import java.util.ArrayList;
import java.util.List;

public interface NotificationsContract {
    interface presenter {

    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void showResult(boolean b, List<Notification> notifications);
    }
}

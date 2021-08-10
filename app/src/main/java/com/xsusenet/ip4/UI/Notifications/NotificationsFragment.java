package com.xsusenet.ip4.UI.Notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Adapters.NotificationsAdapter;
import com.xsusenet.ip4.Models.Notifications.Notification;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends DaggerFragment implements NotificationsContract.view {

    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.notifications_rec)
    RecyclerView notificationsRec;


    Unbinder unbinder;
    @BindView(R.id.progress)
    LinearLayout progress;

    @Inject
    Util util;

    @Inject
    NotificationsPresenterImpl presenter;


    LinearLayoutManager layoutManager;
    boolean isLastPage = false;
    boolean isLoading = false;
    int PAGE_SIZE = 10;
    int page_no = 0;


    List<Notification> notificationList = new ArrayList<>();

    NotificationsAdapter notificationsAdapter;

    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notifications, container, false);

        unbinder = ButterKnife.bind(this, v);

        initView();

        if (util.isNetworkAvailable()) {
            presenter.getNotifications(page_no);

        } else
            util.displayToast(getActivity(), getString(R.string.network_unavailable), 2);


        return v;
    }

    private void initView() {
        if (getActivity() != null) {
            layoutManager = new LinearLayoutManager(getActivity());
            notificationsRec.setLayoutManager(layoutManager);
        }
        notificationsRec.setHasFixedSize(true);

        Util.getUtils().overrideFontsBold(getActivity(), Title);

        notificationsAdapter = new NotificationsAdapter(getActivity(), notificationList);
        notificationsRec.setAdapter(notificationsAdapter);
        RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.getId() == R.id.rec) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            page_no = page_no + 1;

                            presenter.getNotifications(page_no);

                        }
                    }
                } else {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && !isLastPage) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            page_no = page_no + 1;
                            presenter.getNotifications(page_no);


                        }
                    }
                }
            }
        };

        notificationsRec.addOnScrollListener(recyclerViewOnScrollListener);
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
    public void showResult(boolean b, List<Notification> notifications) {
        if (notifications != null) {
            isLoading = false;
            this.isLastPage = b;
            if (notifications.size() > 0) {
                this.notificationList.addAll(notifications);
                notificationsAdapter.notifyDataSetChanged();
            }
        } else {
            notificationList.clear();
            notificationsAdapter.notifyDataSetChanged();
        }
    }
}

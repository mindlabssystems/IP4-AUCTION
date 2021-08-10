package com.xsusenet.ip4.Util;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by sancy .
 */
public class BusProvider {
    private static Bus BUS;

    public static Bus getInstance() {
        if (BUS == null) {
            BUS = new Bus(ThreadEnforcer.ANY);
        }
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }

    public static class MainThreadBus extends Bus {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MainThreadBus.super.post(event);
                    }
                });
            }
        }
    }
}
package com.haiprj.games.superknight;

import com.haiprj.android_app_lib.MyApplication;

public class App extends MyApplication {
    @Override
    protected boolean isPurchased() {
        return false;
    }

    @Override
    protected boolean isShowAdsTest() {
        return true;
    }

    @Override
    public boolean enableAdsResume() {
        return false;
    }

    @Override
    public String getOpenAppAdId() {
        return null;
    }
}

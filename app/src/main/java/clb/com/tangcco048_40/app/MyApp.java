package clb.com.tangcco048_40.app;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by cuilibao on 2017/7/1.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}

package com.xsh.yomobplugin;
import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.soulgame.sgsdk.tgsdklib.TGSDK;
import com.soulgame.sgsdk.tgsdklib.ad.ITGPreloadListener;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;
public class YomobPlugin extends GodotPlugin
{
    public Activity activity;
    public String tag;
    public boolean isPreLoad;
    public YomobPlugin(Godot godot)
    {
        super(godot);
        activity = getActivity();
        //获取到godot的根布局 并动态添加线性布局用于当做banner广告容器
        //layout = getGodot().getFragmentManager().getFragment();
        tag = YomobPlugin.class.toString();
    }

    @NonNull
    @Override
    public String getPluginName()
    {
        return "YomobPlugin";
    }
    @UsedByGodot
    public void initSDK(String appid)
    {
        TGSDK.setDebugModel(true);
        TGSDK.initialize(activity,appid,null);
    }
   /* @UsedByGodot
    public void initDemoSDK()
    {

    }*/
    @UsedByGodot
    public void showTestView(String sceneID)
    {
        TGSDK.preloadAd(activity, new ITGPreloadListener() {
            @Override
            public void onPreloadSuccess(String s)
            {
                Log.d(tag,"广告预加载成功");
                Log.d(tag,s);
                isPreLoad = true;
            }

            @Override
            public void onPreloadFailed(String s, String s1)
            {
                Log.e(tag,"广告预加载失败");
                Log.e(s,s1);
            }

            @Override
            public void onAwardVideoLoaded(String s)
            {
                Log.d(tag,"奖励视频就绪");
                Log.d(tag,s);
            }

            @Override
            public void onInterstitialLoaded(String s)
            {
                Log.d(tag,"静态插屏就绪");
                Log.d(tag,s);
            }

            @Override
            public void onInterstitialVideoLoaded(String s)
            {
                Log.d(tag,"静态插屏视频就绪");
                Log.d(tag,s);
            }
        });
        if(isPreLoad)
        {
            TGSDK.showTestView(activity,sceneID);
        }
        else
        {
            Log.e(tag,"广告预加载失败");
            Log.e("场景id",sceneID);
        }
    }

}

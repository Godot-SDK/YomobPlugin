package com.xsh.yomobplugin;
import android.app.Activity;

import androidx.annotation.NonNull;

import com.soulgame.sgsdk.tgsdklib.TGSDK;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;
public class YomobPlugin extends GodotPlugin
{
    public Activity activity;
    public String tag;
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
        //TGSDK.setDebugModel(true);
        TGSDK.initialize(activity,appid,null);
    }
   /* @UsedByGodot
    public void initDemoSDK()
    {

    }*/
    @UsedByGodot
    public void showTestView(String sceneID)
    {
        TGSDK.showTestView(activity,sceneID);
    }

}

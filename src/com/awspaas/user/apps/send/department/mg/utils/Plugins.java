package com.awspaas.user.apps.send.department.mg.utils;

import com.actionsoft.apps.listener.PluginListener;
import com.actionsoft.apps.resource.AppContext;
import com.actionsoft.apps.resource.plugin.profile.AWSPluginProfile;
import com.actionsoft.apps.resource.plugin.profile.DCPluginProfile;

import java.util.ArrayList;
import java.util.List;

public class Plugins implements PluginListener {
    @Override
    public List<AWSPluginProfile> register(AppContext appContext) {
        // 存放本应用的全部插件扩展点描述
        List<AWSPluginProfile> list = new ArrayList<AWSPluginProfile>();
        // 注册DC
        // list.add(new CloudDCPluginProfile("唯一标识", "XXX对象存储", "参数如果留空，则表示接管所有DC；如果指定AppId，则表示接管指定App的DC", CloudFile.class.getName(), "存储描述信息", true/*是否支持推送到三方存储*/, false/*是否支持拉取三方存储的文件列表，上传直接读取三方存储的列表*/));
        list.add(new DCPluginProfile("myfiledc", PubFileProcessor.class.getName(), "com.awspaas.user.apps.send.department.mg", false));
        return list;
    }
}

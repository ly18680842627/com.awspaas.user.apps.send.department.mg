package com.awspaas.user.apps.send.department.mg.controller;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TfbLcHtmlController {

    /**
     * @Description: 主流程列表--页面，
     * @author 张勇--Mr.Yong
     * @date 2021/3/4 14:52
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg_page")
    public String pageList(UserContext me) {
        Map<String,Object> outMap = new HashMap<>();
        outMap.put("sid", me.getSessionId());
        outMap.put("userName", me.getUserName());
        outMap.put("userModel", me.getUserModel());
        return HtmlPageTemplate.merge("com.awspaas.user.apps.send.department.mg", "zbList.html", outMap);
    }


    /**
     * @Description: 返回详情页面
     * @author 张勇--Mr.Yong
     * @date 2021/3/8 9:53
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg_detailpage")
    public String detailPage(UserContext me){
        Map<String,Object> outMap = new HashMap<>();
        outMap.put("sid", me.getSessionId());
        outMap.put("userName", me.getUserName());
        outMap.put("userModel", me.getUserModel());
        return HtmlPageTemplate.merge("com.awspaas.user.apps.send.department.mg", "zbdetail.html", outMap);
    }



}

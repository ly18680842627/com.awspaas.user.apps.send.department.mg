package com.awspaas.user.apps.send.department.mg;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.awspaas.user.apps.send.department.mg.web.TfbWeb;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Test {
    // 测试用
    @Mapping("com.awspaas.user.apps.send.department.mg_test")
    public String apiTestHome(UserContext me) {
        Map<String,Object> outMap = new HashMap<>();
        outMap.put("username","username");
        outMap.put("user","username");
        System.out.println("测试请求来了！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        return HtmlPageTemplate.merge("com.awspaas.user.apps.send.department.mg", "index.html", outMap);
    }


    @Mapping("com.awspaas.user.apps.send.department.mg_dataList")
    public String dataList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TfbWeb web = new TfbWeb(me);
        return web.getMainPage(map);
    }
}

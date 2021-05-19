package com.awspaas.user.apps.send.department.mg.web;

import com.actionsoft.bpms.commons.database.RowMap;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TfbWeb extends ActionWeb {

    public TfbWeb(UserContext me) {
        super(me);
    }

    public String getMainPage(Map<String,Object> map) {
        String sid = getContext().getSessionId();
        Map<String,Object> outMap = new HashMap<>();
        String sql = "SELECT\n" +
                "\tID,\n" +
                "\tBINDID,\n" +
                "\tCREATEDATE,\n" +
                "\tCREATEUSER,\n" +
                "\tUPDATEDATE,\n" +
                "\tUPDATEUSER,\n" +
                "\tISEND,\n" +
                "\tTZGL_LX_XMBH,\n" +
                "\tTZGL_LX_XMMC,\n" +
                "\tTZGL_LX_ZTZ,\n" +
                "\tTZGL_LX_XMBZ,\n" +
                "\tTZGL_LX_JHKGNY,\n" +
                "\tTZGL_LX_JHWGNY,\n" +
                "\tTZGL_LX_XMXZ,\n" +
                "\tTZGL_LX_KYHPQK,\n" +
                "\tTZGL_LX_GSHPQK,\n" +
                "\tTZGL_LX_LXR,\n" +
                "\tTZGL_LX_TZLX,\n" +
                "\tTZGL_LX_GCDZ,\n" +
                "\tTZGL_LX_XMSZDS,\n" +
                "\tTZGL_LX_XMSZQX,\n" +
                "\tTZGL_LX_JSDW,\n" +
                "\tTZGL_LX_LXSJ,\n" +
                "\tTZGL_LX_XMLX,\n" +
                "\tTZGL_LX_ZJLY,\n" +
                "\tTZGL_LX_TZLY,\n" +
                "\tTZGL_LX_JSZB,\n" +
                "\tTZGL_LX_JSGM,\n" +
                "\tTZGL_LX_ZYJSNR,\n" +
                "\tTZGL_LX_GQ,\n" +
                "\tTZGL_LX_SJJSGM,\n" +
                "\tTZGL_LX_JSNR,\n" +
                "\tTZGL_LX_JSNR_DLCD,\n" +
                "\tTZGL_LX_JSNR_DLKD,\n" +
                "\tTZGL_LX_LXPF,\n" +
                "\tTZGL_LX_LXPF_PWH \n" +
                "FROM\n" +
                "\tBO_EU_TFB_TZGL_XMZB \n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '0'  limit ?,?";
        Integer pageIndex = (Integer)map.get("pageIndex") == null ? 0:(Integer)map.get("pageIndex");
        Integer pageSize = (Integer)map.get("pageSize") == null ? 10:(Integer)map.get("pageSize");
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { pageIndex,pageSize });
        outMap.put("sid", getContext().getSessionId());
        System.out.println("查询结果："+dataMap);
        // outMap.put("iframeList",new Gson().toJson(dataMap));
        outMap.put("iframeList",dataMap);
        System.out.println("请求list：++++" + outMap);
        return HtmlPageTemplate.merge("com.awspaas.user.apps.send.department.mg", "zbList.html", outMap);
    }

    /**
     * @Description: 详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/4 14:49
     * @Version 1.0
     */
    public String getMainDetail(Map<String,Object> map) {
        String sid = getContext().getSessionId();
        Map<String,Object> outMap = new HashMap<>();
        String sql = "";
        return HtmlPageTemplate.merge("com.awspaas.user.apps.send.department.mg", "zbDetail.html", outMap);
    }

    /**
     * @Description: 子流程列表
     * @author 张勇--Mr.Yong
     * @date 2021/3/4 14:36
     * @Version 1.0
     */
    public String getMainZlcList(Map<String,Object> map) {
        String sid = getContext().getSessionId();
        Map<String,Object> outMap = new HashMap<>();
        String sql = "select \t\n" +
                "\tID,\n" +
                "\tBINDID,\n" +
                "\tCREATEDATE,\n" +
                "\tCREATEUSER,\n" +
                "\tUPDATEDATE,\n" +
                "\tUPDATEUSER,\n" +
                "\tISEND,\n" +
                "\tTZGL_LX_XMBH,\n" +
                "\tTZGL_LX_XMMC,\n" +
                "\tTZGL_LX_ZTZ,\n" +
                "\tTZGL_LX_XMBZ,\n" +
                "\tTZGL_LX_JHKGNY,\n" +
                "\tTZGL_LX_JHWGNY,\n" +
                "\tTZGL_LX_XMXZ,\n" +
                "\tTZGL_LX_KYHPQK,\n" +
                "\tTZGL_LX_GSHPQK,\n" +
                "\tTZGL_LX_LXR,\n" +
                "\tTZGL_LX_TZLX,\n" +
                "\tTZGL_LX_GCDZ,\n" +
                "\tTZGL_LX_XMSZDS,\n" +
                "\tTZGL_LX_XMSZQX,\n" +
                "\tTZGL_LX_JSDW,\n" +
                "\tTZGL_LX_LXSJ,\n" +
                "\tTZGL_LX_XMLX,\n" +
                "\tTZGL_LX_ZJLY,\n" +
                "\tTZGL_LX_TZLY,\n" +
                "\tTZGL_LX_JSZB,\n" +
                "\tTZGL_LX_JSGM,\n" +
                "\tTZGL_LX_ZYJSNR,\n" +
                "\tTZGL_LX_GQ,\n" +
                "\tTZGL_LX_SJJSGM,\n" +
                "\tTZGL_LX_JSNR,\n" +
                "\tTZGL_LX_JSNR_DLCD,\n" +
                "\tTZGL_LX_JSNR_DLKD,\n" +
                "\tTZGL_LX_LXPF,\n" +
                "\tTZGL_LX_LXPF_PWH \n" +
                "\tfrom \n" +
                "\tBO_EU_TFB_TZGL_XMZB where  TZGL_LX_LEV = '1' and TZGL_LX_ZXM_ID = ? AND TZGL_LX_ZXM_BINDID = ? limit ?,?";
        String pageIndex = (String) map.get("pageIndex");
        String pageSize = (String) map.get("pageSize");
        String TZGL_LX_ZXM_ID = (String) map.get("TZGL_LX_ZXM_ID");
        String TZGL_LX_ZXM_BINDID = (String) map.get("TZGL_LX_ZXM_BINDID");
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { TZGL_LX_ZXM_ID,TZGL_LX_ZXM_BINDID,pageIndex,pageSize });
        outMap.put("sid", getContext().getSessionId());
        System.out.println("查询结果："+dataMap);
        outMap.put("iframeList",new Gson().toJson(dataMap));
        System.out.println("请求list：++++" + outMap);
        return HtmlPageTemplate.merge("com.awspaas.user.apps.send.department.mg", "zbDetail.html", outMap);
    }
}

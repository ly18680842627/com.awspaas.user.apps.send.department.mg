package com.awspaas.user.apps.send.department.mg.controller;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.commons.database.RowMap;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.awspaas.user.apps.send.department.mg.web.TfbWeb;
import com.actionsoft.bpms.server.bind.annotation.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TfbLcController {
    /**
     * @Description: 列表数据接口
     * @author 张勇--Mr.Yong
     * @date 2021/3/5 16:11
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.listData")
    public String dataList(UserContext me, Map<String,Object> map) {
        String sql = "with count_data as (\n" +
                "\tSELECT\n" +
                "\tcount(0) cnt,\n" +
                "\tTZGL_LX_ZXM_ID\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_XMZB\n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '1'\n" +
                "\tgroup by TZGL_LX_ZXM_ID\n" +
                ")"+
                "SELECT\n" +
                "\tID,\n" +
                "\tCASE WHEN cnt IS NOT NULL THEN cnt else 0 end zzxm_cnt,\n" +
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
                "\tBO_EU_TFB_TZGL_XMZB LEFT JOIN count_data ON BO_EU_TFB_TZGL_XMZB.ID = count_data.TZGL_LX_ZXM_ID\n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '0' ORDER BY BO_EU_TFB_TZGL_XMZB.TZGL_LX_LXSJ DESC limit ?,?";
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { pageIndex,pageSize });
        if (!dataMap.isEmpty()){
            dataMap = getWj(dataMap);
        }
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_XMZB\n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '0'";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        ro.put("userName",me.getUserName());
        ro.put("userModel",me.getUserModel());
        return ro.toString();
    }

    @Mapping("com.awspaas.user.apps.send.department.mg.wjListData")
    public String wjListData(UserContext me, Map<String,Object> map) {
        System.out.println("map++++++++++"+map);
        String sql = "with count_data as (\n" +
                "\tSELECT\n" +
                "\tcount(0) cnt,\n" +
                "\tTZGL_LX_ZXM_ID\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_XMZB\n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '1'\n" +
                "\tgroup by TZGL_LX_ZXM_ID\n" +
                ")"+
                "SELECT\n" +
                "\tID,\n" +
                "\tCASE WHEN cnt IS NOT NULL THEN cnt else 0 end zzxm_cnt,\n" +
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
                "\tTZGL_LX_LXPF_PWH \n" +
                "FROM\n" +
                "\tBO_EU_TFB_TZGL_XMZB LEFT JOIN count_data ON BO_EU_TFB_TZGL_XMZB.ID = count_data.TZGL_LX_ZXM_ID\n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '0' ";
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = null;
        if (null != map.get("id") && !"".equals(map.get("id"))){
            sql = sql + "and id = ? ORDER BY BO_EU_TFB_TZGL_XMZB.TZGL_LX_LXSJ DESC limit ?,?";
            System.out.println("sql+++++++++1"+sql);
            dataMap = DBSql.getMaps(sql, new Object[] {map.get("id"), pageIndex,pageSize });
        }else {
            sql = sql + "ORDER BY BO_EU_TFB_TZGL_XMZB.TZGL_LX_LXSJ DESC limit ?,?";
            System.out.println("sql+++++++++2"+sql);
            dataMap = DBSql.getMaps(sql, new Object[] { pageIndex,pageSize });
        }
        if (!dataMap.isEmpty()){
            dataMap = getWjList(dataMap);
        }
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 文件校验--是否有无
     * @author 张勇--Mr.Yong
     * @date 2021/3/22 16:06
     * @Version 1.0
     */
    private List<RowMap> getWjList(List<RowMap> dataMap){
        for (RowMap map:dataMap) {
            List<RowMap> wjjy = getWjxx(map);
            map.put("wjjy",wjjy);
        }
        return dataMap;
    }

    private List<RowMap> getWj(List<RowMap> dataMap){
        for (RowMap map:dataMap) {
            // 查询可研阶段的信息
            List<RowMap> kyjd = getWjxx("35",map);
            map.put("kyjd",kyjd);
            // 概算阶段
            List<RowMap> gsjd = getWjxx("36",map);
            map.put("gsjd",gsjd);
            // 初步设计阶段
            List<RowMap> cbsjjd = getWjxx("37",map);
            map.put("cbsjjd",cbsjjd);
            // 方案阶段
            List<RowMap> fajd = getWjxx("38",map);
            map.put("fajd",fajd);
            // 工程/用地规划许可证阶段
            List<RowMap> gcjd = getWjxx("39",map);
            map.put("gcjd",gcjd);
            // 施工图阶段
            List<RowMap> sgtjd = getWjxx("40",map);
            map.put("sgtjd",sgtjd);
            // 其他阶段
            // TODO: 2021/3/21 是否需要分开做
            // 其他文件
            List<RowMap> qtwj = getWjxx("0",map);
            map.put("qtwj",qtwj);
        }
        return dataMap;
    }


    public List<RowMap> getWjxx(RowMap map){
        String sql1 = "select ITEMNO,CNNAME,exttext1 as lv from BO_ACT_DICT_KV_ITEM where DICTKEY= ? and ITEMNO NOT IN ('35','36','37','38','39','40')  order by ITEMNO";
        List<RowMap> dataMap = DBSql.getMaps(sql1, new Object[] { "TFB_FILE"});
        if (!dataMap.isEmpty()){
            String ITEMNO = getWjxx(dataMap);
            String sql = "\twith base_data as (\n" +
                    "\tselect\n" +
                    "\tid\n" +
                    "\t,createdate\n" +
                    "\t,xmzlfj\n" +
                    "\t,xmfjmc\n" +
                    "\t,TZGL_LX_XMBH\n" +
                    "\t,TZGL_LX_XMMC\n" +
                    "\t,XMFJLX\n" +
                    "\t,TZGL_LX_ID\n" +
                    "\tfrom \n" +
                    "\tBO_EU_FILE_TFB_FJ_GL \n" +
                    "\tWHERE \n" +
                    "\tTZGL_LX_ID = ?\n" +
                    "\tAND XMFJLX "+ITEMNO+"\n" +
                    "\tORDER BY createdate DESC\n" +
                    "\tlimit 10000000000\n" +
                    "\t)\n" +
                    ",base_da as (\n" +
                    "\t\tselect\n" +
                    "\t\t* \n" +
                    "\t\tfrom base_data\n" +
                    "\t\tGROUP BY XMFJLX\n" +
                    "\t)\n" +
                    "\tselect \t\n" +
                    "\tITEMNO,\n" +
                    "\tCNNAME,\n" +
                    "\tcase when xmzlfj is null then '0' else '1' end as sfcy ,\n" +
                    "\tbase_da.XMFJLX,\n" +
                    "\tbase_da.id,\n" +
                    "\tbase_da.xmzlfj,\n" +
                    "\tbase_da.TZGL_LX_XMBH,\n" +
                    "\tbase_da.TZGL_LX_XMMC,\n" +
                    "\tbase_da.TZGL_LX_ID\n" +
                    "\t\n" +
                    "\tfrom BO_ACT_DICT_KV_ITEM left join base_da on BO_ACT_DICT_KV_ITEM.ITEMNO = base_da.XMFJLX\n" +
                    "\twhere \n" +
                    "\tDICTKEY = 'TFB_FILE' \n" +
                    "\tAND ITEMNO NOT IN ('35','36','37','38','39','40') " +
                    "order by xmzlfj desc\n";
            List<RowMap> dataOutMap = DBSql.getMaps(sql, new Object[] {map.get("id")});
            return dataOutMap;
        }else {
            return null;
        }

    }
    private List<RowMap> getWjxx(String lv,RowMap map){
        // 根据类型获取文件列表数据
        List<RowMap> kywj = getWjxx(lv);
        if (!kywj.isEmpty()){
            String ITEMNO = getWjxx(kywj);
            String sql = "\twith base_data as (\n" +
                    "\tselect\n" +
                    "\tid\n" +
                    "\t,createdate\n" +
                    "\t,xmzlfj\n" +
                    "\t,xmfjmc\n" +
                    "\t,TZGL_LX_XMBH\n" +
                    "\t,TZGL_LX_XMMC\n" +
                    "\t,XMFJLX\n" +
                    "\t,TZGL_LX_ID\n" +
                    "\tfrom \n" +
                    "\tBO_EU_FILE_TFB_FJ_GL \n" +
                    "\tWHERE \n" +
                    "\tTZGL_LX_ID = ?\n" +
                    "\tAND XMFJLX "+ITEMNO+"\n" +
                    "\tORDER BY createdate DESC\n" +
                    "\tlimit 10000000000\n" +
                    "\t)\n" +
                    ",base_da as (\n" +
                    "\t\tselect\n" +
                    "\t\t* \n" +
                    "\t\tfrom base_data\n" +
                    "\t\tGROUP BY XMFJLX\n" +
                    "\t)\n" +
                    "\tselect \t\n" +
                    "\tITEMNO,\n" +
                    "\tCNNAME,\n" +
                    "\tcase when xmzlfj is null then '0' else '1' end as sfcy ,\n" +
                    "\tbase_da.XMFJLX,\n" +
                    "\tbase_da.id,\n" +
                    "\tbase_da.xmzlfj,\n" +
                    "\tbase_da.TZGL_LX_XMBH,\n" +
                    "\tbase_da.TZGL_LX_XMMC,\n" +
                    "\tbase_da.TZGL_LX_ID\n" +
                    "\t\n" +
                    "\tfrom BO_ACT_DICT_KV_ITEM left join base_da on BO_ACT_DICT_KV_ITEM.ITEMNO = base_da.XMFJLX\n" +
                    "\twhere \n" +
                    "\tDICTKEY = 'TFB_FILE' \n" +
                    "\tAND ITEMNO NOT IN ('35','36','37','38','39','40') \n" +
                    "\tAND ITEMNO "+ITEMNO;
            List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] {map.get("id")});
            return dataMap;
        }else{
            return null;
        }
    }
    public String getWjxx( List<RowMap> rowMaps){
        StringBuilder sb = new StringBuilder();
        for (int i = 0,len = rowMaps.size(); i < len; i++) {
            String ITEMNO = (String) rowMaps.get(i).get("ITEMNO");
            if (i > 0 && i < len - 1){
                sb.append("'"+ITEMNO+"',");
            } else if(i == len - 1){
                sb.append("'"+ITEMNO+"')");
            }else if(i == 0){
                sb.append("in ('"+ITEMNO+"',");
            }
        }
        return sb.toString();
    }
    public List<RowMap> getWjxx(String lv){
        // 获取文件类型
        String sql = "select ITEMNO,CNNAME,exttext1 as lv from BO_ACT_DICT_KV_ITEM where DICTKEY= ? and exttext1 = ?  order by ITEMNO";
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { "TFB_FILE",lv });
        return dataMap;
    }



    /**
     * @Description: 详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/4 14:51
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.data_detail")
    public String detail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_XMZB \n" +
                "WHERE\n" +
                "\tid = ?";

        String id = map.get("id").equals("") || map.get("id") == null ? "":(String) map.get("id");
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id });
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 子流程列表
     * @author 张勇--Mr.Yong
     * @date 2021/3/4 14:51
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.data_zlclist")
    public String zlcList(UserContext me, Map<String,Object> map) {
        String sql = "select\n" +
                "* \n" +
                "from \n" +
                "BO_EU_TFB_TZGL_XMZB \n" +
                "where TZGL_LX_LEV = '1' and TZGL_LX_ZXM_ID = ? limit ?,?";
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        String id = map.get("id").equals("") || map.get("id") == null ? "":(String) map.get("id");
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt,\n" +
                "\tTZGL_LX_ZXM_ID\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_XMZB\n" +
                "WHERE\n" +
                "\tTZGL_LX_LEV = '1'  and TZGL_LX_ZXM_ID = ?";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 新增接口~
     * @author 张勇--Mr.Yong
     * @date 2021/3/8 16:05
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.dataAdd")
    public String addLc(UserContext me, Map<String,Object> map){
        ResponseObject ro = ResponseObject.newOkResponse();
        map.put("TZGL_LX_LEV","0"); // 新增默认为0级
        BO newBo = new BO();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_XMZB",newBo, null,me);
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 修改接口~~~
     * @author 张勇--Mr.Yong
     * @date 2021/3/8 16:49
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.dataUpdate")
    public String updateLc(UserContext me, Map<String,Object> map){
        ResponseObject ro = ResponseObject.newOkResponse();
        BO newBo = new BO();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_XMZB",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 子项目新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 14:35
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.dataAddZlc")
    public String addZlc(UserContext me, Map<String,Object> map){
        ResponseObject ro = ResponseObject.newOkResponse();
        map.put("TZGL_LX_LEV","1"); // 新增为1级
        map.put("TZGL_LX_ZXM_ID",map.get("ID"));
        map.put("TZGL_LX_ZXM_BINDID",map.get("BINDID"));
        map.remove("ORGID");
        map.remove("CREATEDATE");
        map.remove("CREATEUSER");
        map.remove("UPDATEDATE");
        map.remove("UPDATEUSER");
        map.remove("PROCESSDEFID");
        map.remove("ISEND");
        map.remove("ID");
        map.remove("BINDID");
        BO newBo = new BO();
        newBo.setAll(map);
        System.out.println("请求参数："+newBo);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_XMZB",newBo, null,me);
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    @Mapping("com.awspaas.user.apps.send.department.mg.dataUpdateZlc")
    public String updateZlc(UserContext me, Map<String,Object> map){
        ResponseObject ro = ResponseObject.newOkResponse();
        BO newBo = new BO();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_XMZB",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 启动流程-主
     * @author 张勇--Mr.Yong
     * @date 2021/3/4 15:07
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.data_add")
    public String add(UserContext me, Map<String,Object> map) {
        System.out.println("map+++++++++++:"+map.toString());
        String sid = me.getSessionId();
        String TZGL_LX_XMMC = (String) map.get("TZGL_LX_XMMC");
        ResponseObject ro = ResponseObject.newOkResponse();
        ProcessInstance engineerProInst = SDK.getProcessAPI().createProcessInstance("obj_df12ae93f18746dcb83de280a132a423",
                me.getUserModel().getUserName(),TZGL_LX_XMMC);
        SDK.getProcessAPI().start(engineerProInst).fetchActiveTasks();
        String username = me.getUserName();

        System.out.println("用户名称：+++"+username);
        BO newBo = new BO();
        newBo = (BO) map;
        System.out.println("newBo++++++++++++:"+newBo.toString());
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_XMZB", newBo, engineerProInst,me);
        ro.put("TZGL_LX_XMMC",TZGL_LX_XMMC);
        ro.put("code","200");
        ro.put("value","操作成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    @Mapping("com.awspaas.user.apps.send.department.mg.data_update")
    public String update(UserContext me, Map<String,Object> map) {
        System.out.println("map+++++++++++:"+map.toString());
        String sid = me.getSessionId();
        String TZGL_LX_XMMC = (String) map.get("TZGL_LX_XMMC");
        ResponseObject ro = ResponseObject.newOkResponse();
        String username = me.getUserName();
        System.out.println("用户名称：+++"+username);
        BO newBo = new BO();
        newBo = (BO) map;
        System.out.println("newBo++++++++++++:"+newBo.toString());
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_XMZB", newBo);
        ro.put("TZGL_LX_XMMC",TZGL_LX_XMMC);
        ro.put("code","200");
        ro.put("value","操作成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /*@Mapping("com.awspaas.user.apps.send.department.mg.data_update")
    public String updateFile(UserContext me, Map<String,Object> map) {

        return "";

    }*/

}

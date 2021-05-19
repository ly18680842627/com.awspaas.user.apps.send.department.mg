package com.awspaas.user.apps.send.department.mg.controller;


import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.database.RowMap;
import com.actionsoft.bpms.commons.mvc.dao.DaoObject;
import com.actionsoft.bpms.commons.mvc.dao.IDaoQuery;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.awspaas.user.apps.send.department.mg.dao.TfbKyjdDao;
import com.awspaas.user.apps.send.department.mg.dao.TfbKypfDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @Description: 主记录阶段信心 Controller
 * @author 张勇--Mr.Yong
 * @date 2021/3/16 15:37
 * @Version 1.0
 */
@Controller
public class TfbZlcJdController {

    /**
     * @Description: 可研批复--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 18:43
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.kypfAdd")
    public String kypfAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_JJPS",newBo, null,me);
        // 修改主表中的总投资额
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("TZGL_LX_ZTZ",map.get("TZGL_LX_ZTZ"));
        xmMap.put("TZGL_LX_KYHPQK",map.get("TZGL_LX_KYPF_JSNR")+"可研批复:"+map.get("TZGL_LX_KYPF_KYPF"));
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("data",newBo.toString());
        ro.put("id",newBo.getId());
        //todo 预留位置进行多次数据记录。
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 可研批复--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 18:57
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.kypfUpdate")
    public String kypfUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_JJPS",newBo);
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("TZGL_LX_ZTZ",map.get("TZGL_LX_ZTZ"));
        xmMap.put("TZGL_LX_KYHPQK",map.get("TZGL_LX_KYPF_JSNR")+"可研批复:"+map.get("TZGL_LX_KYPF_KYPF"));
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }
    /**
     * @Description: 可研批复--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 19:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.kypfDetail")
    public String kypfDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_JJPS \n" +
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
     * @Description: 可研批复list
     * @author 张勇--Mr.Yong
     * @date 2021/3/17 19:17
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.kypfList")
    public String kypfList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了！！！！！++++可研批复");
        String sql = "select * from BO_EU_TFB_TZGL_JJPS where 1=1 and TZGL_LX_ID = ? limit ?,?";
        String id = (String) map.get("TZGL_LX_ID");
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_JJPS\n" +
                "WHERE\n" +
                "\t1=1 and TZGL_LX_ID = ?";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }


    /**
     * @Description: 概算评估--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 20:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gspfAdd")
    public String gspfAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_GSPF",newBo, null,me);
        // 修改主表中的总投资额
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        xmMap.put("TZGL_LX_ZTZ",map.get("TZGL_LX_GSPF_PFGS_ZTZ"));
        xmMap.put("TZGL_LX_GSHPQK",map.get("TZGL_LX_GSPF_PFGS_ZY"));
        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("data",newBo.toString());
        ro.put("id",newBo.getId());
        //todo 预留位置进行多次数据记录。
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 概算批复--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 18:57
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gspfUpdate")
    public String gspfUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_GSPF",newBo);
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        xmMap.put("TZGL_LX_ZTZ",map.get("TZGL_LX_GSPF_PFGS_ZTZ"));
        xmMap.put("TZGL_LX_GSHPQK",map.get("TZGL_LX_GSPF_PFGS_ZY"));
        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 概算批复--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 19:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gspfDetail")
    public String gspfDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_GSPF \n" +
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
     * @Description: 概算批复--list
     * @author 张勇--Mr.Yong
     * @date 2021/3/18 10:36
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gspfList")
    public String gspfList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了！！！！！++++概算");
        String sql = "select * from BO_EU_TFB_TZGL_GSPF where 1=1 and TZGL_LX_ID = ? limit ?,?";
        String id = (String) map.get("TZGL_LX_ID");
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_GSPF\n" +
                "WHERE\n" +
                "\t1=1 and TZGL_LX_ID = ?";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }
    
    /**
     * @Description: 初步设计--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 10:43
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.cbsjAdd")
    public String cbsjAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_CBSJ",newBo, null,me);
        // 修改主表中的总投资额
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        // todo 需要修改主表的信息待定

        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("id",newBo.getId());
        //todo 预留位置进行多次数据记录。
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 初步设计--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 18:57
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.cbsjUpdate")
    public String cbsjUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_CBSJ",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 初步设计--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 19:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.cbsjDetail")
    public String cbsjDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_CBSJ \n" +
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
     * @Description: 初步设计批复list
     * @author 张勇--Mr.Yong
     * @date 2021/3/18 15:46
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.cbsjList")
    public String cbsjList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了！！！！！++++初步设计");
        String sql = "select * from BO_EU_TFB_TZGL_CBSJ where 1=1 and TZGL_LX_ID = ? limit ?,?";
        String id = (String) map.get("TZGL_LX_ID");
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_CBSJ\n" +
                "WHERE\n" +
                "\t1=1 and TZGL_LX_ID = ?";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 方案审查--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 10:43
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.fapfAdd")
    public String fapfAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_FASC_FAPF",newBo, null,me);
        // 修改主表中的总投资额
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        // todo 需要修改主表的信息待定

        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("id",newBo.getId());
        //todo 预留位置进行多次数据记录。
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 方案审查--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 18:57
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.fapfUpdate")
    public String fapfUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_FASC_FAPF",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 方案审查--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 19:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.fapfDetail")
    public String fapfDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_FASC_FAPF \n" +
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
     * @Description: 方案审查--列表
     * @author 张勇--Mr.Yong
     * @date 2021/3/18 16:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.fapfList")
    public String fapfList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了！！！！！++++初步设计");
        String sql = "select * from BO_EU_TFB_TZGL_FASC_FAPF where 1=1 and TZGL_LX_ID = ? limit ?,?";
        String id = (String) map.get("TZGL_LX_ID");
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_FASC_FAPF\n" +
                "WHERE\n" +
                "\t1=1 and TZGL_LX_ID = ?";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 工程规划许可--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 11:03
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gcghxkAdd")
    public String gcghxkAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_GCGHXK",newBo, null,me);
        // 修改主表中的总投资额
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        // todo 需要修改主表的信息待定

        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("id",newBo.getId());
        //todo 预留位置进行多次数据记录。
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 工程规划许可--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 18:57
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gcghxkUpdate")
    public String gcghxkUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_GCGHXK",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 工程规划许可--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/10 19:01
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gcghxkDetail")
    public String gcghxkDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_GCGHXK \n" +
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
     * @Description: 工程规划许可--列表
     * @author 张勇--Mr.Yong
     * @date 2021/3/18 16:04
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.gcghxkList")
    public String gcghxkList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了！！！！！++++初步设计");
        String sql = "select * from BO_EU_TFB_TZGL_GCGHXK where 1=1 and TZGL_LX_ID = ? limit ?,?";
        String id = (String) map.get("TZGL_LX_ID");
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_GCGHXK\n" +
                "WHERE\n" +
                "\t1=1  and TZGL_LX_ID = ?";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 施工管理--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 14:33
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgglAdd")
    public String sgglAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_SGGL",newBo, null,me);
        // 修改主表中的总投资额
        Map<String,Object> xmMap = new HashMap<>();
        xmMap.put("ID",map.get("TZGL_LX_ID"));
        // todo 需要修改主表的信息待定

        TfbKypfDao tfbKypfDao = new TfbKypfDao();
        tfbKypfDao.update(xmMap);
        ro.put("id",newBo.getId());
        //todo 预留位置进行多次数据记录。
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 施工管理 -- 修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 14:34
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgglUpdate")
    public String sgglUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_SGGL",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }
    /**
     * @Description: 施工管理--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 14:50
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgglDetail")
    public String sgglDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_SGGL \n" +
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
     * @Description:施工图--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 14:55
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgtAdd")
    public String sgtAdd(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_SGT",newBo, null,me);
        // 修改主表中的总投资额
        //todo 预留位置进行多次数据记录。
        ro.put("id",newBo.getId());
        ro.put("code","1");
        ro.put("value","新增成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }
    /**
     * @Description: 施工图--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 14:57
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgtUpdate")
    public String sgtUpdate(UserContext me, Map<String,Object> map) {
        //todo 可能不需要修改功能
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_SGT",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }
    /**
     * @Description: 施工图--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 15:19
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgtDetail")
    public String sgtDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_TFB_TZGL_SGT \n" +
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
     * @Description: 施工图--列表
     * @author 张勇--Mr.Yong
     * @date 2021/3/18 16:04
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.sgtList")
    public String sgtList(UserContext me, Map<String,Object> map) {
        System.out.println("请求进来了！！！！！++++初步设计");
        String sql = "select * from BO_EU_TFB_TZGL_SGT where 1=1 and TZGL_LX_ID = ? limit ?,?";
        String id = (String) map.get("TZGL_LX_ID");
        Integer pageIndex = map.get("pageIndex") == null ? 0:Integer.parseInt((String) map.get("pageIndex"));
        Integer pageSize = map.get("pageSize") == null ? 10:Integer.parseInt((String) map.get("pageSize"));
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { id,pageIndex,pageSize });
        String sqlCount = "SELECT\n" +
                "\tcount(0) cnt\n" +
                "\tFROM\n" +
                "\tBO_EU_TFB_TZGL_SGT\n" +
                "WHERE\n" +
                "\t1=1 and TZGL_LX_ID = ? ";
        Map<String,Object> countMap = DBSql.getMap(sqlCount,new Object[] {id});
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("count",countMap.get("cnt"));
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    /**
     * @Description: 项目涉及文件
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 16:33
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.wjxsList")
    public String wjxsList(UserContext me, Map<String,Object> map){
        String sql = "select ITEMNO,CNNAME,exttext1 as lv from BO_ACT_DICT_KV_ITEM where DICTKEY= ? order by ITEMNO";
        List<RowMap> dataMap = DBSql.getMaps(sql, new Object[] { "TFB_FILE" });
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }
    /**
     * @Description: 文件上传--新增
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 19:15
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.wjAdd")
    public String wjAdd(UserContext me, Map<String,Object> map){
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        map.put("TZGL_LX_ID",map.get("ID"));
        map.remove("ID");
        newBo.setAll(map);
        SDK.getBOAPI().create("BO_EU_FILE_TFB_FJ_GL",newBo, null,me);
        ro.put("msg","新增成功！");
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }
    /**
     * @Description: 文件上传--修改
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 19:32
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.wjUpdate")
    public String wjUpdate(UserContext me, Map<String,Object> map){
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_FILE_TFB_FJ_GL",newBo);
        ro.put("code","1");
        ro.put("value","修改成功！");
        ro.put("newBo",newBo.toString());
        return ro.toString();
    }

    /**
     * @Description: 文件--详情
     * @author 张勇--Mr.Yong
     * @date 2021/3/11 15:19
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.wjDetail")
    public String wjDetail(UserContext me, Map<String,Object> map) {
        String sql = "SELECT * from BO_EU_FILE_TFB_FJ_GL \n" +
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
     * @Description: 文件list
     * @author 张勇--Mr.Yong
     * @date 2021/3/15 10:09
     * @Version 1.0
     */
    @Mapping("com.awspaas.user.apps.send.department.mg.wjList")
    public String wjList(UserContext me, Map<String,Object> map){
        // 获取文件列表，可以根据类型进行分组
        String sql = "";
        String id = map.get("TZGL_LX_ID").equals("") || map.get("TZGL_LX_ID") == null ? "":(String) map.get("TZGL_LX_ID");
        String XMFJLX = map.get("XMFJLX").equals("") || map.get("XMFJLX") == null ? "":(String) map.get("XMFJLX");
        List<RowMap> dataMap = null;
        if (null == map.get("TZGL_LX_YW_ID") || "".equals(map.get("TZGL_LX_YW_ID"))){
            sql  = "SELECT * from BO_EU_FILE_TFB_FJ_GL \n" +
                    "WHERE\n" +
                    "\tTZGL_LX_ID = ? AND XMFJLX = ?";
            dataMap  = DBSql.getMaps(sql, new Object[] { id,XMFJLX });
        }else{
            String TZGL_LX_YW_ID = map.get("TZGL_LX_YW_ID").equals("") || map.get("TZGL_LX_YW_ID") == null ? "":(String) map.get("TZGL_LX_YW_ID");
            sql = "SELECT * from BO_EU_FILE_TFB_FJ_GL \n" +
                    "WHERE\n" +
                    "\tTZGL_LX_ID = ? AND XMFJLX = ? and TZGL_LX_YW_ID = ?";
            dataMap  = DBSql.getMaps(sql, new Object[] { id,XMFJLX,TZGL_LX_YW_ID });
        }
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",dataMap);
        ro.put("code","1");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

    // TODO: 2021/3/16 文件删除---不知道能不能用
    @Mapping("com.awspaas.user.apps.send.department.mg.wjdelete")
    public String wjDelete(UserContext me, Map<String,Object> map){
        DCContext context = (DCContext) map.get("DCContext");
        context.delete();
        ResponseObject ro = ResponseObject.newOkResponse();
        ro.put("dataMap",null);
        ro.put("code","1");
        ro.put("msg","成功！");
        ro.put("sid",me.getSessionId());
        return ro.toString();
    }

}

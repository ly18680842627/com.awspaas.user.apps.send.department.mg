package com.awspaas.user.apps.send.department.mg.controller;


import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.commons.database.RowMap;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.bcel.generic.NEW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TfbStartLcController {

    @Mapping("com.awspaas.user.apps.send.department.mg.startLc")
    public String dataList(UserContext me, Map<String,Object> map) {
        BO newBo = new BO();
        ResponseObject ro = ResponseObject.newOkResponse();
        // 启动校验流程--数据装表等
        String TZGL_LX_XMMC = (String) map.get("TZGL_LX_XMMC");
        ProcessInstance engineerProInst = SDK.getProcessAPI().createProcessInstance("obj_df12ae93f18746dcb83de280a132a423",
                me.getUID(),TZGL_LX_XMMC);
        SDK.getProcessAPI().start(engineerProInst).fetchActiveTasks();
        map.put("BINDID",engineerProInst.getId());
        map.put("PROCESSDEFID",engineerProInst.getProcessDefId());
        newBo.setAll(map);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_XMZB",newBo);
        ro.put("TZGL_LX_XMMC",TZGL_LX_XMMC);
        ro.put("code","1");
        ro.put("value","操作成功！");
        ro.put("newBo",newBo.toString());
        // 子表继续插入数据
        // 1.先查询对应的数据是否存在一份
        /*String sqlZb = "SELECT * FROM BO_EU_FILE_TFB_FJ_LX WHERE TZGL_LX_ID = ?";
        List<RowMap> dataMap = DBSql.getMaps(sqlZb, new Object[] { map.get("id") });
        if (!dataMap.isEmpty()){

        }else{
            BO fjlxBo = new BO();
            Map fjlxMap = new HashMap();
            fjlxMap.put("TZGL_LX_ID",map.get("id"));
            fjlxMap.put("TZGL_LX_BINID",map.get("bindid"));
            fjlxMap.put("XMBH",map.get("TZGL_LX_XMBH"));
            fjlxMap.put("XMMC",map.get("TZGL_LX_XMMC"));
            RowMap inMap = new RowMap(map);
            List list = new ArrayList();
            // 为空的时候 需要判断有哪些文件需要
            TfbLcController tfbLcController = new TfbLcController();
            List<RowMap> wjCzMap = tfbLcController.getWjxx(inMap);
            for (RowMap rowMap : wjCzMap) {
                Map inMap1 = new HashMap();
                inMap1.put("code",rowMap.get("ITEMNO"));
                inMap1.put("value",rowMap.get("CNNAME"));
                inMap1.put("sfcy",rowMap.get("sfcy"));
                list.add(inMap1);
            }
            String JSO = new Gson().toJson(list);
            fjlxMap.put("LX_WJ_JSON",JSO);
            fjlxBo.setAll(fjlxMap);
            SDK.getBOAPI().create("BO_EU_FILE_TFB_FJ_LX",fjlxBo,null,me.getUID());
        }*/
        return ro.toString();

    }

}

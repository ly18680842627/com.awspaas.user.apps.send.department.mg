package com.awspaas.user.apps.send.department.mg.controller;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.sdk.local.SDK;

import java.util.Map;

@Controller
public class TfbZlcController {

    @Mapping("com.awspaas.user.apps.send.department.mg.zlcqd")
    public String getContractNumber(UserContext me, Map<String,Object> inMap) {
        System.out.println("inMap+++++++++++:"+inMap.toString());
        String sid = me.getSessionId();
        String TZGL_LX_XMMC = (String) inMap.get("TZGL_LX_XMMC");
        ResponseObject ro = ResponseObject.newOkResponse();
        ProcessInstance engineerProInst = SDK.getProcessAPI().createProcessInstance("obj_8c642d0bb8bb4777843dd97168b0c8b3",
                me.getUserModel().getUserName(),TZGL_LX_XMMC);
        SDK.getProcessAPI().start(engineerProInst).fetchActiveTasks();
        String username = me.getUserName();
        System.out.println("用户名称：+++"+username);
        BO newBo = new BO();
        newBo = (BO) inMap;
        System.out.println("newBo++++++++++++:"+newBo.toString());
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_XMZB", newBo, engineerProInst,null);
        ro.put("TZGL_LX_XMMC",TZGL_LX_XMMC);
        ro.put("code","200");
        ro.put("value","操作成功！");
        ro.put("newBo",newBo.toString());
        return "";
    }
}

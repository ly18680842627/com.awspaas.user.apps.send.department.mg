package com.awspaas.user.apps.send.department.mg.task;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.commons.database.RowMap;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;

/**
 * @Description: 启动经济评审流程
 * @author 张勇--Mr.Yong
 * @date 2021/2/19 20:16
 * @Version 1.0
 */
public class StartTfbJjpsTask extends ValueListener implements ValueListenerInterface {
    @Override
    public String execute(ProcessExecutionContext ctx) throws Exception {
        // 判断是否有存在得一条经济评审流程
        String bindid = ctx.getProcessInstance().getId(); // 获取当前流程binid
        BO engineerBo = SDK.getBOAPI().query("BO_EU_TFB_TZGL_XMZB").detailByBindId(bindid);
        String TZGL_LX_ID = engineerBo.getId();
        String jjpsSql = "select TZGL_LX_XMBH,TZGL_LX_XMMC,TZGL_LX_ID,TZGL_LX_BINID from BO_EU_TFB_TZGL_JJPS WHERE TZGL_LX_ID = ? and TZGL_LX_BINID = ?";
        RowMap jjpsYyData = DBSql.getMap(jjpsSql,new Object[]{TZGL_LX_ID,bindid});
        if (null != jjpsYyData && !jjpsYyData.isEmpty()){
            // throw new BPMNError("0311", "项目【"+engineerBo.getString("TZGL_LX_XMMC")+"】已存在经济评审流程，请悉知！");
            return "项目【"+engineerBo.getString("TZGL_LX_XMMC")+"】已存在经济评审流程，请悉知！";
        }
        // 启动流程
        // 获取流程相关信息
        String TZGL_LX_XMMC = engineerBo.getString("TZGL_LX_XMMC");
        ProcessInstance engineerProInst = SDK.getProcessAPI().createProcessInstance("obj_f8c03f951ddb45a394bd31b860f35978",
                ctx.getProcessInstance().getCreateUser(),TZGL_LX_XMMC);
        SDK.getProcessAPI().start(engineerProInst).fetchActiveTasks();
        // 数据存库
        BO newBo = new BO();
        newBo.set("TZGL_LX_XMBH",engineerBo.get("TZGL_LX_XMBH"));
        newBo.set("TZGL_LX_XMMC",engineerBo.get("TZGL_LX_XMMC"));
        newBo.set("TZGL_LX_BINID",bindid);
        newBo.set("TZGL_LX_ID",engineerBo.getId());
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_JJPS", newBo, engineerProInst,null);
        System.out.println("BO_EU_TFB_TZGL_JJPS+新流程创建人:" + ctx.getProcessInstance().getCreateUser());
        return "启动经济评审成功！请到待办查看！";
    }
}

package com.awspaas.user.apps.send.department.mg.task;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.sdk.local.SDK;

public class StartTfbZxmLcTask  extends ValueListener implements ValueListenerInterface {
    @Override
    public String execute(ProcessExecutionContext ctx) throws Exception {
        String bindid = ctx.getProcessInstance().getId(); // 获取当前流程binid
        BO engineerBo = SDK.getBOAPI().query("BO_EU_TFB_TZGL_XMZB").detailByBindId(bindid);
        String TZGL_LX_XMMC = engineerBo.getString("TZGL_LX_XMMC");
        ProcessInstance engineerProInst = SDK.getProcessAPI().createProcessInstance("obj_f3803449d9ac456cb91c9e50e5725e97",
                ctx.getProcessInstance().getCreateUser(),TZGL_LX_XMMC);
        SDK.getProcessAPI().start(engineerProInst).fetchActiveTasks();
        BO newBo = new BO();



        System.out.println("正确的BO+++++++【"+engineerBo.toString());
        engineerBo.set("TZGL_LX_ZXM_ID",engineerBo.getId());
        engineerBo.set("TZGL_LX_ZXM_BINDID",engineerBo.getBindId());
        engineerBo.set("TZGL_LX_LEV","1");
        engineerBo.set("ID","");
        engineerBo.set("UPDATEUSER","");
        engineerBo.set("ORGID","");
        engineerBo.set("UPDATEDATE","");
        engineerBo.set("BINDID","");
        engineerBo.set("PROCESSDEFID","");
        engineerBo.set("CREATEDATE","");
        engineerBo.set("UPDATEUSER","");
        engineerBo.set("CREATEUSER","");
        System.out.println("正确的BO+++++++++++++++++++++"+engineerBo.toString());
        System.out.println("TZGL_LX_XMMC++++++++++++【"+TZGL_LX_XMMC+"】");
        SDK.getBOAPI().create("BO_EU_TFB_TZGL_XMZB", engineerBo, engineerProInst,null);
        return "新增成功！请到待办任务处确认信息！";
    }
}

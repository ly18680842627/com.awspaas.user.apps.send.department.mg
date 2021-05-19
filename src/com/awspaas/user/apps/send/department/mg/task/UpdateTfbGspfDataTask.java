package com.awspaas.user.apps.send.department.mg.task;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class UpdateTfbGspfDataTask  extends ExecuteListener implements ExecuteListenerInterface {
    @Override
    public void execute(ProcessExecutionContext ctx) throws Exception {
        String bindid = ctx.getProcessInstance().getId(); // 获取当前流程binid
        BO engineerBo = SDK.getBOAPI().query("BO_EU_TFB_TZGL_GSPF").detailByBindId(bindid);
        //String sjId = engineerBo.getString("TZGL_LX_ID");
        String sjBindId = engineerBo.getString("TZGL_LX_BINID");
        BO newBo = SDK.getBOAPI().query("BO_EU_TFB_TZGL_XMZB").detailByBindId(sjBindId);
        // BO newBo = new BO();
        newBo.set("TZGL_LX_GSPF",engineerBo.get("TZGL_LX_GSPF"));
        newBo.set("TZGL_LX_GSPF_FJ",engineerBo.get("TZGL_LX_GSPF_FJ"));
        // SDK.getBOAPI().create("BO_EU_TFB_TZGL_JJPS", newBo, engineerProInst,null);
        SDK.getBOAPI().update("BO_EU_TFB_TZGL_XMZB", newBo);
    }
}

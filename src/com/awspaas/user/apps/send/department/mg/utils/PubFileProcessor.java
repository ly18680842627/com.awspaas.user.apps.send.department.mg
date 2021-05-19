package com.awspaas.user.apps.send.department.mg.utils;

import com.actionsoft.bpms.server.fs.AbstFileProcessor;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.bpms.server.fs.FileProcessorListener;
import com.actionsoft.bpms.server.fs.dc.DCMessage;

import java.util.Map;

public class PubFileProcessor extends AbstFileProcessor implements FileProcessorListener {

    public boolean uploadReady(Map<String, Object> param) {
        DCContext context = (DCContext) param.get("DCContext");
        System.out.println("准备上传文件--" + context.getPath() + context.getFileName());
        return true;
    }

    public void uploadError(Map<String, Object> param) {
        DCContext context = (DCContext) param.get("DCContext");
        System.out.println("上传失败--" + context.getPath() + context.getFileName());
    }

    public void uploadBeforeEncrypt(Map<String, Object> param) {
        DCContext context = (DCContext) param.get("DCContext");
        System.out.println("已上传明文，准备加密前--" + context.getPath() + context.getFileName());
    }

    public void uploadSuccess(Map<String, Object> param) {
        DCContext context = (DCContext) param.get("DCContext");
        context.setDCMessage(DCMessage.OK, "");
        context.getDCMessage().addAttr("fileName", context.getFileName());
        context.getDCMessage().addAttr("url", context.getDownloadURL());
        // TODO: 2021/3/16  返回的文件路径存入库中？ 
        System.out.println("上传成功--" + context.getPath() + context.getFileName());
    }

    public boolean downloadValidate(Map<String, Object> param) {
        DCContext context = (DCContext) param.get("DCContext");
        System.out.println("下载校验--" + context.getPath() + context.getFileName());
        return true;
    }

    public void downloadComplete(Map<String, Object> param) {
        DCContext context = (DCContext) param.get("DCContext");
        System.out.println("下载结束--" + context.getPath() + context.getFileName());
    }



}

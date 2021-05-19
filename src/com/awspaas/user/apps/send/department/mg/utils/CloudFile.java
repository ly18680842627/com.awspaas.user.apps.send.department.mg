package com.awspaas.user.apps.send.department.mg.utils;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.bpms.server.fs.dc.DCConst;
import com.actionsoft.bpms.server.fs.dc.cloud.AbstractCloudFileProcessor;

import java.io.InputStream;
import java.util.Map;

public class CloudFile extends AbstractCloudFileProcessor {
    @Override
    public String pushCloudFile(Map<String, Object> map) {
        ResponseObject result = ResponseObject.newOkResponse();
        // 文件全路径的MD5摘要，开发者可使用该路径作为云存储的一个路径
        String pathMD5 = (String) map.get("PathMD5");
        // 文件的输入流
        InputStream in = (InputStream) map.get("data");
        // 存储后的结果，一个标准的ResponseObject对象封装，处理成功封装为OK的状态
        return result.toString();
    }

    @Override
    public InputStream downloadCloudFile(Map<String, Object> map) {
        return null;
    }

    @Override
    public String getDownloadURL(Map<String, Object> map) {
        // 返回文件的访问地址
        String finalUrl = "";
        return finalUrl;
    }

    public Boolean uploadReady(Map<String, Object> param) {
        DCContext dcContext = (DCContext) param.get("DCContext");
        if (DCConst.REPOSITORY_UI_FILE.equals(dcContext.getRepositoryName())) {
            // 举例如果需要接管平台的UI组件中“附件”的文件
            // ...
            // 自行处理后需要返回true
            return true;
        }
        //默认情况返回null，这样默认其他的自定义DC，则使用平台自身的处理机制
        return null;
    }
}

package com.awspaas.user.apps.send.department.mg.dao;

import com.actionsoft.bpms.commons.database.RowMapper;
import com.actionsoft.bpms.commons.mvc.dao.DaoObject;
import com.actionsoft.exception.AWSDataAccessException;

import java.util.Map;

public class TfbKypfDao extends DaoObject<Map<String,Object>> {

    @Override
    public int insert(Map<String, Object> map) throws AWSDataAccessException {
        return 0;
    }

    @Override
    public int update(Map<String, Object> map) throws AWSDataAccessException {
        String id = (String) map.get("ID");
        map.remove("id");
        return update(id,map);
    }

    @Override
    public String entityName() {
        return "BO_EU_TFB_TZGL_XMZB";
    }

    @Override
    public RowMapper<Map<String, Object>> rowMapper() {
        return null;
    }
}

package com.awspaas.user.apps.send.department.mg.dao;

import com.actionsoft.bpms.commons.database.RowMapper;
import com.actionsoft.bpms.commons.mvc.dao.DaoObject;
import com.actionsoft.exception.AWSDataAccessException;

import java.util.Map;

public class TfbKyjdDao extends DaoObject<Map<String,Object>> {
    @Override
    public int insert(Map<String, Object> map) throws AWSDataAccessException {
        return 0;
    }

    @Override
    public int update(Map<String, Object> map) throws AWSDataAccessException {
        return 0;
    }

    @Override
    public String entityName() {
        return "BO_EU_TFB_TZGL_JJPS";
    }

    @Override
    public RowMapper<Map<String, Object>> rowMapper() {
        return null;
    }



}

package com.b2s.scrumlr.admin.dao;

import com.b2s.scrumlr.admin.model.KeyValueParamter;
import com.b2s.scrumlr.odoo.model.OdooLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Allen Wu on 2016/10/27.
 */
@Repository
public class LogsDaoImpl extends BaseDao{

    private static final String TABLE_NAME_BY_DATE = "logs";

    public void saveLogs(final String date, List<OdooLog> logs){
        save(new KeyValueParamter(date,logs), TABLE_NAME_BY_DATE);
    }
}

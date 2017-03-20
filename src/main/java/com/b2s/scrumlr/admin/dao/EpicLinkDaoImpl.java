package com.b2s.scrumlr.admin.dao;

import com.b2s.scrumlr.admin.model.EpicLinkMap;
import com.b2s.scrumlr.admin.model.KeyValueParamter;
import com.b2s.scrumlr.admin.model.ScrumblrUser;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Repository
public class EpicLinkDaoImpl extends BaseDao {
    private static final String TABLE_NAME = "epicLinkMap";

    public void saveEpicLink(final EpicLinkMap map){
        this.save(new KeyValueParamter(map.getEpicName(),map), TABLE_NAME);
    }

    @SuppressWarnings("unchecked")
    public List<EpicLinkMap> listEpicLinks(){
        final Object result = query(TABLE_NAME);
        final List<EpicLinkMap> users = new ArrayList<>();
        if(result!=null){
            Map<String,String> map = null;
            if(result instanceof Map){
                map = (Map<String,String>)result;
            }
            final Collection<String> jsons = map.values();
            for(final String json : jsons){
                users.add(JsonUtil.fromJson(json, EpicLinkMap.class));
            }
        }
        Collections.sort(users, new Comparator<EpicLinkMap>() {
            @Override
            public int compare(final EpicLinkMap o1, final EpicLinkMap o2) {
                return o1.getEpicName().compareToIgnoreCase(o2.getEpicName());
            }
        });
        return users;
    }
}

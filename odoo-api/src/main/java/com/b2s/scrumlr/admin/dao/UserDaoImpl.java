package com.b2s.scrumlr.admin.dao;

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
public class UserDaoImpl extends BaseDao {
    private static final String TABLE_NAME = "userOdooMap";

    public void saveScrumblrUser(final ScrumblrUser user){
        this.save(new KeyValueParamter(user.getName(),user), TABLE_NAME);
    }

    @SuppressWarnings("unchecked")
    public List<ScrumblrUser> listScrumblrUser(){
        final Object result = query(TABLE_NAME);
        final List<ScrumblrUser> users = new ArrayList<>();
        if(result!=null){
            Map<String,String> map = null;
            if(result instanceof Map){
                map = (Map<String,String>)result;
            }
            final Collection<String> jsons = map.values();
            for(final String json : jsons){
                users.add(JsonUtil.fromJson(json, ScrumblrUser.class));
            }
        }
        Collections.sort(users, new Comparator<ScrumblrUser>() {
            @Override
            public int compare(final ScrumblrUser o1, final ScrumblrUser o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return users;
    }

    public void deleteScrumblrUser(final String key){
        this.delete(TABLE_NAME, key);
    }
}

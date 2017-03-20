package com.b2s.scrumlr.admin.dao;

import com.b2s.scrumlr.admin.model.KeyValueParamter;
import com.b2s.scrumlr.admin.model.ScrumblrAccount;
import com.b2s.scrumlr.admin.model.ScrumblrUser;
import com.b2s.scrumlr.odoo.model.User;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDao {
    private static final String TABLE_NAME = "userOdooMap";

    private static final String TABLE_NAME_BY_DATE = "customsTasks";


    private static final String TABLE_NAME_USERS = "odooUsers";

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        final Object result = query(TABLE_NAME_USERS);
        final List<User> users = new ArrayList<>();
        if (result != null) {
            Map<String, String> map = null;
            if (result instanceof Map) {
                map = (Map<String, String>) result;
            }
            final Collection<String> jsons = map.values();
            for (final String json : jsons) {
                users.add(JsonUtil.fromJson(json, User.class));
            }
        }

        return users;
    }

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

    public List<ScrumblrUser> listScrumblrUserByDate(final String date){
        final Object result = get(TABLE_NAME_BY_DATE,date);
        List<ScrumblrUser> users = new ArrayList<>();
        if(result!=null){
            if(result instanceof String){
                users = JsonUtil.fromJson((String)result, JsonUtil.getTypeFactory().constructCollectionType(List.class, ScrumblrUser.class));
            }
        }
        return join(this.listScrumblrUser(), users, date);
    }

    @SuppressWarnings("unchecked")
    public List<ScrumblrAccount> listScrumblrAccount(){
        final Object result = query("user");
        final List<ScrumblrAccount> users = new ArrayList<>();
        if(result!=null){
            Map<String,String> map = null;
            if(result instanceof Map){
                map = (Map<String,String>)result;
            }
            final Collection<String> jsons = map.values();
            for(final String json : jsons){
                users.add(JsonUtil.fromJson(json, ScrumblrAccount.class));
            }
        }
        Collections.sort(users, new Comparator<ScrumblrAccount>() {
            @Override
            public int compare(final ScrumblrAccount o1, final ScrumblrAccount o2) {
                return o1.getUname().compareToIgnoreCase(o2.getUname());
            }
        });
        return users;
    }

    public void saveScrumblrAccount(final ScrumblrAccount account){
        this.save(new KeyValueParamter(account.getUname(),account), "user");
    }

    public void deleteScrumblrAccount(final ScrumblrAccount account){
        this.delete("user",account.getUname());
    }

    private List<ScrumblrUser> join(final List<ScrumblrUser> users, final List<ScrumblrUser> tasks, final String date){
        if(!tasks.isEmpty()){
            final Map<String,ScrumblrUser> map = new HashMap<>();
            for(final ScrumblrUser task: tasks){
                map.put(task.getName(), task);
            }
            for(final ScrumblrUser user: users){
                final ScrumblrUser task = map.get(user.getName());
                if(task!=null){
                    user.setCustoms(task.getCustoms());
                }
                user.setDate(date);
            }
        }else{
            for(final ScrumblrUser user: users){
                user.setDate(date);
            }
        }
        return users;
    }

    public void saveScrumblrUserByDate(final String date, final List<ScrumblrUser> users){
        save(new KeyValueParamter(date,users), TABLE_NAME_BY_DATE);
    }

    public void deleteScrumblrUser(final String key){
        this.delete(TABLE_NAME, key);
    }

    public boolean isAdmin(final String name, final String password){
        boolean flag = false;
        try{
            final List<ScrumblrAccount> accs = this.listScrumblrAccount();
            for(final ScrumblrAccount scrumblrAccount: accs){
                if(scrumblrAccount.getUname().equals(name) && scrumblrAccount.getPwd().equals(password)){
                    if(scrumblrAccount.getAuthority()==9){
                        flag = true;
                    }
                }
            }
        }catch (final Exception e){
            LOGGER.error(e.getMessage(), e);
            return flag;
        }
        return flag;
    }
}

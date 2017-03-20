package com.b2s.scrumlr.common.inteceptor;

import com.b2s.scrumlr.admin.model.Constants;
import com.b2s.scrumlr.odoo.model.ResponseMap;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler) throws Exception {
        if (request.getRequestURI().indexOf("login") != -1) {
            return true;
        }
        final Object user = request.getSession().getAttribute(Constants.USER);
        if (user == null) {
            response.getWriter().print(JsonUtil.toJson(new ResponseMap(false, "No access!")));
            return false;
        } else {
            return true;
        }
    }

}

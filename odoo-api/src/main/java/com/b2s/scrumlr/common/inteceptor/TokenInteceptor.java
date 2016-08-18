package com.b2s.scrumlr.common.inteceptor;

import com.b2s.scrumlr.odoo.model.ResponseMap;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInteceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInteceptor.class);
    private static final String TOKEN = "ODOO-API-TOKEN";
    public static final String XML_HTTP_REQUEST = "X-Requested-With";
    public static final String XML_HTTP_REQUEST_VALUE = "XMLHttpRequest";

    @Override
    public boolean preHandle(final HttpServletRequest request,final HttpServletResponse response,final Object handler) throws Exception {
        final boolean isValidate = isValidateToken(request.getHeader(TOKEN));
        if(!isValidate){
            LOGGER.error("Invalidate request : {}", request.getContextPath());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            if (XML_HTTP_REQUEST_VALUE.equals(request.getHeader(XML_HTTP_REQUEST))) {
                response.getWriter().print(JsonUtil.toJson(new ResponseMap(false,"Invalidate token!")));
            }else{
                response.sendRedirect(request.getContextPath());
            }
        }
        return isValidate;
    }

    private boolean isValidateToken(final String token){
        return "1234567890".equals(token);
    }
}

package org.java.hrm.interceptor;

import org.java.hrm.domain.Operation;
import org.java.hrm.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限拦截器，拦截用户没有权限的uri
 */
public class OperationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri =  httpServletRequest.getRequestURI();
        List<Operation> operations = (List<Operation>)httpServletRequest.getSession().getAttribute(HrmConstants.USER_OPERATION_SESSION);
        /**  如果当前请求的uri中有禁止当前用户访问的资源，就不允许其访问 */
        if (operations != null){
            for (Operation operation : operations) {
                if (operation.getUrl().indexOf(uri) >= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

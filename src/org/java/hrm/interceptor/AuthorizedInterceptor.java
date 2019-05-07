package org.java.hrm.interceptor;

import org.java.hrm.domain.User;
import org.java.hrm.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizedInterceptor implements HandlerInterceptor {
    //定义不需要拦截的页面
    private static final String[] IGNORE_URL = {"/loginForm", "/login", "/404.html"};

    /**
     * 这个方法在执行Controller中的方法之前执行，
     * 当这个方法返回false时，整个的请求就结束了，即已经成功的拦截了不合法的请求
     * 当这个方法的返回值是true时，表示这个请求是合法的
     * 这个方法的方法体是具体执行拦截的地方
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Boolean flag = false;  //是否需要拦截
        String servletPath = httpServletRequest.getServletPath();  //用户请求的url

        /** 判断是否需要拦截 **/
        for (String s : IGNORE_URL) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            User user = (User)httpServletRequest.getSession().getAttribute(HrmConstants.USER_SESSION);
            if (user == null){
                /** 用户未登录，跳转到登录页面 */
                httpServletRequest.getRequestDispatcher(HrmConstants.LOGIN).forward(httpServletRequest, httpServletResponse);
            }else {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 这个方只有在preHandle方法返回true时，才会执行
     * 这个方法的执行时间是，Controller中的具体方法执行之后。
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {

    }

    /**
     * 这个是最后可能执行的方法，也是只有在preHandle方法返回true时才会执行
     * 该方法将在整个请求完成之后执行，主要用于清理资源
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

package org.java.hrm.controller;

import org.java.hrm.myException.AddUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(AddUserException.class)
    public ModelAndView addUserExceptionHandler() {
        System.out.println("添加用户失败啦，调用了异常的处理发");
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "对不住啦，添加用户信息失败啦！");
        mv.addObject("page", "500.jsp");
        mv.setViewName("main");
        return mv;
    }
}

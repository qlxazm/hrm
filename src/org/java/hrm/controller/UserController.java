package org.java.hrm.controller;

import org.java.hrm.domain.User;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.common.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv){
        User user = hrmService.login(loginname, password);
        if (user != null) {
            session.setAttribute(HrmConstants.USER_SESSION, user);
            mv.setViewName("redirect:/main");
        }else {
            mv.addObject("message", "用户名或者密码错误！");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    @RequestMapping(value = "/loginForm")
    public ModelAndView loginForm(ModelAndView mv) {
        mv.setViewName("/loginForm");
        return mv;
    }


    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("/main");
        return mv;
    }

}

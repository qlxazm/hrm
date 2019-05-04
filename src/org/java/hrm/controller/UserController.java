package org.java.hrm.controller;

import org.java.hrm.domain.User;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.common.HrmConstants;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 用户登录
     * @param loginname
     * @param password
     * @param session
     * @param mv
     * @return
     */
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

    /**
     * 这里使用ModelAttribute注解的属性。前台控件的值会自动传入到user对应的属性中。
     * @param pageIndex
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex,
                             @ModelAttribute User user,
                             Model model) {
        System.out.println("用户管理之用户查询请求 -- >> " + user);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        /** 查询用户的信息 */
        List<User> users = hrmService.findUser(user, pageModel);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("users", users);
        /** 设置前台显示的页面 */
        model.addAttribute("page", "user/user.jsp");
        return "main";
    }

    /**
     * 添加用户
     * @param flag   flag=1 只是跳转到添加用户界面  flag=2 添加用户
     * @param user
     * @param mv
     * @return
     */
    @RequestMapping(value = "/user/addUser")
    public ModelAndView addUser(String flag, @ModelAttribute User user, ModelAndView mv){
        System.out.println("/user/addUser -- >> " + user);
        if (flag.equals("1")){
            mv.addObject("page", "user/addUser.jsp");
        }else {
            hrmService.addUser(user);
            String message = user.getId() != null && user.getId() > 0 ? "添加用户成功！" : "添加用户失败！";
            mv.addObject("message", message);
            mv.addObject("page", "user/addUser.jsp");
        }
        mv.setViewName("main");
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
        mv.addObject("page", "default.jsp");
        return mv;
    }

}

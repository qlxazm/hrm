package org.java.hrm.controller;

import org.java.hrm.domain.Operation;
import org.java.hrm.domain.Role;
import org.java.hrm.domain.User;
import org.java.hrm.domain.UserRole;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.common.HrmConstants;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 更新用户信息
     * @param flag
     * @param mv
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(String flag,
                                   ModelAndView mv,
                                   @Valid @ModelAttribute User user,
                                   Errors errors
                                  ){
        System.out.println("用户更新 -- >> " + user);

        try{
            if (flag.equals("1")) {
                /** 跳转到用户信息更改页面 */
                user = hrmService.findUserById(user.getId());
            }else{
                /** 真正的更改用户信息 */
                if (!errors.hasErrors()) {
                    hrmService.modifyUser(user);
                    mv.addObject("message", "更新成功！");
                }
            }
        }catch (Exception e){
            mv.addObject("message", "更新失败！");
        }finally {
            List<Role> roles = new ArrayList<>();
            try{
                roles = hrmService.selectAllRole();
            }catch (Exception e){
                mv.addObject("message", "查询角色信息失败！");
            }finally {
                mv.addObject("roles", roles);
                mv.addObject("user", user);
                mv.addObject("page", "user/updateUser.jsp");
                mv.setViewName("main");
                return mv;
            }
        }
    }

    /**
     * 批量删除用户
     * @param ids
     * @param mv
     * @return
     */
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids, ModelAndView mv){
        System.out.println("删除用户 -- >> " + ids);
        try{
            hrmService.removeUser(ids);
        }catch (Exception e){
            System.out.println("删除用户时，出现了错误。这个用户的id是：" + ids);
        }finally {
            mv.setViewName("redirect:/user/selectUser");
            return  mv;
        }
    }

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
            List<Operation> operations = hrmService.selectOperationByUid(user.getId());
            session.setAttribute(HrmConstants.USER_OPERATION_SESSION, operations);
            session.setAttribute(HrmConstants.USER_SESSION, user);
            mv.setViewName("redirect:/main");
        }else {
            mv.addObject("message", "用户名或者密码错误！");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    /**
     * 登出系统
     * @param request
     * @param mv
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView mv, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(HrmConstants.USER_SESSION);
        session.removeAttribute(HrmConstants.USER_OPERATION_SESSION);
        mv.setViewName("redirect:/loginForm");
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
     * 添加新的用户时，还需要为用户指定角色信息，所以需要操作用户表和角色表，用户必须有角色，
     * 需要使用事务保持数据库的一致性。
     * @param flag   flag=1 只是跳转到添加用户界面  flag=2 添加用户
     * @param user
     * @param mv
     * @return
     */
    @RequestMapping(value = "/user/addUser")
    public ModelAndView addUser(String flag,
                                @Valid @ModelAttribute User user,
                                Errors errors,
                                ModelAndView mv){
        System.out.println("/user/addUser -- >> " + user);
        String message = "";

        if (flag.equals("2")){
            if (!errors.hasErrors()) {
                hrmService.addUser(user);
                message = "添加用户成功！";
            }
        }

        List<Role> roles = new ArrayList<>();
        roles = hrmService.selectAllRole();
        if (roles.size() == 0) {
            message = "查询系统角色列表失败！";
        }
        mv.addObject("roles", roles);
        mv.addObject("message", message);
        mv.addObject("page", "user/addUser.jsp");
        mv.setViewName("main");
        return mv;
    }

    /**
     * 请求登录页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "/loginForm")
    public ModelAndView loginForm(ModelAndView mv) {
        mv.setViewName("/loginForm");
        return mv;
    }

    /**
     * 请求main页面
     * @param mv
     * @return
     */
    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("/main");
        mv.addObject("page", "default.jsp");
        return mv;
    }

}

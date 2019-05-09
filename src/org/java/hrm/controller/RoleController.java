package org.java.hrm.controller;

import org.apache.ibatis.annotations.Result;
import org.java.hrm.domain.Permission;
import org.java.hrm.domain.Role;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.common.HrmConstants;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;


    /**
     * 根据条件查询角色信息
     * @param mv
     * @param role
     * @param pageIndex
     * @return
     */
    @RequestMapping("/role/selectRole")
    public ModelAndView selectRole(
            ModelAndView mv,
            Role role,
            Integer pageIndex
    ){
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Role> roles = hrmService.findRole(role, pageModel);
        mv.addObject("roles", roles);
        mv.addObject("pageModel", pageModel);
        mv.addObject("page", "role/role.jsp");
        mv.setViewName("main");
        return mv;
    }

    /**
     * 删除用户
     * @param mv
     * @param ids
     * @return
     */
    @RequestMapping("/role/removeRole")
    public ModelAndView removeRole(ModelAndView mv, String ids) {
        try{
            hrmService.removeRole(ids);
        }catch (Exception e) {
            System.out.println("删除用户失败！");
        }finally {
            mv.setViewName("redirect:/role/selectRole");
            return mv;
        }
    }

    /**
     * 添加新的角色
     * @param mv
     * @param flag
     * @param role
     * @return
     */
    @RequestMapping("/role/addRole")
    public ModelAndView addRole(ModelAndView mv,
                                String flag,
                                @Valid @ModelAttribute Role role,
                                Errors errors) {
        String message = "";
        List<Permission> permissions = new ArrayList<>();
        try{
            permissions = hrmService.selectAllPermission();
            /** flag是2时表示添加用户*/
            if (flag.equals("2") && !errors.hasErrors()) {
                hrmService.addRole(role);
                message = "添加新的角色成功！";
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "添加新的角色失败！";
        }finally {
            mv.addObject("permissions", permissions);
            mv.addObject("role", role);
            mv.addObject("page", "role/addRole.jsp");
            mv.addObject("message", message);
            mv.setViewName("main");
            return mv;
        }
    }


    /**
     * 更新角色信息
     * @param mv
     * @param flag
     * @param role
     * @param errors
     * @return
     */
    @RequestMapping("/role/updateRole")
    public ModelAndView updateRole(ModelAndView mv,
                                   String flag,
                                   @Valid @ModelAttribute Role role,
                                   Errors errors) {
        String message = "";
        List<Permission> permissions = new ArrayList<>();
        try{
            permissions = hrmService.selectAllPermission();
            if (flag.equals("2") && !errors.hasErrors()) {
                hrmService.modifyRole(role);
                message = "更新角色信息成功！";
            }else {
                role = hrmService.selectRoleById(role.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
            message = "更新角色信息失败！";
        }finally {
            mv.addObject("role", role);
            mv.addObject("message", message);
            mv.addObject("permissions", permissions);
            mv.addObject("page", "role/updateRole.jsp");
            mv.setViewName("main");
            return mv;
        }
    }
}

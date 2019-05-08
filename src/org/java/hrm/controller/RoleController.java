package org.java.hrm.controller;

import org.apache.ibatis.annotations.Result;
import org.java.hrm.domain.Role;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.common.HrmConstants;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;


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
        System.out.println("删除角色 -- >> " + ids);
        try{
            hrmService.removeRole(ids);
        }catch (Exception e) {

        }finally {
            mv.setViewName("redirect:/role/selectRole");
            return mv;
        }
    }
}

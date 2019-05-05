package org.java.hrm.controller;

import org.java.hrm.domain.Dept;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeptController {
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 分页查询部门信息
     * @param pageIndex
     * @param dept
     * @param model
     * @return
     */
    @RequestMapping("/dept/selectDept")
    public String selectDept(Integer pageIndex,
                             @ModelAttribute Dept dept,
                             Model model){
        System.out.println("部门信息查询 -- >> " + dept);

        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Dept> depts = hrmService.findDept(dept, pageModel);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);
        /** 设置前台显示的页面 */
        model.addAttribute("page", "dept/dept.jsp");
        return "main";
    }

    /**
     * 批量删除部门
     * @param ids
     * @param mv
     * @return
     */
    @RequestMapping(value = "/dept/removeDept")
    public  ModelAndView removeDept(String ids, ModelAndView mv) {
        System.out.println("删除Dept -- >> " + ids);
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            hrmService.removeDeptById(Integer.parseInt(id));
        }
        /** 前端再次请求/user/selectUser，如果不加redirect就默认的是服务器端转发 */
        mv.setViewName("redirect:/dept/selectDept");
        return mv;
    }
}

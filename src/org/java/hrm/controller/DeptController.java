package org.java.hrm.controller;

import org.java.hrm.domain.Dept;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.tag.PageModel;
import org.java.hrm.utils.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    @ApiRequest
    @RequestMapping("/dept/selectDept")
    public String selectDept(Integer pageIndex,
                             @ModelAttribute Dept dept,
                             Model model){
       /* System.out.println("门部信息查询 pageIndex -- >> " + pageIndex);
        System.out.println("门部信息查询 dept -- >> " + dept);
        System.out.println("门部信息查询 model -- >> " + model);*/

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

    /**
     * 添加dept
     * @param flag flag = "1" 只是跳转到添加页面  flag = "2" 真正的添加
     * @param dept
     * @param mv
     * @return
     */
    @RequestMapping(value = "/dept/addDept")
    public ModelAndView addDept(String flag,
                                @Valid @ModelAttribute Dept dept,
                                Errors errors,
                                ModelAndView mv) {
        System.out.println("添加dept -- >> " + dept);
        if (flag != null && flag.equals("2")){
            /** 真正的添加dept */
            if (!errors.hasErrors()) {
                hrmService.addDept(dept);
                String message = dept.getId() != null && dept.getId() > 0 ? "添加成功！" : "添加失败！";
                mv.addObject("message", message);
            }
        }
        mv.addObject("page", "dept/addDept.jsp");
        mv.setViewName("/main");
        return mv;
    }

    @RequestMapping(value = "/dept/updateDept")
    public ModelAndView updateDept(String flag,
                                   @Valid @ModelAttribute Dept dept,
                                   Errors errors,
                                   ModelAndView mv){
        if (flag.equals("1")){
            /** 只是跳转到更新页面 */
            dept = hrmService.findDeptById(dept.getId());
        }else{
            /** 进行更新 */
            if (!errors.hasErrors()) {
                hrmService.modifyDept(dept);
                mv.addObject("message", "更新成功！");
            }
        }
        mv.addObject("dept", dept);
        mv.addObject("page", "dept/updateDept.jsp");
        mv.setViewName("/main");
        return mv;
    }
}

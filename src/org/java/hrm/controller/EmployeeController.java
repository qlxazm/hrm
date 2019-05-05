package org.java.hrm.controller;

import org.java.hrm.domain.Dept;
import org.java.hrm.domain.Employee;
import org.java.hrm.domain.Job;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 根据参数查询员工信息
     * @param pageIndex
     * @param employee
     * @param mv
     * @return
     */
    @RequestMapping(value = "/employee/selectEmployee")
    public ModelAndView selectEmployee(Integer pageIndex,
            @ModelAttribute Employee employee,
            ModelAndView mv
            ){
        System.out.println("员工信息查询 -- >> " + employee);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        List<Employee> employees = hrmService.findEmployee(employee, pageModel);
        List<Dept> depts = hrmService.findAllDept();
        List<Job> jobs = hrmService.findAllJob();
        mv.addObject("employees", employees);
        mv.addObject("depts", depts);
        mv.addObject("jobs", jobs);
        mv.addObject("pageModel", pageModel);
        mv.addObject("page","employee/employee.jsp");
        mv.setViewName("main");
        return mv;
    }
}

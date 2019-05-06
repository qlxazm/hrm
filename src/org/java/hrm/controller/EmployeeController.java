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


    /**
     * 根据id删除员工
     * @param ids
     * @param mv
     * @return
     */
    @RequestMapping(value = "/employee/removeEmployee")
    public ModelAndView removeEmployee(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            hrmService.removeEmployeeById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/employee/selectEmployee");
        return mv;
    }


    /**
     * 添加员工
     * @param flag
     * @param employee
     * @param mv
     * @return
     */
    @RequestMapping(value = "/employee/addEmployee")
    public ModelAndView addEmployee(String flag,
                                    @ModelAttribute Employee employee,
                                    ModelAndView mv){
        System.out.println("添加员工 -->> " + employee);
        String message = "";
        try{
            if (flag.equals("2")) {
                hrmService.addEmployee(employee);
                message = "添加成功！";
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "添加失败！";
        }finally {
            mv.addObject("message", message);
            List<Dept> depts = hrmService.findAllDept();
            List<Job> jobs = hrmService.findAllJob();
            mv.addObject("depts", depts);
            mv.addObject("employee", employee);
            mv.addObject("jobs", jobs);
            mv.addObject("page", "employee/addEmployee.jsp");
            mv.setViewName("main");
            return mv;
        }
    }

    /**
     * 更新员工信息
     * @param flag
     * @param employee
     * @param mv
     * @return
     */
    @RequestMapping(value = "/employee/updateEmployee")
    public ModelAndView updateEmployee(String flag,
                                       @ModelAttribute Employee employee,
                                       ModelAndView mv){

        System.out.println("更新员工信息 -- >> " + employee);

        if (flag.equals("1")) {
            employee = hrmService.findEmployeeById(employee.getId());
        }else{
            hrmService.modifyEmployee(employee);
            mv.addObject("message", "修改成功！");
        }
        List<Dept> depts = hrmService.findAllDept();
        List<Job> jobs = hrmService.findAllJob();

        mv.addObject("depts", depts);
        mv.addObject("jobs", jobs);
        mv.addObject("employee", employee);
        mv.addObject("page", "employee/updateEmployee.jsp");
        mv.setViewName("main");
        return mv;
    }
}

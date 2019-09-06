package org.java.hrm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java.hrm.domain.Dept;
import org.java.hrm.domain.Employee;
import org.java.hrm.domain.Job;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.tag.PageModel;
import org.java.hrm.utils.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
                                    @Valid @ModelAttribute Employee employee,
                                    Errors errors,
                                    ModelAndView mv){
        System.out.println("添加员工 -->> " + employee);
        String message = "";
        try{
            if (flag.equals("2")) {
                message = "添加员工失败！";
                if (!errors.hasErrors()) {
                    hrmService.addEmployee(employee);
                    if (employee.getId() != null && employee.getId() > 0){
                        message = "添加员工成功！";
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
                                      @Valid @ModelAttribute Employee employee,
                                        Errors errors,
                                       ModelAndView mv){

        System.out.println("更新员工信息 -- >> " + employee);

        if (flag.equals("1")) {
            employee = hrmService.findEmployeeById(employee.getId());
        }else{
            if (!errors.hasErrors()) {
                hrmService.modifyEmployee(employee);
                mv.addObject("message", "修改成功！");
            }
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

    /**
     * 查看员工详情
     * @param employee
     * @param response
     */
    @ApiRequest
    @RequestMapping(value = "/employee/detailEmployee")
    public void detailEmployee(@RequestBody Employee employee,
                               HttpServletResponse response) throws Exception{

        System.out.println("查询员工具体信息 employee ->" + employee);

        response.setContentType("application/json; charset=utf-8");
        employee = hrmService.findEmployeeById(employee.getId());
        ObjectMapper objectMapper = new ObjectMapper();


        /** 将查询到的员工对象返回 */
        String data = objectMapper.writeValueAsString(employee);
        String result = "{code: 0, data:" + data + "}";
        response.getWriter().println(data);
    }
}

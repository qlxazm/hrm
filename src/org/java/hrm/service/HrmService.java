package org.java.hrm.service;

import org.java.hrm.dao.JobDao;
import org.java.hrm.domain.Dept;
import org.java.hrm.domain.Employee;
import org.java.hrm.domain.Job;
import org.java.hrm.domain.User;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

public interface HrmService {

    /* ==================== 用户部分 ======================== */


    /**
     * 用户登陆
     * @param loginname
     * @param password
     * @return
     */
    public User login(String loginname, String password);


    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User findUserById(Integer id);

    /**
     * 根据条件获取用户
     * @param user
     * @param pageModel
     * @return
     */
    public List<User> findUser(User user, PageModel pageModel);

    /**
     * 添加新的用户
     * @param user
     * @return
     */
    public void addUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    public void removeUserById(Integer id);

    /**
     * 更新用户
     * @param user
     */
    public void modifyUser(User user);


    /* ========================   部门部分  =========================== */

    /**
     * 根据条件查询部门信息
     * @param dept
     * @param pageModel
     * @return
     */
    public List<Dept> findDept(Dept dept, PageModel pageModel);

    /**
     * 根据id删除部门信息
     * @param id
     */
    public void removeDeptById(Integer id);

    /**
     * 插入部门
     * @param dept
     */
    public void addDept(Dept dept);

    /**
     * 跟新dept
     * @param dept
     */
    public void modifyDept(Dept dept);

    /**
     * 根据id查询出dept信息
     * @param id
     * @return
     */
    public Dept findDeptById(Integer id);


    /**
     * 查询出所有的部门
     * @return
     */
    public List<Dept> findAllDept();


    /*==========================   员工部分   ===================================*/

    /**
     * 根据条件，查询员工信息
     * @param employee
     * @param pageModel
     * @return
     */
    public List<Employee> findEmployee(Employee employee, PageModel pageModel);


    /**
     * 根据id删除用户
     * @param id
     */
    public void removeEmployeeById(Integer id);

    /**
     * 添加用户
     * @param employee
     */
    public void addEmployee(Employee employee);


    /*===================================   岗位部分   ======================================*/


    /**
     * 查询出所有的工作
     * @return
     */
    public List<Job> findAllJob();
}
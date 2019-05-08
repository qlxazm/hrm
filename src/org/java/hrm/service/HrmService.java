package org.java.hrm.service;

import org.java.hrm.dao.JobDao;
import org.java.hrm.dao.OperationDao;
import org.java.hrm.domain.*;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HrmService {

    /* ==================== 用户部分 ======================== */


    /**
     * 用户登陆
     * @param loginname
     * @param password
     * @return
     */
    User login(String loginname, String password);


    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 根据条件获取用户
     * @param user
     * @param pageModel
     * @return
     */
    List<User> findUser(User user, PageModel pageModel);

    /**
     * 添加新的用户
     * @param user
     * @return
     */
    void addUser(User user);

    /**
     * 根据id删除用户
     * @param ids
     */
    void removeUser(String ids);

    /**
     * 更新用户
     * @param user
     */
    void modifyUser(User user);


    /* ========================   部门部分  =========================== */

    /**
     * 根据条件查询部门信息
     * @param dept
     * @param pageModel
     * @return
     */
    List<Dept> findDept(Dept dept, PageModel pageModel);

    /**
     * 根据id删除部门信息
     * @param id
     */
    void removeDeptById(Integer id);

    /**
     * 插入部门
     * @param dept
     */
    void addDept(Dept dept);

    /**
     * 跟新dept
     * @param dept
     */
    void modifyDept(Dept dept);

    /**
     * 根据id查询出dept信息
     * @param id
     * @return
     */
    Dept findDeptById(Integer id);


    /**
     * 查询出所有的部门
     * @return
     */
    List<Dept> findAllDept();


    /*==========================   员工部分   ===================================*/

    /**
     * 根据条件，查询员工信息
     * @param employee
     * @param pageModel
     * @return
     */
    List<Employee> findEmployee(Employee employee, PageModel pageModel);


    /**
     * 根据id删除用户
     * @param id
     */
    void removeEmployeeById(Integer id);

    /**
     * 添加用户
     * @param employee
     */
    void addEmployee(Employee employee);


    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Employee findEmployeeById(Integer id);

    /**
     * 更新员工信息
     * @param employee
     */
    void modifyEmployee(Employee employee);

    /*===================================   岗位部分   ======================================*/


    /**
     * 查询出所有的工作
     * @return
     */
    List<Job> findAllJob();


    /*========================================  用户权限部分  =================================*/


    List<Operation> selectOperationByUid(Integer id);


    /*===========================================   角色部分  =================================*/

    /**
     * 查询所有的角色
     * @return
     */
    List<Role> selectAllRole();

    /**
     * 根据条件查询角色信息
     * @param role
     * @param pageModel
     * @return
     */
    List<Role> findRole(Role role, PageModel pageModel);


    /**
     * 珊瑚角色
     * @param ids
     */
    void removeRole(String ids);

    /*==========================================      用户角色中间表      =============================*/

    void addUserRole(UserRole userRole);

}
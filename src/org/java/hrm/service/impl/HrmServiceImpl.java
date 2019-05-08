package org.java.hrm.service.impl;

import org.java.hrm.dao.*;
import org.java.hrm.domain.*;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*使用事务数据库的默认的事务隔离级别*/
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {

    /* ==================== 用户部分 ======================== */

    @Autowired
    private UserDao userDao;  //自动注入UserDao

    /**
     * 根据id删除用户
     * @param ids
     */
    @Transactional
    @Override
    public void removeUser(String ids) {
        for (String id : ids.split(",")) {
            userRoleDao.removeByUserId(Integer.parseInt(id));
            userDao.deleteById(Integer.parseInt(id));
        }
    }

    /**
     * 修改用户
     * 对于用户角色的修改，应该先删除userrole中间表中的记录，再增加新的记录
     * @param user
     */
    @Transactional
    @Override
    public void modifyUser(User user) {
        userDao.update(user);
        /** 删除用户原来的角色信息 */
        userRoleDao.removeByUserId(user.getId());
        /** 添加新的角色 */
        UserRole userRole = new UserRole();
        for (String roleId : user.getRole_ids().split(",")) {
            userRole.setUserid(user.getId());
            userRole.setRoleid(Integer.parseInt(roleId));
            userRoleDao.save(userRole);
        }
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @Transactional
    @Override
    public void addUser(User user) {
        userDao.save(user);
        /**储存用户的角色信息*/
        UserRole userRole = new UserRole();
        for (String roleId : user.getRole_ids().split(",")) {
            userRole.setRoleid(Integer.parseInt(roleId));
            userRole.setUserid(user.getId());
            addUserRole(userRole);
        }
    }

    /**
     * 分页查询用户
     * @param user
     * @param pageModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        int recordCount = userDao.count(params);
        System.out.println("查询符合条件的用户总数 -- >> " + recordCount);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0){
            params.put("pageModel", pageModel);
        }
        return userDao.selectByPage(params);
    }

    /**
     * 登录
     * @param loginname
     * @param password
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        System.out.println("HrmServiceImpl -- >> login");
        return userDao.selectByLoginnameAndPassword(loginname, password);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        System.out.println("HrmServiceImpl -- >> findUserById");
        return userDao.selectById(id);
    }

    /* ========================   部门部分  =========================== */

    @Autowired
    private DeptDao deptDao; //自动注入deptDao

    /**
     * 根据条件查询部门
     * @param dept
     * @param pageModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("dept", dept);
        int deptCount = deptDao.count(params);
        System.out.println("查询出来的符合条件的部门总数：" + deptCount);
        pageModel.setRecordCount(deptCount);

        if (deptCount > 0) {
            params.put("pageModel", pageModel);
        }
        return deptDao.selectByPage(params);
    }

    /**
     * 根据id删除部门
     * @param id
     */
    @Override
    public void removeDeptById(Integer id) {
        deptDao.deleteById(id);
    }

    /**
     * 插入部门
     * @param dept
     */
    @Override
    public void addDept(Dept dept) {
        deptDao.save(dept);
    }

    /**
     * 更新dept
     * @param dept
     */
    @Override
    public void modifyDept(Dept dept) {
        deptDao.update(dept);
    }

    /**
     * 根据id查询出dept信息
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }

    /**
     * 查询出所有部门
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Dept> findAllDept() {
        return deptDao.selectAll();
    }

    /*==========================   员工部分   ===================================*/

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 分页查询员工信息
     * @param employee
     * @param pageModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {

        Map<String, Object> params = new HashMap<>();
        params.put("employee", employee);
        int employeeCount = employeeDao.count(params);
        System.out.println("查询出来的符合条件的员工总数：" + employeeCount);
        pageModel.setRecordCount(employeeCount);

        if (employeeCount > 0){
            params.put("pageModel", pageModel);
        }

        return employeeDao.selectByPage(params);
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @Override
    public void removeEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }

    /**
     * 添加用户
     * @param employee
     */
    @Override
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    /**
     * 根据员工id查询员工信息
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    /**
     * 更新员工信息
     * @param employee
     */
    @Override
    public void modifyEmployee(Employee employee) {
        employeeDao.update(employee);
    }


    /*===================================   岗位部分   ======================================*/


    @Autowired
    private JobDao jobDao;

    /**
     * 查询出所有的工作
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Job> findAllJob() {
        return jobDao.selectAll();
    }

    /*========================================  用户权限部分  =================================*/

    @Autowired
    private OperationDao operationDao;

    /**
     * 根据用户id查询出他权限范围内的操作
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Operation> selectOperationByUid(Integer id) {
        return operationDao.selectByUid(id);
    }

    /*===========================================   角色部分  =================================*/

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有的角色
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Role> selectAllRole() {
        return roleDao.selectAll();
    }

    /*==========================================      用户角色中间表      =============================*/

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void addUserRole(UserRole userRole) {
        userRoleDao.save(userRole);
    }
}

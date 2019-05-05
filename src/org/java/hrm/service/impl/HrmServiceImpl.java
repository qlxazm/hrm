package org.java.hrm.service.impl;

import org.java.hrm.dao.DeptDao;
import org.java.hrm.dao.EmployeeDao;
import org.java.hrm.dao.JobDao;
import org.java.hrm.dao.UserDao;
import org.java.hrm.domain.Dept;
import org.java.hrm.domain.Employee;
import org.java.hrm.domain.Job;
import org.java.hrm.domain.User;
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
     * @param id
     */
    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }

    /**
     * 修改用户
     * @param user
     */
    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @Override
    public void addUser(User user) {
        userDao.save(user);
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
    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }

    /**
     * 查询出所有部门
     * @return
     */
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


    /*===================================   岗位部分   ======================================*/


    @Autowired
    private JobDao jobDao;

    /**
     * 查询出所有的工作
     * @return
     */
    @Override
    public List<Job> findAllJob() {
        return jobDao.selectAll();
    }
}

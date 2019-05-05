package org.java.hrm.service;

import org.java.hrm.domain.Dept;
import org.java.hrm.domain.User;
import org.java.hrm.util.tag.PageModel;

import javax.jws.soap.SOAPBinding;
import java.util.List;

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

}
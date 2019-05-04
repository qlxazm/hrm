package org.java.hrm.service;

import org.java.hrm.domain.User;
import org.java.hrm.util.tag.PageModel;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface HrmService {
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
}

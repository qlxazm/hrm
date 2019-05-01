package org.java.hrm.service;

import org.java.hrm.domain.User;

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
}

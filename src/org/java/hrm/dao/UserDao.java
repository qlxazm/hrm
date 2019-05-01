package org.java.hrm.dao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.java.hrm.domain.User;

import static org.java.hrm.util.common.HrmConstants.USERTABLE;


public interface UserDao {

    /**
     * 根据用户名和密码查询用户
     * @param loginname
     * @param password
     * @return
     */
    @Select("SELECT * FROM " + USERTABLE + " WHERE loginname = #{loginname} AND password = #{password}")
    User selectByLoginnameAndPassword(@Param("loginname") String loginname, @Param("password") String password);

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM " + USERTABLE + " WHERE id = #{id}")
    User selectById(@Param("id") Integer id);
}

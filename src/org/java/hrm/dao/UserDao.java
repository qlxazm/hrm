package org.java.hrm.dao;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.ibatis.annotations.*;
import org.java.hrm.dao.provider.UserDynaSqlProvider;
import org.java.hrm.domain.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 分页查询用户信息
     * @param params
     * @return
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String, Object> params);

    /**
     * 查询用户总数
     * @param params
     * @return
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    int count(Map<String, Object> params);

    /**
     * 插入新的用户
     * @param user
     * @return
     */
    @InsertProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(User user);
}

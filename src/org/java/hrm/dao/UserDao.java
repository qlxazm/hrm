package org.java.hrm.dao;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.java.hrm.dao.provider.UserDynaSqlProvider;
import org.java.hrm.domain.User;

import java.util.Date;
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
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "loginname", property = "loginname"),
            @Result(column = "userstatus", property = "userstatus"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "id", property = "roles",
                    many = @Many(
                            select = "org.java.hrm.dao.RoleDao.selectWithUserId",
                            fetchType = FetchType.LAZY
                    ))
    })
    User selectById(@Param("id") Integer id);

    /**
     * 分页查询用户信息
     * @param params
     * @return
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "loginname", property = "loginname"),
            @Result(column = "userstatus", property = "userstatus"),
            @Result(column = "createdate", property = "createdate"),
            @Result(column = "id", property = "roles",
            many = @Many(
                    select = "org.java.hrm.dao.RoleDao.selectWithUserId",
                    fetchType = FetchType.LAZY
            ))
    })
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

    /**
     * 根据用户id删除用户
     * @param id
     */
    @Delete("DELETE FROM " + USERTABLE + " WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);

    /**
     * 更新用户
     * @param user
     */
    @UpdateProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    /**
     * 更新用户的登录时间
     * @param username
     */
    @Update("UPDATE " + USERTABLE + " SET lastLoginTime = current_timestamp WHERE username = #{username}")
    void updateLoginTime(@Param("username") String username);
}

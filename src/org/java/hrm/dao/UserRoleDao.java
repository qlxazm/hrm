package org.java.hrm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.java.hrm.domain.UserRole;
import static org.java.hrm.util.common.HrmConstants.USERROLETABLE;

public interface UserRoleDao {

    /**
     * 插入一条记录
     * @param userRole
     */
    @Insert(" INSERT INTO " + USERROLETABLE + " VALUES(#{userid}, #{roleid})")
    void save(UserRole userRole);

    /**
     * 根据用户id删除记录
     * @param id
     */
    @Delete("DELETE FROM " + USERROLETABLE + " WHERE userid = #{id}")
    void removeByUserId(@Param("id") Integer id);
}

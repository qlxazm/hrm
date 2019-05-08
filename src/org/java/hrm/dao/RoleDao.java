package org.java.hrm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.java.hrm.dao.provider.RoleDynaSqlProvider;
import org.java.hrm.domain.Role;

import static org.java.hrm.util.common.HrmConstants.ROLEPERMISSIONTABLE;
import static org.java.hrm.util.common.HrmConstants.ROLETABLE;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    /**
     * 根据id删除角色
     * @param id
     */
    @Delete("DELETE FROM " + ROLETABLE + " WHERE id=#{id}")
    void deleteById(@Param("id") Integer id);

    /**
     * 根据角色id将中间表role_permission中的记录删除
     * @param id
     */
    @Delete("DELETE FROM " + ROLEPERMISSIONTABLE + " WHERE roleid=#{id}")
    void deleteRolePermissionByRoleId(@Param("id") Integer id);

    /**
     * 根据查询条件分页角色信息
     * @param params
     * @return
     */
    @SelectProvider(type = RoleDynaSqlProvider.class, method = "selectWithParam")
    List<Role> selectByPage(Map<String, Object> params);

    /**
     * 统计符合查询条件的角色总数
     * @param params
     * @return
     */
    @SelectProvider(type = RoleDynaSqlProvider.class, method = "count")
    int count(Map<String, Object> params);


    /**
     * 查询所有的角色
     * @return
     */
    @Select("SELECT * FROM " + ROLETABLE)
    List<Role> selectAll();

    /**
     * 根据用户id查询角色信息
     * @param id
     * @return
     */
    @SelectProvider(type = RoleDynaSqlProvider.class, method = "selectWithUserId")
    List<Role> selectWithUserId(@Param("id") Integer id);
}

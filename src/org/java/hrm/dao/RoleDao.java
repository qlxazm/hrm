package org.java.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
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

    /**
     * 插入新的角色
     * @param role
     */
    @Insert("INSERT INTO " + ROLETABLE + "(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addRole(Role role);


    /**
     * 新建角色时向role-permision中间表插入数据
     * @param roleid
     * @param permissionid
     */
    @Insert("INSERT INTO " + ROLEPERMISSIONTABLE + " VALUES(#{roleid}, #{permissionid})")
    void addRolePermission(@Param("roleid") Integer roleid, @Param("permissionid") Integer permissionid);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM " + ROLETABLE + " WHERE id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "permissionIds",
            many = @Many(
                    select = "org.java.hrm.dao.PermissionDao.seletcByRoleId",
                    fetchType = FetchType.LAZY
            ))
    })
    Role selectById(@Param("id") Integer id);

    /**
     * 更新角色信息
     * @param role
     */
    @Update("UPDATE " + ROLETABLE + " SET name=#{name} WHERE id = #{id} ")
    void update(Role role);
}

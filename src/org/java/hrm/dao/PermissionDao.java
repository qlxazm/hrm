package org.java.hrm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.java.hrm.domain.Permission;

import java.util.List;

import static org.java.hrm.util.common.HrmConstants.PERMISSIONTABLE;
import static org.java.hrm.util.common.HrmConstants.ROLEPERMISSIONTABLE;

public interface PermissionDao {

    /**
     * 查询所有的权限
     */
    @Select("SELECT * FROM " + PERMISSIONTABLE)
    List<Permission> selectAll();

    /**
     * 根据roleid查询permission的信息
     * @param id
     * @return
     */
    @Select("SELECT id FROM " + PERMISSIONTABLE + " WHERE id in( SELECT permissionid FROM " + ROLEPERMISSIONTABLE + " WHERE roleid=#{id})")
    List<String> seletcByRoleId(@Param("id") Integer id);
}

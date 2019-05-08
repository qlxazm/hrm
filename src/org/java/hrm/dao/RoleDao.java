package org.java.hrm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.java.hrm.dao.provider.RoleDynaSqlProvider;
import org.java.hrm.domain.Role;
import static org.java.hrm.util.common.HrmConstants.ROLETABLE;

import java.util.List;

public interface RoleDao {

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

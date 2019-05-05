package org.java.hrm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import static org.java.hrm.util.common.HrmConstants.EMPLOYEETABLE;

public interface EmployeeDao {

    /**
     * 查询某个部门下的员工总数
     * @param id
     * @return
     */
    @Select("SELECT count(*) FROM " + EMPLOYEETABLE + " WHERE dept_id = #{id}")
    public int countByDeptNum(@Param("id") Integer id);
}

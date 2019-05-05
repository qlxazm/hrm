package org.java.hrm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.java.hrm.dao.provider.EmployeeDynaSqlProvider;
import org.java.hrm.domain.Employee;

import java.util.List;
import java.util.Map;

import static org.java.hrm.util.common.HrmConstants.EMPLOYEETABLE;

public interface EmployeeDao {

    /**
     * 查询某个部门下的员工总数
     * @param id
     * @return
     */
    @Select("SELECT count(*) FROM " + EMPLOYEETABLE + " WHERE dept_id = #{id}")
    public int countByDeptNum(@Param("id") Integer id);

    /**
     * 分页查询员工
     * @param params
     * @return
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
    public List<Employee> selectByPage(Map<String, Object> params);

    /**
     * 查询员工总数
     * @param params
     * @return
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
    public int count(Map<String, Object> params);
}

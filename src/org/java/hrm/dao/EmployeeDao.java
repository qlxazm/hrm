package org.java.hrm.dao;

import org.apache.ibatis.annotations.*;
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


    /**
     * 根据id删除员工
     * @param id
     */
    @Delete("DELETE FROM " + EMPLOYEETABLE + " WHERE id=#{id}")
    public void deleteById(@Param("id") Integer id);

    /**
     * 插入员工
     * @param employee
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "addEmployee")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void save(Employee employee);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM " + EMPLOYEETABLE + " WHERE id=#{id}")
    Employee selectById(@Param("id") Integer id);

    /**
     * 更新员工信息
     * @param employee
     */
    @UpdateProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmployee")
    void update(Employee employee);
}

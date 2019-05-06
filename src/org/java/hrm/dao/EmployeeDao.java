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
    int countByDeptNum(@Param("id") Integer id);


    /**
     * 分页查询员工
     * @param params
     * @return
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "dept_id", property = "dept_id"),
            @Result(column = "job_id", property = "job_id"),
            @Result(column = "name", property = "name"),
            @Result(column = "card_id", property = "card_id"),
            @Result(column = "address", property = "address"),
            @Result(column = "post_code", property = "post_code"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "QQ_num", property = "QQ_num"),
            @Result(column = "email", property = "email"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "party", property = "party"),
            @Result(column = "birthday", property = "birthday", javaType = java.util.Date.class),
            @Result(column = "race", property = "race"),
            @Result(column = "education", property = "education"),
            @Result(column = "speciality", property = "speciality"),
            @Result(column = "hobby", property = "hobby"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "create_date", property = "create_date", javaType = java.util.Date.class),
            @Result(property = "dept", column = "dept_id",
                    one = @One(select = "org.java.hrm.dao.DeptDao.selectById")),
            @Result(property = "job", column = "job_id",
                    one = @One(select = "org.java.hrm.dao.JobDao.selectById"))
    })
    List<Employee> selectByPage(Map<String, Object> params);

    /**
     * 查询员工总数
     * @param params
     * @return
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
    int count(Map<String, Object> params);


    /**
     * 根据id删除员工
     * @param id
     */
    @Delete("DELETE FROM " + EMPLOYEETABLE + " WHERE id=#{id}")
    void deleteById(@Param("id") Integer id);

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
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "dept_id", property = "dept_id"),
            @Result(column = "job_id", property = "job_id"),
            @Result(column = "name", property = "name"),
            @Result(column = "card_id", property = "card_id"),
            @Result(column = "address", property = "address"),
            @Result(column = "post_code", property = "post_code"),
            @Result(column = "tel", property = "tel"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "QQ_num", property = "QQ_num"),
            @Result(column = "email", property = "email"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "party", property = "party"),
            @Result(column = "birthday", property = "birthday", javaType = java.util.Date.class),
            @Result(column = "race", property = "race"),
            @Result(column = "education", property = "education"),
            @Result(column = "speciality", property = "speciality"),
            @Result(column = "hobby", property = "hobby"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "create_date", property = "create_date", javaType = java.util.Date.class),
            @Result(property = "dept", column = "dept_id",
            one = @One(select = "org.java.hrm.dao.DeptDao.selectById")),
            @Result(property = "job", column = "job_id",
            one = @One(select = "org.java.hrm.dao.JobDao.selectById"))
    })
    Employee selectById(@Param("id") Integer id);

    /**
     * 更新员工信息
     * @param employee
     */
    @UpdateProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmployee")
    void update(Employee employee);
}

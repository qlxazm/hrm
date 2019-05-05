package org.java.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.java.hrm.dao.provider.DeptDynaSqlProvider;
import org.java.hrm.domain.Dept;

import java.util.List;
import java.util.Map;
import static org.java.hrm.util.common.HrmConstants.DEPTTABLE;

public interface DeptDao {

    /**
     * 分页查询部门信息
     * @param params
     * @return
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "id", property = "employeeNum",
            one=@One(
                    select = "org.java.hrm.dao.EmployeeDao.countByDeptNum"
            ))
    })
    List<Dept> selectByPage(Map<String, Object> params);

    /**
     * 计算部门总数
     * @param params
     * @return
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
    int count(Map<String, Object> params);

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("DELETE FROM " + DEPTTABLE + " WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}
